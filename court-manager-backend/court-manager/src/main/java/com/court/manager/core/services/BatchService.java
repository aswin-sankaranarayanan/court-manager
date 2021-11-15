package com.court.manager.core.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.court.manager.common.RecordNotFoundException;
import com.court.manager.core.dto.BatchDTO;
import com.court.manager.core.dto.BranchDTO;
import com.court.manager.core.dto.PagedResponseDTO;
import com.court.manager.core.entity.BatchEntity;
import com.court.manager.core.repository.BatchRepository;

@Service
public class BatchService extends BaseService<BatchEntity, BatchDTO, BatchRepository>{
	
	private BranchService branchService;
	private BatchRepository repository;
	
	public BatchService(BatchRepository repository,BranchService branchService) {
		this.branchService = branchService;
		this.repository = repository;
		init(BatchEntity.class, BatchDTO.class, repository);
	}



	public BatchDTO saveBatch(BatchDTO batchDTO) throws RecordNotFoundException {
		BranchDTO branchDTO = branchService.findBranch(batchDTO.getBranch());
		batchDTO.setBranch(branchDTO);
		return save(batchDTO);
	}



	public BatchDTO updateBatch(BatchDTO batchDTO) throws RecordNotFoundException {
		BatchDTO sourceBatchDTO = findOne(batchDTO);
		batchDTO.setBranch(sourceBatchDTO.getBranch());
		return update(batchDTO);
	}
	
	public PagedResponseDTO<BatchDTO> findAllBatchesByBranchId(int pageNum, int size,Long branchId){
		Pageable pageable = PageRequest.of(pageNum == 0?0:pageNum-1, size);
		Page<BatchEntity> pages =  repository.findByBranchId(branchId,pageable);
		return constructPagedResponseDTO(pages);
	}
	
	public void deleteBatch(Long batchId) throws RecordNotFoundException {
		delete(batchId);
	}

}
