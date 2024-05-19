package com.redhat.stock.repositories;

import com.redhat.stock.models.StockEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository {

    StockEntity save(StockEntity stockEntity);

    List<StockEntity> findAll();

    StockEntity findOneById(String id);

    StockEntity findOneByName(String name);

    long deleteAll();
}
