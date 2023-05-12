package org.geekhub.denis.util;

/**
 * @author Apilat Denis
 * Date :08.05.2023
 * Time :4:38
 * Project Name :gh-hw-denis-apilat
 */

public class CreditCardInterest {

    public static Integer getCardInterest(Integer percentage, Integer arrears) {
        return (arrears * percentage / 100) + arrears;
    }
}
