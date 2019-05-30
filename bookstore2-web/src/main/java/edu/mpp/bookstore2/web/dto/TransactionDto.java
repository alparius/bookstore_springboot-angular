package edu.mpp.bookstore2.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class TransactionDto {
    private long clientId;
    private String clientName;

    private long bookId;
    private String bookAuthor;
    private String bookTitle;

    private String details;
}
