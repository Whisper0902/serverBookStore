package com.example.demo.DTO.OrderDto;

import com.example.demo.Customer.CustomerEntity.CustomerAccountEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CartDto {

    private Long id;
    private String paymentMethod;
    private CustomerAccountEntity customerAccount;
    private BigDecimal totalCart;

    private CustomerAccountEntity CustomerId;
    private LocalDateTime createdTime;
    private LocalDateTime updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerAccountEntity getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(CustomerAccountEntity customerId) {
        CustomerId = customerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CustomerAccountEntity getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccountEntity customerAccount) {
        this.customerAccount = customerAccount;
    }

    public BigDecimal getTotalCart() {
        return totalCart;
    }

    public void setTotalCart(BigDecimal totalCart) {
        this.totalCart = totalCart;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }


}
