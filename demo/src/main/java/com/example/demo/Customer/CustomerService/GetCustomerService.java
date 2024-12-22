package com.example.demo.Customer.CustomerService;

import com.example.demo.Customer.CustomerEntity.CustomerAccountEntity;
import com.example.demo.Customer.CustomerRepository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class GetCustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public List<CustomerAccountEntity> getCustomer ()
    {
      List<CustomerAccountEntity> resultList = customerRepository.findAll();
      if(resultList.isEmpty())
      {
          throw new RuntimeException("Not found customer");
      }
      return resultList;
    }
}
