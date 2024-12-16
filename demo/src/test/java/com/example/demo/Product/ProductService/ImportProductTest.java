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

    private ProductEntity resultProduct;

    private SearchEntity searchEntity;
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

        resultProduct = new ProductEntity();
        resultProduct.setId(1L);
        resultProduct.setTitle("Di qua mua nho");
        resultProduct.setGenre("Van hoc");
        resultProduct.setPublisher("Nha nam");
        resultProduct.setAuthor("Van Cao");
        resultProduct.setDescription("Vi noi nho dong day");

        searchEntity = new SearchEntity();
        searchEntity.setId(1L);
        searchEntity.setTitle("Di qua mua nho");
        searchEntity.setGenre("Van hoc");
        searchEntity.setPublisher("Nha nam");
        searchEntity.setAuthor("Van Cao");
        searchEntity.setDescription("Vi noi nho dong day");

        resultSearchEntity = new SearchEntity();
        resultSearchEntity.setId(1L);
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
        when(productHomepage.save(product)).thenReturn(resultProduct);
        when(removeAccents.removeAccent(searchEntity)).thenReturn(resultSearchEntity);
        when(searchRepository.save(resultSearchEntity)).thenReturn(resultSearchEntity);

        ProductEntity resultProduct = importProductService.saveBook(product);

        assertNotNull(resultProduct);


    }

}
