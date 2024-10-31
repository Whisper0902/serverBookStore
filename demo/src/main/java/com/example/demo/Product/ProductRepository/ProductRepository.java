package com.example.demo.Product.ProductRepository;

import com.example.demo.Product.ProductEntity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p SET p.quantity = p.quantity - :purchaseQuantity " +
            "WHERE p.id = :productId AND p.quantity >= :purchaseQuantity")
    int updateProductQuantity(@Param("productId") Long productId,
                              @Param("purchaseQuantity") BigDecimal purchaseQuantity);
}
