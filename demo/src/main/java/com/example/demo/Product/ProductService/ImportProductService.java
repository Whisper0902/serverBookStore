package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductEntity.SearchEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import com.example.demo.Product.ProductRepository.SearchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportProductService {

    @Autowired
    private ProductHomepage productHomepage;

    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private RemoveAccents removeAccents;

    public ProductEntity saveBook(ProductEntity importBookDto) {
        if (importBookDto == null || importBookDto.getTitle() == null) {
            throw new IllegalArgumentException("Product import is null");
        }

        ProductEntity saveBook = productHomepage.save(importBookDto);
        if (saveBook.getId() == null) {
            throw new EntityNotFoundException("Can not import product");
        }

        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setAuthor(saveBook.getAuthor());
        searchEntity.setBook_id(saveBook);
        searchEntity.setDescription(saveBook.getDescription());
        searchEntity.setGenre(saveBook.getGenre());
        searchEntity.setPublisher(saveBook.getPublisher());
        searchEntity.setTitle(saveBook.getTitle());

        SearchEntity resultSearch = removeAccents.removeAccent(searchEntity);
        SearchEntity searchEntityResult = searchRepository.save(resultSearch);
        if(searchEntityResult.getId() == null)
        {
            throw new RuntimeException("Can not save search product");
        }

        return saveBook;
    }

}

