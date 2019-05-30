package edu.mpp.bookstore2.core.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class TransactionPK implements Serializable {
    private Client client;
    private Book book;
}
