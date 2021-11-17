package com.court.manager.core.dto;

import java.time.LocalTime;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Component
@Data  @EqualsAndHashCode(callSuper = true) @ToString(callSuper = true)
public class BatchDTO extends BaseDTO {

	private String type;
	
	private BranchDTO branch;
	
	@JsonFormat(pattern = "hh:mm a")
	private LocalTime startTime;
	
	@JsonFormat(pattern = "hh:mm a")
	private LocalTime endTime;
	
	public BatchDTO(){
		super();
	}

}
