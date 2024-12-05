package com.example.demo.ProductTest.TestProductService;


import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductEntity.SearchEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import com.example.demo.Product.ProductRepository.SearchRepository;
import com.example.demo.Product.ProductService.SearchProductService;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.Console;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchProductTest {

    @Mock
    private ProductHomepage productHomepage;

    @Mock
    private SearchRepository searchRepository;

    @InjectMocks
    private SearchProductService searchProductService;

    private String keyword;
    private ProductEntity product1;
    private ProductEntity product2;
    private SearchEntity searchEntity;
    private SearchEntity searchEntity1;
    private List<SearchEntity> listSearch;
    private List<ProductEntity> listProduct;


    @BeforeEach
    public void setUp(){
        keyword = "Van hoc";



        product1 = new ProductEntity();
        product1.setId(1L);
        product1.setTitle("LẦN THEO DẤU CHỮ");
        product1.setPublisher("THÔNG TẤN");
        product1.setGenre("Văn học");
        product1.setAuthor("TRỊNH HÙNG CƯỜNG");
        product1.setDescription("Ngày nay, tận mắt nhìn lại một cuốn sách, tận tay chạm vào một tờ báo đã ra đời cách đây hơn một trăm năm, đa phần chúng ta không khỏi xúc động");
        product1.setPrice(new BigDecimal("195.50"));
        product1.setUrlImage("https://bizweb.dktcdn.net/thumb/large/100/363/455/products/lan-theo-dau-chu-01.jpg?v=1727773129520");
        product1.setQuantity(new BigDecimal("40.00"));

        product2 = new ProductEntity();
        product2.setId(2L);
        product2.setTitle("NGHỆ THUẬT LÊN KỆ");
        product2.setPublisher("VĂN HỌC");
        product2.setGenre("Nghệ thuật");
        product2.setAuthor("NGUYỄN THỊ NHƯ QUYỀN");
        product2.setDescription("Một cuốn sách quý giá về nghệ thuật, làm sáng tỏ những khía cạnh trong cuộc sống.");
        product2.setPrice(new BigDecimal("220.75"));
        product2.setUrlImage("https://bizweb.dktcdn.net/thumb/large/100/363/455/products/nghe-thuat-len-ke.jpg?v=1727773129520");
        product2.setQuantity(new BigDecimal("25.00"));

        searchEntity = new SearchEntity();
        searchEntity.setId(1L);
        searchEntity.setGenre("Văn học");
        searchEntity.setBook_id(product1);

        searchEntity1 = new SearchEntity();
        searchEntity1.setId(2L);
        searchEntity1.setPublisher("VĂN HỌC");
        searchEntity1.setBook_id(product2);

        listProduct = new ArrayList<>();
        listProduct.add(product1);
        listProduct.add(product2);

        listSearch = new ArrayList<>();
        listSearch.add(searchEntity);
        listSearch.add(searchEntity1);
        System.out.println(listSearch);
    }
    @Test
    public void SearchProductByKeyWord_ReturnListProduct(){
        when(searchRepository.findByKeyword(keyword)).thenReturn(listSearch);
        when(productHomepage.findById(Mockito.anyLong())).thenAnswer(invocation -> {
            Long id = invocation.getArgument(0);
            if (id.equals(1L)) return Optional.of(product1);
            if (id.equals(2L)) return Optional.of(product2);
            return Optional.empty(); // Trường hợp không tìm thấy
        });
        List<ProductEntity> listResultProduct =  searchProductService.searchProduct(keyword);

       Assertions.assertThat(listResultProduct).isNotNull();
//       Assertions.assertThat(listResultProduct.get(0).getGenre()).isEqualTo("Văn học");
//       Assertions.assertThat(listResultProduct.get(1).getPublisher()).isEqualTo("VĂN HỌC");
//       Assertions.assertThat(listResultProduct).size().isEqualTo(2);

    }

}
