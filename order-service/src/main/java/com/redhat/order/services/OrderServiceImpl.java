package com.redhat.order.services;

import com.redhat.order.dtos.OrderDTO;
import com.redhat.order.exceptions.EntityNotFoundException;
import com.redhat.order.models.OrderEntity;
import com.redhat.order.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDTO save(OrderDTO OrderDTO) {
        return new OrderDTO(orderRepository.save(OrderDTO.toOrderEntity()));
    }

    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream().map(OrderDTO::new).toList();
    }

    @Override
    public OrderDTO findOneById(String id) {
        OrderEntity order = orderRepository.findOneById(id);
        if (order == null) {
            throw new EntityNotFoundException(id);
        }
        return new OrderDTO(order);
    }

    @Override
    public OrderDTO findOneByName(String name) {
        OrderEntity order = orderRepository.findOneByName(name);
        if (order == null) {
            throw new EntityNotFoundException(name);
        }
        return new OrderDTO(order);
    }

    @Override
    public long deleteAll() {
        return orderRepository.deleteAll();
    }

}
