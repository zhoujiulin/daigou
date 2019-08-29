package xiaolan.daigou.web.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import xiaolan.daigou.model.Response;
import xiaolan.daigou.model.entity.Commande;
import xiaolan.daigou.model.entity.Utilisateur;
import xiaolan.daigou.model.enums.EnumStatusCommande;
import xiaolan.daigou.model.enums.EnumTypeCommande;
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
		
		if(dbUser != null) {
			return new ResponseEntity<Response>(new Response("User is saved succesfully"), HttpStatus.OK);
		}
		return null;
	}
}
