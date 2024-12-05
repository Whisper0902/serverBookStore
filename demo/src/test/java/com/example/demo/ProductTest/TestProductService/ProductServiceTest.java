package com.example.demo.ProductTest.TestProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductEntity.SearchEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import com.example.demo.Product.ProductService.*;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductHomepage productHomepage;

    @InjectMocks
    private DeleteProductByIdService deleteProductByIdService;

    @InjectMocks
    private GetDetailProductService getDetailProductService;

    @InjectMocks
    private GetProductByFieldService getProductByFieldService;

    @InjectMocks
    private GetProductById getProductByIdTest;

    @InjectMocks
    private ImportProductService importProductService;


    private long productId;
    private ProductEntity product;


    private ProductEntity productReturn;
    private List<ProductEntity> listProduct;

    private SearchEntity searchEntity;

    private SearchEntity searchEntityReturn;


    @BeforeEach
    public void setUpProduct() {
        MockitoAnnotations.openMocks(this);

        // Khai báo và khởi tạo các giá trị cho product và productId
        productId = 1;
        product = new ProductEntity();
        product.setId(null);
        product.setTitle("LẦN THEO DẤU CHỮ");
        product.setPublisher("THÔNG TẤN");
        product.setGenre("Văn học");
        product.setAuthor("TRỊNH HÙNG CƯỜNG");
        product.setDescription("Ngày nay, tận mắt nhìn lại một cuốn sách, tận tay chạm vào một tờ báo đã ra đời cách đây hơn một trăm năm, đa phần chúng ta không khỏi xúc động");
        product.setPrice(new BigDecimal("195.50"));
        product.setUrlImage("https://bizweb.dktcdn.net/thumb/large/100/363/455/products/lan-theo-dau-chu-01.jpg?v=1727773129520");
        product.setQuantity(new BigDecimal("40.00"));

        productReturn = new ProductEntity();

        product.setId(1L);
        product.setTitle("LẦN THEO DẤU CHỮ");
        product.setPublisher("THÔNG TẤN");
        product.setGenre("Văn học");
        product.setAuthor("TRỊNH HÙNG CƯỜNG");
        product.setDescription("Ngày nay, tận mắt nhìn lại một cuốn sách, tận tay chạm vào một tờ báo đã ra đời cách đây hơn một trăm năm, đa phần chúng ta không khỏi xúc động");
        product.setPrice(new BigDecimal("195.50"));
        product.setUrlImage("https://bizweb.dktcdn.net/thumb/large/100/363/455/products/lan-theo-dau-chu-01.jpg?v=1727773129520");
        product.setQuantity(new BigDecimal("40.00"));

        searchEntity = new SearchEntity();
        searchEntity.setTitle("LẦN THEO DẤU CHỮ");
        searchEntity.setPublisher("THÔNG TẤN");
        searchEntity.setGenre("Văn học");
        searchEntity.setDescription("Ngày nay, tận mắt nhìn lại một cuốn sách, tận tay chạm vào một tờ báo đã ra đời cách đây hơn một trăm năm, đa phần chúng ta không khỏi xúc động");
        searchEntity.setBook_id(product);
        searchEntity.setAuthor("TRỊNH HÙNG CƯỜNG");

        searchEntityReturn = new SearchEntity();
        searchEntity.setTitle("LAN THEO DAU CHU");
        searchEntity.setPublisher("THONG TAN");
        searchEntity.setGenre("Van hoc");
        searchEntity.setDescription("Ngay nay, tận mắt nhin lại một cuon sach, tận tay chạm vào một tờ báo đã ra đời cách đây hơn một trăm năm, đa phần chúng ta không khỏi xúc động");
        searchEntity.setBook_id(product);
        searchEntity.setAuthor("TRINH HUNG CUONG");
    }

    @BeforeEach
    public void setUpProductByField() {
        listProduct = new ArrayList<>();
        ProductEntity product1 = new ProductEntity();
        product1.setTitle("Sách 1");
        product1.setPublisher("Nhà xuất bản 1");
        product1.setGenre("nhat ki");

        ProductEntity product2 = new ProductEntity();
        product2.setTitle("Sách 2");
        product2.setPublisher("Nhà xuất bản 2");
        product2.setGenre("nhat ki");

// Thêm các sản phẩm vào danh sách
        listProduct.add(product1);
        listProduct.add(product2);
    }

    @Test
    public void ProductService_DeleteProduct_ReturnProduct() {

        when(productHomepage.findById(productId)).thenReturn(Optional.ofNullable(product));
        ProductEntity deleteProduct = deleteProductByIdService.deleteProductById(productId);
        Assertions.assertThat(deleteProduct).isNotNull();
        Assertions.assertThat(deleteProduct.getTitle()).isEqualTo("LẦN THEO DẤU CHỮ");
        verify(productHomepage, Mockito.times(1)).findById(productId);
        verify(productHomepage, Mockito.times(1)).deleteById(productId);
    }

    @Test
    public void ProductService_GetDetailProduct_ReturnProduct() {

        when(productHomepage.findById(productId)).thenReturn(Optional.of(product));
        ProductEntity detailProduct = getDetailProductService.getDetailProduct(productId);
        Assertions.assertThat(detailProduct).isNotNull();
        Assertions.assertThat(detailProduct).isEqualTo(product);
        Assertions.assertThat(detailProduct.getTitle()).isEqualTo("LẦN THEO DẤU CHỮ");
        verify(productHomepage, Mockito.times(1)).findById(productId);
    }


    @Test
    public void ProductService_GetProductByField_ReturnListProduct() {
        Map<String, Object> field = new HashMap<>();
        field.put("genre", "nhat ki");

        when(productHomepage.findAllByGenre("nhat ki")).thenReturn(listProduct);
        List<ProductEntity> ListByField = getProductByFieldService.getProductByField(field);

        // check field
        Assertions.assertThat(ListByField).isNotNull();
        Assertions.assertThat(ListByField).isEqualTo(listProduct);
        Assertions.assertThat(ListByField).size().isGreaterThan(0);
        // check field1

        // check quanlity call repo
        verify(productHomepage, Mockito.times(1)).findAllByGenre("nhat ki");

    }

    @Test
    public void ProductService_GetProductById_ReturnProduct() {
        when(productHomepage.findById(productId)).thenReturn(Optional.of(product));
        ProductEntity productEntity = getProductByIdTest.getProductById(productId);
        Assertions.assertThat(productEntity).isNotNull();
    }

    @Test
    void ProductService_GetProductById_ThrowException() {
        // Mock data
        Long productId = 1L;

        // Mock behavior
        when(productHomepage.findById(productId)).thenReturn(Optional.empty());

        // Call the method and expect an exception
        Assertions.assertThatThrownBy(() -> {
                    getProductByIdTest.getProductById(productId); // Sử dụng ID không tồn tại
                }).isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Product with ID " + productId + " not found");
    }
}





