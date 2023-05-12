package org.geekhub.denis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Apilat Denis
 * Date :07.05.2023
 * Time :12:41
 * Project Name :gh-hw-denis-apilat
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardTransactionEntity {
    private Integer senderId;
    private Integer receiverId;
    private Integer senderCardId;
    private Integer receiverCardId;
    private Integer amount;
    private String message;
}