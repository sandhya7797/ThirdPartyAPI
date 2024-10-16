package com.scaler.thirdpartyapi.Repositories;

import com.scaler.thirdpartyapi.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);
}
