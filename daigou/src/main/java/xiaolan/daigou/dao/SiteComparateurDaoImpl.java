package xiaolan.daigou.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xiaolan.daigou.entity.SiteComparateur;

@Repository
@Transactional
public class SiteComparateurDaoImpl extends BaseDaoImpl<SiteComparateur> implements SiteComparateurDao{

	public SiteComparateurDaoImpl() {
		super(SiteComparateur.class);
	}
	
}
