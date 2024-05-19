package com.redhat.stock.services;

import com.redhat.stock.dtos.StockDTO;
import com.redhat.stock.exceptions.EntityNotFoundException;
import com.redhat.stock.models.StockEntity;
import com.redhat.stock.repositories.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public StockDTO save(StockDTO StockDTO) {
        return new StockDTO(stockRepository.save(StockDTO.toStockEntity()));
    }

    @Override
    public List<StockDTO> findAll() {
        return stockRepository.findAll().stream().map(StockDTO::new).toList();
    }

    @Override
    public StockDTO findOneById(String id) {
        StockEntity stock = stockRepository.findOneById(id);
        if (stock == null) {
            throw new EntityNotFoundException(id);
        }
        return new StockDTO(stock);
    }

    @Override
    public StockDTO findOneByName(String name) {
        StockEntity stock = stockRepository.findOneByName(name);
        if (stock == null) {
            throw new EntityNotFoundException(name);
        }
        return new StockDTO(stock);
    }

    @Override
    public long deleteAll() {
        return stockRepository.deleteAll();
    }

}
