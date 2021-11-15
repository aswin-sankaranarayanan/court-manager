package com.court.manager.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.court.manager.core.entity.ClientEntity;

@Repository
public interface ClientRepository extends PagingAndSortingRepository<ClientEntity, Long> {

}
