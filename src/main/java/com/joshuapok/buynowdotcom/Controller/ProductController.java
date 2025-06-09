package com.joshuapok.buynowdotcom.Controller;

import com.joshuapok.buynowdotcom.Dto.ProductDto;
import com.joshuapok.buynowdotcom.Response.ApiResponse;
import com.joshuapok.buynowdotcom.model.Product;
import com.joshuapok.buynowdotcom.service.product.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("@{api.prefix}/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<ApiResponse> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        List<ProductDto> convertedProducts = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Found", convertedProducts));
    }

    @GetMapping("product/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId){
        try {
            Product product = productService.getProductById(productId);
            ProductDto productDto = productService.convertToDto(product);
            return ResponseEntity.ok(new ApiResponse("Found", productDto));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error", e.getMessage()));
        }
    }

    //TODO: IMPLEMENT THE REST OF THE SERVICES
}
