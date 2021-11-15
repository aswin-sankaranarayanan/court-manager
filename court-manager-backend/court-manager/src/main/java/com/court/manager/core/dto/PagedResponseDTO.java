package com.court.manager.core.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;


@Component
@Data
public class PagedResponseDTO<T> {

	long total;
	int currentSize;
	List<T> response;
	
	public PagedResponseDTO(){
		this.response = new ArrayList<>();
	}
}
