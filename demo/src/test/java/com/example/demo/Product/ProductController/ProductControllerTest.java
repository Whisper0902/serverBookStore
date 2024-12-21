package com.example.demo.Product.ProductController;

import com.beust.ah.A;
import com.example.demo.DTO.ProductDto.DetailProductDAO;
import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductService.*;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private GetProductHomepageService getProductHomepageService;

    @Mock
    private GetProductById getProductById;

    @Mock
    private GetDetailProductService getDetailProductService;

    @Mock
    private GetListProductByFieldService getListProductByFieldService;

    @Mock
    private DeleteProductByIdService deleteProductByIdService;

    @Mock
    private UpdateProductService updateProductService;

    @Mock
    private ImportProductService importProductService;

    @Mock
    private SearchProductService searchProductService;

    @Mock
    private RemoveAccents removeAccents;

    @InjectMocks
    private ProductController productController;

    private List<DetailProductDAO> listProductHomepage;
    private List<DetailProductDAO> listNullProductHomepage;

    private DetailProductDAO detailProductDAO;
    private DetailProductDAO detailProductDAO1;

    private ProductEntity product;
    private ProductEntity productNull;

    private Map<String, String> mapField;
    private Map<String, String> mapNull;

    private List<ProductEntity> listProduct;

    private List<ProductEntity> listNull;


    @BeforeEach
    public void setUp() {

        detailProductDAO = new DetailProductDAO();
        detailProductDAO.setId(1L);
        detailProductDAO.setTitle("Di qua mua ha");

        detailProductDAO1 = new DetailProductDAO();
        detailProductDAO1.setId(2L);
        detailProductDAO1.setTitle("Di qua mua dong");

        listProductHomepage = new ArrayList<>();
        listProductHomepage.add(detailProductDAO);
        listProductHomepage.add(detailProductDAO1);

        listNullProductHomepage = new ArrayList<>();

        product = new ProductEntity();
        product.setId(1L);
        product.setAuthor("Nha nam");
        product.setTitle("Di qua mua mua");
        product.setGenre("Van hoc");

        productNull = new ProductEntity();

        mapField = new HashMap<>();
        mapField.put("genre", "Van hoc");

        mapNull = new HashMap<>();

        listProduct = new ArrayList<>();
        listProduct.add(product);

        listNull = new ArrayList<>();


    }

    @Test
    public void getHomepageProduct_WhenProductExist_ReturnListOfProduct() {
        when(getProductHomepageService.getProductHomepage()).thenReturn(listProductHomepage);

        ResponseEntity<List<DetailProductDAO>> responseEntity = productController.getHomepageProduct();

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(1L, responseEntity.getBody().get(0).getId());

    }

    @Test
    public void getHomepageProduct_WhenNoProductFound_ReturnNotFoundHttpStatus() {
        when(getProductHomepageService.getProductHomepage()).thenReturn(listNullProductHomepage);

        ResponseEntity<List<DetailProductDAO>> responseEntity = productController.getHomepageProduct();

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity);

    }

    @Test
    public void GetProductById_WhenFindProductByIdExist_ReturnProduct() {
        when(getProductById.getProductById(1L)).thenReturn(product);

        ResponseEntity<ProductEntity> resultProduct = productController.getProductById(1L);

        Assertions.assertEquals(HttpStatus.CREATED, resultProduct.getStatusCode());
        Assertions.assertEquals(1L, resultProduct.getBody().getId());
        Assertions.assertNotNull(resultProduct.getBody());

    }

    @Test
    public void getProductById_WhenNoFoundProductById_ReturnHttpStatusNotFound() {
        when(getProductById.getProductById(2L)).thenReturn(productNull);

        ResponseEntity<ProductEntity> responseEntity = productController.getProductById(2L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertNull(responseEntity.getBody());


    }

    @Test
    public void getDetailProduct_WhenFoundFindProductById_ReturnProduct() {
        when(getDetailProductService.getDetailProduct(1L)).thenReturn(product);

        ResponseEntity<ProductEntity> responseEntity = productController.getDetailProduct(1L);

        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertEquals(1L, responseEntity.getBody().getId());
    }

    @Test
    public void getDetailProduct_WhenNotExistProduct_ReturnHttpStatusNotFound() {
        when(getDetailProductService.getDetailProduct(2L)).thenReturn(productNull);

        ResponseEntity<ProductEntity> responseEntity = productController.getDetailProduct(2L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertNull(responseEntity.getBody());
    }

    @Test
    public void getListProductByField_WhenFindListProductByFieldTypeMap_ReturnListProduct() {
        when(getListProductByFieldService.getProductByField(mapField)).thenReturn(listProduct);

        ResponseEntity<List<ProductEntity>> responseEntity = productController.getListProductByField(mapField);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity);
        Assertions.assertFalse(responseEntity.getBody().isEmpty());
        Assertions.assertEquals(1L, responseEntity.getBody().get(0).getId());
    }

    @Test
    public void getListProductByField_WhenNoFoundListProduct_ReturnHttpStatusNotFound() {
        when(getListProductByFieldService.getProductByField(mapNull)).thenReturn(listNull);

        ResponseEntity<List<ProductEntity>> responseEntity = productController.getListProductByField(mapNull);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertEquals(null, responseEntity.getBody());

    }

    @Test
    public void deleteProductById_WhenFoundProductByIdAndDelete_ReturnHttpStatusCreate() {
        when(deleteProductByIdService.deleteProductById(1L)).thenReturn(product);

        ResponseEntity<ProductEntity> responseEntity = productController.deleteProductById(1L);

        Assertions.assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(1L, responseEntity.getBody().getId());

    }

    @Test
    public void deleteProductById_WhenNoFoundProductToDelete_ReturnHttpStatusNotFound() {
        when(deleteProductByIdService.deleteProductById(2L)).thenReturn(productNull);

        ResponseEntity<ProductEntity> responseEntity = productController.deleteProductById(2L);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertNull(responseEntity.getBody());
    }

    @Test
    public void updateProduct_WhenFoundProductToUpdate_ReturnHttpStatusCreate() {
        when(updateProductService.updateProduct(product)).thenReturn(product);

        ResponseEntity<ProductEntity> responseEntity = productController.updateProduct(product);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(1L, responseEntity.getBody().getId());

    }

    @Test
    public void updateProduct_WhenNoFoundProduct_ReturnHttpStatusNotFound() {
        when(updateProductService.updateProduct(productNull)).thenReturn(productNull);

        ResponseEntity<ProductEntity> responseEntity = productController.updateProduct(productNull);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertNull(responseEntity.getBody());
    }

    @Test
    public void importBook_WhenBookHasImport_ReturnHttpStatusCreate() {
        when(importProductService.saveBook(product)).thenReturn(product);

        ResponseEntity<ProductEntity> responseEntity = productController.importBook(product);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals(1L, responseEntity.getBody().getId());
    }

    @Test
    public void importBook_WhenNoImportBook_ReturnHttpStatusNotFound() {
        when(importProductService.saveBook(productNull)).thenReturn(productNull);

        ResponseEntity<ProductEntity> responseEntity = productController.importBook(productNull);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assertions.assertNull(responseEntity.getBody());
    }

    @Test
    public void searchProductEntity_WhenSearchProductByStrKeyword_ReturnListProduct() {
        when(removeAccents.removeAccents("Van hoc")).thenReturn("Van hoc");
        when(searchProductService.searchProduct(any(String.class))).thenReturn(listProduct);

        ResponseEntity<List<ProductEntity>> responseEntity = productController.searchProductEntity("Van hoc");

        Assertions.assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
        Assertions.assertNotNull(responseEntity.getBody());
        Assertions.assertEquals("Van hoc",responseEntity.getBody().get(0).getGenre());

    }

    @Test
    public void searchProductEntity_WhenNoSearchProduct_ReturnHttpStatusNotFound(){
        when(removeAccents.removeAccents(any(String.class))).thenReturn("Truyen ngan");
        when(searchProductService.searchProduct(any(String.class))).thenReturn(listNull);

        ResponseEntity<List<ProductEntity>> responseEntity = productController.searchProductEntity("Van hoc");

        Assertions.assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
        Assertions.assertNull(responseEntity.getBody());
    }


}

















