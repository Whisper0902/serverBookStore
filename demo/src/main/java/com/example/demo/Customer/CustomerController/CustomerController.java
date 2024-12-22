package com.example.demo.Customer.CustomerController;

import com.example.demo.Customer.CustomerEntity.CustomerAccountEntity;
import com.example.demo.Customer.CustomerEntity.CustomerAddressEntity;
import com.example.demo.Customer.CustomerService.GetCustomerService;
import com.example.demo.Customer.CustomerService.SaveCustomerService;
import com.example.demo.Customer.CustomerService.SaveInfoCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private SaveCustomerService saveCustomerService;
    @Autowired
    private SaveInfoCustomerService saveInfoCustomerService;

    @Autowired
    private GetCustomerService getCustomerService;

    @GetMapping("/getCustomer")
    public ResponseEntity<List<CustomerAccountEntity>> getAllCustomer() {
        List<CustomerAccountEntity> getCustomer = getCustomerService.getCustomer();
        if (getCustomer.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(getCustomer, HttpStatus.CREATED);
    }


    @PostMapping("/saveCustomer")
    public ResponseEntity<CustomerAccountEntity> saveCustomer(@RequestBody CustomerAccountEntity customerAccount) {
        CustomerAccountEntity saveCustomer = saveCustomerService.saveCustomer(customerAccount);
        if (saveCustomer.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(saveCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/saveInfoCustomer")
    public ResponseEntity<CustomerAddressEntity> saveInfoCustomer(@RequestBody CustomerAddressEntity customerAccount) {
        CustomerAddressEntity saveInfoCustomers = saveInfoCustomerService.saveInfoCustomer(customerAccount);
        if (saveInfoCustomers.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(saveInfoCustomers, HttpStatus.CREATED);

    }
}
