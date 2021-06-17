package com.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bank.domain.Customer;
import com.bank.repository.CustomerRepository;



@Service
public class CustomerServiceImpl implements CustomerService {

	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	Validator validator;
	
	@Override
	public void validate(Customer entity) throws ConstraintViolationException {
		
		Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(entity);
		if(!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Customer> findAll() {

		return customerRepository.findAll();
	
	
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Customer> findById(Integer id) {
		return customerRepository.findById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Long count() {
		return customerRepository.count();
	}



	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Customer save(Customer entity) throws Exception {
		
		if(entity ==  null) {
			throw new Exception("El customer es nulo");
		}
		validate(entity);
		
		if(customerRepository.existsById(entity.getCustId())) {
			throw new Exception("El customer ya existe");
		}
		return customerRepository.save(entity);
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Customer update(Customer entity) throws Exception {

		if(entity ==  null) {
			throw new Exception("El customer es nulo");
		}
		validate(entity);
		
		if(customerRepository.existsById(entity.getCustId())) {
			throw new Exception("El customer ya existe");
		}
		
		return customerRepository.save(entity);
	}

	@Override
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Customer entity) throws Exception {
		if(entity ==  null) {
			throw new Exception("El customer es nulo");
		}
		
		if(entity.getCustId()== null || entity.getCustId()== 0) {
			throw new Exception("El customer id es nulo");
		}
		
		if(customerRepository.existsById(entity.getCustId())) {
			throw new Exception("El customer ya existe");
		}
		
		customerRepository.findById(entity.getCustId()).ifPresent(customer ->{
			if(customer.getAccounts().isEmpty() == false) {
				throw new RuntimeException("El Customer tiene Accounts asociadas");
			}
			if(customer.getRegisteredAccounts().isEmpty() == false) {
				throw new RuntimeException("El Customer tiene RegisteredAccounts asociadas");
			}
		});;
		
		customerRepository.deleteById(entity.getCustId());
		
	}

	@Override
	public void deleteById(Integer id) throws Exception {

		if(id==null || id==0) {
			throw new Exception("El id es nulo o menor igual a 0");
		}
		if(customerRepository.existsById(id) == false) {
			throw new Exception("El customer no existe"); 
		}
		
		
		delete(customerRepository.findById(id).get());
	}

}
