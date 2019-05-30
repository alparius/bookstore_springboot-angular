package edu.mpp.bookstore2.web.dto;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookDto extends BaseDto {
    private String ISBN;
    private String title;
    private String author;
    private String publisher;
    private int price;
    private Set<TransactionDto> transactions;
}
