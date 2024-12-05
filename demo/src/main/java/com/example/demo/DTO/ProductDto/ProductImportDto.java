package com.example.demo.DTO.ProductDto;

public class ProductImportDto {

    private String title;          // Tiêu đề sách
    private String publisher;      // Nhà xuất bản
    private String genre;          // Thể loại
    private String author;         // Tác giả
    private String description;    // Mô tả sách
    private Double price;          // Giá sách
    private String urlImage;       // URL hình ảnh
    private Integer quantity;      // Số lượng sách còn lại

    // Constructors
    public ProductImportDto() {
    }

    public ProductImportDto(String title, String publisher, String genre, String author,
                   String description, Double price, String urlImage, Integer quantity) {
        this.title = title;
        this.publisher = publisher;
        this.genre = genre;
        this.author = author;
        this.description = description;
        this.price = price;
        this.urlImage = urlImage;
        this.quantity = quantity;
    }

    // Getters and Setters
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", urlImage='" + urlImage + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

