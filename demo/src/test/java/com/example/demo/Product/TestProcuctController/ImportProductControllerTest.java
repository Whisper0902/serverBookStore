package com.example.demo.Product.TestProcuctController;

import com.example.demo.DTO.ProductDto.DetailProductDto;
import com.example.demo.Product.ProductController.ProductController;
import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductService.ImportProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(ProductController.class)
public class ImportProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private ImportProductService importProductService;
    @InjectMocks
    private ProductController productController;

    private DetailProductDto product;
    private ProductEntity productEntity;

    public ImportProductControllerTest() throws Exception {
    }

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.openMocks(this);
        product = new DetailProductDto();
        product.setTitle("LẦN THEO DẤU CHỮ");
        product.setPublisher("THÔNG TẤN");
        product.setGenre("Văn học");
        product.setAuthor("TRỊNH HÙNG CƯỜNG");
        product.setDescription("Ngày nay, tận mắt nhìn lại một cuốn sách, tận tay chạm vào một tờ báo đã ra đời cách đây hơn một trăm năm, đa phần chúng ta không khỏi xúc động");
        product.setPrice(new BigDecimal("195.50"));
        product.setUrlImage("https://bizweb.dktcdn.net/thumb/large/100/363/455/products/lan-theo-dau-chu-01.jpg?v=1727773129520");
        product.setQuantity(new BigDecimal("40.00"));
        productEntity = new ProductEntity();
        productEntity.setTitle("LẦN THEO DẤU CHỮ");
        productEntity.setPublisher("THÔNG TẤN");
        productEntity.setGenre("Văn học");
        productEntity.setAuthor("TRỊNH HÙNG CƯỜNG");
        productEntity.setDescription("Ngày nay, tận mắt nhìn lại một cuốn sách, tận tay chạm vào một tờ báo đã ra đời cách đây hơn một trăm năm, đa phần chúng ta không khỏi xúc động");
        productEntity.setPrice(new BigDecimal("195.50"));
        productEntity.setUrlImage("https://bizweb.dktcdn.net/thumb/large/100/363/455/products/lan-theo-dau-chu-01.jpg?v=1727773129520");
        productEntity.setQuantity(new BigDecimal("40.00"));
    }
    @Test
    public void importProductController() throws Exception{
        given(importProductService.saveBook(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
    }

    ResultActions response = mockMvc.perform(post("/books/import")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)));

}
