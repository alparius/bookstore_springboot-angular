package edu.mpp.bookstore2.core.service;

import edu.mpp.bookstore2.core.exception.AppException;
import edu.mpp.bookstore2.core.model.Book;
import edu.mpp.bookstore2.core.model.Client;
import edu.mpp.bookstore2.core.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppServiceInterface {

    /**
     * Adds a {@code Book} to the repo
     * @param book the {@code Book} to be added
     * @throws AppException if the book is not valid or if the id is null
     * @return the added book
     */
    public Book addBook(Book book);

    /**
     * Adds a {@code Client} to the repo
     * @param client the {@code Client} to be added
     * @return the added client
     * @throws AppException if the client is not valid or if the id is null
     */
    public Client addClient(Client client);

    /**
     * Adds a {@code Transaction} to the repo
     * @param client the id of the client
     * @param book the id of the book
     * @param details the details of the transaction
     * @throws AppException if the transaction is not valid or if the id is null
     */
    public void addTransaction(Long client, Long book, String details);

    /**
     * Deletes a {@code Book} from the repo
     * @param id Long, the id of the {@code Book} to be removed
     * @throws AppException if the id is null
     */
    public void deleteBook(Long id);

    /**
     * Deletes a {@code Client} from the repo
     * @param id Long, the id of the {@code Client} to be removed
     * @throws AppException if the id is null
     */
    public void deleteClient(Long id);

    /**
     * Update a {@code Book} from the repo
     * @param newbook  the {@code Book} to be updated
     * @param id Long, the id of the {@code Book} to be updated
     * @return the added book
     * @throws AppException if the book is not valid or if the id is null
     */
    public Book updateBook(Long id, Book newbook);

    /**
     * Update a {@code Client} from the   repo
     * @param newclient  the {@code Client} to be updated
     * @param id Long, the id of the {@code Client} to be updated
     * @return the added client
     * @throws AppException if the client is not valid or if the id is null
     */
    public Client updateClient(Long id, Client newclient);

//    /**
//     * Filters {@code Book} entities based on a {@code Predicate}
//     * @param pattern must not be null
//     * @return list of filtered Books
//     */
//    public List<Book> filterBooks(String pattern);
//
//    /**
//     * Filters {@code Client} entities based on a {@code Predicate}
//     * @param pattern must not be null
//     * @return list of filtered clients
//     */
//    public List<Client> filterClients(String pattern);

    /**
     * @return all stored {@code Book}s
     */
    public List<Book> getAllBooks();

    /**
     * @return all stored {@code Client}s
     */
    public List<Client> getAllClients();

    /**
     * @return all stored {@code Transaction}s
     */
    public List<Transaction> getAllTransactions();

//    /**
//     * @param size the size of a page
//     * @return the first batch of stored {@code Book} entities
//     */
//    public List<Book> getFirstBooks(int size);
//
//    /**
//     * @return a next batch of stored {@code Book} entities
//     */
//    public List<Book> getNextBooks();
//
//    /**
//     * @return the previous batch of stored {@code Book} entities
//     */
//    public List<Book> getPreviousBooks();
//
//    /**
//     * @param size the size of a page
//     * @return the first batch of stored {@code Client} entities
//     */
//    public List<Client> getFirstClients(int size);
//
//    /**
//     * @return a next batch of stored {@code Client} entities
//     */
//    public List<Client> getNextClients();
//
//    /**
//     * @return the previous batch of stored {@code Client} entities
//     */
//    public List<Client> getPreviousClients();
}
