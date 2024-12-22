package com.example.demo.Customer.CustomerController;

import com.example.demo.Customer.CustomerEntity.CustomerAccountEntity;
import com.example.demo.Customer.CustomerEntity.CustomerAddressEntity;
import com.example.demo.Customer.CustomerService.GetCustomerService;
import com.example.demo.Customer.CustomerService.SaveCustomerService;
import com.example.demo.Customer.CustomerService.SaveInfoCustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private SaveCustomerService saveCustomerService;
    @Mock
    private SaveInfoCustomerService saveInfoCustomerService;
    @Mock
    private GetCustomerService getCustomerService;

    @InjectMocks
    private CustomerController customerController;

    private List<CustomerAccountEntity> customerAccountList;

    private List<CustomerAccountEntity> customerAccountListNull;

    private CustomerAccountEntity customerAccount;

    private CustomerAccountEntity customerAccountNull;
    private CustomerAddressEntity customerAddress;

    private CustomerAddressEntity customerAddressNull;

    @BeforeEach
    public void setUp(){
        customerAccount = new CustomerAccountEntity();
        customerAccount.setId(1L);

        customerAccountNull = new CustomerAccountEntity();

        customerAccountList = new ArrayList<>();
        customerAccountList.add(customerAccount);

        customerAddress = new CustomerAddressEntity();
        customerAddress.setId(1L);

        customerAddressNull = new CustomerAddressEntity();

        customerAccountListNull = new ArrayList<>();

    }

    @Test
    public void getCustomer_CustomerController_WhenNotGetProduct_ReturnHttpNotAcceptable(){
        when(getCustomerService.getCustomer()).thenReturn(customerAccountListNull);

        ResponseEntity<List<CustomerAccountEntity>> resultList = customerController.getAllCustomer();

        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE,resultList.getStatusCode());
    }

    @Test
    public void getCustomer_WhenGetAllProduct_ReturnHttpCreate(){
        when(getCustomerService.getCustomer()).thenReturn(customerAccountList);

        ResponseEntity<List<CustomerAccountEntity>> resultList = customerController.getAllCustomer();

        Assertions.assertEquals(HttpStatus.CREATED,resultList.getStatusCode());
        Assertions.assertEquals(1L,resultList.getBody().get(0).getId());
    }

    @Test
    public  void saveCustomer_WhenNotSaveCustomer_ReturnHttpNotAcceptable(){
        when(saveCustomerService.saveCustomer(any(CustomerAccountEntity.class))).thenReturn(customerAccountNull);

        ResponseEntity<CustomerAccountEntity> responseEntity = customerController.saveCustomer(customerAccountNull);

        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE,responseEntity.getStatusCode());
    }

    @Test
    public void saveCustomer_WhenSaveProductIsTrue_ReturnHttpCreate(){
        when(saveCustomerService.saveCustomer(any(CustomerAccountEntity.class))).thenReturn(customerAccount);

        ResponseEntity<CustomerAccountEntity> responseEntity = customerController.saveCustomer(customerAccount);

        Assertions.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        Assertions.assertEquals(1L,responseEntity.getBody().getId());
    }

    @Test
    public void saveInfoCustomer_WhenNotSaveInfoCustomer_ReturnHttpNotAcceptable(){
        when(saveInfoCustomerService.saveInfoCustomer(any(CustomerAddressEntity.class))).thenReturn(customerAddressNull);

        ResponseEntity<CustomerAddressEntity> responseEntity = customerController.saveInfoCustomer(customerAddressNull);

        Assertions.assertEquals(HttpStatus.NOT_ACCEPTABLE,responseEntity.getStatusCode());

    }
    @Test
    public void saveInfoCustomer_WhenSaveInfoCustomer_ReturnHttpCreate(){
        when(saveInfoCustomerService.saveInfoCustomer(any(CustomerAddressEntity.class))).thenReturn(customerAddress);

        ResponseEntity<CustomerAddressEntity> responseEntity = customerController.saveInfoCustomer(customerAddress);

        Assertions.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        Assertions.assertEquals(1L,responseEntity.getBody().getId());

    }

}
