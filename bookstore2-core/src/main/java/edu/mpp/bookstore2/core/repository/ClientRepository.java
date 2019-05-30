package edu.mpp.bookstore2.core.repository;

import edu.mpp.bookstore2.core.model.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends MyJpaRepository<Client, Long>, ClientRepositoryCustom {

    @Query("select distinct c from Client c")
    @EntityGraph(value = "clientWithTransactions", type = EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllWithTransactions();

    @Query("select distinct c from Client c")
    @EntityGraph(value = "clientWithTransactionsAndBook", type = EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllWithTransactionsAndBook();
}
