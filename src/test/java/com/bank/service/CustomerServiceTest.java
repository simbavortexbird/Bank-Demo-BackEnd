package com.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.domain.Customer;
import com.bank.domain.DocumentType;
import com.bank.repository.CustomerRepository;
import com.bank.repository.DocumentTypeRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Rollback(false)
class CustomerServiceTest {
	
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	DocumentTypeService documentTypeService;

	@Test
	@Order(1)
	void debeCrearUnCustomer() throws Exception {
		//Arrange
		Customer customer = new Customer();
		customer.setAccounts(null);
		customer.setAddress("f");
		customer.setCustId(2023);
		customer.setEmail("david@asad.com");
		customer.setEnable("Y");
		customer.setName("David Pruebas de unidad");
		customer.setPhone("3763447");
		customer.setToken(UUID.randomUUID().toString().toUpperCase());
		
		DocumentType documentType = documentTypeService.findById(1).get();
		
		
		
		customer.setDocumentType(documentType);
		
		//Act
		customerService.save(customer);
	
		//Assert
		assertNotNull(customer);
	}
	
	@Test
	@Order(2)
	void debeConsultarUnCustomer() {
		
		//Arrange
		Customer customer = null;
		
		//Act
		customer = customerService.findById(2021).get();
		
		//Assert
		assertNotNull(customer);
		
		 
		
	}
	
	@Test
	@Order(3)
	void debeModificarUnCustomer() throws Exception {
		//Arrange
		Customer customer = null;
		customer = customerService.findById(2022).get();

		//Act
		customer.setName("Nombre cambiado"); 


		customerService.update(customer);
		//Assert
		assertNotNull(customer);
	}
	@Test
	@Order(4)
	void debeBorrarUnCustomer() throws Exception {
		//Arrange
		Optional<Customer> customer = null;
		customer = customerService.findById(2023);
		
		assertTrue(customer.isPresent());
		customerService.delete(customer.get());
		
		
		
	}


}
