package edu.mpp.bookstore2.core.repository;

import edu.mpp.bookstore2.core.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class BookRepositoryImpl extends CustomRepositorySupport implements BookRepositoryCustom {
    @Override
    public List<Book> findAllWithTransactionsAndClientJPQL() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct b from Book b " +
                        "left join fetch b.transactions t " +
                        "left join fetch t.client");
        return (List<Book>) query.getResultList();
    }
}
