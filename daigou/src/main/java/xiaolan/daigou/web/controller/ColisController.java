package xiaolan.daigou.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xiaolan.daigou.common.enums.EnumStatusColis;
import xiaolan.daigou.domain.entity.Article;
import xiaolan.daigou.domain.entity.ArticleStockage;
import xiaolan.daigou.domain.entity.Colis;
import xiaolan.daigou.service.ArticleService;
import xiaolan.daigou.service.ColisService;
import xiaolan.daigou.service.StockageService;
import xiaolan.daigou.web.security.jwt.JwtUser;


@RestController
@RequestMapping(value="/colis")
public class ColisController {

	@Autowired
	private ColisService colisService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private StockageService stockageService;
	
	@PostMapping(value="/createcolis")
	@ResponseBody
	public Colis createColis(@RequestBody Colis colis, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		return this.colisService.createColis(jwtUser.getId());
	}
	
	@GetMapping(value="/getcolisstatus")
	public EnumStatusColis[] getColisStatus() {
		
		return EnumStatusColis.values();
	}
	
	@GetMapping(value="/getcolisbystatus/{status}")
	public List<Colis> getColisByStatus(@PathVariable("status") int status, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		return this.colisService.getColisByStatus(status, jwtUser.getId());
	}
	
	@PostMapping(value="/envoyercolis")
	@ResponseBody
	public Colis envoyerColis(@RequestBody Colis colis, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		return this.colisService.envoyerColis(colis);
	}
	
	@PostMapping(value="/arrivercolis")
	@ResponseBody
	public Colis arriverColis(@RequestBody Colis colis, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		return this.colisService.arriverColis(colis);
	}

	@PostMapping(value="/putarticlestockageincolis")
	@ResponseBody
	public Article putArticleStockageInColis(@RequestBody ArticleStockage articleStockage, @RequestParam(value = "idColis") int idColis, 
			@RequestParam(value = "countArticleStockage") int countArticleStockage, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		return this.colisService.putArticleStockageInColis(articleStockage, idColis, countArticleStockage);
	}
	
	@PostMapping(value="/deletearticlefromcolis")
	@ResponseBody
	public void deleteArticleFromColis(@RequestBody Article article, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		ArticleStockage articleStockage = stockageService.findByNameArticleStockage(article.getNameArticle(), jwtUser.getId());
		articleStockage.setCountStockageFranceAvailable(articleStockage.getCountStockageFranceAvailable() + article.getCount());
		articleStockage.setCountStockageFranceColis(articleStockage.getCountStockageFranceColis() - article.getCount());
		stockageService.save(articleStockage);
		
		articleService.deleteById(article.getIdArticle());
	}
	
	@DeleteMapping(value="/deletecolis/{id}")
	public void deleteCommande(@PathVariable("id") Long id) {
	
		this.colisService.deleteById(id);
	}
}
