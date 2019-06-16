package edu.mpp.bookstore2.core.service;

import edu.mpp.bookstore2.core.exception.AppException;
import edu.mpp.bookstore2.core.exception.ValidatorException;
import edu.mpp.bookstore2.core.model.Book;
import edu.mpp.bookstore2.core.model.Client;
import edu.mpp.bookstore2.core.model.Transaction;
import edu.mpp.bookstore2.core.repository.BookRepository;
import edu.mpp.bookstore2.core.repository.ClientRepository;
import edu.mpp.bookstore2.core.validator.BookValidator;
import edu.mpp.bookstore2.core.validator.ClientValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AppServiceImpl implements AppService {
    private static final Logger log = LoggerFactory.getLogger(AppServiceImpl.class);

    @Autowired private BookValidator bookValidator;
    @Autowired private ClientValidator clientValidator;

    @Autowired private ClientRepository clientRepo;
    @Autowired private BookRepository bookRepo;

    @Override
    public List<Client> getClientsBasic() {
        return  clientRepo.findAll();
    }

    @Override
    public Book addBook(Book book) {
        log.trace("entering method: adding a book: {}", book.toString());
        try {
            bookValidator.validate(book);
            bookRepo.save(book);
        } catch(ValidatorException e) {
            log.trace("ValidatorException caught, throwing BookstoreException");
            throw new AppException(e.getMessage());
        }
        log.trace("returning from method: adding a book; successful");
        return book;
    }

    @Override
    public Client addClient(Client client) {
        log.trace("entering method: adding a client: {}", client.toString());
        try {
            clientValidator.validate(client);
            clientRepo.save(client);
        } catch(ValidatorException e) {
            log.trace("ValidatorException caught, throwing BookstoreException");
            throw new AppException(e.getMessage());
        }
        log.trace("returning from method: adding a client; successful");
        return client;
    }

    @Override
    @Transactional
    public void addTransaction(Long clientId, Long bookId, String details) {
        log.trace("entering method: adding a transaction: client:{}, book:{}, details:{}", clientId, bookId, details);
        Client client = clientRepo.findById(clientId).get();
        Book book = bookRepo.findById(bookId).get();
        client.addTransaction(book, details);
        //book.addTransaction(client, details);
        log.trace("returning from method: adding a transaction; successful");
    }

    @Override
    public void deleteBook(Long id) {
        log.trace("entering method: deleting a book with id: {}", id.toString());
        try {
//            List<Client> cs = clientRepo.findAll();
//            cs.forEach(c -> c.getTransactions().forEach(t -> {
//                if (t.getBook().getId().equals(id)) {
//                    System.out.println("yes");
//            })
//            });
            bookRepo.deleteById(id);
        } catch (IllegalArgumentException e) {
            log.trace("IllegalArgumentException caught, throwing BookstoreException");
            throw new AppException(e.getMessage());
        }
        log.trace("returning from method: deleting a book; successful");
    }

    @Override
    public void deleteClient(Long id) {
        log.trace("entering method: deleting a client with id: {}", id.toString());
        try {
//            List<Transaction> ts = transactionRepo.findAll();
//            ts.forEach(t -> {
//                if (t.getClient().getId().equals(id)) {
//                    transactionRepo.deleteById(t.getId());
//                }
//            });
            clientRepo.deleteById(id);
        } catch (IllegalArgumentException e) {
            log.trace("IllegalArgumentException caught, throwing BookstoreException");
            throw new AppException(e.getMessage());
        }
        log.trace("returning from method: deleting a client; successful");
    }

    @Override
    @Transactional
    public Book updateBook(Long id, Book newbook) throws ValidatorException {
        log.trace("entering method: updating a book with id: {}, book: {}", id.toString(), newbook.toString());
        try {
            bookValidator.validate(newbook);
            Book book = bookRepo.findById(id).get();
            book.setISBN(newbook.getISBN());
            book.setAuthor(newbook.getAuthor());
            book.setTitle(newbook.getTitle());
            book.setPublisher(newbook.getPublisher());
            book.setPrice(newbook.getPrice());
            bookRepo.save(book);
            log.debug("updating a book; successful");
        } catch (ValidatorException | IllegalArgumentException e) {
            log.trace("book invalid or id null, throwing BookstoreException");
            throw new AppException(e.getMessage());
        }
        log.trace("returning from method: updating a book");
        return newbook;
    }

    @Override
    @Transactional
    public Client updateClient(Long id, Client newclient) throws ValidatorException {
        log.trace("entering method: updating a client with id: {}, book: {}", id.toString(), newclient.toString());
        try {
            clientValidator.validate(newclient);
            Client client = clientRepo.findById(id).get();
            client.setEmail(newclient.getEmail());
            client.setName(newclient.getName());
            client.setPersonalId(newclient.getPersonalId());
            clientRepo.save(client);
            log.debug("updating a client; successful");
        } catch (ValidatorException | IllegalArgumentException e) {
            log.trace("client invalid or id null, throwing BookstoreException");
            throw new AppException(e.getMessage());
        }
        log.trace("returning from method: updating a client");
        return newclient;
    }

//    @Override
//    public List<Book> filterBooks(String pattern) {
//        log.trace("entering method: filtering books by pattern: {}", pattern);
//        Iterable<Book> Books = bookRepo.findAll();
//        List<Book> filteredBooks = new ArrayList<>();
//        Books.forEach(filteredBooks::add);
//        filteredBooks = filteredBooks.stream()
//                .filter(b -> b.getAuthor().contains(pattern) ||
//                        b.getISBN().contains(pattern) ||
//                        b.getPublisher().contains(pattern) ||
//                        b.getTitle().contains(pattern) ||
//                        Integer.toString(b.getPrice()).equals(pattern))
//                .collect(Collectors.toList());
//        log.trace("returning from method: filtering books; result size: {}", filteredBooks.size());
//        return filteredBooks;
//    }
//
//    @Override
//    public List<Client> filterClients(String pattern) {
//        log.trace("entering method: filtering clients by pattern: {}", pattern);
//        Iterable<Client> Clients = clientRepo.findAll();
//        List<Client> filteredClients = new ArrayList<>();
//        Clients.forEach(filteredClients::add);
//        filteredClients = filteredClients.stream()
//                .filter(c -> c.getName().contains(pattern) ||
//                        c.getEmail().contains(pattern) ||
//                        Integer.toString(c.getPersonalId()).equals(pattern))
//                .collect(Collectors.toList());
//        log.trace("returning from method: filtering clients; result size: {}", filteredClients.size());
//        return filteredClients;
//    }

    @Override
    public List<Book> getAllBooks() {
        log.trace("entering method: getAllBooks");
        List<Book> books = bookRepo.findAllWithTransactionsAndClient();
        return new ArrayList<>(books);
    }

    @Override
    public List<Client> getAllClients() {
        log.trace("entering method: getAllClients");
        List<Client> clients = clientRepo.findAllWithTransactionsAndBookJPQL();
        return new ArrayList<>(clients);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        log.trace("entering method: getAllTransactions");
        Set<Transaction> transactions = new HashSet<>();
        clientRepo.findAllWithTransactionsAndBook().forEach(c -> {
            transactions.addAll(c.getTransactions());
        });
        return new ArrayList<>(transactions);
    }

//    @Override
//    public List<Book> getFirstBooks(int size) {
//        log.trace("entering method: getting first batch of books of size: {}", size);
//        bookPage = bookRepo.findAll(new PageRequest(0, size));
//        List<Book> batch = bookPage.getContent().collect(Collectors.toList());
//        log.trace("returning from method: getting first batch of books; result size: {}", batch.size());
//        return batch;
//    }
//
//    @Override
//    public List<Book> getNextBooks() {
//        log.trace("entering method: getting next batch of books");
//        Pageable pr = bookPage.nextPageable();
//        bookPage = bookRepo.findAll(pr);
//        List<Book> batch = bookPage.getContent().collect(Collectors.toList());
//        log.trace("returning from method: getting next batch of books; result size: {}", batch.size());
//        return batch;
//    }
//
//    @Override
//    public List<Book> getPreviousBooks() {
//        log.trace("entering method: getting previous batch of books");
//        Pageable pr = bookPage.previousPageable();
//        bookPage = bookRepo.findAll(pr);
//        List<Book> batch = bookPage.getContent().collect(Collectors.toList());
//        log.trace("returning from method: getting previous batch of books; result size: {}", batch.size());
//        return batch;
//    }
//
//    @Override
//    public List<Client> getFirstClients(int size) {
//        log.trace("entering method: getting first batch of clients of size: {}", size);
//        clientPage = clientRepo.findAll(new PageRequest(0, size));
//        List<Client> batch = clientPage.getContent().collect(Collectors.toList());
//        log.trace("returning from method: getting first batch of clients; result size: {}", batch.size());
//        return batch;
//    }
//
//    @Override
//    public List<Client> getNextClients() {
//        log.trace("entering method: getting previous batch of clients");
//        Pageable pr = clientPage.nextPageable();
//        clientPage = clientRepo.findAll(pr);
//        List<Client> batch = clientPage.getContent().collect(Collectors.toList());
//        log.trace("returning from method: getting previous batch of clients; result size: {}", batch.size());
//        return batch;
//    }
//
//    @Override
//    public List<Client> getPreviousClients() {
//        log.trace("entering method: getting previous batch of clients");
//        Pageable pr = clientPage.previousPageable();
//        clientPage = clientRepo.findAll(pr);
//        List<Client> batch = clientPage.getContent().collect(Collectors.toList());
//        log.trace("returning from method: getting previous batch of clients; result size: {}", batch.size());
//        return batch;
//    }
}
