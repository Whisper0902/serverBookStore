package com.example.demo.Customer.CustomerService;

import com.example.demo.Customer.CustomerEntity.CustomerAddressEntity;
import com.example.demo.Customer.CustomerRepository.AddressCustomerRepo;
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
public class SaveInfoCustomerTest {
    @Mock
    private AddressCustomerRepo addressCustomerRepo;

    @InjectMocks
    private SaveInfoCustomerService saveInfoCustomerService;

    private CustomerAddressEntity customerAddress;

    private CustomerAddressEntity customerAddressNull;
    @BeforeEach
    public void setUp(){
        customerAddress = new CustomerAddressEntity();
        customerAddress.setId(1L);

        customerAddressNull = new CustomerAddressEntity();
    }

    @Test
    public void saveInfoCustomer_WhenNotSaveCustomerAddress_ReturnRuntimeException(){
        when(addressCustomerRepo.save(any(CustomerAddressEntity.class))).thenReturn(customerAddressNull);

        RuntimeException exception = assertThrows(RuntimeException.class,()->{
            saveInfoCustomerService.saveInfoCustomer(customerAddressNull);
        });

        Assertions.assertEquals("Can not save customer address",exception.getMessage());
    }

    @Test
    public void saveInfoCustomer_WhenSaveCustomerAddress_ReturnCustomerAddress(){
        when(addressCustomerRepo.save(any(CustomerAddressEntity.class))).thenReturn(customerAddress);

        CustomerAddressEntity result = saveInfoCustomerService.saveInfoCustomer(customerAddress);

        Assertions.assertEquals(1L,result.getId());
        Assertions.assertNotNull(result);
    }


}
