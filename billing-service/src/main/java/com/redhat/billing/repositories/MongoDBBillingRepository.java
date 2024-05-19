package com.redhat.billing.repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.redhat.billing.models.BillingEntity;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoDBBillingRepository implements BillingRepository {

    private final MongoClient client;
    private MongoCollection<BillingEntity> billingCollection;

    public MongoDBBillingRepository(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        billingCollection = client.getDatabase("supplychain").getCollection("billings", BillingEntity.class);
    }

    @Override
    public BillingEntity save(BillingEntity billingEntity) {
        billingEntity.setId(new ObjectId());
        billingCollection.insertOne(billingEntity);
        return billingEntity;
    }

    @Override
    public List<BillingEntity> findAll() {
        return billingCollection.find().into(new ArrayList<>());
    }

    @Override
    public BillingEntity findOneById(String id) {
        return billingCollection.find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public BillingEntity findOneByName(String name) {
        return billingCollection.find(eq("name", name)).first();
    }

    @Override
    public long deleteAll() {
        return billingCollection.deleteMany(new Document()).getDeletedCount();
    }
}