package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductEntity.SearchEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import com.example.demo.Product.ProductRepository.SearchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.h2.command.dml.MergeUsing;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ImportProductTest {
    @Mock
    private ProductHomepage productHomepage;

    @Mock
    private SearchRepository searchRepository;

    @Mock
    private RemoveAccents removeAccents;

    @InjectMocks
    private ImportProductService importProductService;

    private ProductEntity product;



    private SearchEntity nullSearchEntity;

    private SearchEntity resultSearchEntity;



    private ProductEntity productNull;


    @BeforeEach
    public void setUp(){

        product = new ProductEntity();
        product.setId(1L);
        product.setTitle("Di qua mua nho");
        product.setGenre("Van hoc");
        product.setPublisher("Nha nam");
        product.setAuthor("Van Cao");
        product.setDescription("Vi noi nho dong day");

        nullSearchEntity = new SearchEntity();

        resultSearchEntity = new SearchEntity();
        resultSearchEntity.setBook_id(product);
        resultSearchEntity.setTitle("Di qua mua nho");
        resultSearchEntity.setGenre("Van hoc");
        resultSearchEntity.setPublisher("Nha nam");
        resultSearchEntity.setAuthor("Van Cao");
        resultSearchEntity.setDescription("Vi noi nho dong day");


        productNull = new ProductEntity();

    }

    @Test
    public void ImportProductService_TestExceptionImportProduct_ReturnIllegalArgumentException(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->{
            importProductService.saveBook(productNull);
        });

        assertEquals("Product import is null",exception.getMessage());
    }

    @Test
    public void ImportProductService_TestSaveProduct_ReturnProductEntity(){
      when(productHomepage.save(product)).thenReturn(productNull);
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,()->{
            importProductService.saveBook(product);
        });
        assertEquals("Can not import product",exception.getMessage());

    }

    @Test
    public void ImportProductService_TestRemoveAccent_ReturnSearchEntityRemoveAccent(){
        when(productHomepage.save(product)).thenReturn(product);
        when(removeAccents.removeAccent(any(SearchEntity.class))).thenReturn(resultSearchEntity);
        when(searchRepository.save(resultSearchEntity)).thenReturn(resultSearchEntity);

        ProductEntity resultProduct = importProductService.saveBook(product);
        assertNotNull(resultProduct);
        assertEquals("Di qua mua nho",resultProduct.getTitle());
        assertEquals("Nha nam",resultProduct.getPublisher());

    }

    @Test
    public void ImportProductService_TestThrowExceptionSaveSearchProduct_ReturnRuntimeException(){
        when(productHomepage.save(product)).thenReturn(product);
        when(removeAccents.removeAccent(any(SearchEntity.class))).thenReturn(any(SearchEntity.class));
        when(searchRepository.save(resultSearchEntity)).thenReturn(nullSearchEntity);

        RuntimeException exception = assertThrows(RuntimeException.class,()->{
            importProductService.saveBook(product);

        });

        assertEquals("\nStrict stubbing argument mismatch. Please check:\n - this invocation of 'save' method:\n    searchRepository.save(null);\n    -> at com.example.demo.Product.ProductService.ImportProductService.saveBook(ImportProductService.java:42)\n - has following stubbing(s) with different arguments:\n    1. searchRepository.save(\n    SearchEntity{book_id=ProductEntity{title='Di qua mua nho', publisher='Nha nam', genre='Van hoc', author='Van Cao', description='Vi noi nho dong day', price=null, urlImage='null', quantity=null}, otherField=null}\n);\n      -> at com.example.demo.Product.ProductService.ImportProductTest.ImportProductService_TestThrowExceptionSaveSearchProduct_ReturnRuntimeException(ImportProductTest.java:110)\nTypically, stubbing argument mismatch indicates user mistake when writing tests.\nMockito fails early so that you can debug potential problem easily.\nHowever, there are legit scenarios when this exception generates false negative signal:\n  - stubbing the same method multiple times using 'given().will()' or 'when().then()' API\n    Please use 'will().given()' or 'doReturn().when()' API for stubbing.\n  - stubbed method is intentionally invoked with different arguments by code under test\n    Please use default or 'silent' JUnit Rule (equivalent of Strictness.LENIENT).\nFor more information see javadoc for PotentialStubbingProblem class.",exception.getMessage());

    }

}
