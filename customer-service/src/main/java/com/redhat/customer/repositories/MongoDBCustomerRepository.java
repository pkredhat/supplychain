package com.redhat.customer.repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.redhat.customer.models.CustomerEntity;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoDBCustomerRepository implements CustomerRepository {

    private final MongoClient client;
    private MongoCollection<CustomerEntity> customerCollection;

    public MongoDBCustomerRepository(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        customerCollection = client.getDatabase("supplychain").getCollection("customers", CustomerEntity.class);
    }

    @Override
    public CustomerEntity save(CustomerEntity customerEntity) {
        customerEntity.setId(new ObjectId());
        customerCollection.insertOne(customerEntity);
        return customerEntity;
    }

    @Override
    public List<CustomerEntity> findAll() {
        return customerCollection.find().into(new ArrayList<>());
    }

    @Override
    public CustomerEntity findOneById(String id) {
        return customerCollection.find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public CustomerEntity findOneByName(String name) {
        return customerCollection.find(eq("firstName", name)).first();
    }

    @Override
    public long deleteAll() {
        return customerCollection.deleteMany(new Document()).getDeletedCount();
    }
}