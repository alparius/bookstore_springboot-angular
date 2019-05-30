package edu.mpp.bookstore2.web.controller;

import edu.mpp.bookstore2.core.model.Book;
import edu.mpp.bookstore2.core.service.AppService;
import edu.mpp.bookstore2.web.converter.BookConverter;
import edu.mpp.bookstore2.web.dto.BookDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(ClientController.class);

    @Autowired private AppService service;
    @Autowired private BookConverter converter;

    @RequestMapping(value = "", method = RequestMethod.GET)
    List<BookDto> getAllBooks() {
        log.trace("getAllBooks --- method entered");
        List<Book> books = service.getAllBooks();
        List<BookDto> result = new ArrayList<>(converter.convertModelsToDtos(books));
        log.trace("getAllBooks: result={}", result);
        return result;
    }
    
//    @RequestMapping(value = "/filter={key}", method = RequestMethod.GET)
//    List<BookDto> filterBooks(@PathVariable String key) {
//        log.trace("filterBooks --- method entered");
//        List<Book> books = service.filterBooks(key);
//        List<BookDto> result = new ArrayList<>(converter.convertModelsToDtos(books));
//        log.trace("filterBooks: result={}", result);
//        return result;
//    }
    
    @RequestMapping(value = "", method = RequestMethod.POST)
    BookDto saveBook(@RequestBody BookDto dto) {
        log.trace("saveBook: dto={}", dto);
        Book saved = this.service.addBook(converter.convertDtoToModel(dto));
        BookDto result = converter.convertModelToDto(saved);
        log.trace("saveBook: result={}", result);
        return result;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    BookDto updateBook(@PathVariable Long id, @RequestBody BookDto dto) {
        log.trace("updateBook: id={},dto={}", id, dto);
        Book update = service.updateBook(id, converter.convertDtoToModel(dto));
        BookDto result = converter.convertModelToDto(update);
        log.trace("updateBook: result={}", result);
        return result;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteBook(@PathVariable Long id) {
        log.trace("deleteBook: id={}", id);
        service.deleteBook(id);
        log.trace("deleteBook --- method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
