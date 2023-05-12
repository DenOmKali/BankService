package org.geekhub.denis.repository;

import org.geekhub.denis.entity.CardTransactionEntity;
import org.geekhub.denis.entity.JuniorCardEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author Apilat Denis
 * Date :08.05.2023
 * Time :11:35
 * Project Name :gh-hw-denis-apilat
 */

public interface CardTransactionRepository {
    List<CardTransactionEntity> allTransactions();
    List<CardTransactionEntity> findTransactionsByUserId(Integer userId);
    void saveTransaction(CardTransactionEntity cardTransaction);
}