package com.redhat.order.dtos;
import com.redhat.order.models.OrderEntity;
import org.bson.types.ObjectId;

import java.util.Date;


public record OrderDTO(String id, String name, String headquarters, Date created) {

    public OrderDTO(OrderEntity p) {
        this(p.getId() == null ? new ObjectId().toHexString() : p.getId().toHexString(), p.getName(),
             p.getHeadquarters(), p.getCreated());
    }

    public OrderEntity toOrderEntity() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new OrderEntity(_id, name, headquarters, created);
    }
}
