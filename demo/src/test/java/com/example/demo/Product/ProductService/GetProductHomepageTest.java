package com.example.demo.Product.ProductService;

import com.example.demo.DTO.ProductDto.DetailProductDAO;
import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetProductHomepageTest {

    @Mock
    private ProductHomepage productHomepage;

    @InjectMocks
    private GetProductHomepageService getProductHomepageService;

    private ProductEntity product1;
    private ProductEntity product2;

    private DetailProductDAO product3;

    private DetailProductDAO product4;
    List<ProductEntity> listProduct;

    List<DetailProductDAO> listProductResult;

    List<ProductEntity> listEmpty;

    @BeforeEach
    public void setUp() {
        product1 = new ProductEntity();
        product1.setId(1L);
        product1.setTitle("Di qua mua nho");
        product1.setGenre("Van hoc");
        product1.setUrlImage("Link.com");
        product1.setPrice(BigDecimal.valueOf(23));

        product2 = new ProductEntity();
        product2.setId(2L);
        product2.setTitle("Con lai gi ");
        product2.setGenre("Tieu thuyet");
        product2.setUrlImage("Link.com");
        product2.setPrice(BigDecimal.valueOf(33));

        listEmpty = new ArrayList<>();

        listProduct = new ArrayList<>();
        listProduct.add(product1);
        listProduct.add(product2);

    }


    @Test
    public void GetProductHomepageTest_TestThrowException_ReturnRuntimeException(){
        when(productHomepage.findTop8ByOrderByIdAsc()).thenReturn(listEmpty);

        RuntimeException exception = assertThrows(RuntimeException.class,()->{
            getProductHomepageService.getProductHomepage();
        });
        assertEquals("Can not find product",exception.getMessage());
        assertTrue(listEmpty.isEmpty());
    }

    @Test
    public void GetProductHomepageTest_TestHasReturnListProduct_ReTurnListProduct(){
        when(productHomepage.findTop8ByOrderByIdAsc()).thenReturn(listProduct);

        List<DetailProductDAO> resultProduct = getProductHomepageService.getProductHomepage();


        assertFalse(resultProduct.isEmpty());
        assertEquals(1L,resultProduct.get(0).getId());
        assertEquals(2L,resultProduct.get(1).getId());
        assertEquals("Di qua mua nho",resultProduct.get(0).getTitle());
        assertEquals("Con lai gi ",resultProduct.get(1).getTitle());

    }
}
