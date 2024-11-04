package com.scaler.thirdpartyapi.Services;

import com.scaler.thirdpartyapi.Exceptions.CategoryNotExistsException;
import com.scaler.thirdpartyapi.Exceptions.ProductNotExistsException;
import com.scaler.thirdpartyapi.Models.Category;
import com.scaler.thirdpartyapi.Models.Product;
import com.scaler.thirdpartyapi.Repositories.CategoryRepository;
import com.scaler.thirdpartyapi.Repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


//@Primary
@Service("productservice")
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProduct(long productId) throws ProductNotExistsException {

        Optional<Product> productOptional = productRepository.findById(productId);

        if(productOptional.isEmpty()) {
            throw new ProductNotExistsException("Product with id " + productId + " not exists");
        }

        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Product addProduct(Product product) throws CategoryNotExistsException {

        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());

        if(categoryOptional.isEmpty()) {
//            Category savedCategory = categoryRepository.save(product.getCategory());
//            product.setCategory(savedCategory); this code is replaced with cascade in product model
        } else {
            product.setCategory(categoryOptional.get());
        }

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(long productId, Product product) throws ProductNotExistsException {

        Product savedProduct = productRepository.findById(productId).orElseThrow( () ->
                new ProductNotExistsException("Product with id " + productId + " not exists"));

        if(product.getTitle()!=null) {
            savedProduct.setTitle(product.getTitle());
        }
        if(product.getDescription()!=null) {
            savedProduct.setDescription(product.getDescription());
        }
        if(product.getImage()!=null) {
            savedProduct.setImage(product.getImage());
        }

        Optional<Category> categoryOptional = categoryRepository.findByName(savedProduct.getCategory().getName());
        if (categoryOptional.isPresent()) {
            savedProduct.setCategory(categoryOptional.get());
        }

        if(product.getPrice()!= savedProduct.getPrice()) {
            savedProduct.setPrice(product.getPrice());
        }

        return productRepository.save(savedProduct);

    }

    @Override
    public Product replaceProduct(long productId, Product product) throws ProductNotExistsException, CategoryNotExistsException {

        Product savedProduct = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotExistsException("Product with id " + productId + " not exists ")
        );

        Category category = categoryRepository.findByName(product.getCategory().getName()).orElseThrow(
                () -> new CategoryNotExistsException("Category not exists"));

        savedProduct.setTitle(product.getTitle());
        savedProduct.setDescription(product.getDescription());
        savedProduct.setPrice(product.getPrice());
        savedProduct.setCategory(category);
        savedProduct.setImage(product.getImage());

        return productRepository.save(savedProduct);
    }

    @Override
    public Product deleteProduct(long productId) throws ProductNotExistsException {

        Product product =
                productRepository.findById(productId).orElseThrow(() -> new ProductNotExistsException("Product with id " + productId + " not exists"));

        productRepository.delete(product);

        return product;

    }
}
