package edu.mpp.bookstore2.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NamedEntityGraphs({
        @NamedEntityGraph(name = "clientWithTransactions",
                attributeNodes = @NamedAttributeNode(value = "transactions")),

        @NamedEntityGraph(name = "clientWithTransactionsAndBook",
                attributeNodes = @NamedAttributeNode(value = "transactions",
                        subgraph = "transactionWithBook"),
                subgraphs = @NamedSubgraph(name = "transactionWithBook",
                        attributeNodes = @NamedAttributeNode(value =
                                "book")))
})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
//@ToString(callSuper = true)
public class Client extends BaseEntity<Long> {
    private int personalId;
    private String name;
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> transactions;

    public Set<Transaction> getTransactions() {
        this.transactions = this.transactions == null ? new HashSet<>() : this.transactions;
        return this.transactions;
    }

    public void addTransaction(Book book, String details) {
        Transaction transaction = Transaction.builder()
                .client(this)
                .book(book)
                .details(details)
                .build();
        this.transactions = this.getTransactions();
        this.transactions.add(transaction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return personalId == client.personalId;
    }

    @Override
    public int hashCode() {
        return Integer.toString(personalId).hashCode();
    }

    /**
     * @return the fields of the Client entity as a String
     */
    @Override
    public String toString() {
        return "ID='" + this.getId() + '\'' +
                ", personalId='" + personalId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email +  '\'';
    }
}
