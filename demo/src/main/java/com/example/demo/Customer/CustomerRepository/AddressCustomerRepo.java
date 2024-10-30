package com.example.demo.Customer.CustomerRepository;

import com.example.demo.Customer.CustomerEntity.CustomerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressCustomerRepo extends JpaRepository<CustomerAddressEntity,Long> {
}
