package edu.mpp.bookstore2.core.repository;

import edu.mpp.bookstore2.core.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ClientRepositoryImpl extends CustomRepositorySupport implements ClientRepositoryCustom {
    @Override
    public List<Client> findAllWithTransactionsAndBookJPQL() {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createQuery(
                "select distinct c from Client c " +
                        "left join fetch c.transactions t " +
                        "left join fetch t.book");
        return (List<Client>) query.getResultList();
    }
}
