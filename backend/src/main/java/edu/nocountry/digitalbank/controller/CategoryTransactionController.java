package edu.nocountry.digitalbank.controller;

import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransactionData;
import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransactionDataDelete;
import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransactionDetails;
import edu.nocountry.digitalbank.model.categoryTransaction.CategoryTransactionDetailsList;
import edu.nocountry.digitalbank.service.CategoryTransactionService;
import edu.nocountry.digitalbank.util.JwtUtils;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories-transaction")
@CrossOrigin
@RequiredArgsConstructor
public class CategoryTransactionController {

    private final JwtUtils jwtUtils;
    private final CategoryTransactionService categoryTransactionService;

    @PostMapping("/assign")
    @Transactional
    public ResponseEntity<CategoryTransactionDetails> assignCategoryToTransaction(@RequestHeader("Authorization") String token, @RequestBody @Valid CategoryTransactionData data) {
        String username = jwtUtils.extractUsername(token);
        var categoryTransaction = categoryTransactionService.saveCategoryTransaction(username, data);

        return ResponseEntity.ok().body(categoryTransaction);
    }

    @DeleteMapping("/delete")
    @Transactional
    public ResponseEntity<String> deleteCategoryTransaction(@RequestBody @Valid CategoryTransactionDataDelete data) {
        categoryTransactionService.deleteCategoryTransaction(data);

        return ResponseEntity.ok().body("Operación exitosa la transacción ya no tiene categoría");
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<CategoryTransactionDetailsList> listByCategory(@RequestHeader("Authorization") String token, @PathVariable String category) {
        String username = jwtUtils.extractUsername(token);
        var transactionByCategory = categoryTransactionService.listByCategory(username, category);

        return ResponseEntity.ok().body(transactionByCategory);
    }
}
