package edu.mpp.bookstore2.core.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@IdClass(TransactionPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Transaction implements Serializable {

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Id
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "notes")
    private String details;

    @Override
    public String toString() {
        return "Transaction{" +
                "client=" + client.getId() +
                ", book=" + book.getId() +
                ", details=" + details +
                '}';
    }
}
