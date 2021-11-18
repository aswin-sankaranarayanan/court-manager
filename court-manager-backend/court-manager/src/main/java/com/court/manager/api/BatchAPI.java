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
import com.court.manager.core.dto.BatchDTO;
import com.court.manager.core.dto.PagedResponseDTO;
import com.court.manager.core.services.BatchService;

@RestController
@RequestMapping("/api/batch")
public class BatchAPI {
	
	@Autowired
	private BatchService batchService;
	
	@GetMapping("/{branchId}")
	public ResponseEntity<PagedResponseDTO<BatchDTO>> findAllBatchesByBranchId(@PathVariable("branchId") Long branchId,
			@RequestParam(name = "page", defaultValue = "0") int pageNum,
			@RequestParam(name = "size", defaultValue = "5") int size
			){
				return ResponseEntity.ok(batchService.findAllBatchesByBranchId(pageNum, size, branchId));
		
	}
	
	@PostMapping
	public ResponseEntity<BatchDTO> saveBatch(@RequestBody BatchDTO batch){
		return ResponseEntity.ok(batchService.saveBatch(batch));
	}
	
	@PutMapping
	public ResponseEntity<BatchDTO> updateBatch(@RequestBody BatchDTO batch) throws RecordNotFoundException{
		return ResponseEntity.ok(batchService.updateBatch(batch));
	}
	
	@DeleteMapping("/{batchId}")
	public ResponseEntity<Void> deleteBatch(@PathVariable("batchId")Long batchId) throws RecordNotFoundException{
		batchService.deleteBatch(batchId);
		return ResponseEntity.ok().build();
	}
	

}
