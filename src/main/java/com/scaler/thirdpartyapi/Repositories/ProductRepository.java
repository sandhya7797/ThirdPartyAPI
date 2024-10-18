package com.scaler.thirdpartyapi.Repositories;

import com.scaler.thirdpartyapi.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long aLong);

    @Override
    Product save(Product product);

    @Override
    List<Product> findAll();

    @Override
    void delete(Product entity);
}
