package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetProductByFieldTest {
    @Mock
    private ProductHomepage productHomepage;

    @InjectMocks
    private GetListProductByFieldService getListProductByFieldService;

    Map<String, String> fieldGenre;
    Map<String, String> fieldAuthor;
    Map<String, String> fieldPublisher;
    Map<String, String> fieldNull;

    Map<String,String> fieldTestFalse;
    List<ProductEntity> listByGenre;
    List<ProductEntity> listByAuthor;
    List<ProductEntity> listByPublisher;

    List<ProductEntity> listProductEmpty;

    ProductEntity product1;
    ProductEntity product2;
    ProductEntity product3;


    @BeforeEach
    public void setUp() {
        fieldGenre = new HashMap<>();
        fieldGenre.put("genre", "Van hoc");

        fieldAuthor = new HashMap<>();
        fieldAuthor.put("author", "Nha Nam");

        fieldPublisher = new HashMap<>();
        fieldPublisher.put("publisher", "Kim Dong");

        fieldNull = new HashMap<>();
        fieldNull.put(null, null);
        fieldTestFalse = new HashMap<>();
        fieldTestFalse.put("genre","Van hoc hien dai");




        product1 = new ProductEntity();
        product1.setId(1L);
        product1.setTitle("Duong vao tim em");
        product1.setGenre("Van hoc");
        product1.setAuthor("Nha Nam");
        product1.setPublisher("Tuoi tre");

        product2 = new ProductEntity();
        product2.setId(2L);
        product2.setTitle("Con duong mua");
        product2.setGenre("Van hoc");
        product2.setAuthor("Nha Nam");
        product2.setPublisher("Kim Dong");

        product3 = new ProductEntity();
        product3.setId(3L);
        product3.setTitle("Doi la the thoi");
        product3.setGenre("Truyen ngan");
        product3.setAuthor("Nam Cao");
        product3.setPublisher("Kim Dong");


        listByGenre = new ArrayList<>();
        listByGenre.add(product1);
        listByGenre.add(product2);

        listByAuthor = new ArrayList<>();
        listByAuthor.add(product1);
        listByAuthor.add(product2);

        listByPublisher = new ArrayList<>();
        listByPublisher.add(product2);
        listByPublisher.add(product3);

        listProductEmpty = new ArrayList<>();

    }

    @Test
    public void GetListProductByGenreTest_TestValueNull_ReturnException() {
        GetListProductByFieldService TestValueNull = new GetListProductByFieldService();
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
        {
            TestValueNull.getProductByField(fieldNull);
        });

        assertEquals("Value input is null", exception.getMessage());
    }

    @Test
    public void GetListProductByGenreTest_TestSwitchInputGenre_ReturnListProduct() {
        when(productHomepage.findListProductByGenre(product1.getGenre())).thenReturn(listByGenre);

        List<ProductEntity> resultListGenre = getListProductByFieldService.getProductByField(fieldGenre);

        assertEquals(false, resultListGenre.isEmpty());
        assertEquals(product1, resultListGenre.get(0));
    }

    @Test
    public void GetListProductByAuthorTest_TestSwitchInputAuthor_ReturnListProduct() {
        when(productHomepage.findListProductByAuthor(product2.getAuthor())).thenReturn(listByAuthor);

        List<ProductEntity> resultListAuthor = getListProductByFieldService.getProductByField(fieldAuthor);

        assertEquals(false, resultListAuthor.isEmpty());
        assertEquals(product2, resultListAuthor.get(1));

    }

    @Test
    public void GetListProductByPublisherTest_TestSwitchInputPublisher_ReturnListProduct() {
        when(productHomepage.findListProductByPublisher(product2.getPublisher())).thenReturn(listByPublisher);

        List<ProductEntity> resultListPublisher = getListProductByFieldService.getProductByField(fieldPublisher);

        assertEquals(false, resultListPublisher.isEmpty());
        assertEquals(product2, resultListPublisher.get(0));
    }

    @Test
    public void TestExceptionWhenInputValueFalse_ReturnException() {

//        when(productHomepage.findListProductByGenre(null)).thenReturn(listProductEmpty);
//
//
//        RuntimeException exception = assertThrows(RuntimeException.class, ()-> {
//                getListProductByFieldService.getProductByField(fieldAuthor);
//        });
//        assertEquals("Not found product by fieldSearch",exception.getMessage());

        when(productHomepage.findListProductByGenre("Van hoc hien dai")).thenReturn(listProductEmpty); // Danh sách rỗng

        // Giả sử fieldSearch là một map chứa dữ liệu hợp lệ


        // Act & Assert: Kiểm tra xem ngoại lệ có được ném ra khi danh sách sản phẩm là rỗng
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            getListProductByFieldService.getProductByField(fieldTestFalse);
        });

        // Kiểm tra thông điệp ngoại lệ
        assertEquals("Not found product by fieldSearch", exception.getMessage());
    }


}
