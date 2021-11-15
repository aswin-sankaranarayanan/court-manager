package com.court.manager.core.services;

import org.springframework.stereotype.Service;

import com.court.manager.core.dto.BatchDTO;
import com.court.manager.core.entity.BatchEntity;
import com.court.manager.core.repository.BatchRepository;

@Service
public class BatchService extends BaseService<BatchEntity, BatchDTO, BatchRepository> {

}
