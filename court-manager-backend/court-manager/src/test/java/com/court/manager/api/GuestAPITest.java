
package com.court.manager.api;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Disabled;
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
import com.court.manager.core.dto.GuestDTO;
import com.court.manager.core.dto.PagedResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(value = OrderAnnotation.class)
class GuestAPITest extends TestHelper{

	@Autowired
	private ObjectMapper mapper;
	
	
	@Test
	@DisplayName("Get All Guests")
	@Order(1)
	@Sql(scripts = {"classpath:sql/client.sql","classpath:sql/guest.sql"})
	void test_getAllClients() throws Exception {
		MockHttpServletResponse response = get("/guest");
		isOk(response);
		
		PagedResponseDTO<GuestDTO> pagedResponseDTO  =  mapper.readValue(response.getContentAsByteArray(), 
														new TypeReference<PagedResponseDTO<GuestDTO>>() {});
		System.out.println("Response ----->"+pagedResponseDTO);
		assertThat(pagedResponseDTO).isNotNull();
		assertThat(pagedResponseDTO.getResponse().size()).isEqualTo(4);
	}

	
	@Test
	@DisplayName("Save Guest")
	@Order(2)
	@Disabled
	void test_saveClient() throws Exception {}
	
	
	@Test
	@DisplayName("Update Guest")
	@Order(3)
	@Disabled
	void test_updateClient() throws Exception {
		GuestDTO actualGuestDTO = new GuestDTO();
		actualGuestDTO.setId(1L);
		actualGuestDTO.setName("Guest One");
		
		MockHttpServletResponse response = put("/client", actualGuestDTO);
		isOk(response);
		GuestDTO expectedClientDTO = mapper.readValue(response.getContentAsString(), GuestDTO.class);
		System.out.println("Actual -->"+actualGuestDTO);
		System.out.println("Expected -->"+expectedClientDTO);
		
		assertThat(expectedClientDTO.getId()).isPositive();
		assertThat(actualGuestDTO.getName()).isEqualTo(expectedClientDTO.getName());
	}
	
	@Test
	@DisplayName("Delete Guest")
	@Order(4)
	@Disabled
	void test_deleteClient() throws Exception {
		GuestDTO actualGuestDTO = new GuestDTO();
		actualGuestDTO.setId(1L);
		
		MockHttpServletResponse response = delete("/client/1");
		isOk(response);
	}

}
