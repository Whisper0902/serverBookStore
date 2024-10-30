package com.example.demo.Customer.CustomerEntity;
import com.example.demo.Order.OrderEntity.Orders;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;

@Repository
@Entity
@Table(name = "address")
public class CustomerAddressEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;

    private String address;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @OneToOne
    @JoinColumn(name = "account_id") // Cột khóa ngoại
    private CustomerAccountEntity accountId;

    @OneToOne
    @JoinColumn(name= "order_id")
    private Orders orderAddress;
    // Getter setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public CustomerAccountEntity getAccountId() {
        return accountId;
    }

    public void setAccountId(CustomerAccountEntity accountId) {
        this.accountId = accountId;
    }
}


