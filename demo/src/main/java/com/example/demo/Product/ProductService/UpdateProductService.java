package com.example.demo.Product.ProductService;

import com.example.demo.Product.ProductEntity.ProductEntity;
import com.example.demo.Product.ProductRepository.ProductHomepage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Map;

@Service
public class UpdateProductService {
    @Autowired
    private ProductHomepage productHomepage;

    public ProductEntity updateProduct(Map<String , Object> updateProduct,Long id)
    {
        ProductEntity productEntity = productHomepage.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: "+id));
        Class<?> productClass = productEntity.getClass();

        for (Map.Entry<String, Object> entry : updateProduct.entrySet()) {
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();


            try {
                // Lấy Field từ tên thuộc tính
                Field field = productClass.getDeclaredField(fieldName);
                field.setAccessible(true);  // Cho phép truy cập vào các thuộc tính private

                // Kiểm tra kiểu dữ liệu của field và giá trị mới
                if (fieldValue != null && field.getType().isAssignableFrom(fieldValue.getClass())) {
                    field.set(productEntity, fieldValue);  // Cập nhật giá trị mới cho thuộc tính
                }
                else if (field.getType() == BigDecimal.class && fieldValue instanceof Number) {
                    // Chuyển đổi từ Number (như Double) sang BigDecimal
                    BigDecimal bigDecimalValue = BigDecimal.valueOf(((Number) fieldValue).doubleValue());
                    field.set(productEntity, bigDecimalValue);  // Cập nhật giá trị BigDecimal
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("Lỗi khi cập nhật thuộc tính: " + fieldName);
                e.printStackTrace();
            }
        }

        productHomepage.save(productEntity);
        return productEntity;


    }


}
