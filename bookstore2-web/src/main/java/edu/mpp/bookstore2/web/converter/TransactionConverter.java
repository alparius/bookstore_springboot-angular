package edu.mpp.bookstore2.web.converter;

import edu.mpp.bookstore2.core.model.Transaction;
import edu.mpp.bookstore2.web.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter extends AbstractConverter<Transaction, TransactionDto> {

    @Autowired private BookConverter bookConverter;
    @Autowired private ClientConverter clientConverter;

    @Override
    public Transaction convertDtoToModel(TransactionDto dto) {
        return Transaction.builder()
                .client(null) // TODO
                .book(null) // TODO
                .details(dto.getDetails())
                .build();
    }

    @Override
    public TransactionDto convertModelToDto(Transaction transaction) {
        return TransactionDto.builder()
                .clientId(transaction.getClient().getId())
                .clientName(transaction.getClient().getName())
                .bookId(transaction.getBook().getId())
                .bookAuthor(transaction.getBook().getAuthor())
                .bookTitle(transaction.getBook().getTitle())
                .details(transaction.getDetails())
                .build();
    }
}