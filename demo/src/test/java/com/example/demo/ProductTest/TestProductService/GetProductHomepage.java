package com.example.demo.ProductTest.TestProductService;

import com.example.demo.DTO.ProductDto.DetailProductDAO;
import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import com.example.demo.Product.ProductRepository.SearchRepository;
import com.example.demo.Product.ProductService.GetProductHomepageService;
import com.example.demo.Product.ProductService.SearchProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetProductHomepage {

    @Mock
    private ProductHomepage productHomepage;

    @InjectMocks
    private GetProductHomepageService getProductHomepageService;

    private ProductEntity product1;
    private ProductEntity product2;
    private ProductEntity product3;

    private DetailProductDAO detailProductDAO;
    private DetailProductDAO detailProductDAO1;
    private DetailProductDAO detailProductDAO2;
    private List<ProductEntity> products;

    private List<DetailProductDAO> listProductDAO;
    @BeforeEach
    public void setUp(){
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

        product3 = new ProductEntity();
        product3.setId(3L);
        product3.setTitle("THƯ MỚI");
        product3.setPublisher("MƯA");
        product3.setGenre("Tiểu thuyết");
        product3.setAuthor("HOÀNG VĂN TÂM");
        product3.setDescription("Câu chuyện về sự trở lại của một nhân vật huyền thoại, với những tình tiết bất ngờ.");
        product3.setPrice(new BigDecimal("150.00"));
        product3.setUrlImage("https://bizweb.dktcdn.net/thumb/large/100/363/455/products/thu-moi.jpg?v=1727773129520");
        product3.setQuantity(new BigDecimal("60.00"));

        // Thêm các sản phẩm vào danh sách
         products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);

        detailProductDAO = new DetailProductDAO();
        detailProductDAO.setId(1L);
        detailProductDAO.setTitle("LẦN THEO DẤU CHỮ");
        detailProductDAO.setUrlImage("https://bizweb.dktcdn.net/thumb/large/100/363/455/products/lan-theo-dau-chu-01.jpg?v=1727773129520");
        detailProductDAO.setPrice(new BigDecimal("195.50"));

        detailProductDAO1 = new DetailProductDAO();
        detailProductDAO1.setId(2L);
        detailProductDAO1.setTitle("NGHỆ THUẬT LÊN KỆ");
        detailProductDAO1.setUrlImage("https://bizweb.dktcdn.net/thumb/large/100/363/455/products/nghe-thuat-len-ke.jpg?v=1727773129520");
        detailProductDAO1.setPrice(new BigDecimal("220.75"));

        detailProductDAO2 = new DetailProductDAO();
        detailProductDAO2.setId(3L);
        detailProductDAO2.setTitle("THƯ MỚI");
        detailProductDAO2.setUrlImage("https://bizweb.dktcdn.net/thumb/large/100/363/455/products/thu-moi.jpg?v=1727773129520");
        detailProductDAO2.setPrice(new BigDecimal("150.00"));
        listProductDAO = new ArrayList<>();

        listProductDAO.add(detailProductDAO);

        listProductDAO.add(detailProductDAO1);

        listProductDAO.add(detailProductDAO2);

    }
    @Test
    public void testGetProductHomepage_ReturnListProduct(){

        when(productHomepage.findTop8ByOrderByIdAsc()).thenReturn(products);

        List<DetailProductDAO> getProduct = getProductHomepageService.getProductHomepage();

        Assertions.assertThat(getProduct).isNotNull();
        Assertions.assertThat(getProduct).size().isEqualTo(3);
        Assertions.assertThat(getProduct)
                .usingRecursiveComparison()
                .isEqualTo(listProductDAO);
        /// usingRecursiveComparison để so sánh nội dung thay vì so sánh so sánh tham chiếu bộ nhớ như mặc định của java

//
    }




}
