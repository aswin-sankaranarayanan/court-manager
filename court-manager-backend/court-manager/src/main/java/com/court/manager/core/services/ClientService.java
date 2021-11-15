package com.court.manager.core.services;

import org.springframework.stereotype.Service;

import com.court.manager.common.RecordNotFoundException;
import com.court.manager.core.dto.ClientDTO;
import com.court.manager.core.dto.PagedResponseDTO;
import com.court.manager.core.entity.ClientEntity;
import com.court.manager.core.repository.ClientRepository;

@Service
public class ClientService extends BaseService<ClientEntity, ClientDTO,ClientRepository>{
	
	private ClientRepository clientRepository;
	
	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
		init(ClientEntity.class,ClientDTO.class,this.clientRepository);
	}
	
	
	public ClientDTO saveClient(ClientDTO clientDTO) {
		clientDTO.getBranches().forEach(branch -> branch.setClient(clientDTO));
		return save(clientDTO);
	}

	public PagedResponseDTO<ClientDTO> getAllClients(int pageNum, int size) {
		return findAll(pageNum, size);
	}
	
	public ClientDTO updateClient(ClientDTO clientDTO) throws RecordNotFoundException {
		clientDTO.getBranches().forEach(branch -> branch.setClient(clientDTO));
		return update(clientDTO);
	}
	
	public void deleteClient(Long id) throws RecordNotFoundException {
		delete(id);
	}

}
