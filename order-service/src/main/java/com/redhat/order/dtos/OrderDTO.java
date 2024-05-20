package com.redhat.order.dtos;
import com.redhat.order.models.OrderEntity;
import org.bson.types.ObjectId;

import java.util.Date;

// Order
//     public String orderName;
//     public int customerId;
//     public int productId;
//     public int orderTotal;
//     public LocalDate createdDate;


public record OrderDTO(String id, String orderName, int customerId, int productId, Float orderTotal, Date created) {

    public OrderDTO(OrderEntity p) {
        this(p.getId() == null ? new ObjectId().toHexString() : p.getId().toHexString(), p.getOrderName(),
             p.getCustomerId(), p.getProductId(), p.getOrderTotal(), p.getCreated());
    }

    public OrderEntity toOrderEntity() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new OrderEntity(_id, orderName, customerId, productId, orderTotal, created);
    }
}
