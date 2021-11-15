package com.court.manager.api;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import com.court.manager.core.dto.BranchDTO;
import com.court.manager.core.dto.ClientDTO;
import com.court.manager.core.dto.ClientUserDTO;
import com.court.manager.core.dto.PagedResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(value = OrderAnnotation.class)
class ClientAPITest extends TestHelper{

	@Autowired
	private ObjectMapper mapper;
	
	
	@Test
	@DisplayName("Get All Clients")
	@Order(1)
	@Sql(scripts = {"classpath:sql/client.sql"})
	void test_getAllClients() throws Exception {
		MockHttpServletResponse response = get("/client");
		isOk(response);
		
		PagedResponseDTO<ClientDTO> pagedResponseDTO  =  mapper.readValue(response.getContentAsByteArray(), 
														new TypeReference<PagedResponseDTO<ClientDTO>>() {});
		System.out.println("Response ----->"+pagedResponseDTO);
		assertThat(pagedResponseDTO).isNotNull();
		assertThat(pagedResponseDTO.getResponse().size()).isPositive();
	}

	
	@Test
	@DisplayName("Save Client")
	@Order(2)
	void test_saveClient() throws Exception {
		ClientDTO actualClientDTO = new ClientDTO();
		actualClientDTO.setName("Chennai-63");
		actualClientDTO.setOwner("Madhan");
		actualClientDTO.setEmail("chennai63@gmail.com");
		
		BranchDTO actualBranchDTO1 = new BranchDTO();
		actualBranchDTO1.setLocation("Perungalathur");
		actualBranchDTO1.setCourtFee(400.00);
		actualBranchDTO1.setGuestFee(100.00);
		actualBranchDTO1.setMonthlyFee(1000.00);
		
		BranchDTO actualBranchDTO2 = new BranchDTO();
		actualBranchDTO2.setLocation("Urapakkam");
		actualBranchDTO2.setCourtFee(400.00);
		actualBranchDTO2.setGuestFee(100.00);
		actualBranchDTO2.setMonthlyFee(1000.00);
		
		actualClientDTO.addBranch(actualBranchDTO1);
		actualClientDTO.addBranch(actualBranchDTO2);
		
		ClientUserDTO actualClientUser1 = new ClientUserDTO();
		actualClientUser1.setAdmin(true);
		actualClientUser1.setName("Saravanan");
		actualClientUser1.setEmail("saravanan@gmail.com");
		actualClientUser1.setBranch(actualBranchDTO1);
		
		ClientUserDTO actualClientUser2 = new ClientUserDTO();
		actualClientUser2.setAdmin(true);
		actualClientUser2.setName("Gokul");
		actualClientUser2.setEmail("gokul@gmail.com");
		actualClientUser2.setBranch(actualBranchDTO2);
		
		List<ClientUserDTO> clientUsers1 = new ArrayList<ClientUserDTO>();
		clientUsers1.add(actualClientUser1);
		
		List<ClientUserDTO> clientUsers2 = new ArrayList<ClientUserDTO>();
		clientUsers2.add(actualClientUser2);

		actualBranchDTO1.setClientUsers(clientUsers1);
		actualBranchDTO2.setClientUsers(clientUsers2);
		
		MockHttpServletResponse response = post("/client", actualClientDTO);
		isOk(response);
		ClientDTO expectedClientDTO = mapper.readValue(response.getContentAsString(), ClientDTO.class);
		System.out.println("Actual -->"+actualClientDTO);
		System.out.println("Expected -->"+expectedClientDTO);
		
		assertThat(expectedClientDTO.getId()).isPositive();
		assertThat(actualClientDTO.getName()).isEqualTo(expectedClientDTO.getName());
		assertThat(actualClientDTO.getBranches().size()).isEqualTo(expectedClientDTO.getBranches().size());
		
		assertThat(actualClientDTO.getBranches().get(0).getClientUsers().size()).isEqualTo(expectedClientDTO.getBranches().get(0).getClientUsers().size());
		assertThat(actualClientDTO.getBranches().get(1).getClientUsers().size()).isEqualTo(expectedClientDTO.getBranches().get(1).getClientUsers().size());
	}
	
	
	@Test
	@DisplayName("Update Client")
	@Order(3)
	void test_updateClient() throws Exception {
		ClientDTO actualClientDTO = new ClientDTO();
		actualClientDTO.setId(1L);
		actualClientDTO.setName("Client One");
		
		MockHttpServletResponse response = put("/client", actualClientDTO);
		isOk(response);
		ClientDTO expectedClientDTO = mapper.readValue(response.getContentAsString(), ClientDTO.class);
		System.out.println("Actual -->"+actualClientDTO);
		System.out.println("Expected -->"+expectedClientDTO);
		
		assertThat(expectedClientDTO.getId()).isPositive();
		assertThat(actualClientDTO.getName()).isEqualTo(expectedClientDTO.getName());
	}
	
	@Test
	@DisplayName("Delete Client")
	@Order(4)
	void test_deleteClient() throws Exception {
		ClientDTO actualClientDTO = new ClientDTO();
		actualClientDTO.setId(1L);
		
		MockHttpServletResponse response = delete("/client/1");
		isOk(response);
	}

}
