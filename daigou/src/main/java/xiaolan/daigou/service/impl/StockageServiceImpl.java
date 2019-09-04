package xiaolan.daigou.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.dao.BaseDao;
import xiaolan.daigou.dao.StockageDao;
import xiaolan.daigou.dao.UtilisateurDao;
import xiaolan.daigou.model.dto.ArticleDTO;
import xiaolan.daigou.model.dto.ArticleStockageDTO;
import xiaolan.daigou.model.entity.ArticleStockage;
import xiaolan.daigou.model.entity.Utilisateur;
import xiaolan.daigou.model.exception.DaigouException;
import xiaolan.daigou.service.StockageService;

@Service
public class StockageServiceImpl extends AbstractServiceImpl<ArticleStockage> implements StockageService {

	@Autowired
	private StockageDao stockageDao;
	
	@Autowired
	private UtilisateurDao utilisateurDao;
	
	@Autowired
    protected Mapper dozerMapper;
	
    public StockageServiceImpl() {
        super(ArticleStockage.class);
    }
	
	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public List<ArticleStockage> getArticleStockages(String key, long userId){
		return this.stockageDao.getArticleStockages(key, userId);
	}
	
	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public List<ArticleStockage> getAllStockageSelectable(long userId){
		return this.stockageDao.getAll(userId);
	}
	
	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public ArticleStockage createArticleStockage(ArticleStockageDTO articleStockageDTO, long userId){
		ArticleStockage articleStockage = dozerMapper.map(articleStockageDTO, ArticleStockage.class);
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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public List<ArticleStockageDTO> saveReinitStockage(List<ArticleStockageDTO> articleStockages) {
		List<ArticleStockageDTO> result = new ArrayList<ArticleStockageDTO>();
		for(ArticleStockageDTO articleStockageDTO : articleStockages) {
			ArticleStockage articleStockage = dozerMapper.map(articleStockageDTO, ArticleStockage.class);
			articleStockage = this.stockageDao.save(articleStockage);
			result.add(dozerMapper.map(articleStockage, ArticleStockageDTO.class));
		}

		return result;
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public void updateArticleStockageForDeleteArticle(ArticleDTO articleDTO, Long idUser) {
		ArticleStockage articleStockage = this.findByNameArticleStockage(articleDTO.getNameArticle(), idUser);
		articleStockage.setCountStockageFranceAvailable(articleStockage.getCountStockageFranceAvailable() + articleDTO.getCount());
		articleStockage.setCountStockageFranceColis(articleStockage.getCountStockageFranceColis() - articleDTO.getCount());
		this.save(articleStockage);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = DaigouException.class)
	public ArticleStockage findByNameArticleStockage(String nameArticleStockage, long idUser) {
		return this.stockageDao.findByNameArticleStockage(nameArticleStockage, idUser);
	}
	
	@Override
	public BaseDao<ArticleStockage> getDao() {
		return this.stockageDao;
	}
}
