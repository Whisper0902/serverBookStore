package com.example.demo.Product.ProductRepository;

import com.example.demo.Product.ProductEntity.ProductEntity;
import lombok.val;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductHomepage extends JpaRepository<ProductEntity, Long> {

    // Lấy 10 sản phẩm đầu tiên
    List<ProductEntity> findTop8ByOrderByIdAsc();



    @Query( "SELECT p FROM ProductEntity p WHERE p.genre = :value")
    List<ProductEntity> findListProductByGenre( @Param("value") String value);

    @Query( "SELECT p FROM ProductEntity p WHERE p.author = :value")
    List<ProductEntity> findListProductByAuthor( @Param("value") String value);

    @Query( "SELECT p FROM ProductEntity p WHERE p.publisher = :value")
    List<ProductEntity> findListProductByPublisher( @Param("value") String value);

}
