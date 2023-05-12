package org.geekhub.denis.service;

import lombok.RequiredArgsConstructor;
import org.geekhub.denis.entity.CardTransactionEntity;
import org.geekhub.denis.repository.CardTransactionRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Apilat Denis
 * Date :08.05.2023
 * Time :13:50
 * Project Name :gh-hw-denis-apilat
 */

@Service
@RequiredArgsConstructor
public class CardTransactionService {
    private final AuthenticationService authenticationService;

    private final CardTransactionRepositoryImpl transactionRepository;
    public List<CardTransactionEntity> findTransactionsByUserId() {
        return transactionRepository.findTransactionsByUserId(authenticationService.getCurrentUser().getId());
    }
}
