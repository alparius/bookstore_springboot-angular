package edu.mpp.bookstore2.core.validator;

import edu.mpp.bookstore2.core.exception.ValidatorException;

public interface Validator<T> {
    /**
     * Validates the data in an entity.
     * @param entity not null
     * @throws ValidatorException if one of the entity's fields has invalid values.
     */
    void validate(T entity) throws ValidatorException;
}
