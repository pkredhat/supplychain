package com.redhat.stock.controllers;

import com.redhat.stock.dtos.StockDTO;
import com.redhat.stock.exceptions.EntityNotFoundException;
import com.redhat.stock.services.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StockController {

    private final static Logger LOGGER = LoggerFactory.getLogger(StockController.class);
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping("stock")
    @ResponseStatus(HttpStatus.CREATED)
    public StockDTO postStock(@RequestBody StockDTO stockDTO) {
        LOGGER.info("POST /stock {}", stockDTO.name());
        return stockService.save(stockDTO);
    }

    @GetMapping("stocks")
    public List<StockDTO> getStocks() {
        LOGGER.info("GET /stocks");
        return stockService.findAll();
    }

    @GetMapping("stock/id/{id}")
    public StockDTO getStockById(@PathVariable("id") String id) {
        LOGGER.info("GET /stocks/id/{}", id);
        return stockService.findOneById(id);
    }

    @GetMapping("stock/name/{name}")
    public StockDTO getStockByName(@PathVariable("name") String name) {
        LOGGER.info("GET /stocks/name/{}", name);
        return stockService.findOneByName(name);
    }

    @DeleteMapping("stocks")
    public long deleteStocks() {
        LOGGER.info("DELETE /stocks");
        return stockService.deleteAll();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "MongoDB didn't find any document.")
    public final void handleNotFoundExceptions(EntityNotFoundException e) {
        LOGGER.info("=> Stock not found: {}", e.toString());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
    public final void handleAllExceptions(RuntimeException e) {
        LOGGER.error("=> Internal server error.", e);
    }
}
