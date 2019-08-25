package xiaolan.daigou.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.common.enums.EnumTypeArticle;
import xiaolan.daigou.common.utils.DozerUtils;
import xiaolan.daigou.dao.ArticleDao;
import xiaolan.daigou.dao.BaseDao;
import xiaolan.daigou.dao.CommandeDao;
import xiaolan.daigou.domain.dto.ArticleDTO;
import xiaolan.daigou.domain.dto.ArticleInClientDTO;
import xiaolan.daigou.domain.dto.ColisDTO;
import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.ArticleStockage;
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
	private StockageService stockageService;
	
    @Autowired
    protected Mapper dozerMapper;
	
	@Override
	public BaseDao<Article> getDao() {
		return this.articleDao;
	}

	@Override
	public void terminerArticle(ArticleInClientDTO articleInClientDTO, Long idUser) {
		Long idArticle = articleInClientDTO.getIdArticle();
		//Article article = this.findById(idArticle);
		if(articleInClientDTO.getCountArticleFromStockageChine() > 0) {
			ArticleStockage articleStockage = stockageService.findByNameArticleStockage(articleInClientDTO.getNameArticle(), idUser);
			articleStockage.setCountStockageChine(articleStockage.getCountStockageChine() - articleInClientDTO.getCountArticleFromStockageChine());
			
		}
	}

	@Override
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
