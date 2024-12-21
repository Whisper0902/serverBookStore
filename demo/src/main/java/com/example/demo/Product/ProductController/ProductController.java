package com.example.demo.Product.ProductController;

import com.example.demo.DTO.ProductDto.DetailProductDAO;
import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class ProductController {
    @Autowired
    private ImportProductService importProductService;

    @Autowired
    private GetProductHomepageService getProductHomepageService;
    @Autowired
    private GetDetailProductService getDetailProductService;
    @Autowired
    private DeleteProductByIdService deleteProductByIdService;

    @Autowired
    private UpdateProductService updateProductService;
    @Autowired
    private GetListProductByFieldService getListProductByFieldService;

    @Autowired
    private SearchProductService searchProductService;

    @Autowired
    private GetProductById getProductById;

    @Autowired
    private RemoveAccents removeAccents;


    @GetMapping("/GetHomepageProduct")
    public ResponseEntity<List<DetailProductDAO>> getHomepageProduct() {
        List<DetailProductDAO> productHomepage = getProductHomepageService.getProductHomepage();
        if (productHomepage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productHomepage, HttpStatus.CREATED);
    }

    @GetMapping("/GetProductById")

    public ResponseEntity<ProductEntity> getProductById(@RequestParam Long id) {
        ProductEntity product = getProductById.getProductById(id);
        if (product.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/GetDetailProduct")
    public ResponseEntity<ProductEntity> getDetailProduct(@RequestParam Long id) {
        ProductEntity DetailProduct = getDetailProductService.getDetailProduct(id);
        if (DetailProduct.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(DetailProduct, HttpStatus.CREATED);
    }

    @GetMapping("/GetProductByField")
    public ResponseEntity<List<ProductEntity>> getListProductByField(@RequestParam Map<String, String> fieldSearch) {
        List<ProductEntity> productByField = getListProductByFieldService.getProductByField(fieldSearch);
        if (productByField.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productByField, HttpStatus.CREATED);
    }


    @PostMapping("/DeleteById")
    public ResponseEntity<ProductEntity> deleteProductById(@RequestParam Long id) {
        ProductEntity deleteProduct = deleteProductByIdService.deleteProductById(id);
        if (deleteProduct.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(deleteProduct, HttpStatus.ACCEPTED);
    }


    @PostMapping("/UpdateProduct")
    public ResponseEntity<ProductEntity> updateProduct(@RequestBody ProductEntity product) {
        ProductEntity updateProduct = updateProductService.updateProduct(product);
        if (updateProduct.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updateProduct, HttpStatus.CREATED);
    }

    @PostMapping("/import")
    public ResponseEntity<ProductEntity> importBook(@RequestBody ProductEntity importBookDto) {
        ProductEntity savedBook = importProductService.saveBook(importBookDto);
        if (savedBook.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/searchProduct")
    public ResponseEntity<List<ProductEntity>> searchProductEntity(@RequestParam String keyWord) {

        String valueKeyWord = removeAccents.removeAccents(keyWord);
        List<ProductEntity> listSearchProduct = searchProductService.searchProduct(valueKeyWord);
        if (listSearchProduct.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listSearchProduct, HttpStatus.CREATED);

    }


}


