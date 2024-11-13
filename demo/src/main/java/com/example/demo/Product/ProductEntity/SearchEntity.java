package com.example.demo.Product.ProductEntity;

import com.example.demo.Customer.CustomerEntity.CustomerAccountEntity;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

@Repository
@Entity
@Table(name = "searchProduct")
public class SearchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String author;
    private String genre;
    private String publisher;
    @OneToOne
    @JoinColumn(name = "book_id")
    private ProductEntity book_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public ProductEntity getBook_id() {
        return book_id;
    }

    public void setBook_id(ProductEntity book_id) {
        this.book_id = book_id;
    }
}
