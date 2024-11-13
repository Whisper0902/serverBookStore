package com.example.demo.Product.ProductRepository;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductEntity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends JpaRepository<SearchEntity, Long> {

    @Query("SELECT b FROM SearchEntity b WHERE b.author LIKE %:keyword% OR b.title LIKE %:keyword% OR b.description LIKE %:keyword% " +
            "OR b.genre LIKE %:keyword%  OR b.publisher LIKE %:keyword%")
    List<SearchEntity> findByKeyword(@Param("keyword") String keyword);
}
