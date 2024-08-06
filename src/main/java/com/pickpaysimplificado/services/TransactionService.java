package com.pickpaysimplificado.services;

import com.pickpaysimplificado.dto.TransactionCreationDTO;
import com.pickpaysimplificado.entities.Transaction;
import com.pickpaysimplificado.entities.User;
import com.pickpaysimplificado.enums.UserType;
import com.pickpaysimplificado.exceptions.UnauthorizedException;
import com.pickpaysimplificado.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    private final TransactionRepository repository;
    private final UserService userService;
    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;

    @Autowired
    public TransactionService(TransactionRepository repository, UserService userService, AuthorizationService authorizationService, NotificationService notificationService) {
        this.repository = repository;
        this.userService = userService;
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
    }

    @Transactional
    public Transaction createTransaction(TransactionCreationDTO transaction) throws Exception {
        //
        // Get users
        User sender = userService.findUserById(transaction.senderId());
        User receiver = userService.findUserById(transaction.receiverId());

        //
        // Validations
        if (sender.getUserType() == UserType.MERCHANT)
            throw new UnauthorizedException("Operation not permitted for the user");

        if (sender.getBalance().compareTo(transaction.value()) < 0)
            throw new UnauthorizedException("Insufficient balance to carry out transaction.");

        if (!authorizationService.authorizeTransaction())
            throw new UnauthorizedException("Unauthorized user.");

        //
        // Build transaction
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        //
        // Set balance
        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        //
        // Send notification
        notificationService.sendNotification(sender, "Transaction completed successfully\n");

        return repository.save(newTransaction);
    }
}
