package com.court.manager.core.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Component
@Data @EqualsAndHashCode(callSuper = true)
public class CourtDTO extends BaseDTO {

	private String name;
	
	@JsonIgnore
	private BranchDTO branch;
}
