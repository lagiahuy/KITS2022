package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.Category;
import com.bezkoder.springjwt.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("/category")
    @PreAuthorize("hasRole('ADMIN')")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @DeleteMapping("/category/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map< String, Boolean >> deleteCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(category);
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
