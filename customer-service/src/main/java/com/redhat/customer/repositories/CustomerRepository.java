package com.redhat.customer.repositories;

import com.redhat.customer.models.CustomerEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository {

    CustomerEntity save(CustomerEntity customerEntity);

    List<CustomerEntity> findAll();

    CustomerEntity findOneById(String id);

    CustomerEntity findOneByName(String name);

    long deleteAll();
}
