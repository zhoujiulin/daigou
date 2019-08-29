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

import xiaolan.daigou.model.dto.ArticleDTO;
import xiaolan.daigou.model.dto.ArticleInClientDTO;
import xiaolan.daigou.model.dto.ColisDTO;
import xiaolan.daigou.service.ArticleService;
import xiaolan.daigou.web.security.jwt.JwtUser;


@RestController
@RequestMapping(value="/articles")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	
	@PostMapping(value="/envoyerarticleauclient")
	@ResponseBody
	public void envoyerArticleAuClient(@RequestBody ArticleInClientDTO articleInClient, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		this.articleService.envoyerArticleAuClient(articleInClient, jwtUser.getId());
	}
	
	@PostMapping(value="/computearticlestockagefromcolisarriver")
	@ResponseBody
	public Map<Long, List<ArticleDTO>> computearticlestockagefromcolisarriver(@RequestBody ColisDTO colisDTO, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		Map<Long, List<ArticleDTO>> result = this.articleService.computeArticleStockageFromColiAarriver(colisDTO, jwtUser.getId());
		
		return result;
	}
}
