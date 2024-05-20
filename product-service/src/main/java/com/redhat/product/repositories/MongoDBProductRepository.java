package com.redhat.product.repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.redhat.product.models.ProductEntity;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoDBProductRepository implements ProductRepository {

    private final MongoClient client;
    private MongoCollection<ProductEntity> productCollection;

    public MongoDBProductRepository(MongoClient mongoClient) {
        this.client = mongoClient;
    }

    @PostConstruct
    void init() {
        productCollection = client.getDatabase("supplychain").getCollection("products", ProductEntity.class);
    }

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        productEntity.setId(new ObjectId());
        productCollection.insertOne(productEntity);
        return productEntity;
    }

    @Override
    public List<ProductEntity> findAll() {
        return productCollection.find().into(new ArrayList<>());
    }

    @Override
    public ProductEntity findOneById(String id) {
        return productCollection.find(eq("_id", new ObjectId(id))).first();
    }

    @Override
    public ProductEntity findOneByName(String name) {
        return productCollection.find(eq("productName", name)).first();
    }

    @Override
    public long deleteAll() {
        return productCollection.deleteMany(new Document()).getDeletedCount();
    }
}