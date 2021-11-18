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
import org.springframework.web.bind.annotation.RestController;

import com.court.manager.common.RecordNotFoundException;
import com.court.manager.core.dto.BranchDTO;
import com.court.manager.core.services.BranchService;

@RestController
@RequestMapping("/api/branches")
public class BranchAPI {
	
	@Autowired
	private BranchService branchService;

	@PostMapping
	public ResponseEntity<BranchDTO> saveBranch(@RequestBody BranchDTO branchDTO){
		return ResponseEntity.ok(branchService.saveBranch(branchDTO));
	}
	
	@GetMapping("/{branchId}")
	public ResponseEntity<BranchDTO> findBranch(@PathVariable("branchId") Long branchId) throws RecordNotFoundException{
		return ResponseEntity.ok(branchService.findBranch(branchId));
	}
	
	@DeleteMapping("/{branchId}")
	public  ResponseEntity<Void> deleteBranch(@PathVariable("branchId") Long branchId) throws RecordNotFoundException{
		branchService.deleteBranch(branchId);
		return ResponseEntity.ok().build();
	}
}
