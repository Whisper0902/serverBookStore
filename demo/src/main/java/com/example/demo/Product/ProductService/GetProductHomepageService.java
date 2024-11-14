package com.example.demo.Product.ProductService;

import com.example.demo.DTO.ProductDto.GetProductDto;
import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class GetProductHomepageService {

    @Autowired
    private ProductHomepage productHomepage;

    public List<GetProductDto> getProductHomepage ()
    {

        List<ProductEntity> listDetail = productHomepage.findTop8ByOrderByIdAsc();
        List<GetProductDto> listProductDto = new ArrayList<>();
        for(var listDetails : listDetail)
        {
            GetProductDto getProductDto = new GetProductDto();
            getProductDto.setId(listDetails.getId());
            getProductDto.setTitle(listDetails.getTitle());
            getProductDto.setUrlImage(listDetails.getUrlImage());
            getProductDto.setPrice(listDetails.getPrice());
            listProductDto.add(getProductDto);
        }
        return listProductDto;
    }
}
