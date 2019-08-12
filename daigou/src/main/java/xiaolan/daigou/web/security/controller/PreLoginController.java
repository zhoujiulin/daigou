package xiaolan.daigou.web.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import xiaolan.daigou.common.enums.EnumStatusCommande;
import xiaolan.daigou.common.enums.EnumTypeCommande;
import xiaolan.daigou.domain.Response;
import xiaolan.daigou.domain.entity.Commande;
import xiaolan.daigou.domain.entity.Utilisateur;
import xiaolan.daigou.service.CommandeService;
import xiaolan.daigou.service.UserService;

@RestController
public class PreLoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CommandeService commandeService;
	
	@PostMapping(value="/registration")
	public ResponseEntity<Response> registration(@RequestBody Utilisateur user){
		Utilisateur dbUser = userService.inscription(user);
		
		Commande commandePourStockage = new Commande();
		commandePourStockage.setId(0L);
		commandePourStockage.setTypeCommande(EnumTypeCommande.COMMANDE_STOCKAGE);
		commandePourStockage.setStatus(EnumStatusCommande.NEW_COMMANDE);
		commandePourStockage.setUtilisateur(dbUser);
		this.commandeService.saveCommande(commandePourStockage);
		
		if(dbUser != null) {
			return new ResponseEntity<Response>(new Response("User is saved succesfully"), HttpStatus.OK);
		}
		return null;
	}
}
