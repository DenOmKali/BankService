package org.geekhub.denis.service;

import lombok.RequiredArgsConstructor;
import org.geekhub.denis.entity.CardTransactionEntity;
import org.geekhub.denis.entity.JuniorCardEntity;
import org.geekhub.denis.model.CardResponseModel;
import org.geekhub.denis.entity.CreditCardEntity;
import org.geekhub.denis.entity.DebitCardEntity;
import org.geekhub.denis.enums.CardType;
import org.geekhub.denis.generator.CardDataGenerator;
import org.geekhub.denis.model.JuniorCardRequestModel;
import org.geekhub.denis.model.TransactionRequestModel;
import org.geekhub.denis.repository.CardRepositoryImpl;
import org.geekhub.denis.repository.CardTransactionRepositoryImpl;
import org.geekhub.denis.repository.UserRepositoryImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

/**
 * @author Apilat Denis
 * Date :06.05.2023
 * Time :15:48
 * Project Name :gh-hw-denis-apilat
 */

@Service
@RequiredArgsConstructor
public class CardService {
    private static final Logger LOGGER = Logger.getGlobal();
    private final UserRepositoryImpl userRepository;
    private final CardRepositoryImpl cardRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final CardTransactionRepositoryImpl transactionRepository;

    public List<CardResponseModel> getAllCards() {
        return cardRepository.findCardsByUserId(authenticationService.getCurrentUser().getId());
    }

    public String saveDebitCard(String pinCode) {
        DebitCardEntity debitCard = DebitCardEntity.builder()
                .cardType(CardType.DEBIT.toString())
                .cardNumber(CardDataGenerator.getRandomCardNumber())
                .dateCreation(new Date())
                .dateExpiration(CardDataGenerator.getExpirationDate())
                .cvv2(CardDataGenerator.getRandomCVV2())
                .pinCode(passwordEncoder.encode(pinCode))
                .balance(0)
                .valid(true)
                .userId(authenticationService.getCurrentUser().getId())
                .build();
        cardRepository.saveDebitCard(debitCard);
        return "Debit Card Created";
    }

    public String saveCreditCard(String pinCode) {
        CreditCardEntity creditCard = CreditCardEntity.builder()
                .cardType(CardType.CREDIT.toString())
                .cardNumber(CardDataGenerator.getRandomCardNumber())
                .dateCreation(new Date())
                .dateExpiration(CardDataGenerator.getExpirationDate())
                .cvv2(CardDataGenerator.getRandomCVV2())
                .pinCode(passwordEncoder.encode(pinCode))
                .percentage(2)
                .arrears(0)
                .balance(10000)
                .valid(true)
                .userId(authenticationService.getCurrentUser().getId())
                .build();
        cardRepository.saveCreditCard(creditCard);
        return "Credit Card Created";
    }

    public String saveJuniorCard(JuniorCardRequestModel cardRequestModel) {
        JuniorCardEntity creditCard = JuniorCardEntity.builder()
                .cardType(CardType.JUNIOR.toString())
                .cardNumber(CardDataGenerator.getRandomCardNumber())
                .dateCreation(new Date())
                .dateExpiration(CardDataGenerator.getExpirationDate())
                .cvv2(CardDataGenerator.getRandomCVV2())
                .pinCode(passwordEncoder.encode(cardRequestModel.getPinCode()))
                .parentEmail(cardRequestModel.getParentEmail())
                .balance(0)
                .valid(true)
                .userId(authenticationService.getCurrentUser().getId())
                .build();
        cardRepository.saveJuniorCard(creditCard);
        return "Junior Card Created";
    }

    @Transactional
    public String sendMoney(TransactionRequestModel requestModel) {
        if (authenticationService.getCurrentUser().getId() != null) {
            cardRepository.sendMoney(
                    requestModel,
                    cardRepository.findCardByCard(requestModel.getSendCardNumber()),
                    cardRepository.findCardByCard(requestModel.getReceivedCardNumber())
            );
            CardTransactionEntity transaction = CardTransactionEntity.builder()
                    .senderId(userRepository.findUserByCardUserId(requestModel.getSendCardNumber()).get().getId())
                    .receiverId(userRepository.findUserByCardUserId(requestModel.getReceivedCardNumber()).get().getId())
                    .senderCardId(cardRepository.findCardByCard(requestModel.getSendCardNumber()).getId())
                    .receiverCardId(cardRepository.findCardByCard(requestModel.getReceivedCardNumber()).getId())
                    .amount(requestModel.getAmount())
                    .message(requestModel.getMessage())
                    .build();
            transactionRepository.saveTransaction(transaction);
            return "Send Money";
        }
        throw new RuntimeException();
    }

    @Scheduled(fixedDelay = 60000)
    public void updateArrears() {
        for (int i = 0; i < getAllCards().size(); i++) {
            if (getAllCards().get(i).getCardType().equals("CREDIT")) {
                LOGGER.info("SAVE ARREARS");
                cardRepository.saveInterestArrears(getAllCards().get(i));
            }
        }
    }

}
