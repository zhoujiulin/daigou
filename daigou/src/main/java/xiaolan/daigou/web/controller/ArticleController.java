package xiaolan.daigou.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xiaolan.daigou.domain.dto.ArticleDTO;
import xiaolan.daigou.domain.dto.ArticleInClientDTO;
import xiaolan.daigou.domain.dto.ColisDTO;
import xiaolan.daigou.domain.entity.ArticleStockage;
import xiaolan.daigou.service.ArticleService;
import xiaolan.daigou.web.security.jwt.JwtUser;


@RestController
@RequestMapping(value="/articles")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@PostMapping(value="/terminerarticle")
	@ResponseBody
	public void terminerArticle(@RequestBody ArticleInClientDTO articleInClient, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		this.articleService.terminerArticle(articleInClient, jwtUser.getId());
		
		System.out.println(articleInClient);
	}
	
	@PostMapping(value="/computearticlestockagefromcolisarriver")
	@ResponseBody
	public Map<Long, List<ArticleDTO>> computearticlestockagefromcolisarriver(@RequestBody ColisDTO colisDTO, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		Map<Long, List<ArticleDTO>> result = this.articleService.computeArticleStockageFromColiAarriver(colisDTO, jwtUser.getId());
		
		return result;
	}
}
