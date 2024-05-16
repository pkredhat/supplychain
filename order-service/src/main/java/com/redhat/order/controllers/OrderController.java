package com.redhat.order.controllers;

import com.redhat.order.dtos.OrderDTO;
import com.redhat.order.exceptions.EntityNotFoundException;
import com.redhat.order.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("order")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO postOrder(@RequestBody OrderDTO orderDTO) {
        LOGGER.info("POST /order {}", orderDTO.name());
        return orderService.save(orderDTO);
    }

    @GetMapping("orders")
    public List<OrderDTO> getOrders() {
        LOGGER.info("GET /orders");
        return orderService.findAll();
    }

    @GetMapping("order/id/{id}")
    public OrderDTO getOrderById(@PathVariable("id") String id) {
        LOGGER.info("GET /orders/id/{}", id);
        return orderService.findOneById(id);
    }

    @GetMapping("order/name/{name}")
    public OrderDTO getOrderByName(@PathVariable("name") String name) {
        LOGGER.info("GET /orders/name/{}", name);
        return orderService.findOneByName(name);
    }

    @DeleteMapping("orders")
    public long deleteOrders() {
        LOGGER.info("DELETE /orders");
        return orderService.deleteAll();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "MongoDB didn't find any document.")
    public final void handleNotFoundExceptions(EntityNotFoundException e) {
        LOGGER.info("=> Order not found: {}", e.toString());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
    public final void handleAllExceptions(RuntimeException e) {
        LOGGER.error("=> Internal server error.", e);
    }
}
