package com.scaler.thirdpartyapi.Services;

import com.scaler.thirdpartyapi.Exceptions.CategoryNotExistsException;
import com.scaler.thirdpartyapi.Exceptions.ProductNotExistsException;
import com.scaler.thirdpartyapi.Models.Product;

import java.util.List;

public interface ProductService {

    public Product getProduct(long productId) throws ProductNotExistsException;
    public List<Product> getAllProducts();
    public Product addProduct(Product product) throws CategoryNotExistsException;
    public Product updateProduct(long productId, Product product) throws ProductNotExistsException;
    public Product replaceProduct(long productId, Product product) throws ProductNotExistsException, CategoryNotExistsException;
    public Product deleteProduct(long productId) throws ProductNotExistsException;

}
