package org.geekhub.denis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Apilat Denis
 * Date :09.05.2023
 * Time :8:39
 * Project Name :gh-hw-denis-apilat
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeStatisticsEntity {
    private Integer id;
    private String currencyName;
    private String currencyBuy;
    private String currencySell;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date currencyDate;
}
