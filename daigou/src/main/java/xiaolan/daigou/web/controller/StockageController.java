package xiaolan.daigou.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xiaolan.daigou.domain.entity.ArticleStockage;
import xiaolan.daigou.service.StockageService;
import xiaolan.daigou.web.security.jwt.JwtUser;


@RestController
@RequestMapping(value="/stockage")
public class StockageController {

	@Autowired
	private StockageService stockageService;
	
	@GetMapping(value="/all")
	public List<ArticleStockage> getAllStokage(Authentication authentication) {
		
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		List<ArticleStockage> stockageList = this.stockageService.getAllStockage(jwtUser.getId());
		return stockageList;
	}
	
	@PostMapping(value="/create")
	@ResponseBody
	public ArticleStockage createArticleStokage(@RequestBody ArticleStockage articleStockage, Authentication authentication) {
		
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		ArticleStockage result = this.stockageService.createArticleStockage(articleStockage, jwtUser.getId());
		return result;
	}
	
	@PostMapping(value="/save")
	@ResponseBody
	public ArticleStockage saveArticleStokage(@RequestBody ArticleStockage articleStockage, Authentication authentication) {
		
		ArticleStockage result = this.stockageService.saveArticleStokage(articleStockage);
		return result;
	}
	
	@GetMapping(value="/getarticlestockage/{id}")
	public ArticleStockage getArticleStockage(@PathVariable("id") int id) {
		
		ArticleStockage result = this.stockageService.findById(Long.valueOf(id));
		return result;
	}
	
	@GetMapping(value="/getarticlestockagebyname/{name}")
	public ArticleStockage findByNameArticleStockage(@PathVariable("name") String nameArticleStockage, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		ArticleStockage result = this.stockageService.findByNameArticleStockage(nameArticleStockage, jwtUser.getId());
		return result;
	}
}
