package com.example.demo.ProductTest.TestProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductEntity.SearchEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import com.example.demo.Product.ProductRepository.SearchRepository;
import com.example.demo.Product.ProductService.ImportProductService;
import com.example.demo.Product.ProductService.RemoveAccents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;
import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImportProduct {
    



        @Mock
        private ProductHomepage productHomepage;

        @Mock
        private SearchRepository searchRepository;

        @Mock
        private RemoveAccents removeAccents;

        @InjectMocks
        private ImportProductService importProductService;

        private ProductEntity product;
        private SearchEntity searchEntityReturn;
        private SearchEntity searchEntity;

        @BeforeEach
        void setUp() {
            // Khởi tạo các đối tượng cần thiết cho các test case
            product = new ProductEntity();
            product.setId(1L);
            product.setTitle("LẦN THEO DẤU CHỮ");
            product.setPublisher("THÔNG TẤN");
            product.setGenre("Văn học");
            product.setAuthor("TRỊNH HÙNG CƯỜNG");
            product.setDescription("Ngày nay, tận mắt nhìn lại một cuốn sách, tận tay chạm vào một tờ báo đã ra đời cách đây hơn một trăm năm, đa phần chúng ta không khỏi xúc động");
            product.setPrice(new BigDecimal("195.50"));
            product.setUrlImage("https://bizweb.dktcdn.net/thumb/large/100/363/455/products/lan-theo-dau-chu-01.jpg?v=1727773129520");
            product.setQuantity(new BigDecimal("40.00"));

            searchEntity=new SearchEntity();
            searchEntity.setTitle("LẦN THEO DẤU CHỮ");
            searchEntity.setPublisher("THÔNG TẤN");
            searchEntity.setGenre("Văn học");
            searchEntity.setDescription("Ngày nay, tận mắt nhìn lại một cuốn sách, tận tay chạm vào một tờ báo đã ra đời cách đây hơn một trăm năm, đa phần chúng ta không khỏi xúc động");
            searchEntity.setBook_id(product);
            searchEntity.setAuthor("TRỊNH HÙNG CƯỜNG");


            searchEntityReturn = new SearchEntity();
            searchEntityReturn.setTitle("LAN THEO DAU CHU");
            searchEntityReturn.setPublisher("THONG TAN");
            searchEntityReturn.setGenre("Van hoc");
            searchEntityReturn.setDescription("Ngay nay, tận mắt nhin lại một cuon sach, tận tay chạm vào một tờ báo đã ra đời cách đây hơn một trăm năm, đa phần chúng ta không khỏi xúc động");
            searchEntityReturn.setBook_id(product);
            searchEntityReturn.setAuthor("TRINH HUNG CUONG");
        }


       @Test
        public void testSaveBook_ShouldReturnSavedProduct() {
            // Mocking
            when(productHomepage.save(product)).thenReturn(product);
           when(removeAccents.removeAccent(any(SearchEntity.class))).thenReturn(searchEntityReturn);
            when(searchRepository.save(searchEntityReturn)).thenReturn(searchEntityReturn);

            ProductEntity savedProduct = importProductService.saveBook(product);

            // Assertions
            Assertions.assertThat(savedProduct).isNotNull();
            Assertions.assertThat(savedProduct.getId()).isEqualTo(1);
        }
    }


