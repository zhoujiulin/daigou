package xiaolan.daigou.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import xiaolan.daigou.domain.entity.Client;
import xiaolan.daigou.service.ClientService;


@RestController
@RequestMapping(value="/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	@PostMapping(value="/create")
	@ResponseBody
	public Client createClient(@RequestBody Client client){
		
		return this.clientService.createClient(client);
	}
	
	@GetMapping(value="/client/{id}")
	public Client getClientById(@PathVariable("id") Long id) {
		
		return this.clientService.findClientById(id);
	}
}