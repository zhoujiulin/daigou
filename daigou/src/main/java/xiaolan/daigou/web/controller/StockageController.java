package xiaolan.daigou.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xiaolan.daigou.common.enums.EnumStatusArticle;
import xiaolan.daigou.domain.entity.ArticleStockage;
import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.service.CommandeService;
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
}
