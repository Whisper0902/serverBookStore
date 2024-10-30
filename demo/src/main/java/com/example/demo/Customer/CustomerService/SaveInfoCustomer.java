package com.example.demo.Customer.CustomerService;

import com.example.demo.Customer.CustomerEntity.CustomerAddressEntity;
import com.example.demo.Customer.CustomerRepository.AddressCustomerRepo;
import com.example.demo.Customer.CustomerRepository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveInfoCustomer {

    @Autowired
    private AddressCustomerRepo addressCustomerRepo;

    public CustomerAddressEntity saveInfoCustomerService(CustomerAddressEntity customerAddress)
    {
       return addressCustomerRepo.save(customerAddress);
    }
}
