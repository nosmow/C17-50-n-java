package edu.nocountry.digitalbank.controller;

import edu.nocountry.digitalbank.model.transaction.TransactionData;
import edu.nocountry.digitalbank.model.transaction.TransactionListDetails;
import edu.nocountry.digitalbank.model.transaction.TransactionResponseSend;
import edu.nocountry.digitalbank.service.TransactionService;
import edu.nocountry.digitalbank.util.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/transactions")
@CrossOrigin()
@RequiredArgsConstructor
public class TransactionController {

    private final JwtUtils jwtUtils;
    private final TransactionService transactionService;

    @PostMapping("/send")
    public ResponseEntity<TransactionResponseSend> sendMoney(@RequestHeader("Authorization") String token, @RequestBody @Valid TransactionData data) {
        String username = jwtUtils.extractUsername(token);
        var transaction = transactionService.sendMoney(username, data);

        return ResponseEntity.ok().body(transaction);
    }

    @GetMapping("/home")
    public ResponseEntity<TransactionListDetails> getHome(@RequestHeader("Authorization") String token) {
        String username = jwtUtils.extractUsername(token);
        var transactions = transactionService.getUserTransactions(username, true);

        return ResponseEntity.ok().body(transactions);
    }

    @GetMapping("/all")
    public ResponseEntity<TransactionListDetails> getAll(@RequestHeader("Authorization") String token) {
        String username = jwtUtils.extractUsername(token);
        var transactions = transactionService.getUserTransactions(username, false);

        return ResponseEntity.ok().body(transactions);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<TransactionListDetails> getUserTransactionsDate(@RequestHeader("Authorization") String token, @PathVariable @Valid LocalDate date) {
        String username = jwtUtils.extractUsername(token);
        var transactions = transactionService.getUserTransactionsDate(username, date);

        return ResponseEntity.ok().body(transactions);
    }
}
