package xiaolan.daigou.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.dao.BaseDao;
import xiaolan.daigou.dao.StockageDao;
import xiaolan.daigou.dao.UtilisateurDao;
import xiaolan.daigou.domain.dto.ArticleDTO;
import xiaolan.daigou.domain.entity.ArticleStockage;
import xiaolan.daigou.domain.entity.Utilisateur;
import xiaolan.daigou.service.StockageService;

@Service
public class StockageServiceImpl extends AbstractServiceImpl<ArticleStockage> implements StockageService {

	@Autowired
	private StockageDao stockageDao;
	
	@Autowired
	private UtilisateurDao utilisateurDao;
	
    public StockageServiceImpl() {
        super(ArticleStockage.class);
    }
	
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
			as.setCountStockageFranceAvailable(as.getCountStockageFranceAvailable() + articleStockage.getCountStockageFranceAvailable());
			as.setCountStockageFranceReserve(as.getCountStockageFranceReserve() + articleStockage.getCountStockageFranceReserve());
			as.setCountStockageFranceColis(as.getCountStockageFranceColis() + articleStockage.getCountStockageFranceColis());
			
			as.setCountStockageChineAvailable(as.getCountStockageChineAvailable() + articleStockage.getCountStockageChineAvailable());
			as.setCountStockageEnRouteAvailable(as.getCountStockageEnRouteAvailable() + articleStockage.getCountStockageEnRouteAvailable());
			
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
	public BaseDao<ArticleStockage> getDao() {
		return this.stockageDao;
	}
	

	@Override
	public void updateArticleStockageForDeleteArticle(ArticleDTO articleDTO, Long idUser) {
		ArticleStockage articleStockage = this.findByNameArticleStockage(articleDTO.getNameArticle(), idUser);
		articleStockage.setCountStockageFranceAvailable(articleStockage.getCountStockageFranceAvailable() + articleDTO.getCount());
		articleStockage.setCountStockageFranceColis(articleStockage.getCountStockageFranceColis() - articleDTO.getCount());
		this.save(articleStockage);
	}

	@Override
	public ArticleStockage findByNameArticleStockage(String nameArticleStockage, long idUser) {
		return this.stockageDao.findByNameArticleStockage(nameArticleStockage, idUser);
	}
}
