package com.court.manager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.court.manager.common.RecordNotFoundException;
import com.court.manager.core.dto.ClientDTO;
import com.court.manager.core.dto.PagedResponseDTO;
import com.court.manager.core.services.ClientService;

@RestController
@RequestMapping("/client")
public class ClientAPI {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<PagedResponseDTO<ClientDTO>> getAllClients(@RequestParam(name = "page", defaultValue = "0") int pageNum,
			@RequestParam(name = "size", defaultValue = "5") int size){
		return ResponseEntity.ok(clientService.getAllClients(pageNum,size));
	}
	

	@PostMapping
	public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO client) {
		return ResponseEntity.ok(clientService.saveClient(client));
	}
	
	
	@PutMapping
	public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO client) throws RecordNotFoundException {
		return ResponseEntity.ok(clientService.updateClient(client));
	}
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) throws RecordNotFoundException {
		clientService.deleteClient(id);
		return ResponseEntity.ok().build();
	}
	
}
