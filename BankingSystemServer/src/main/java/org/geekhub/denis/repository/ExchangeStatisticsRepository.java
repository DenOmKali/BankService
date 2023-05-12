package org.geekhub.denis.repository;

import org.geekhub.denis.entity.ExchangeStatisticsEntity;

/**
 * @author Apilat Denis
 * Date :09.05.2023
 * Time :8:42
 * Project Name :gh-hw-denis-apilat
 */

public interface ExchangeStatisticsRepository {
    void saveExchangeStatistics(ExchangeStatisticsEntity statisticsEntity);
}