package com.example.demo.Product.ProductService;

import com.example.demo.DTO.ProductDto.DetailProductDAO;
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

    public List<DetailProductDAO> getProductHomepage ()
    {
        List<ProductEntity> listDetail = productHomepage.findTop8ByOrderByIdAsc();

        if(listDetail.isEmpty())
        {
            throw new RuntimeException("Can not find product");
        }
        List<DetailProductDAO> listProductDAO = new ArrayList<>();
        for(var listDetails : listDetail)
        {
            DetailProductDAO getProductDto = new DetailProductDAO();
            getProductDto.setId(listDetails.getId());
            getProductDto.setTitle(listDetails.getTitle());
            getProductDto.setUrlImage(listDetails.getUrlImage());
            getProductDto.setPrice(listDetails.getPrice());
            listProductDAO.add(getProductDto);
        }
        return listProductDAO;
    }
}
