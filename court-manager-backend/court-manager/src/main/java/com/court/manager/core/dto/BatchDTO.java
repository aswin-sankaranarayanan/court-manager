package com.court.manager.core.dto;

import java.time.LocalTime;
import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Component
@Data @EqualsAndHashCode(callSuper = true)
public class BatchDTO extends BaseDTO {

	private String type;
	private LocalTime startTime;
	private LocalTime endTime;
	private BranchDTO branch;
}
