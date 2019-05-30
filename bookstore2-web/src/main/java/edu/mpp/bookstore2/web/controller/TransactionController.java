package edu.mpp.bookstore2.web.controller;

import edu.mpp.bookstore2.core.model.Transaction;
import edu.mpp.bookstore2.core.service.AppService;
import edu.mpp.bookstore2.web.converter.TransactionConverter;
import edu.mpp.bookstore2.web.dto.TransactionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired private AppService service;
    @Autowired private TransactionConverter converter;

    @RequestMapping(value = "", method = RequestMethod.GET)
    List<TransactionDto> getAllTransactions() {
        log.trace("getAllTransactions --- method entered");
        List<Transaction> transactions = service.getAllTransactions();
        List<TransactionDto> result = new ArrayList<>(converter.convertModelsToDtos(transactions));
        log.trace("getAllTransactions: result={}", result);
        return result;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    void saveTransaction(@RequestBody TransactionDto dto) {
        log.trace("saveTransaction: dto={}", dto);
        this.service.addTransaction(dto.getClientId(), dto.getBookId(), dto.getDetails());
    }
}
