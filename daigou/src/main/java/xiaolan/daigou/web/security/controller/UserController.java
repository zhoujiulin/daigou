package xiaolan.daigou.web.security.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import xiaolan.daigou.model.entity.Utilisateur;
import xiaolan.daigou.service.UserService;

@RestController
public class UserController {

	@Autowired 
	private UserService userService;
	
	@GetMapping(value="/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Utilisateur>> getAllUsers(){
		List<Utilisateur> users = userService.findAll();
		return new ResponseEntity<List<Utilisateur>>(users, HttpStatus.OK);
	}
	
	@GetMapping(value="/getuser")
	//@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Utilisateur> getUser(Principal principal){
		Utilisateur utilisateur = userService.findByUsername(principal.getName());
		return new ResponseEntity<Utilisateur>(utilisateur, HttpStatus.OK);
	}
}
