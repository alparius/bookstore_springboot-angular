package edu.mpp.bookstore2.core.repository;

import edu.mpp.bookstore2.core.model.Book;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> findAllWithTransactionsAndClientJPQL();
}
