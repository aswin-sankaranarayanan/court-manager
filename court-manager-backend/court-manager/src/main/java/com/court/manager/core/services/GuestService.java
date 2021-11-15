package com.court.manager.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.court.manager.common.RecordNotFoundException;
import com.court.manager.core.dto.GuestDTO;
import com.court.manager.core.dto.PagedResponseDTO;
import com.court.manager.core.entity.GuestEntity;
import com.court.manager.core.repository.GuestReposiotry;

@Service
public class GuestService extends BaseService<GuestEntity, GuestDTO,GuestReposiotry> {
	
	@Autowired
	private GuestReposiotry repository;
	
	public GuestService() {
		init(GuestEntity.class,GuestDTO.class,repository);
	}
	
	
	public GuestDTO saveGuest(GuestDTO guestDTO) {
		return save(guestDTO);
	}

	public PagedResponseDTO<GuestDTO> getAllGuests(int pageNum, int size) {
		return findAll(pageNum, size);
	}
	
	public GuestDTO updateGuest(GuestDTO guestDTO) throws RecordNotFoundException {
		return update(guestDTO);
	}
	
	public void deleteGuest(Long id) throws RecordNotFoundException {
		delete(id);
	}
}
