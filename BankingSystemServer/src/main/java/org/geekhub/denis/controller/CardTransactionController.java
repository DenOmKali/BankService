package org.geekhub.denis.controller;

import lombok.RequiredArgsConstructor;
import org.geekhub.denis.entity.CardTransactionEntity;
import org.geekhub.denis.service.CardTransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Apilat Denis
 * Date :08.05.2023
 * Time :13:49
 * Project Name :gh-hw-denis-apilat
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class CardTransactionController {
    private final CardTransactionService transactionService;

    @GetMapping("/userTransactions")
    public ResponseEntity<List<CardTransactionEntity>> findTransactionsByUserId() {
        return ResponseEntity.ok(transactionService.findTransactionsByUserId());
    }
}
