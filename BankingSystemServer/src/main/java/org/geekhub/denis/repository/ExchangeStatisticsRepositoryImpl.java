package org.geekhub.denis.repository;

import org.geekhub.denis.entity.ExchangeStatisticsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * @author Apilat Denis
 * Date :09.05.2023
 * Time :8:46
 * Project Name :gh-hw-denis-apilat
 */

@Repository
public class ExchangeStatisticsRepositoryImpl implements ExchangeStatisticsRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveExchangeStatistics(ExchangeStatisticsEntity statisticsEntity) {
        jdbcTemplate.update(
                "INSERT INTO exchange_statistics (currency_name, currency_buy, currency_sell, currency_date) VALUES(?,?,?,?)",
                statisticsEntity.getCurrencyName(),
                statisticsEntity.getCurrencyBuy(),
                statisticsEntity.getCurrencySell(),
                statisticsEntity.getCurrencyDate());
    }
}
