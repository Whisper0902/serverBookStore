package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductEntity.SearchEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import com.example.demo.Product.ProductRepository.SearchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchProductTest {

    @Mock
    private SearchRepository searchRepository;

    @Mock
    private ProductHomepage productHomepage;

    @InjectMocks
    private SearchProductService searchProductService;

    private String keyWord;
    private String keyWordFalse;
    private String keyWordNull;
    private List<SearchEntity> listSearchNull;
    private ProductEntity product;
    private ProductEntity product1;
    private SearchEntity searchProduct;
    private SearchEntity searchProductOne;
    private List<SearchEntity> resultListSearch;

    @BeforeEach
    void setUp() {
        keyWord = "Van hoc";
        keyWordFalse = "Hien dai";
        keyWordNull = null;

        listSearchNull = new ArrayList<>();

        product = new ProductEntity();
        product.setId(1L);
        product.setTitle("Đi qua mùa nhớ");
        product.setGenre("Văn học");
        product.setPublisher("Nhã nam");
        product.setAuthor("Văn Cao");
        product.setDescription("Vì nỗi nhớ đong đầy");

        product1 = new ProductEntity();
        product1.setId(2L);
        product1.setTitle("Đi qua mùa nhớ");
        product1.setGenre("Văn học");
        product1.setPublisher("Nhã nam");
        product1.setAuthor("Văn Cao");
        product1.setDescription("Vì nỗi nhớ đong đầy");


        searchProductOne = new SearchEntity();
        searchProductOne.setBook_id(product);
        searchProductOne.setTitle("Đi qua mua nho");
        searchProductOne.setGenre("Van hoc");
        searchProductOne.setPublisher("Nha nam");
        searchProductOne.setAuthor("Van Cao");
        searchProductOne.setDescription("Vi noi nho đong đay");

        searchProduct = new SearchEntity();
        searchProduct.setBook_id(product1);
        searchProduct.setTitle("Đi qua mua nho");
        searchProduct.setGenre("Van hoc");
        searchProduct.setPublisher("Nha nam");
        searchProduct.setAuthor("Van Cao");
        searchProduct.setDescription("Vi noi nho đong đay");

        resultListSearch = new ArrayList<>();
        resultListSearch.add(searchProductOne);
        resultListSearch.add(searchProduct);

    }

    @Test
    public void SearchProductService_TestWhenKeyWordIsNull_ReturnRuntimeException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            searchProductService.searchProduct(keyWordNull);
        });

        assertEquals("Value input is null", exception.getMessage());
    }

    @Test
    public void SearchProductService_TestFindByKeyWord_ReturnListSearchProduct() {
        when(searchRepository.findByKeyword(keyWordFalse)).thenReturn(listSearchNull);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            searchProductService.searchProduct(keyWordFalse);
        });

        assertEquals("Not found product", exception.getMessage());
    }

    @Test
    public void SearchProductService_TestSearchProductByKeyword_ReturnListProductSearch() {
        when(searchRepository.findByKeyword(keyWord)).thenReturn(resultListSearch);
        when(productHomepage.findById(resultListSearch.get(0).getBook_id().getId())).thenReturn(Optional.ofNullable(product));
        when(productHomepage.findById(resultListSearch.get(1).getBook_id().getId())).thenReturn(Optional.ofNullable(product1));

        List<ProductEntity> resultListSearchProduct = searchProductService.searchProduct(keyWord);

        assertFalse(resultListSearchProduct.isEmpty());
        assertEquals(1,resultListSearchProduct.get(0).getId());
        assertEquals(2,resultListSearchProduct.get(1).getId());

    }
}
