package org.geekhub.denis.repository;

import org.geekhub.denis.model.CardResponseModel;
import org.geekhub.denis.entity.CreditCardEntity;
import org.geekhub.denis.entity.DebitCardEntity;
import org.geekhub.denis.entity.JuniorCardEntity;
import org.geekhub.denis.model.TransactionRequestModel;
import org.geekhub.denis.service.CardService;
import org.geekhub.denis.util.CreditCardInterest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Apilat Denis
 * Date :06.05.2023
 * Time :15:43
 * Project Name :gh-hw-denis-apilat
 */

@Repository
public class CardRepositoryImpl implements CardRepository{
    @Autowired
    public JdbcTemplate jdbcTemplate;
    public static final Integer CREDIT_CARD_LIMIT = 10000;

    @Override
    public List<CardResponseModel> findCardsByUserId(Integer userId) {
        return jdbcTemplate.query(
                "select * from cards where user_id = ?",
                new Object[]{userId},
                (rs, rowNum) ->
                        new CardResponseModel(
                                rs.getInt("id"),
                                rs.getString("card_type"),
                                rs.getString("card_number"),
                                rs.getDate("date_creation"),
                                rs.getDate("date_expiration"),
                                rs.getString("cvv2"),
                                rs.getString("pin_code"),
                                rs.getInt("percentage"),
                                rs.getInt("arrears"),
                                rs.getString("parent_email"),
                                rs.getInt("balance"),
                                rs.getBoolean("valid"),
                                rs.getInt("user_id")
                        )
        );
    }

    public CardResponseModel findCardByCard(String card) {
        return jdbcTemplate.queryForObject(
                "select * from cards where card_number = ?",
                new Object[]{card},
                (rs, rowNum) ->
                                new CardResponseModel(
                                        rs.getInt("id"),
                                        rs.getString("card_type"),
                                        rs.getString("card_number"),
                                        rs.getDate("date_creation"),
                                        rs.getDate("date_expiration"),
                                        rs.getString("cvv2"),
                                        rs.getString("pin_code"),
                                        rs.getInt("percentage"),
                                        rs.getInt("arrears"),
                                        rs.getString("parent_email"),
                                        rs.getInt("balance"),
                                        rs.getBoolean("valid"),
                                        rs.getInt("user_id")
                                )
        );
    }

    @Override
    public <S extends DebitCardEntity> void saveDebitCard(S cardEntity) {
        jdbcTemplate.update(
                "INSERT INTO cards (" +
                        "card_type," +
                        "card_number, " +
                        "date_creation, " +
                        "date_expiration, " +
                        "cvv2, " +
                        "pin_code," +
                        "balance, " +
                        "valid, " +
                        "user_id) " +
                        "VALUES(?,?,?,?,?,?,?,?,?)",
                cardEntity.getCardType(),
                cardEntity.getCardNumber(),
                cardEntity.getDateCreation(),
                cardEntity.getDateExpiration(),
                cardEntity.getCvv2(),
                cardEntity.getPinCode(),
                cardEntity.getBalance(),
                cardEntity.isValid(),
                cardEntity.getUserId()
        );
    }

    @Override
    public <S extends CreditCardEntity> void saveCreditCard(S debitCard) {
        jdbcTemplate.update(
                "INSERT INTO cards (" +
                        "card_type," +
                        "card_number, " +
                        "date_creation, " +
                        "date_expiration, " +
                        "cvv2, " +
                        "pin_code," +
                        "percentage," +
                        "arrears," +
                        "balance, " +
                        "valid, " +
                        "user_id) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?,?)",
                debitCard.getCardType(),
                debitCard.getCardNumber(),
                debitCard.getDateCreation(),
                debitCard.getDateExpiration(),
                debitCard.getCvv2(),
                debitCard.getPinCode(),
                debitCard.getPercentage(),
                debitCard.getArrears(),
                debitCard.getBalance(),
                debitCard.isValid(),
                debitCard.getUserId()
        );
    }

    @Override
    public <S extends JuniorCardEntity> void saveJuniorCard(S juniorCard) {
        jdbcTemplate.update(
                "INSERT INTO cards (" +
                        "card_type," +
                        "card_number, " +
                        "date_creation, " +
                        "date_expiration, " +
                        "cvv2, " +
                        "pin_code," +
                        "parent_email," +
                        "balance, " +
                        "valid, " +
                        "user_id) " +
                        "VALUES(?,?,?,?,?,?,?,?,?,?)",
                juniorCard.getCardType(),
                juniorCard.getCardNumber(),
                juniorCard.getDateCreation(),
                juniorCard.getDateExpiration(),
                juniorCard.getCvv2(),
                juniorCard.getPinCode(),
                juniorCard.getParentEmail(),
                juniorCard.getBalance(),
                juniorCard.isValid(),
                juniorCard.getUserId()
        );
    }

    @Override
    public void sendMoney(TransactionRequestModel requestModel, CardResponseModel sendResponseModel, CardResponseModel receiveResponseModel) {
        if (sendResponseModel.getBalance() <= 0) {
            throw new RuntimeException();
        }
        if (requestModel.getAmount() > sendResponseModel.getBalance()) {
            throw new RuntimeException();
        }
        if (sendResponseModel.getArrears() != null) {
            jdbcTemplate.update("UPDATE cards " +
                            "SET balance = balance - ? " +
                            "WHERE card_number = ? ",
                    requestModel.getAmount(),
                    requestModel.getSendCardNumber());
            jdbcTemplate.update("UPDATE cards " +
                            "SET arrears = arrears - ? " +
                            "WHERE card_number = ? ",
                    requestModel.getAmount(),
                    requestModel.getSendCardNumber());
            if (receiveResponseModel.getArrears() < requestModel.getAmount()) {
                    jdbcTemplate.update("UPDATE cards " +
                                    "SET arrears = arrears + ? " +
                                    "WHERE card_number = ? ",
                            requestModel.getAmount(),
                            requestModel.getReceivedCardNumber());
                    jdbcTemplate.update("UPDATE cards " +
                                    "SET balance = balance + ? " +
                                    "WHERE card_number = ? ",
                            requestModel.getAmount(),
                            requestModel.getReceivedCardNumber());
            } else if (receiveResponseModel.getCardType().equals("CREDIT") && receiveResponseModel.getBalance().equals(CREDIT_CARD_LIMIT)) {
                throw new RuntimeException();
            } else {
                jdbcTemplate.update("UPDATE cards " +
                                "SET balance = balance + ? " +
                                "WHERE card_number = ? ",
                        requestModel.getAmount(),
                        requestModel.getReceivedCardNumber());
            }
        }
    }

    @Override
    public void saveInterestArrears(CardResponseModel responseModel) {
        jdbcTemplate.update("UPDATE cards " +
                        "SET arrears = ? " +
                        "WHERE card_type = 'CREDIT' ",
                CreditCardInterest.getCardInterest(
                        responseModel.getPercentage(),
                        responseModel.getArrears()
                ));
    }

}