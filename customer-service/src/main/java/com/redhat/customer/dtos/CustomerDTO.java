package com.redhat.customer.dtos;
import com.redhat.customer.models.CustomerEntity;
import org.bson.types.ObjectId;

import java.util.Date;


// public String firstName;
// public String lastName;
// public String address;
// public String phoneNumber;
// public LocalDate createdDate;


public record CustomerDTO(String id, String firstName, String lastName, String address, String phoneNumber, Date created) {

    public CustomerDTO(CustomerEntity p) {
        this(p.getId() == null ? new ObjectId().toHexString() : p.getId().toHexString(), p.getFirstName(),
             p.getLastName(), p.getAddress(), p.getPhoneNumber(), p.getCreated());
    }

    public CustomerEntity toCustomerEntity() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new CustomerEntity(_id, firstName, lastName, address, phoneNumber, created);
    }
}
