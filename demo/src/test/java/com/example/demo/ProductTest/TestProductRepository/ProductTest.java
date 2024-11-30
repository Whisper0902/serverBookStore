package com.example.demo.ProductTest.TestProductRepository;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import org.junit.Test;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

    @Autowired
    private ProductHomepage productHomepage;

    @Test
    public void saveTest()
    {
        ProductEntity product = new ProductEntity();
        product.setTitle("LẦN THEO DẤU CHỮ");
        product.setPublisher("THÔNG TẤN");
        product.setGenre("Văn học");
        product.setAuthor("TRỊNH HÙNG CƯỜNG");
        product.setDescription("Ngày nay, tận mắt nhìn lại một cuốn sách, tận tay chạm vào một tờ báo đã ra đời cách đây hơn một trăm năm, đa phần chúng ta không khỏi xúc động");
        product.setPrice(new BigDecimal("195.50"));
        product.setUrlImage("https://bizweb.dktcdn.net/thumb/large/100/363/455/products/lan-theo-dau-chu-01.jpg?v=1727773129520");
        product.setQuantity(new BigDecimal("40.00"));
        ProductEntity savedProduct = productHomepage.save(product);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.getId()).isGreaterThan(0);

    }
}
