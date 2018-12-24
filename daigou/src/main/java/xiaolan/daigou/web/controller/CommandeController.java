package xiaolan.daigou.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xiaolan.daigou.domain.entity.Client;
import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.service.ClientService;
import xiaolan.daigou.service.CommandeService;


@RestController
@RequestMapping(value="/api/commandes")
public class CommandeController {

	@Autowired
	private CommandeService commandeService;
	
	@PostMapping(value="/create")
	@ResponseBody
	public Commande createCommande(@RequestBody Commande commande){
		
		return this.commandeService.createCommande(commande);
	}
	
	@GetMapping(value="/commande/{id}")
	public Commande getCommandeById(@PathVariable("id") Long id) {
		
		return this.commandeService.findCommandeById(id);
	}
	
	@GetMapping(value="/all")
	public List<Commande> getAllCommandes() {
		
		return this.commandeService.findAll();
	}
	
	@PostMapping(value="/status")
	public List<Commande> getCommandesByStatus(@RequestBody List<Integer> statusList) {
		
		return this.commandeService.getCommandesByStatus(statusList);
	}
}
