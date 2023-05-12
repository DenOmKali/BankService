package org.geekhub.denis.generator;

import java.util.Date;
import java.util.Random;

/**
 * @author Apilat Denis
 * Date :03.05.2023
 * Time :23:54
 * Project Name :gh-hw-denis-apilat
 */

public class CardDataGenerator {
    private static final Random RANDOM = new Random();

    public static String getRandomCardNumber() {
        long remainingPart = RANDOM.nextLong() % 100000000000000L;
        return String.valueOf(8180L * 100000000000000L + Math.abs(remainingPart));
    }
    public static Date getExpirationDate() {
        Date getExpirationDate = new Date();
        getExpirationDate.setYear(getExpirationDate.getYear() + 4);
        return getExpirationDate;
    }

    public static String getRandomCVV2() {
        return String.valueOf(RANDOM.nextInt(900) + 100);
    }
}
