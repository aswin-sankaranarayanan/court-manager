package com.court.manager.core.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = true)
public class GuestDTO extends BaseDTO{

	private String name;
	private String contactDetails;
	
	@JsonFormat(pattern = "hh:mm a")
	private LocalTime startTime;
	
	@JsonFormat(pattern = "hh:mm a")
	private LocalTime endTime;
	
	private Double fee;
	@JsonFormat(pattern = "dd-MM-yyyy")

	private LocalDate feePaidOn;
	private Boolean due;
	private Boolean fullCourt;
	
	private BranchDTO branch;

}
