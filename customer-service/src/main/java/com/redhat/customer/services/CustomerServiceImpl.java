package com.redhat.customer.services;

import com.redhat.customer.dtos.CustomerDTO;
import com.redhat.customer.exceptions.EntityNotFoundException;
import com.redhat.customer.models.CustomerEntity;
import com.redhat.customer.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO save(CustomerDTO CustomerDTO) {
        return new CustomerDTO(customerRepository.save(CustomerDTO.toCustomerEntity()));
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream().map(CustomerDTO::new).toList();
    }

    @Override
    public CustomerDTO findOneById(String id) {
        CustomerEntity customer = customerRepository.findOneById(id);
        if (customer == null) {
            throw new EntityNotFoundException(id);
        }
        return new CustomerDTO(customer);
    }

    @Override
    public CustomerDTO findOneByName(String name) {
        CustomerEntity customer = customerRepository.findOneByName(name);
        if (customer == null) {
            throw new EntityNotFoundException(name);
        }
        return new CustomerDTO(customer);
    }

    @Override
    public long deleteAll() {
        return customerRepository.deleteAll();
    }

}
