package com.example.demo.Product.ProductController;

import com.example.demo.DTO.ProductDto.DetailProductDto;
import com.example.demo.DTO.ProductDto.GetProductDto;
import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    private GetProductByFieldService getProductByFieldService;


    @GetMapping("/GetHomepageProduct")
    public ResponseEntity<List<GetProductDto>> getHomepageProduct() {
        List<GetProductDto> productHomepage = getProductHomepageService.getProductHomepage() ;
        return new ResponseEntity<>(productHomepage, HttpStatus.CREATED);
    }
    @GetMapping("/GetDetailProduct")
    public ResponseEntity<ProductEntity> getDetailProduct(@RequestParam Long id) {
        ProductEntity DetailProduct = getDetailProductService.getDetailProduct(id) ;
        return new ResponseEntity<>(DetailProduct, HttpStatus.CREATED);
    }

    @GetMapping("/GetProductByField")
    public ResponseEntity<List<ProductEntity>> getProductByField(@RequestParam Map<String,Object> field)
    {
        List<ProductEntity> productByField = getProductByFieldService.getProductByField(field);
        return new ResponseEntity<>(productByField, HttpStatus.CREATED);
    }

    @PostMapping("/DeleteById")
    public  ResponseEntity<ProductEntity> deleteProductById(@RequestParam Long id){
        ProductEntity deleteProduct = deleteProductByIdService.deleteProductById(id);
        return new ResponseEntity<>(deleteProduct, HttpStatus.ACCEPTED);
    }

    @PostMapping("/UpdateProduct")
    public ResponseEntity<ProductEntity> updateProductById(@RequestBody Map<String, Object> bookMapUpdate,@RequestParam Long id)
    {
        ProductEntity updateProduct = updateProductService.updateProduct(bookMapUpdate,id);
        return new ResponseEntity<>(updateProduct, HttpStatus.CREATED);
    }

    @PostMapping("/import")
    public ResponseEntity<ProductEntity> importBook(@RequestBody DetailProductDto importBookDto) {
        ProductEntity savedBook = importProductService.saveBook(importBookDto);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }


}


