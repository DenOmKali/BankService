package org.geekhub.denis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Apilat Denis
 * Date :27.04.2023
 * Time :12:16
 * Project Name :gh-hw-denis-apilat
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyModel {
    private List<String> exchangeRateEUR = new ArrayList<>();
    private List<String> exchangeRateUSD = new ArrayList<>();
    private List<String> exchangeRatePLN = new ArrayList<>();
}