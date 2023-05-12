package org.geekhub.denis.parser;

import org.geekhub.denis.model.CurrencyModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Apilat Denis
 * Date :27.04.2023
 * Time :12:16
 * Project Name :gh-hw-denis-apilat
 */

@Service
public class CurrencyParser {
    private static final String URL = "https://privatbank.ua";

    public CurrencyModel getExchangeRates() {
        Matcher matcher;
        try {
            Document document = Jsoup.connect(URL).get();
            Elements exchangeRatesDiv = document.select("div.wr_inner.course_type_container");
            String regex = "\\d+(\\.\\d+)?";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(exchangeRatesDiv.text());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int count = 0;
        List<String> exchangeRates = new ArrayList<>();
        while (matcher.find() && count < 6) {
            String number = matcher.group();
            exchangeRates.add(number);
            count++;
        }

        List<String> exchangeRateEUR = new ArrayList<>();
        List<String> exchangeRateUSD = new ArrayList<>();
        List<String> exchangeRatePLN = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            exchangeRateEUR.add(exchangeRates.get(i));
            exchangeRateUSD.add(exchangeRates.get(2+i));
            exchangeRatePLN.add(exchangeRates.get(4+i));
        }

        return new CurrencyModel(exchangeRateEUR, exchangeRateUSD, exchangeRatePLN);
    }

}