package xiaolan.daigou.web.controller;

import java.util.List;
import java.util.Map;

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
import xiaolan.daigou.domain.entity.Colis;
import xiaolan.daigou.service.ColisService;
import xiaolan.daigou.service.StockageService;
import xiaolan.daigou.web.security.jwt.JwtUser;


@RestController
@RequestMapping(value="/colis")
public class ColisController {

	@Autowired
	private ColisService colisService;
	
	@PostMapping(value="/create")
	@ResponseBody
	public Colis createColis(@RequestBody Colis colis, Authentication authentication) {
		
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		return this.colisService.createColis(colis, jwtUser.getId());
	}
	
	@GetMapping(value="/getcolisstatus")
	public Map<Integer, String> getColisStatus() {
		
		return this.colisService.getColisStatus();
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
}
