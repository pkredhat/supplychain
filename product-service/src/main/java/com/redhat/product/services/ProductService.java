package com.redhat.product.services;

import com.redhat.product.dtos.ProductDTO;
import java.util.List;


public interface ProductService {

    ProductDTO save(ProductDTO ProductDTO);

    List<ProductDTO> findAll();

    ProductDTO findOneById(String id);

    ProductDTO findOneByName(String name);

    long deleteAll();
}
