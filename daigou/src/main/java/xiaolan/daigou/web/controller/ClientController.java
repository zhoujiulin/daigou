package xiaolan.daigou.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xiaolan.daigou.model.dto.ClientDTO;
import xiaolan.daigou.model.entity.Client;
import xiaolan.daigou.service.ClientService;
import xiaolan.daigou.web.security.jwt.JwtUser;


@RestController
@RequestMapping(value="/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@GetMapping(value="/client/{id}")
	public Client getClientById(@PathVariable("id") Long id, Authentication authentication) {
		
		return this.clientService.findById(id);
	}
	
	@GetMapping(value="/getclienthascommandeencours")
	public List<ClientDTO> getClientHasCommandeEnCours(Authentication authentication) {
		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		
		List<ClientDTO> clientDTOs = this.clientService.getClientHasCommandeEnCours(jwtUser.getId());
 		return clientDTOs;
	}
}
