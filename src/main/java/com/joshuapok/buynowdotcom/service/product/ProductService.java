package com.joshuapok.buynowdotcom.service.product;

import com.joshuapok.buynowdotcom.Dto.ProductDto;
import com.joshuapok.buynowdotcom.Requests.AddProductRequest;
import com.joshuapok.buynowdotcom.Requests.ProductUpdateRequest;
import com.joshuapok.buynowdotcom.model.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(AddProductRequest product);
    Product updateProduct(ProductUpdateRequest product, Long productId);
    Product getProductById(Long productId);
    void deleteProductById(Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByBrandAndName(String brand, String name);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);

    ProductDto convertToDto(Product product);

    List<ProductDto> getConvertedProducts(List<Product> products);
}
