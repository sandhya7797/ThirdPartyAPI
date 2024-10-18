package com.scaler.thirdpartyapi.Services;

import com.scaler.thirdpartyapi.Exceptions.ProductNotExistsException;
import com.scaler.thirdpartyapi.Models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productservice")
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProduct(long productId) throws ProductNotExistsException {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product addProduct(FakeStoreResponseDTO fakeStoreProduct) {
        return null;
    }

    @Override
    public Product replaceProduct(long productId, FakeStoreResponseDTO fakeStoreProduct) {
        return null;
    }

    @Override
    public Product deleteProduct(long productId) {
        return null;
    }
}
