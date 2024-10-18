package com.scaler.thirdpartyapi.Controllers;


import com.scaler.thirdpartyapi.Exceptions.ProductNotExistsException;
import com.scaler.thirdpartyapi.Models.Product;
import com.scaler.thirdpartyapi.Services.FakeStoreResponseDTO;
import com.scaler.thirdpartyapi.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("productservice") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") long id) throws ProductNotExistsException {
        return productService.getProduct(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody FakeStoreResponseDTO fakeStoreProduct) {
        return productService.addProduct(fakeStoreProduct);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") long id, @RequestBody FakeStoreResponseDTO fakeStoreProduct) {
        return productService.replaceProduct(id, fakeStoreProduct);
    }

//    @PatchMapping("/{id}")
//    public Product updateProduct(@PathVariable("id") long id, @RequestBody FakeStoreResponseDTO fakeStoreProduct) {
//        return productService.updateProduct(id, fakeStoreProduct);
//    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") long id) {
        return productService.deleteProduct(id);
    }
}
