package edu.mpp.bookstore2.core.repository;

import edu.mpp.bookstore2.core.model.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MyJpaRepository<Book, Long>, BookRepositoryCustom {

    @Query("select distinct b from Book b")
    @EntityGraph(value = "bookWithTransactions", type = EntityGraph.EntityGraphType.LOAD)
    List<Book> findAllWithTransactions();

    @Query("select distinct b from Book b")
    @EntityGraph(value = "bookWithTransactionsAndClient", type = EntityGraph.EntityGraphType.LOAD)
    List<Book> findAllWithTransactionsAndClient();
}
