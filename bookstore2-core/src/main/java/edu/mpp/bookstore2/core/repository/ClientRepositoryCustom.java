package edu.mpp.bookstore2.core.repository;

import edu.mpp.bookstore2.core.model.Client;

import java.util.List;

public interface ClientRepositoryCustom {
    List<Client> findAllWithTransactionsAndBookJPQL();
}
