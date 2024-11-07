package com.scaler.thirdpartyapi.Services;

import com.scaler.thirdpartyapi.Exceptions.CategoryNotExistsException;
import com.scaler.thirdpartyapi.Exceptions.ProductNotExistsException;
import com.scaler.thirdpartyapi.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    public Product getProduct(long productId) throws ProductNotExistsException;
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortBy, String sortOrder);
    public Product addProduct(Product product) throws CategoryNotExistsException;
    public Product updateProduct(long productId, Product product) throws ProductNotExistsException;
    public Product replaceProduct(long productId, Product product) throws ProductNotExistsException, CategoryNotExistsException;
    public Product deleteProduct(long productId) throws ProductNotExistsException;

}
