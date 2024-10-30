package com.example.demo.Customer.CustomerRepository;

import com.example.demo.Customer.CustomerEntity.CustomerAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerAccountEntity,Long> {
}
