package com.court.manager.core.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Component
@Data @ToString(exclude = {"client"},callSuper = true) @EqualsAndHashCode(callSuper = true)
public class BranchDTO extends BaseDTO {
	
	private String location;
	private Double monthlyFee;
	private Double guestFee;
	private Double courtFee;
	private List<ClientUserDTO> clientUsers;
	private List<BatchDTO> batches;
	private List<GuestDTO> guests;



	@JsonIgnore
	private ClientDTO client;
	
	public BranchDTO() {
		this.clientUsers = new ArrayList<>();
		this.batches =  new ArrayList<>();
		this.guests = new ArrayList<>();
		
	}
}
