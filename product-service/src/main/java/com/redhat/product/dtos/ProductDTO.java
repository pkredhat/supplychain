package com.redhat.product.dtos;
import com.redhat.product.models.ProductEntity;
import org.bson.types.ObjectId;

import java.util.Date;


public record ProductDTO(String id, String productName, String productDescription, Date created) {

    public ProductDTO(ProductEntity p) {
        this(p.getId() == null ? new ObjectId().toHexString() : p.getId().toHexString(), p.getProductName(),
             p.getProductDescription(), p.getCreated());
    }

    public ProductEntity toProductEntity() {
        ObjectId _id = id == null ? new ObjectId() : new ObjectId(id);
        return new ProductEntity(_id, productName, productDescription, created);
    }
}
