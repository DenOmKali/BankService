package org.geekhub.denis.repository;

import org.geekhub.denis.model.CardResponseModel;
import org.geekhub.denis.entity.CreditCardEntity;
import org.geekhub.denis.entity.DebitCardEntity;
import org.geekhub.denis.entity.JuniorCardEntity;
import org.geekhub.denis.model.TransactionRequestModel;

import java.util.List;
import java.util.Optional;

/**
 * @author Apilat Denis
 * Date :06.05.2023
 * Time :15:43
 * Project Name :gh-hw-denis-apilat
 */

public interface CardRepository {
    List<CardResponseModel> findCardsByUserId(Integer userId);
    <S extends DebitCardEntity> void saveDebitCard(S debitCard);
    <S extends CreditCardEntity> void saveCreditCard(S creditCard);
    <S extends JuniorCardEntity> void saveJuniorCard(S juniorCard);
    void sendMoney(TransactionRequestModel requestModel, CardResponseModel sendResponseModel, CardResponseModel receiveResponseModel);
    void saveInterestArrears(CardResponseModel responseModel);
}