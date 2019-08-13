package xiaolan.daigou.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
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
import xiaolan.daigou.common.enums.EnumStatusCommande;
import xiaolan.daigou.common.enums.EnumStatusCommandeGroup;
import xiaolan.daigou.common.enums.EnumTypeCommande;
import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.service.CommandeService;
import xiaolan.daigou.web.security.jwt.JwtUser;


@RestController
@RequestMapping(value="/commandes")
public class CommandeController {

	@Autowired
	private CommandeService commandeService;
	
	@PostMapping(value="/creationcommande")
	@ResponseBody
	public Commande creationCommande(@RequestBody Commande commande, Authentication authentication){
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		return this.commandeService.createNewCommande(commande, jwtUser.getId());
	}
	
	@GetMapping(value="/commande/{id}")
	public Commande getCommandeById(@PathVariable("id") Long id) {
		
		return this.commandeService.findById(id);
	}
	
	@GetMapping(value="/status")
	public List<Commande> getCommandesByStatus(@RequestParam(value = "status") List<String> statusList, Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		return this.commandeService.getCommandesByStatus(statusList, jwtUser.getId());
	}
	
	@GetMapping(value="/commandestatus")
	public Map<Integer, Map<String, String>> getCommandeStatus() {
		
		return this.commandeService.getCommandeStatus();
	}

	@GetMapping(value="/gettypecommande")
	public Map<Integer, String> getTypeCommande() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		
		for(EnumTypeCommande typeCommande : EnumTypeCommande.values()) {
			map.put(typeCommande.getIndex(), typeCommande.getLibelle());
		}
		return map;
	}
	
	@GetMapping(value="/commandestatusgroup")
	public Map<String, String> getCommandeStatusGroup() {
		
		return this.commandeService.getCommandeStatusGroup();
	}
	
	@PostMapping(value="/updatecommande")
	@ResponseBody
	public Commande updateCommande(@RequestBody Commande commande){
		
		return this.commandeService.saveCommande(commande);
	}
	
	@DeleteMapping(value="/deletecommande")
	public void deleteCommande(@RequestParam(value = "idCommande") String idCommande) {
	
		this.commandeService.deleteById(Long.valueOf(idCommande));
	}
}
