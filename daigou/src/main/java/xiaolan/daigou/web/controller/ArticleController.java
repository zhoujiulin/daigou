package xiaolan.daigou.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xiaolan.daigou.domain.dto.ArticleInClientDTO;
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
}
