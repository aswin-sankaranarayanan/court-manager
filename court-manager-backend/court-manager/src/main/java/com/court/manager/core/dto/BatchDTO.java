package com.court.manager.core.dto;

import java.time.LocalTime;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Component
@Data  @EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class BatchDTO extends BaseDTO {

	private String type;
	@JsonIgnore
	private BranchDTO branch;
	private LocalTime startTime;
	private LocalTime endTime;
	
	public BatchDTO(){
		super();
	}

}
