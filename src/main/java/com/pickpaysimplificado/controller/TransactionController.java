package com.pickpaysimplificado.controller;

import com.pickpaysimplificado.dto.TransactionCreationDTO;
import com.pickpaysimplificado.dto.TransactionDTO;
import com.pickpaysimplificado.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public TransactionDTO createTransaction(@RequestBody TransactionCreationDTO transaction) throws Exception {
        return TransactionDTO.fromEntity(
                transactionService.createTransaction(transaction)
        );
    }
}