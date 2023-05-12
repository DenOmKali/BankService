package org.geekhub.denis.service;

import lombok.RequiredArgsConstructor;
import org.geekhub.denis.entity.ExchangeStatisticsEntity;
import org.geekhub.denis.enums.CurrencyType;
import org.geekhub.denis.manager.CurrencyManager;
import org.geekhub.denis.repository.ExchangeStatisticsRepositoryImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Apilat Denis
 * Date :09.05.2023
 * Time :8:54
 * Project Name :gh-hw-denis-apilat
 */

@Service
@RequiredArgsConstructor
public class ExchangeStatisticsService {
    private static final Date CURRENT_DATE = new Date();
    private final ExchangeStatisticsRepositoryImpl statisticsRepository;
    private final CurrencyManager currencyManager;

    @Scheduled(fixedDelay = 60000)
    public void saveExchangeStatistics() {
        ExchangeStatisticsEntity EURStatistics = ExchangeStatisticsEntity.builder()
                .currencyName(CurrencyType.EUR.toString())
                .currencyBuy(currencyManager.getExchangeRates().getExchangeRateEUR().get(0))
                .currencySell(currencyManager.getExchangeRates().getExchangeRateEUR().get(1))
                .currencyDate(CURRENT_DATE)
                .build();
        ExchangeStatisticsEntity USDStatistics = ExchangeStatisticsEntity.builder()
                .currencyName(CurrencyType.USD.toString())
                .currencyBuy(currencyManager.getExchangeRates().getExchangeRateUSD().get(0))
                .currencySell(currencyManager.getExchangeRates().getExchangeRateUSD().get(1))
                .currencyDate(CURRENT_DATE)
                .build();
        ExchangeStatisticsEntity PLNStatistics = ExchangeStatisticsEntity.builder()
                .currencyName(CurrencyType.PLN.toString())
                .currencyBuy(currencyManager.getExchangeRates().getExchangeRatePLN().get(0))
                .currencySell(currencyManager.getExchangeRates().getExchangeRatePLN().get(1))
                .currencyDate(CURRENT_DATE)
                .build();
        statisticsRepository.saveExchangeStatistics(EURStatistics);
        statisticsRepository.saveExchangeStatistics(USDStatistics);
        statisticsRepository.saveExchangeStatistics(PLNStatistics);
    }
}