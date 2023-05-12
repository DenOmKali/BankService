package org.geekhub.denis.manager;

import lombok.RequiredArgsConstructor;
import org.geekhub.denis.model.CurrencyModel;
import org.geekhub.denis.parser.CurrencyParser;
import org.springframework.stereotype.Component;

/**
 * @author Apilat Denis
 * Date :30.04.2023
 * Time :12:30
 * Project Name :gh-hw-denis-apilat
 */

@Component
@RequiredArgsConstructor
public class CurrencyManager {
    private final CurrencyParser currencyParser;

    public CurrencyModel getExchangeRates() {
        return currencyParser.getExchangeRates();
    }
}
