package com.redhat.customer.services;

import com.redhat.customer.dtos.CustomerDTO;
import java.util.List;


public interface CustomerService {

    CustomerDTO save(CustomerDTO CustomerDTO);

    List<CustomerDTO> findAll();

    CustomerDTO findOneById(String id);

    CustomerDTO findOneByName(String name);

    long deleteAll();
}
