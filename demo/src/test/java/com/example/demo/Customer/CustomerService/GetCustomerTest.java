package com.example.demo.Customer.CustomerService;

import com.beust.ah.A;
import com.example.demo.Customer.CustomerEntity.CustomerAccountEntity;
import com.example.demo.Customer.CustomerEntity.CustomerAddressEntity;
import com.example.demo.Customer.CustomerRepository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCustomerTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private GetCustomerService getCustomerService;

    private CustomerAccountEntity customerAccount;

    private List<CustomerAccountEntity> customerAccountList;

    private List<CustomerAccountEntity> customerAccountListNull;

    @BeforeEach
    private void setUp(){
        customerAccount = new CustomerAccountEntity();
        customerAccount.setId(1L);

        customerAccountList = new ArrayList<>();
        customerAccountList.add(customerAccount);

        customerAccountListNull = new ArrayList<>();

    }

    @Test
    public void getCustomer_WhenNotFoundCustomer_ReturnRuntimeException(){
        when(customerRepository.findAll()).thenReturn(customerAccountListNull);

        RuntimeException exception = assertThrows(RuntimeException.class,()->{
            getCustomerService.getCustomer();
        });

        Assertions.assertEquals("Not found customer",exception.getMessage());
    }

    @Test
    public void getCustomer_WhenFoundCustomer_ReturnCustomerAccount(){
        when(customerRepository.findAll()).thenReturn(customerAccountList);

        List<CustomerAccountEntity> resultList = getCustomerService.getCustomer();

        Assertions.assertFalse(resultList.isEmpty());
        Assertions.assertEquals(1,resultList.get(0).getId());

    }

}
