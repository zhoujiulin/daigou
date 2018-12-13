package xiaolan.daigou.service;

import java.util.List;

import xiaolan.daigou.entity.SiteComparateur;

public interface SiteComparateurService {

	SiteComparateur addSite(SiteComparateur site);
	
	SiteComparateur updateSite(SiteComparateur site);
	
	void deleteSite(SiteComparateur site);
	
	void deleteSiteById(Long id);
	
	List<SiteComparateur> findAllSite();
	
	List<String> search();
}
