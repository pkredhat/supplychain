package com.redhat.vendor.repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.redhat.vendor.models.VendorEntity;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoDBVendorRepository implements VendorRepository {

    private final MongoClient client;
    private MongoCollection<VendorEntity> vendorCollection;

    public MongoDBVendorRepository(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        vendorCollection = client.getDatabase("supplychain").getCollection("vendors", VendorEntity.class);
    }

    @Override
    public VendorEntity save(VendorEntity vendorEntity) {
        vendorEntity.setId(new ObjectId());
        vendorCollection.insertOne(vendorEntity);
        return vendorEntity;
    }

    @Override
    public List<VendorEntity> findAll() {
        return vendorCollection.find().into(new ArrayList<>());
    }

    @Override
    public VendorEntity findOneById(String id) {
        return vendorCollection.find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public VendorEntity findOneByName(String name) {
        return vendorCollection.find(eq("name", name)).first();
    }

    @Override
    public long deleteAll() {
        return vendorCollection.deleteMany(new Document()).getDeletedCount();
    }
}