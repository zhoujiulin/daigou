package xiaolan.daigou.web.controller;

import java.util.List;

import org.dozer.Mapper;
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

import xiaolan.daigou.common.utils.DozerUtils;
import xiaolan.daigou.model.dto.ArticleStockageDTO;
import xiaolan.daigou.model.entity.ArticleStockage;
import xiaolan.daigou.service.StockageService;
import xiaolan.daigou.web.security.jwt.JwtUser;


@RestController
@RequestMapping(value="/stockage")
public class StockageController {

	@Autowired
	private StockageService stockageService;
	
    @Autowired
    protected Mapper dozerMapper;
	
	@GetMapping(value="/getstockages")
	public List<ArticleStockageDTO> getAllStokage(@RequestParam(value = "key") String key, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		List<ArticleStockage> stockageList = this.stockageService.getArticleStockages(key, jwtUser.getId());
		List<ArticleStockageDTO> result = DozerUtils.mapList(dozerMapper, stockageList, ArticleStockageDTO.class);
		return result;
	}
	
	@GetMapping(value="/allstockageselectable")
	public List<ArticleStockageDTO> getAllStockageSelectable(Authentication authentication) {
		
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		List<ArticleStockage> stockageList = this.stockageService.getAllStockageSelectable(jwtUser.getId());
		List<ArticleStockageDTO> result = DozerUtils.mapList(dozerMapper, stockageList, ArticleStockageDTO.class);
		return result;
	}
	
	
	@PostMapping(value="/create")
	@ResponseBody
	public ArticleStockage createArticleStokage(@RequestBody ArticleStockageDTO articleStockage, Authentication authentication) {
		
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		ArticleStockage result = this.stockageService.createArticleStockage(articleStockage, jwtUser.getId());
		return result;
	}
	
	@PostMapping(value="/save")
	@ResponseBody
	public ArticleStockage saveArticleStokage(@RequestBody ArticleStockageDTO articleStockage, Authentication authentication) {
		
		ArticleStockage result = this.stockageService.save(dozerMapper.map(articleStockage, ArticleStockage.class));
		return result;
	}
	
	@PostMapping(value="/savereinitstockage")
	@ResponseBody
	public List<ArticleStockageDTO> saveReinitStockage(@RequestBody List<ArticleStockageDTO> stockages, Authentication authentication) {
		
		List<ArticleStockageDTO> result = this.stockageService.saveReinitStockage(stockages);
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
