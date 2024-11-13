package com.example.demo.Product.ProductRepository;

import com.example.demo.Product.ProductEntity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.awt.print.Book;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findAllByAuthor(String author);
    List<ProductEntity> findAllByGenre(String genre);
    List<ProductEntity> findAllByPublisher(String publisher);
    ProductEntity findByTitle(String title);



}




