package com.bank.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.domain.Customer;
import com.bank.domain.DocumentType;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Rollback(false)
class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DocumentTypeRepository documentTypeRepository;

	@Test
	@Order(1)
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	void debeCrearUnCustomer() {
		//Arrange
		Customer customer = new Customer();
		customer.setAccounts(null);
		customer.setAddress("Avenida 123");
		customer.setCustId(2023);
		customer.setEmail("david@asad.com");
		customer.setEnable("Y");
		customer.setName("David Pruebas de unidad");
		customer.setPhone("3763447");
		customer.setToken(UUID.randomUUID().toString().toUpperCase());
		
		DocumentType documentType = documentTypeRepository.findById(1).get();
		
		
		
		customer.setDocumentType(documentType);
		
		//Act
		customerRepository.save(customer);
	
		//Assert
		assertNotNull(customer);
	}
	
	@Test
	@Order(2)
	@Transactional(readOnly = true)

	void debeConsultarUnCustomer() {
		
		//Arrange
		Customer customer = null;
		
		//Act
		customer = customerRepository.findById(2021).get();
		
		//Assert
		assertNotNull(customer);
		
		 
		
	}
	
	@Test
	@Order(3)
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	void debeModificarUnCustomer() {
		//Arrange
		Customer customer = null;
		customer = customerRepository.findById(2023).get();

		//Act
		customer.setName("Nombre cambiado"); 


		customerRepository.save(customer);
		//Assert
		assertNotNull(customer);
	}
	@Test
	@Order(4)
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	void debeBorrarUnCustomer() {
		//Arrange
		Optional<Customer> customer = null;
		customer = customerRepository.findById(2023);
		
		assertTrue(customer.isPresent());
		customerRepository.delete(customer.get());
		
		
		
	}

}
