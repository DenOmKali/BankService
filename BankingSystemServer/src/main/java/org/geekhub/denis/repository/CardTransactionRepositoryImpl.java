package org.geekhub.denis.repository;

import org.geekhub.denis.entity.CardTransactionEntity;
import org.geekhub.denis.entity.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Apilat Denis
 * Date :08.05.2023
 * Time :11:43
 * Project Name :gh-hw-denis-apilat
 */

@Repository
public class CardTransactionRepositoryImpl implements CardTransactionRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<CardTransactionEntity> allTransactions() {
        return jdbcTemplate.query(
                "SELECT * FROM card_transactions ",
                (rs, rowNum) ->
                        new CardTransactionEntity(
                                rs.getInt("sender_id"),
                                rs.getInt("receiver_id"),
                                rs.getInt("sender_card_id"),
                                rs.getInt("receiver_card_id"),
                                rs.getInt("amount"),
                                rs.getString("message")
                        )
                );
    }

    @Override
    public List<CardTransactionEntity> findTransactionsByUserId(Integer userId) {
        return jdbcTemplate.query(
                "SELECT * FROM card_transactions " +
                        "WHERE sender_id = ?",
                new Object[]{userId},
                (rs, rowNum) ->
                        new CardTransactionEntity(
                                rs.getInt("sender_id"),
                                rs.getInt("receiver_id"),
                                rs.getInt("sender_card_id"),
                                rs.getInt("receiver_card_id"),
                                rs.getInt("amount"),
                                rs.getString("message")
                        )
        );
    }

    @Override
    public void saveTransaction(CardTransactionEntity cardTransaction) {
        jdbcTemplate.update(
                "INSERT INTO card_transactions (" +
                        "sender_id," +
                        "receiver_id, " +
                        "sender_card_id, " +
                        "receiver_card_id, " +
                        "amount, " +
                        "message) " +
                        "VALUES(?,?,?,?,?,?) ",
                cardTransaction.getSenderId(),
                cardTransaction.getReceiverId(),
                cardTransaction.getSenderCardId(),
                cardTransaction.getReceiverCardId(),
                cardTransaction.getAmount(),
                cardTransaction.getMessage()
        );
    }
}
