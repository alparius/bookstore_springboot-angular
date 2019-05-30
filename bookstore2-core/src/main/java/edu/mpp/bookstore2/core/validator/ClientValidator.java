package edu.mpp.bookstore2.core.validator;

import edu.mpp.bookstore2.core.exception.ValidatorException;
import edu.mpp.bookstore2.core.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.function.Predicate;

@Component
public class ClientValidator implements Validator<Client> {
    private static final Logger log = LoggerFactory.getLogger(ClientValidator.class);

    private ArrayList<Predicate<Client>> arr;

    /**
     * Base constructor for a ClientValidator
     * setting up the filter functions of the entity
     */
    public ClientValidator() {
        Predicate<Client> id = c -> c.getPersonalId() > 0;
        Predicate<Client> name = c -> { String str=c.getName();
            return !((str.equals("")) || (!str.matches("^[ a-zA-Z\\s]*$")));};
        Predicate<Client> email = c -> { String str=c.getEmail();
            return !( (!str.contains("@") || !str.matches("^[a-zA-Z.1234567890_@]*$")));};
        arr = new ArrayList<>();
        arr.add(id);
        arr.add(name);
        arr.add(email);
    }

    /**
     * Validates the data in a {@code Client} entity.
     * @param entity
     *            must be not null,
     *            personalID must be greater than 0
     *            Name must not be empty and must be alphabetic
     *            Email must not be empty and must be alphanumeric and must contain the character '@'
     * @throws ValidatorException
     *            if one of the entity's fields has invalid values.
     */
    @Override
    public void validate(Client entity) throws ValidatorException {
        log.trace("validating a client entity: {}", entity.toString());
        arr.stream()
                .map(s -> { return s.test(entity); })
                .filter(s -> !s)
                .forEach(b -> {
                    log.debug("client is invalid, throwing ValidatorException");
                    throw new ValidatorException("incorrect client data");
                });
        log.trace("client is valid, returning from validation");
    }
}
