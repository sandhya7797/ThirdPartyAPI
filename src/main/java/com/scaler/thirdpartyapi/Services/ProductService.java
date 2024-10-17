package com.scaler.thirdpartyapi.Services;

import com.scaler.thirdpartyapi.Exceptions.ProductNotExistsException;
import com.scaler.thirdpartyapi.Models.Product;

import java.util.List;

public interface ProductService {

    public Product getProduct(long productId) throws ProductNotExistsException;
    public List<Product> getAllProducts();
    public Product addProduct(FakeStoreResponseDTO fakeStoreProduct);
//    public Product updateProduct(long productId, FakeStoreResponseDTO fakeStoreProduct);
    public Product replaceProduct(long productId, FakeStoreResponseDTO fakeStoreProduct);
    public Product deleteProduct(long productId);

}
