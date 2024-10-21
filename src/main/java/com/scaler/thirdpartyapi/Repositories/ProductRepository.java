package com.scaler.thirdpartyapi.Repositories;

import com.scaler.thirdpartyapi.Models.Product;
import com.scaler.thirdpartyapi.Repositories.Projections.ProductWithIdAndTitle;
import com.scaler.thirdpartyapi.Repositories.Projections.ProductWithIdTitleAndDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Override
    Optional<Product> findById(Long aLong);

    @Override
    Product save(Product product);

    @Override
    List<Product> findAll();

    @Override
    void delete(Product entity);

    //HQL Query
    @Query("select p from Product p where p.id=102")
    List<Product> query1();

    //HQL Query with specific parameters in select query (need Projections)
    @Query("select p.id as id,p.title as title from Product p where p.id=102")
    List<ProductWithIdAndTitle> query2();

    //HQL Query
    @Query("select p.id as id,p.title title,p.description as description from Product p where p.id=:id")
    List<ProductWithIdTitleAndDescription> query3(@Param("id") Long id);

    //Native Query
    @Query(value="select * from product p", nativeQuery=true)
    List<Product> nativeSqlQuery();

    //Native Query
    @Query(value="select p.id,p.title from product p where p.id=:id", nativeQuery=true)
    List<ProductWithIdAndTitle> nativeSql3(@Param("id") Long id);


}
