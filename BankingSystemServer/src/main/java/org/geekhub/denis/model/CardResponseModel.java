package org.geekhub.denis.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Apilat Denis
 * Date :06.05.2023
 * Time :23:14
 * Project Name :gh-hw-denis-apilat
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseModel {
    private Integer id;
    private String cardType;
    private String cardNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateCreation;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateExpiration;
    private String cvv2;
    private String pinCode;
    private Integer percentage;
    private Integer arrears;
    private String parentEmail;
    private Integer balance;
    private boolean valid;
    private Integer userId;
}