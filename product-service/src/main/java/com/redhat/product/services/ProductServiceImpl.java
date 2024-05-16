package com.redhat.product.services;

import com.redhat.product.dtos.ProductDTO;
import com.redhat.product.exceptions.EntityNotFoundException;
import com.redhat.product.models.ProductEntity;
import com.redhat.product.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO save(ProductDTO ProductDTO) {
        return new ProductDTO(productRepository.save(ProductDTO.toProductEntity()));
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(ProductDTO::new).toList();
    }

    @Override
    public ProductDTO findOneById(String id) {
        ProductEntity product = productRepository.findOneById(id);
        if (product == null) {
            throw new EntityNotFoundException(id);
        }
        return new ProductDTO(product);
    }

    @Override
    public ProductDTO findOneByName(String name) {
        ProductEntity product = productRepository.findOneByName(name);
        if (product == null) {
            throw new EntityNotFoundException(name);
        }
        return new ProductDTO(product);
    }

    @Override
    public long deleteAll() {
        return productRepository.deleteAll();
    }

}
