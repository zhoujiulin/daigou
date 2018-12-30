package xiaolan.daigou.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.service.CommandeService;


@RestController
@RequestMapping(value="/commandes")
public class CommandeController {

	@Autowired
	private CommandeService commandeService;
	
	@PostMapping(value="/creationcommande")
	@ResponseBody
	public Commande creationCommande(@RequestBody Commande commande){
		
		return this.commandeService.createNewCommande(commande);
	}
	
	@GetMapping(value="/commande/{id}")
	public Commande getCommandeById(@PathVariable("id") Long id) {
		
		return this.commandeService.findCommandeById(id);
	}
	
	@GetMapping(value="/all")
	public List<Commande> getAllCommandes() {
		
		return this.commandeService.findAll();
	}
	
	@GetMapping(value="/status")
	public List<Commande> getCommandesByStatus(@RequestParam(value = "status") List<String> statusList) {
		
		return this.commandeService.getCommandesByStatus(statusList);
	}
	
	@GetMapping(value="/commandestatus")
	public Map<Integer, String> getCommandeStatus() {
		
		return this.commandeService.getCommandeStatus();
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
	
		this.commandeService.deleteCommandeById(Long.valueOf(idCommande));
	}
}
