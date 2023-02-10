package com.mobile.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.app.entity.Customer;
import com.mobile.app.exception.CustomerException;
import com.mobile.app.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer newCustomer) {

		return customerRepository.save(newCustomer);
	}

	@Override
	public Customer getCustomerById(Integer customerId) throws CustomerException {

		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		if (optCustomer.isEmpty())
			throw new CustomerException("Cusomer id not found :" + customerId);

		return optCustomer.get();
	}

	@Override
	public Customer updateCustomer(Customer updateCustomer) throws CustomerException {
		Optional<Customer> customerOpt = this.customerRepository.findById(updateCustomer.getId());
		if (customerOpt.isEmpty())
			throw new CustomerException("Customer id does not exist to update.");

		Customer customer = customerOpt.get();
		customer.setName(updateCustomer.getName());
		customer.setMobileNo(updateCustomer.getMobileNo());
		customer.setEmail(updateCustomer.getEmail());

		return this.customerRepository.save(updateCustomer);
	}

	@Override
	public String deleteCustomerById(Integer customerId) throws CustomerException {
		Optional<Customer> optCustomer = this.customerRepository.findById(customerId);
		if (optCustomer.isEmpty())
			throw new CustomerException("Customer id does not exists to delete !");
		Customer customer = optCustomer.get();
		this.customerRepository.deleteById(customerId);
		return "Id Deleted Successfully";
	}

	@Override
	public List<Customer> getAllCustomers() {

		return customerRepository.findAll();
	}

}
