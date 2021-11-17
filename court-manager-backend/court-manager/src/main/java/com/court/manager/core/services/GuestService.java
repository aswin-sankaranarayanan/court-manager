package com.court.manager.core.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.court.manager.common.RecordNotFoundException;
import com.court.manager.core.dto.GuestDTO;
import com.court.manager.core.dto.PagedResponseDTO;
import com.court.manager.core.entity.GuestEntity;
import com.court.manager.core.repository.GuestReposiotry;

@Service
public class GuestService extends BaseService<GuestEntity, GuestDTO, GuestReposiotry> {

	private GuestReposiotry guestRepository;

	public GuestService(GuestReposiotry repository) {
		this.guestRepository = repository;
		init(GuestEntity.class, GuestDTO.class, repository);
	}

	public GuestDTO saveGuest(GuestDTO guestDTO) {
		return save(guestDTO);
	}

	public PagedResponseDTO<GuestDTO> getAllGuests(Long branchId, int pageNum, int size) {
		Pageable pageable = PageRequest.of(pageNum, size,Sort.by(Direction.DESC, "startTime"));
		Page<GuestEntity> pages = guestRepository.findAllByBranch_Id(branchId,pageable);
		return constructPagedResponseDTO(pages);
	}

	public GuestDTO updateGuest(GuestDTO guestDTO) throws RecordNotFoundException {
		return update(guestDTO);
	}

	public void deleteGuest(Long id) throws RecordNotFoundException {
		delete(id);
	}

	public PagedResponseDTO<GuestDTO> findGuest(Long branchId, String query, int pageNum, int size) {
		Pageable pageable = PageRequest.of(pageNum, size,Sort.by(Direction.ASC, "name"));
		Page<GuestEntity> pages = guestRepository.findGuestInBranch(branchId,query,pageable);
		return constructPagedResponseDTO(pages);
	}

}
