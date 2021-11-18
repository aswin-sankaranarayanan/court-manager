package com.court.manager.core.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Component
@Data  @EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class BatchDTO extends BaseDTO {

	private String type;
	
	@JsonBackReference
	private BranchDTO branch;
	
	@JsonFormat(pattern = "hh:mm a")
	private LocalTime startTime;
	
	@JsonFormat(pattern = "hh:mm a")
	private LocalTime endTime;
	
	@JsonManagedReference
	private List<MemberDTO> members;
	
	
	public BatchDTO(){
		super();
		this.members = new ArrayList<>();
	}

}
