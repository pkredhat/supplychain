package com.redhat.product.repositories;

import com.redhat.product.models.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {

    ProductEntity save(ProductEntity productEntity);

    List<ProductEntity> findAll();

    ProductEntity findOneById(String id);

    ProductEntity findOneByName(String name);

    long deleteAll();
}
