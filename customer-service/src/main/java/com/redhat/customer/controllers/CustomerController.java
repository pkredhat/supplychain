package com.redhat.customer.controllers;

import com.redhat.customer.dtos.CustomerDTO;
import com.redhat.customer.exceptions.EntityNotFoundException;
import com.redhat.customer.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("customer")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO postCustomer(@RequestBody CustomerDTO customerDTO) {
        LOGGER.info("POST /customer {}", customerDTO.firstName());
        return customerService.save(customerDTO);
    }

    @GetMapping("customers")
    public List<CustomerDTO> getCustomers() {
        LOGGER.info("GET /customers");
        return customerService.findAll();
    }

    @GetMapping("customer/id/{id}")
    public CustomerDTO getCustomerById(@PathVariable("id") String id) {
        LOGGER.info("GET /customers/id/{}", id);
        return customerService.findOneById(id);
    }

    @GetMapping("customer/name/{name}")
    public CustomerDTO getCustomerByName(@PathVariable("name") String name) {
        LOGGER.info("GET /customers/name/{}", name);
        return customerService.findOneByName(name);
    }

    @DeleteMapping("customers")
    public long deleteCustomers() {
        LOGGER.info("DELETE /customers");
        return customerService.deleteAll();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "MongoDB didn't find any document.")
    public final void handleNotFoundExceptions(EntityNotFoundException e) {
        LOGGER.info("=> Customer not found: {}", e.toString());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
    public final void handleAllExceptions(RuntimeException e) {
        LOGGER.error("=> Internal server error.", e);
    }
}
