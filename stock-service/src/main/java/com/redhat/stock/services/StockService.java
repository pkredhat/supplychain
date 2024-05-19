package com.redhat.stock.services;

import com.redhat.stock.dtos.StockDTO;
import java.util.List;


public interface StockService {

    StockDTO save(StockDTO StockDTO);

    List<StockDTO> findAll();

    StockDTO findOneById(String id);

    StockDTO findOneByName(String name);

    long deleteAll();
}
