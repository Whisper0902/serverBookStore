package com.example.demo.Product.ProductService;
import com.example.demo.DTO.ProductDto.DetailProductDto;
import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ImportProductService {

        @Autowired
        private BookRepository bookRepository;

        public ProductEntity saveBook(DetailProductDto importBookDto) {
            ProductEntity book = new ProductEntity();
            book.setTitle(importBookDto.getTitle());
            book.setPublisher(importBookDto.getPublisher());
            book.setGenre(importBookDto.getGenre());
            book.setAuthor(importBookDto.getAuthor());
            book.setDescription(importBookDto.getDescription());
            book.setPrice(importBookDto.getPrice());
            book.setUrlImage(importBookDto.getUrlImage());
            book.setQuantity(importBookDto.getQuantity());


            return bookRepository.save(book);
        }
    }

