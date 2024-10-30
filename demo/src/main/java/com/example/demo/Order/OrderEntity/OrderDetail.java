package com.example.demo.Order.OrderEntity;
import com.example.demo.Customer.CustomerEntity.CustomerAccountEntity;
import com.example.demo.Product.ProductEntity.ProductEntity;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
@Entity
@Table(name = "orderDetail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal quantity;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private ProductEntity bookId;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders orderId;
    private BigDecimal total;

    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public ProductEntity getBookId() {
        return bookId;
    }

    public void setBookId(ProductEntity bookId) {
        this.bookId = bookId;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

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

    public OrderDetail() {
    }
}


