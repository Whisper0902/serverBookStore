package com.example.demo.Customer.CustomerService;

import com.example.demo.Customer.CustomerEntity.CustomerAccountEntity;
import com.example.demo.Customer.CustomerRepository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaveCustomerTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private SaveCustomerService saveCustomerService;

    private CustomerAccountEntity customerAccount;

    private CustomerAccountEntity customerAccountNull;

    @BeforeEach
    public  void setUp(){
        customerAccount = new CustomerAccountEntity();
        customerAccount.setId(1L);

        customerAccountNull = new CustomerAccountEntity();

    }

    @Test
    public void saveCustomer_WhenNotSaveCustomer_ReturnRuntimeException(){
        when(customerRepository.save(any(CustomerAccountEntity.class))).thenReturn(customerAccountNull);

        RuntimeException exception = assertThrows(RuntimeException.class,()->{
            saveCustomerService.saveCustomer(customerAccountNull);
        });

        Assertions.assertEquals("Can not save customer",exception.getMessage());
    }

    @Test
    public void saveCustomer_WhenSaveCustomer_ReturnCustomerAccount(){
        when(customerRepository.save(any(CustomerAccountEntity.class))).thenReturn(customerAccount);

        CustomerAccountEntity result = saveCustomerService.saveCustomer(customerAccount);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L,result.getId());

    }

}
