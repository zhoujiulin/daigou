package xiaolan.daigou.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xiaolan.daigou.dao.SiteComparateurDao;
import xiaolan.daigou.entity.SiteComparateur;

@Service
public class SiteComparateurServiceImpl implements SiteComparateurService{

	@Autowired
	private SiteComparateurDao siteComparateurDao;
	
	@Override
	public SiteComparateur addSite(SiteComparateur site) {
		return this.siteComparateurDao.save(site);
	}

	@Override
	public SiteComparateur updateSite(SiteComparateur site) {
		return this.siteComparateurDao.save(site);
	}

	@Override
	public void deleteSite(SiteComparateur site) {
		this.siteComparateurDao.delete(site);
	}
	
	@Override
	public void deleteSiteById(Long id) {
		this.siteComparateurDao.deleteById(id);
	}

	@Override
	public List<SiteComparateur> findAllSite() {
		List<SiteComparateur> list = this.siteComparateurDao.getAll();

		return list;
	}

	@Override
	public List<String> search() {

		return null;
	}
}
