package com.court.manager.core.services;

import java.util.List;
import java.util.stream.Collectors;

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

@Service
public class BaseService<E extends BaseEntity,D extends BaseDTO,R extends PagingAndSortingRepository<E, Long>> {
	
	private Class<E> entityClass;
	private Class<D> dtoClass;
	private R repository;
	
	@Autowired
	private ModelMapper mapper;
	
	protected void init(Class<E> entityClass,Class<D> dtoClass, R repository) {
		this.entityClass = entityClass;
		this.dtoClass = dtoClass;
		this.repository = repository;
	}
	
	protected D convertToDTO(E entity, Class<D> dtoClass) {
		return  mapper.map(entity, dtoClass);
	}
	
	protected E convertToEntity(D dto, Class<E> entityClass) {
		return  mapper.map(dto, entityClass);
	}
	
	protected D save(D dto) {
		E convertToEntity = convertToEntity(dto, entityClass);
		E savedEntity = repository.save(convertToEntity);
		return convertToDTO(savedEntity,dtoClass);
	}
	
	protected PagedResponseDTO<D> findAll(int pageNum, int size) {
		Pageable pageable = PageRequest.of(pageNum == 0?0:pageNum-1, size);
		Page<E> pages =  repository.findAll(pageable);
		return constructPagedResponseDTO(pages);
	}

	protected PagedResponseDTO<D> constructPagedResponseDTO(Page<E> pages) {
		PagedResponseDTO<D> pagedResponseDTO = new PagedResponseDTO<>();

		List<D> dtos = pages.getContent()
						.stream().map((E e) -> convertToDTO(e, dtoClass))
						.collect(Collectors.toList());
							

		pagedResponseDTO.setTotal(pages.getTotalElements());
		pagedResponseDTO.setCurrentSize(pages.getNumberOfElements());
		pagedResponseDTO.setResponse(dtos);
		return pagedResponseDTO;
	}
	
	protected D findOne(D dto) throws RecordNotFoundException {
		return 	repository.findById(dto.getId())
				.map(entity -> convertToDTO(entity,dtoClass))
				.orElseThrow(() -> new RecordNotFoundException("Entity not found!!"));
	}
	
	protected D findById(Long id) throws RecordNotFoundException {
		return 	repository.findById(id)
				.map(entity -> convertToDTO(entity,dtoClass))
				.orElseThrow(() -> new RecordNotFoundException("Entity not found!!"));
	}
	
	protected D update(D dto) throws RecordNotFoundException {
		
		E savedEntity = this.repository.findById(dto.getId()).orElseThrow(() -> new RecordNotFoundException("Entity not found for update!!"));
		D source = convertToDTO(savedEntity, dtoClass);
		mapper.map(dto, source);
		return save(source);
	}
	
	protected void delete(Long id) throws RecordNotFoundException {
		if(!this.repository.existsById(id)) {
			throw new RecordNotFoundException("Entity not found for delete!!");
		}
		repository.deleteById(id);
	}
}
