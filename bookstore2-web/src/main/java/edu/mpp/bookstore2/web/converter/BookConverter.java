package edu.mpp.bookstore2.web.converter;

import edu.mpp.bookstore2.core.model.Book;
import edu.mpp.bookstore2.core.model.Transaction;
import edu.mpp.bookstore2.web.dto.BookDto;
import edu.mpp.bookstore2.web.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BookConverter extends AbstractConverterBaseEntity<Book, BookDto> {

    @Autowired TransactionConverter transactionConverter;

    @Override
    public Book convertDtoToModel(BookDto dto) {
        Book book = Book.builder()
                .ISBN(dto.getISBN())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .publisher(dto.getPublisher())
                .price(dto.getPrice())
                .build();
        book.setId(dto.getId());
        return book;
    }

    @Override
    public BookDto convertModelToDto(Book book) {
        Set<Transaction> transactionSet = book.getTransactions();
        Set<TransactionDto> transactionDtos = new HashSet<>();
        transactionSet.forEach(t -> transactionDtos.add(transactionConverter.convertModelToDto(t)));

        BookDto dto = BookDto.builder()
                .ISBN(book.getISBN())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .price(book.getPrice())
                .transactions(transactionDtos)
                .build();
        dto.setId(book.getId());
        return dto;
    }
}
