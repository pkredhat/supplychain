package com.redhat.order.services;

import com.redhat.order.dtos.OrderDTO;
import java.util.List;


public interface OrderService {

    OrderDTO save(OrderDTO OrderDTO);

    List<OrderDTO> findAll();

    OrderDTO findOneById(String id);

    OrderDTO findOneByName(String name);

    long deleteAll();
}
