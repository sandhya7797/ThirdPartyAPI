package com.scaler.thirdpartyapi.Services;

import com.scaler.thirdpartyapi.Models.Product;

import java.util.List;

public interface ProductService {

    public Product getProduct(long productId);
    public List<Product> getAllProducts();
}
