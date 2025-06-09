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
        try {
            List<Category> categories = categoryService.getAllCategories();
            return ResponseEntity.ok(new ApiResponse("All Categories", categories));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("Error: ", e.getMessage()));
        }

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category){
        try {
            Category theCategory = categoryService.addCategory(category);
            return ResponseEntity.ok(new ApiResponse("Category Added", theCategory));
        } catch (EntityExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Error: ", e.getMessage()));
        }
    }

    @GetMapping("/category/{id}/category")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id){
        try{
            Category targetCategory = categoryService.findCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Category Found", targetCategory));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: ", e.getMessage()));
        }
    }

    @GetMapping("/category/{name}/category")
    public ResponseEntity<ApiResponse> findCategoryByName(@PathVariable String name){
        try{
            Category targetCategory = categoryService.findCategoryByName(name);
            return ResponseEntity.ok(new ApiResponse("Category Found", targetCategory));
        }catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: ", e.getMessage()));
        }
    }

    @PutMapping("/category/{id}/update")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody Category category){
        try {
            Category updatedCategory = categoryService.updateCategory(category, id);
            return ResponseEntity.ok(new ApiResponse("Category Updated", updatedCategory));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: ", e.getMessage()));
        }
    }

    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id){
        try {
            categoryService.findCategoryById(id);
            return ResponseEntity.ok(new ApiResponse("Category Deleted", null));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Error: ", null));
        }
    }

}
