package com.example.demo.Order.OrderEntity;

import com.example.demo.Customer.CustomerEntity.CustomerAccountEntity;
import com.example.demo.Customer.CustomerEntity.CustomerAddressEntity;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Repository
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customerId")
    private CustomerAccountEntity customerId;

    private String status;

    private String paymentMethod;
    private BigDecimal total;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderID;
    @OneToOne(mappedBy = "orderAddress", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerAddressEntity orderAddress;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerAccountEntity getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerAccountEntity customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public List<OrderDetail> getOrderID() {
        return orderID;
    }

    public void setOrderID(List<OrderDetail> orderID) {
        this.orderID = orderID;
    }

    public CustomerAddressEntity getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(CustomerAddressEntity orderAddress) {
        this.orderAddress = orderAddress;
    }

    public Orders() {
        this.status = "pending";
    }
}
