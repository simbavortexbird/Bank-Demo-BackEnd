package com.bank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.domain.Customer;
import com.bank.dto.CustomerDTO;
import com.bank.mapper.CustomerMapper;
import com.bank.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin(origins = "*")
public class CustomerRestController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerMapper customerMapper;

	@GetMapping(value = "/{custId}")
	public ResponseEntity<?> findById(@PathVariable("custId") Integer custId) throws Exception {

		Customer customer = (customerService.findById(custId).isPresent() == true)
				? customerService.findById(custId).get()
				: null;
		
		return ResponseEntity.ok().body(customerMapper.toCustomerDTO(customer));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {

		return ResponseEntity.ok().body(customerMapper.toCustomerDTOs(customerService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {

		Customer customer = customerMapper.toCustomer(customerDTO);
		customer = customerService.save(customer);

		return ResponseEntity.ok().body(customerMapper.toCustomerDTO(customer));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {

		Customer customer = customerMapper.toCustomer(customerDTO);
		customer = customerService.update(customer);

		return ResponseEntity.ok().body(customerMapper.toCustomerDTO(customer));
	}

	@DeleteMapping(value = "/{custId}")
	public ResponseEntity<?> delete(@PathVariable("custId") Integer custId) throws Exception {

		customerService.deleteById(custId);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(customerService.count());
	}
}
