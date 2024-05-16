package com.redhat.order.repositories;

import com.redhat.order.models.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {

    OrderEntity save(OrderEntity orderEntity);

    List<OrderEntity> findAll();

    OrderEntity findOneById(String id);

    OrderEntity findOneByName(String name);

    long deleteAll();
}
