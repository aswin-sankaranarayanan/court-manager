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
import com.court.manager.core.dto.GuestDTO;
import com.court.manager.core.dto.PagedResponseDTO;
import com.court.manager.core.services.GuestService;

@RestController
@RequestMapping("/guest")
public class GuestApi {
	
	@Autowired
	private GuestService guestService;
	
	@GetMapping
	public ResponseEntity<PagedResponseDTO<GuestDTO>> getAllGuests(@RequestParam(name = "page", defaultValue = "0") int pageNum,
			@RequestParam(name = "size", defaultValue = "5") int size){
		return ResponseEntity.ok(guestService.getAllGuests(pageNum,size));
	}
	

	@PostMapping
	public ResponseEntity<GuestDTO> saveGuest(@RequestBody GuestDTO client) {
		return ResponseEntity.ok(guestService.saveGuest(client));
	}
	
	
	@PutMapping
	public ResponseEntity<GuestDTO> updateGuest(@RequestBody GuestDTO client) throws RecordNotFoundException {
		return ResponseEntity.ok(guestService.update(client));
	}
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<Void> deleteGuest(@PathVariable("id") Long id) throws RecordNotFoundException {
		guestService.delete(id);
		return ResponseEntity.ok().build();
	}
	
}
