package com.bank.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;

import com.bank.domain.Customer;
import com.bank.dto.CustomerDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Rollback(false)
class CustomerMapperTest {

	@Autowired
	CustomerMapper customerMapper;

	@Test
	void debeMappearDeCustomeraCustomerDTO() {
		
		Customer customer = new Customer();
		customer.setAccounts(null);
		customer.setAddress("f");
		customer.setCustId(2023);
		customer.setEmail("david@asad.com");
		customer.setEnable("Y");
		customer.setName("David Pruebas de unidad");
		customer.setPhone("3763447");
		customer.setToken(UUID.randomUUID().toString().toUpperCase());
		
		CustomerDTO customerDTO = null;
		
		customerDTO= customerMapper.toCustomerDTO(customer);
	}
	
	@Test
	void debeMappearDeCustomerDTOaCustomer() {
		
		//Arrange
		CustomerDTO customerDTO = new CustomerDTO();
		
		customerDTO.setAddress("avenida asdas");
		customerDTO.setCustId(1234);
		customerDTO.setEmail("hola@pruebas.com");
		customerDTO.setEnable("Y");
		customerDTO.setName("hla");
		customerDTO.setPhone("3223165498");
		customerDTO.setToken(UUID.randomUUID().toString().toUpperCase());
		customerDTO.setDotyId(1);
		Customer customer = null;
		
		//Act
		customer= customerMapper.toCustomer(customerDTO);
		
		//Assert
		assertNotNull(customer);

	
	}

}
