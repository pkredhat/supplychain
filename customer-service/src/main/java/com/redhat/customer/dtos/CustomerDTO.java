package com.redhat.customer.dtos;
import com.redhat.customer.models.CustomerEntity;
import org.bson.types.ObjectId;

import java.util.Date;


public record CustomerDTO(String id, String name, String headquarters, Date created) {

    public CustomerDTO(CustomerEntity p) {
        this(p.getId() == null ? new ObjectId().toHexString() : p.getId().toHexString(), p.getName(),
             p.getHeadquarters(), p.getCreated());
    }

    public CustomerEntity toCustomerEntity() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new CustomerEntity(_id, name, headquarters, created);
    }
}
