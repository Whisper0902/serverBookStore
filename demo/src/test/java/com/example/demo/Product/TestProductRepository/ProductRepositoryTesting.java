package com.example.demo.Product.TestProductRepository;
import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

@DataJpaTest
public class ProductRepositoryTesting {
    @Autowired
    private ProductHomepage productHomepage;

    // 1 GetListProduct
    // 1 List<ProductEntity> listDetail = productHomepage.findTop8ByOrderByIdAsc();



            @Test
            public void ProductRepository_FindBy8Product_ReturnListProduct(){

                BigDecimal price = BigDecimal.valueOf(195);
                ProductEntity product = new ProductEntity();
                product.setTitle("LẦN THEO DẤU CHỮ");
                product.setPublisher("THÔNG TẤN");
                product.setGenre("Văn học");
                product.setAuthor("TRỊNH HÙNG CƯỜNG");
                product.setDescription("Ngày nay, tận mắt nhìn lại một cuốn sách, tận tay chạm vào một tờ báo đã ra đời cách đây hơn một trăm năm, đa phần chúng ta không khỏi xúc động");
                product.setPrice(new BigDecimal("195.50"));
                product.setUrlImage("https://bizweb.dktcdn.net/thumb/large/100/363/455/products/lan-theo-dau-chu-01.jpg?v=1727773129520");
                product.setQuantity(new BigDecimal("40.00"));

               ProductEntity saveBook = productHomepage.save(product);

                Assertions.assertThat(saveBook).isNotNull();
                Assertions.assertThat(saveBook.getId()).isEqualTo(0);
//        List<ProductEntity> listDetail = productHomepage.findTop8ByOrderByIdAsc();
//
//        Assertions.assertThat(listDetail).isNotNull();
//        Assertions.assertThat(listDetail.size()).isEqualTo(8);

            }


        }







