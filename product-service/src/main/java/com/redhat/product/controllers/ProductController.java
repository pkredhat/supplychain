package com.redhat.product.controllers;

import com.redhat.product.dtos.ProductDTO;
import com.redhat.product.exceptions.EntityNotFoundException;
import com.redhat.product.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("product")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO postProduct(@RequestBody ProductDTO productDTO) {
        LOGGER.info("POST /product {}", productDTO.name());
        return productService.save(productDTO);
    }

    @GetMapping("products")
    public List<ProductDTO> getProducts() {
        LOGGER.info("GET /products");
        return productService.findAll();
    }

    @GetMapping("product/id/{id}")
    public ProductDTO getProductById(@PathVariable("id") String id) {
        LOGGER.info("GET /products/id/{}", id);
        return productService.findOneById(id);
    }

    @GetMapping("product/name/{name}")
    public ProductDTO getProductByName(@PathVariable("name") String name) {
        LOGGER.info("GET /products/name/{}", name);
        return productService.findOneByName(name);
    }

    @DeleteMapping("products")
    public long deleteProducts() {
        LOGGER.info("DELETE /products");
        return productService.deleteAll();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "MongoDB didn't find any document.")
    public final void handleNotFoundExceptions(EntityNotFoundException e) {
        LOGGER.info("=> Product not found: {}", e.toString());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
    public final void handleAllExceptions(RuntimeException e) {
        LOGGER.error("=> Internal server error.", e);
    }
}
