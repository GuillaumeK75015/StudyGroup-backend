package com.dauphine.blogger.controllers;

import com.dauphine.blogger.controllers.requestbody.CategoryRequestBody;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.net.URI;
import java.util.UUID;
import com.dauphine.blogger.services.exceptions.CategoryNotFoundByIdException;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam(required = false) String name){
        List<Category> categories = name == null || name.isBlank()
                ? categoryService.getAll()
                : categoryService.getAllByName(name);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable UUID id) throws CategoryNotFoundByIdException{
            Category category = categoryService.getById(id);
            return ResponseEntity.ok(category);
    }

    @PostMapping("")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryRequestBody categoryRequestBody){
        Category category = categoryService.create(categoryRequestBody.name());
        return ResponseEntity
                .created(URI.create("v1/categories/" + category.getId()))
                .body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategoryName(@PathVariable UUID id, @RequestBody CategoryRequestBody categoryRequestBody) throws CategoryNotFoundByIdException {
       Category category = categoryService.updateName(id,categoryRequestBody.name());
       return ResponseEntity
               .created(URI.create("v1/categories/" + category.getId()))
               .body(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable UUID id) throws CategoryNotFoundByIdException {
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}