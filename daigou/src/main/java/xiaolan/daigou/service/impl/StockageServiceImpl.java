package xiaolan.daigou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.dao.StockageDao;
import xiaolan.daigou.dao.UtilisateurDao;
import xiaolan.daigou.domain.entity.ArticleStockage;
import xiaolan.daigou.domain.entity.Utilisateur;
import xiaolan.daigou.service.StockageService;

@Service
public class StockageServiceImpl implements StockageService {

	@Autowired
	private StockageDao stockageDao;
	
	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Override
	public List<ArticleStockage> getAllStockage(long userId){
		return this.stockageDao.getAll(userId);
	}
	
	@Override
	public ArticleStockage createArticleStockage(ArticleStockage articleStockage, long userId){
		Utilisateur utilisateur = utilisateurDao.findById(userId);
		articleStockage.setUtilisateur(utilisateur);
		
		ArticleStockage as = this.stockageDao.findByNameArticleStockage(articleStockage.getNameArticleStockage(), userId);
		
		if(as != null) {
			as.setCountStockageChine(as.getCountStockageChine() + articleStockage.getCountStockageChine());
			as.setCountStockageEnRoute(as.getCountStockageEnRoute() + articleStockage.getCountStockageEnRoute());
			as.setCountStockageFrance(as.getCountStockageFrance() + articleStockage.getCountStockageFrance());

			return this.stockageDao.save(as);
		}else {
			
			return this.stockageDao.save(articleStockage);
		}
	}

	@Override
	public ArticleStockage saveArticleStokage(ArticleStockage articleStockage) {
		return this.stockageDao.save(articleStockage);
	}

	@Override
	public ArticleStockage findById(Long id) {
		return this.stockageDao.findById(id);
	}
}
