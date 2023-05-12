package org.geekhub.denis.controller;

import lombok.RequiredArgsConstructor;
import org.geekhub.denis.entity.CardTransactionEntity;
import org.geekhub.denis.model.CardResponseModel;
import org.geekhub.denis.model.JuniorCardRequestModel;
import org.geekhub.denis.model.TransactionRequestModel;
import org.geekhub.denis.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.TimerTask;

/**
 * @author Apilat Denis
 * Date :06.05.2023
 * Time :15:49
 * Project Name :gh-hw-denis-apilat
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {
    private final CardService cardsService;

    @GetMapping("/userCards")
    public ResponseEntity<List<CardResponseModel>> getAllDebitCards() {
        return ResponseEntity.ok(cardsService.getAllCards());
    }

    @PostMapping("/saveDebitCard")
    public ResponseEntity<String> saveDebitCard(@RequestBody String pinCode) {
        return ResponseEntity.ok(cardsService.saveDebitCard(pinCode));
    }

    @PostMapping("/saveCreditCard")
    public ResponseEntity<String> saveCreditCard(@RequestBody String pinCode) {
        return ResponseEntity.ok(cardsService.saveCreditCard(pinCode));
    }

    @PostMapping("/saveJuniorCard")
    public ResponseEntity<String> saveJuniorCard(@RequestBody JuniorCardRequestModel requestModel) {
        return ResponseEntity.ok(cardsService.saveJuniorCard(requestModel));
    }

    @PutMapping("/sendMoney")
    public ResponseEntity<String> sendMoney(@RequestBody TransactionRequestModel transactionRequestModel) {
        return ResponseEntity.ok(cardsService.sendMoney(transactionRequestModel));
    }
}