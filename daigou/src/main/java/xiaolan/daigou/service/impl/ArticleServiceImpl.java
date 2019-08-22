package xiaolan.daigou.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.dao.ArticleDao;
import xiaolan.daigou.dao.BaseDao;
import xiaolan.daigou.domain.dto.ArticleInClientDTO;
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
	
	@Override
	public BaseDao<Article> getDao() {
		return this.articleDao;
	}

	@Override
	public void terminerArticle(ArticleInClientDTO articleInClientDTO, Long idUser) {
		Long idArticle = articleInClientDTO.getIdArticle();
		Article article = this.findById(idArticle);
		if(articleInClientDTO.getCountArticleFromStockageChine() > 0) {
			ArticleStockage articleStockage = stockageService.findByNameArticleStockage(articleInClientDTO.getNameArticle(), idUser);
			articleStockage.setCountStockageChine(articleStockage.getCountStockageChine() - articleInClientDTO.getCountArticleFromStockageChine());
			
		}
	}
}
