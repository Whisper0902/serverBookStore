package com.example.demo.ProductTest.TestProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import com.example.demo.Product.ProductService.DeleteProductByIdService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteProductByIdServiceTest {

    @Mock
    private ProductHomepage productHomepage;

    @InjectMocks
    private DeleteProductByIdService deleteProductByIdService;



    @Test
    public void ProductService_DeleteProduct_ReturnProduct() {
        long productId = 1;
        ProductEntity product = new ProductEntity();
        product.setTitle("LẦN THEO DẤU CHỮ");
        product.setPublisher("THÔNG TẤN");
        product.setGenre("Văn học");
        product.setAuthor("TRỊNH HÙNG CƯỜNG");
        product.setDescription("Ngày nay, tận mắt nhìn lại một cuốn sách, tận tay chạm vào một tờ báo đã ra đời cách đây hơn một trăm năm, đa phần chúng ta không khỏi xúc động");
        product.setPrice(new BigDecimal("195.50"));
        product.setUrlImage("https://bizweb.dktcdn.net/thumb/large/100/363/455/products/lan-theo-dau-chu-01.jpg?v=1727773129520");
        product.setQuantity(new BigDecimal("40.00"));
        when(productHomepage.findById(productId)).thenReturn(Optional.ofNullable(product));
        ProductEntity deleteProduct = deleteProductByIdService.deleteProductById(productId);

        Assertions.assertThat(deleteProduct).isNotNull();
        Assertions.assertThat(deleteProduct.getTitle()).isEqualTo("LẦN THEO DẤU CHỮ");

    }

}