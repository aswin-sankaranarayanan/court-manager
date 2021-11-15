package com.court.manager.core.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import com.court.manager.common.RecordNotFoundException;
import com.court.manager.core.dto.BaseDTO;
import com.court.manager.core.dto.PagedResponseDTO;
import com.court.manager.core.entity.BaseEntity;

@Service @Transactional
public class BaseService<E extends BaseEntity,D extends BaseDTO,R extends PagingAndSortingRepository<E, Long>> {
	
	private Class<E> entityClass;
	private Class<D> dtoClass;
	private R repostiory;
	
	@Autowired
	private ModelMapper mapper;
	
	
	public void init(Class<E> entityClass,Class<D> dtoClass, R repository) {
		this.entityClass = entityClass;
		this.dtoClass = dtoClass;
		this.repostiory = repository;
	}
	
	protected D convertToDTO(E entity, Class<D> dtoClass) {
		return  mapper.map(entity, dtoClass);
	}
	
	protected E convertToEntity(D dto, Class<E> entityClass) {
		return  mapper.map(dto, entityClass);
	}
	
	public D save(D dto) {
		E convertToEntity = convertToEntity(dto, entityClass);
		E savedEntity = repostiory.save(convertToEntity);
		return convertToDTO(savedEntity,dtoClass);
	}
	
	public PagedResponseDTO<D> findAll(int pageNum, int size) {
		PagedResponseDTO<D> pagedResponseDTO = new PagedResponseDTO<>();
		Pageable pageable = PageRequest.of(pageNum, size);
		Page<E> pages =  repostiory.findAll(pageable);
		List<D> dtos = pages.getContent()
						.stream().map((E e) -> convertToDTO(e, dtoClass))
						.collect(Collectors.toList());
							

		pagedResponseDTO.setTotal(pages.getTotalElements());
		pagedResponseDTO.setCurrentSize(pages.getNumberOfElements());
		pagedResponseDTO.setResponse(dtos);
		return pagedResponseDTO;
	}
	
	public D findOne(D dto) throws RecordNotFoundException {
		return 	repostiory.findById(dto.getId())
				.map(entity -> convertToDTO(entity,dtoClass))
				.orElseThrow(() -> new RecordNotFoundException("Entity not found!!"));
	}
	
	public D update(D dto) throws RecordNotFoundException {
		if(!repostiory.existsById(dto.getId())) {
			throw new RecordNotFoundException("Entity not found for update!!");
		}
		return save(dto);
	}
	
	public void delete(Long id) throws RecordNotFoundException {
		if(!repostiory.existsById(id)) {
			throw new RecordNotFoundException("Entity not found for delete!!");
		}
		repostiory.deleteById(id);
	}
	
}
