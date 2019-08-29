package xiaolan.daigou.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.common.utils.DozerUtils;
import xiaolan.daigou.dao.ArticleDao;
import xiaolan.daigou.dao.BaseDao;
import xiaolan.daigou.model.dto.ArticleDTO;
import xiaolan.daigou.model.dto.ArticleInClientDTO;
import xiaolan.daigou.model.dto.ColisDTO;
import xiaolan.daigou.model.entity.Article;
import xiaolan.daigou.model.entity.ArticleStockage;
import xiaolan.daigou.model.enums.EnumStatusArticle;
import xiaolan.daigou.model.enums.EnumTypeArticle;
import xiaolan.daigou.model.exception.DaigouException;
import xiaolan.daigou.service.ArticleService;
import xiaolan.daigou.service.StockageService;

@Service
public class ArticleServiceImpl extends AbstractServiceImpl<Article> implements ArticleService{

    public ArticleServiceImpl() {
        super(Article.class);
    }
	
	@Autowired
	private ArticleDao articleDao;
	
    @Autowired
    protected Mapper dozerMapper;
	
	@Override
	public BaseDao<Article> getDao() {
		return this.articleDao;
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public void envoyerArticleAuClient(ArticleInClientDTO articleInClientDTO, Long idUser) {
		Long idArticle = articleInClientDTO.getIdArticle();
		Article article = this.findById(idArticle);
		//article.setStatusArticle(EnumStatusArticle.ARTICLE_ENVOYE_AU_CLIENT);
		if(articleInClientDTO.getCountArticleAchete() > 0) {
			article.setCountArticleAcheteDistribue(article.getCountArticleAcheteDistribue() + articleInClientDTO.getCountArticleAchete());
			this.articleDao.save(article);
		} else if(articleInClientDTO.getCountArticleFromStockageChine() > 0) {
			article.setCountArticleFromStockageChineDistribue(article.getCountArticleFromStockageChineDistribue() + articleInClientDTO.getCountArticleFromStockageChine());
			this.articleDao.save(article);
		}
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public Map<Long, List<ArticleDTO>> computeArticleStockageFromColiAarriver(ColisDTO colisDTO, Long idUser) {
		Map<Long, List<ArticleDTO>> result = new HashMap<Long, List<ArticleDTO>>();

		List<ArticleDTO> articlesFromStockageEnRouteDTO = DozerUtils.mapList(dozerMapper, this.getArticleEnRoute(idUser), ArticleDTO.class);
		
		for(ArticleDTO articleDTO : colisDTO.getArticles()){
			if(articleDTO.getTypeArticle() == EnumTypeArticle.ARTICLE_STOCKAGE) {
				for(ArticleDTO articleFromStockageEnRoute : articlesFromStockageEnRouteDTO) {
					if(articleDTO.getNameArticle().equals(articleFromStockageEnRoute.getNameArticle())) {
						Long id = articleDTO.getIdArticle();
						if(result.get(id) != null) {
							List<ArticleDTO> articles = result.get(id);
							articles.add(articleFromStockageEnRoute);
							result.put(id, articles);
						}else {
							List<ArticleDTO> articles = new ArrayList<ArticleDTO>();
							articles.add(articleFromStockageEnRoute);
							result.put(id, articles);
						}
					}
				}
			}
		}
		
		return result;
	}

	@Override
	public List<Article> getArticleEnRoute(Long idUser) {
		return this.articleDao.getArticleEnRoute(idUser);
	}
}
