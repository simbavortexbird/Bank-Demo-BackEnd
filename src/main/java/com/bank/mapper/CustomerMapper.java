package com.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bank.domain.Customer;
import com.bank.dto.CustomerDTO;

@Mapper
public interface CustomerMapper {

	@Mapping(source="documentType.dotyId",target="dotyId")
	public CustomerDTO toCustomerDTO(Customer customer);
	
	@Mapping(source="dotyId",target="documentType.dotyId")
	public Customer toCustomer(CustomerDTO customerDTO);
	
	public List<CustomerDTO> toCustomerDTOs(List<Customer> customerDTO);
	
	public List<Customer> toCustomers(List<CustomerDTO> customer);
	
}
