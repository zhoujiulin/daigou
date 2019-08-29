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

import xiaolan.daigou.model.dto.ArticleDTO;
import xiaolan.daigou.model.dto.ArticleMapEnRouteDTO;
import xiaolan.daigou.model.dto.ArticleMapEnRoutesDTO;
import xiaolan.daigou.model.dto.ArticleStockageDTO;
import xiaolan.daigou.model.dto.ColisDTO;
import xiaolan.daigou.model.entity.Article;
import xiaolan.daigou.model.entity.Colis;
import xiaolan.daigou.model.enums.EnumStatusColis;
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
	public Colis createColis(@RequestBody ColisDTO colisDTO, Authentication authentication) {
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
	public ColisDTO arriverColis(@RequestBody ArticleMapEnRoutesDTO articlesMapEnRoutesDTO, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		ColisDTO colisDTO = this.colisService.arriverColis(articlesMapEnRoutesDTO);
		return colisDTO;
	}

	@PostMapping(value="/putarticlestockageincolis")
	@ResponseBody
	public Article putArticleStockageInColis(@RequestBody ArticleStockageDTO articleStockageDTO, @RequestParam(value = "idColis") int idColis, 
			@RequestParam(value = "countArticleStockage") int countArticleStockage, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		return this.colisService.putArticleStockageInColis(articleStockageDTO, idColis, countArticleStockage);
	}
	
	@PostMapping(value="/deletearticlefromcolis")
	@ResponseBody
	public void deleteArticleFromColis(@RequestBody ArticleDTO articleDTO, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		this.stockageService.updateArticleStockageForDeleteArticle(articleDTO, jwtUser.getId());
		this.articleService.deleteById(articleDTO.getIdArticle());
	}
	
	@DeleteMapping(value="/deletecolis/{id}")
	public void deleteCommande(@PathVariable("id") Long id) {
	
		this.colisService.deleteById(id);
	}
}
