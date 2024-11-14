package com.example.demo.Product.ProductRepository;

import com.example.demo.Product.ProductEntity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductHomepage extends JpaRepository<ProductEntity, Long> {

    // Lấy 10 sản phẩm đầu tiên
    List<ProductEntity> findTop8ByOrderByIdAsc();
}
