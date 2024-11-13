package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.SearchEntity;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Service
public class RemoveAccents {

    public String removeAccent(String input) {


        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    public SearchEntity removeAccent(SearchEntity searchEntity) {

            SearchEntity result = new SearchEntity();
            result.setTitle(removeAccent(searchEntity.getTitle()));
            result.setPublisher(removeAccent(searchEntity.getPublisher()));
            result.setGenre(removeAccent(searchEntity.getGenre()));
            result.setDescription(removeAccent(searchEntity.getDescription()));
            result.setBook_id(searchEntity.getBook_id());
            result.setAuthor(removeAccent(searchEntity.getAuthor()));
            return result;

    }
}
