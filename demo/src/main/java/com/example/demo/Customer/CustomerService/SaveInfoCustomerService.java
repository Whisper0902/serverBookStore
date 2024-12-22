package com.example.demo.Customer.CustomerService;

import com.example.demo.Customer.CustomerEntity.CustomerAddressEntity;
import com.example.demo.Customer.CustomerRepository.AddressCustomerRepo;
import com.example.demo.Customer.CustomerRepository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveInfoCustomerService {

    @Autowired
    private AddressCustomerRepo addressCustomerRepo;

    public CustomerAddressEntity saveInfoCustomer(CustomerAddressEntity customerAddress)
    {
       CustomerAddressEntity result = addressCustomerRepo.save(customerAddress);
        if(result.getId() == null){
            throw new RuntimeException("Can not save customer address");
        }
        return result;
    }
}
