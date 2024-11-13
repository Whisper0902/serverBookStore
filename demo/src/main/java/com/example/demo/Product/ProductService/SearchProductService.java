package com.example.demo.Product.ProductService;
import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductEntity.SearchEntity;
import com.example.demo.Product.ProductRepository.BookRepository;
import com.example.demo.Product.ProductRepository.SearchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchProductService {

    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private BookRepository bookRepository;


    public List<ProductEntity> searchProduct(String keyword)
    {
       List<SearchEntity> listSearch = searchRepository.findByKeyword(keyword);
       List<ProductEntity> listProductSearch = new ArrayList<>();
       for(var nSearch : listSearch)
       {
           ProductEntity result = bookRepository.findById(nSearch.getBook_id().getId()) .orElseThrow(() -> new EntityNotFoundException("Product with ID not found"));
            listProductSearch.add(result);
       }
       return listProductSearch;
    }
}
