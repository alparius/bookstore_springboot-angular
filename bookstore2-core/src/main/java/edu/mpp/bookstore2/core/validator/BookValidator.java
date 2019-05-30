package edu.mpp.bookstore2.core.validator;

import edu.mpp.bookstore2.core.exception.ValidatorException;
import edu.mpp.bookstore2.core.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.function.Predicate;

@Component
public class BookValidator implements Validator<Book> {
    private static final Logger log = LoggerFactory.getLogger(BookValidator.class);

    private ArrayList<Predicate<Book>> arr;

    /**
     * Base constructor for a BookValidator
     * setting up the filter functions of the entity
     */
    public BookValidator() {
        Predicate<Book> isbn = b -> { String str = b.getISBN();
            return !((str.equals("")) || (!str.matches("^[a-zA-Z.1234567890]*$")) || str.length() <= 4 || !str.startsWith("ISBN")); };
        Predicate<Book> title = b-> { String str = b.getTitle();
            return !((str.equals("")) || (!str.matches("^[ ;:,'\"a-zA-Z.1234567890]*$"))); };
        Predicate<Book> aut_pub = b -> { String str = b.getAuthor(); String str2 = b.getPublisher();
            return !((str.equals("")) || (!str.matches("^[ a-zA-Z\\s]*$")))&&!((str2.equals("")) || (!str2.matches("^[ a-zA-Z\\s]*$"))); };
        Predicate<Book> price = b -> { int nr = b.getPrice();
            return !((nr <= 0 || nr > 9999)); };
        arr = new ArrayList<>();
        arr.add(isbn);
        arr.add(title);
        arr.add(aut_pub);
        arr.add(price);
    }

    /**
     * Validates the data in a {@code Book} entity.
     * @param entity
     *            must be not null,
     *            ISBN must not be empty and must be alphanumeric, must start with 'ISBN',
     *            Title must not be empty and must be alphanumeric + ;:,'"
     *            Author and Publisher must be not empty and must be alphabetic
     *            price must be greater than 0 or less than 9999
     * @throws ValidatorException
     *            if one of the entity's fields has invalid values.
     */
    @Override
    public void validate(Book entity) throws ValidatorException {
        log.trace("validating a book entity: {}", entity.toString());
        arr.stream()
                .map(s -> s.test(entity))
                .filter(s -> !s)
                .forEach(b -> {
                    log.debug("book is invalid, throwing ValidatorException");
                    throw new ValidatorException("incorrect book data");
                });
        log.trace("book is valid, returning from validation");
    }
}
