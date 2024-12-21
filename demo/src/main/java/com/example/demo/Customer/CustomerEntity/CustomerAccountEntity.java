package com.example.demo.Customer.CustomerEntity;

import com.example.demo.Order.OrderEntity.Orders;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Entity
@Table(name = "customer")
public class CustomerAccountEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    @OneToOne(mappedBy = "accountId", cascade = CascadeType.ALL, orphanRemoval = true)
    private CustomerAddressEntity customerAccount;

    @OneToOne(mappedBy = "customerId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Orders customerId;
    // Getter setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}


