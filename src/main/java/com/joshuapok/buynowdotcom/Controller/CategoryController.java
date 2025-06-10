package com.joshuapok.buynowdotcom.Controller;

import com.joshuapok.buynowdotcom.Response.ApiResponse;
import com.joshuapok.buynowdotcom.model.Category;
import com.joshuapok.buynowdotcom.service.Category.CategoryService;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllCategories(){
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("All Categories", categories));

    };

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){
            Category theCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Category Added", theCategory));

    };

    @GetMapping("/category/{id}/category")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
            Category targetCategory = categoryService.findCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Category Found", targetCategory));
    };

    @GetMapping("/category/{name}/category")
    public ResponseEntity<ApiResponse> findCategoryByName(@PathVariable String name){
            Category targetCategory = categoryService.findCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Category Found", targetCategory));
    };

    @PutMapping("/category/{id}/update")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category){
            Category updatedCategory = categoryService.updateCategory(category, id);
            return ResponseEntity.ok(new ApiResponse("Category Updated", updatedCategory));
    };

    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){
            categoryService.findCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Category Deleted", null));

    };

}
