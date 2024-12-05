package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.SearchEntity;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Service
public class RemoveAccents {

    public String removeAccents(String input) {


        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    public SearchEntity removeAccent(SearchEntity searchEntity) {

            SearchEntity result = new SearchEntity();
            result.setTitle(removeAccents(searchEntity.getTitle()));
            result.setPublisher(removeAccents(searchEntity.getPublisher()));
            result.setGenre(removeAccents(searchEntity.getGenre()));
            result.setDescription(removeAccents(searchEntity.getDescription()));
            result.setBook_id(searchEntity.getBook_id());
            result.setAuthor(removeAccents(searchEntity.getAuthor()));
            return result;

    }
}
