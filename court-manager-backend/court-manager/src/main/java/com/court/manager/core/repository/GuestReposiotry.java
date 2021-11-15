package com.court.manager.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.court.manager.core.entity.GuestEntity;

public interface GuestReposiotry extends PagingAndSortingRepository<GuestEntity, Long> {

}
