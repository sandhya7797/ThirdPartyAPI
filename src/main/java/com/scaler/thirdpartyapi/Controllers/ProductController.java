package com.scaler.thirdpartyapi.Controllers;


import com.scaler.thirdpartyapi.Commons.AuthenticationCommon;
import com.scaler.thirdpartyapi.Exceptions.CategoryNotExistsException;
import com.scaler.thirdpartyapi.Exceptions.ProductNotExistsException;
import com.scaler.thirdpartyapi.Models.Product;
import com.scaler.thirdpartyapi.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    private AuthenticationCommon authenticationCommon;

    public ProductController(@Qualifier("productservice") ProductService productService, AuthenticationCommon authenticationCommon) {
        this.productService = productService;
        this.authenticationCommon = authenticationCommon;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) throws ProductNotExistsException {
        Product product = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }


    //Lets assume product service received token from client and it will send token to user service for Authentication .
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> responseList =  productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws CategoryNotExistsException {
         Product productInResponse = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(productInResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") long id, @RequestBody Product product) throws ProductNotExistsException, CategoryNotExistsException {
        Product productInResponse =  productService.replaceProduct(id, product);
        return ResponseEntity.status(HttpStatus.OK).body(productInResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) throws ProductNotExistsException {
        Product productInResponse =  productService.updateProduct(id, product);
        return ResponseEntity.status(HttpStatus.OK).body(productInResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) throws ProductNotExistsException {
        Product productInResponse = productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(productInResponse);
    }
}
