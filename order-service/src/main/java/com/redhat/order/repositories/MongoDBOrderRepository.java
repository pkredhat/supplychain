package com.redhat.order.repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.redhat.order.models.OrderEntity;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoDBOrderRepository implements OrderRepository {

    private final MongoClient client;
    private MongoCollection<OrderEntity> orderCollection;

    public MongoDBOrderRepository(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        orderCollection = client.getDatabase("supplychain").getCollection("orders", OrderEntity.class);
    }

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        orderEntity.setId(new ObjectId());
        orderCollection.insertOne(orderEntity);
        return orderEntity;
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderCollection.find().into(new ArrayList<>());
    }

    @Override
    public OrderEntity findOneById(String id) {
        return orderCollection.find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public OrderEntity findOneByName(String name) {
        return orderCollection.find(eq("name", name)).first();
    }

    @Override
    public long deleteAll() {
        return orderCollection.deleteMany(new Document()).getDeletedCount();
    }
}