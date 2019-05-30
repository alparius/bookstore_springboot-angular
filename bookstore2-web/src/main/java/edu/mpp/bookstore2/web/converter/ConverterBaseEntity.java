package edu.mpp.bookstore2.web.converter;

import edu.mpp.bookstore2.core.model.BaseEntity;
import edu.mpp.bookstore2.web.dto.BaseDto;

public interface ConverterBaseEntity<Model extends BaseEntity<Long>, Dto extends BaseDto>
        extends Converter<Model, Dto> {

}
