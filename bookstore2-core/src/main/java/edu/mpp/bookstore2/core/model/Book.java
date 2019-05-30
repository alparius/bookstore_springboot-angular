package edu.mpp.bookstore2.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@NamedEntityGraphs({
        @NamedEntityGraph(name = "bookWithTransactions",
                attributeNodes = @NamedAttributeNode(value = "transactions")),

        @NamedEntityGraph(name = "bookWithTransactionsAndClient",
                attributeNodes = @NamedAttributeNode(value = "transactions",
                        subgraph = "transactionWithClient"),
                subgraphs = @NamedSubgraph(name = "transactionWithClient",
                        attributeNodes = @NamedAttributeNode(value =
                                "client")))
})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
//@ToString(callSuper = true)
public class Book extends BaseEntity<Long> {
    private String ISBN;
    private String title;
    private String author;
    private String publisher;
    private int price;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

    public Set<Transaction> getTransactions() {
        this.transactions = this.transactions == null ? new HashSet<>() : this.transactions;
        return this.transactions;
    }

    public void addTransaction(Client client, String details) {
        Transaction transaction = Transaction.builder()
                .client(client)
                .book(this)
                .details(details)
                .build();
        this.transactions = this.getTransactions();
        this.transactions.add(transaction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return ISBN.equals(book.ISBN);
    }

    @Override
    public int hashCode() {
        return ISBN.hashCode();
    }

    /**
     * @return the fields of the Book entity as a String
     */
    @Override
    public String toString() {
        return "ID='" + this.getId() + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", price='" + price + '\'';
    }
}
