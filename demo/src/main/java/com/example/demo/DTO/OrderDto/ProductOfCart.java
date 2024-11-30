package com.example.demo.DTO.OrderDto;

import com.example.demo.Product.ProductEntity.ProductEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductOfCart {

    private BigDecimal quantity;
    private BigDecimal total;

    private ProductEntity bookId;
    private LocalDateTime created;
    private LocalDateTime updateTime;

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    public ProductEntity getBookId() {
        return bookId;
    }

    public void setBookId(ProductEntity bookId) {
        this.bookId = bookId;
    }
}
