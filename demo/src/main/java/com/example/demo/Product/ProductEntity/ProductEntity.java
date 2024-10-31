package com.example.demo.Product.ProductEntity;

import com.example.demo.Order.OrderEntity.OrderDetail;
import jakarta.persistence.*;
import org.hibernate.annotations.DialectOverride;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Entity
@Table(name = "book")
public class ProductEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String publisher;

    private String genre;

    private String author;

    private String description;

    @Column(precision = 19, scale = 2)
    private BigDecimal price;

    private String urlImage;

    @Column(precision = 19, scale = 2)
    private BigDecimal quantity;
    @OneToMany(mappedBy = "bookId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> bookId;

    @Version
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public ProductEntity() {
    }

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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductEntity{" + "title='" + title + '\'' + ", publisher='" + publisher + '\'' + ", genre='" + genre + '\'' + ", author='" + author + '\'' + ", description='" + description + '\'' + ", price=" + price + ", urlImage='" + urlImage + '\'' + ", quantity=" + quantity + '}';
    }
}


