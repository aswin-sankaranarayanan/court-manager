package com.court.manager.deserializer;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.jackson.JsonComponent;

import com.court.manager.core.dto.BatchDTO;
import com.court.manager.core.dto.BranchDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

@JsonComponent
public class BatchDeserializer extends StdDeserializer<BatchDTO>{

	private static final long serialVersionUID = 1L;
	
	 public BatchDeserializer() { 
	        this(null); 
	    } 


	protected BatchDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public BatchDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = p.getCodec().readTree(p);
		String type = node.get("type").asText();
		String startTime = node.get("startTime").asText();
		String endTime = node.get("endTime").asText();
		Long batchId = node.get("id")!= null? node.get("id").asLong() : null;
		
		JsonNode branch = node.get("branch");
		Long branchId = branch!=null?branch.get("id").asLong(): null;
		
		BatchDTO batchDTO = new BatchDTO();
		BranchDTO branchDTO = new BranchDTO();
		branchDTO.setId(branchId);
		
		batchDTO.setId(batchId);
		batchDTO.setBranch(branchDTO);
		batchDTO.setType(type);
		batchDTO.setStartTime(convertToLocalTime(startTime));
		batchDTO.setEndTime(convertToLocalTime(endTime));
		return batchDTO;
	}
	
	private LocalTime convertToLocalTime(String strTime) {
		return LocalTime.parse(strTime, DateTimeFormatter.ofPattern("hh:mm a"));
	}

}
