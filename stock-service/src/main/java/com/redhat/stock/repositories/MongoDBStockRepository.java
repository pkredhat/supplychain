package com.redhat.stock.repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.redhat.stock.models.StockEntity;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoDBStockRepository implements StockRepository {

    private final MongoClient client;
    private MongoCollection<StockEntity> stockCollection;

    public MongoDBStockRepository(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        stockCollection = client.getDatabase("supplychain").getCollection("stocks", StockEntity.class);
    }

    @Override
    public StockEntity save(StockEntity stockEntity) {
        stockEntity.setId(new ObjectId());
        stockCollection.insertOne(stockEntity);
        return stockEntity;
    }

    @Override
    public List<StockEntity> findAll() {
        return stockCollection.find().into(new ArrayList<>());
    }

    @Override
    public StockEntity findOneById(String id) {
        return stockCollection.find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public StockEntity findOneByName(String name) {
        return stockCollection.find(eq("name", name)).first();
    }

    @Override
    public long deleteAll() {
        return stockCollection.deleteMany(new Document()).getDeletedCount();
    }
}