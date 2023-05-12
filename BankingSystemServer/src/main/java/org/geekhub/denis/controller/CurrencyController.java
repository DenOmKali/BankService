package org.geekhub.denis.controller;

import lombok.RequiredArgsConstructor;
import org.geekhub.denis.manager.CurrencyManager;
import org.geekhub.denis.model.CurrencyModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Apilat Denis
 * Date :27.04.2023
 * Time :12:36
 * Project Name :gh-hw-denis-apilat
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class CurrencyController {
    private final CurrencyManager currencyManager;

    @GetMapping("/rates")
    public ResponseEntity<CurrencyModel> getExchangeRates() {
        return ResponseEntity.ok(currencyManager.getExchangeRates());
    }
}
