package xiaolan.daigou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xiaolan.daigou.entity.Client;
import xiaolan.daigou.entity.SiteComparateur;
import xiaolan.daigou.service.ClientService;
import xiaolan.daigou.service.SiteComparateurService;


@RestController
@RequestMapping(value="/api/comparateur")
public class SiteComparateurController {

	@Autowired
	private SiteComparateurService siteComparateurService;
	
	@PostMapping(value="/create")
	public SiteComparateur createSite(SiteComparateur site){
		
		return this.siteComparateurService.addSite(site);
	}
	
	@GetMapping(value="/site/list")
	public List<SiteComparateur> getSites() {
		return this.siteComparateurService.findAllSite();
	}
	
	@PostMapping(value="/remove/{id}")
	public void removeSiteById(@PathVariable("id") Long id){
		
		this.siteComparateurService.deleteSiteById(id);
	}
	
	@GetMapping(value="/search")
	public List<String> search() {
		return null;
	}
}
