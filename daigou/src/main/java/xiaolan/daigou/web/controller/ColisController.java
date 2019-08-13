package xiaolan.daigou.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xiaolan.daigou.common.enums.EnumStatusArticle;
import xiaolan.daigou.common.enums.EnumStatusColis;
import xiaolan.daigou.common.enums.EnumTypeArticle;
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
	
	@PostMapping(value="/create")
	@ResponseBody
	public Colis createColis(@RequestBody Colis colis, Authentication authentication) {
		
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		return this.colisService.createColis(colis, jwtUser.getId());
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
		
		Colis colis = this.colisService.findById(Long.valueOf(idColis));
		
		Article article = new Article();
		article.setNameArticle(articleStockage.getNameArticleStockage());
		article.setTypeArticle(EnumTypeArticle.ARTICLE_STOCKAGE);
		article.setStatusArticle(EnumStatusArticle.STOCKAGE);
		article.setDateCreation(new Date());
		article.setCountArticleFromStockageFrance(countArticleStockage);
		article.setColis(colis);
		
		return articleService.save(article);
	}
	
	@PostMapping(value="/deletearticlefromcolis")
	@ResponseBody
	public void deleteArticleFromColis(@RequestBody Article article, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		articleService.deleteById(article.getIdArticle());
	}
}
