package edu.nocountry.digitalbank.controller;

import edu.nocountry.digitalbank.model.category.CategoryData;
import edu.nocountry.digitalbank.model.category.CategoryDetails;
import edu.nocountry.digitalbank.service.CategoryService;
import edu.nocountry.digitalbank.util.JwtUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@CrossOrigin
@RequiredArgsConstructor
public class CategoryController {

    private final JwtUtils jwtUtils;
    private final CategoryService categoryService;

    @PostMapping("/save")
    @Transactional
    public ResponseEntity<CategoryDetails> saveCategory(@RequestHeader("Authorization") String token, @RequestBody @Valid CategoryData data) {
        String username = jwtUtils.extractUsername(token);
        var category = categoryService.saveCategory(username, data);

        return ResponseEntity.ok().body(category);
    }
}
