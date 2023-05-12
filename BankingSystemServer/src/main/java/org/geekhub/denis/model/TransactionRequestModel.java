package org.geekhub.denis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Apilat Denis
 * Date :07.05.2023
 * Time :16:18
 * Project Name :gh-hw-denis-apilat
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestModel {
    private String sendCardNumber;
    private String receivedCardNumber;
    private Integer amount;
    private String message;
}
