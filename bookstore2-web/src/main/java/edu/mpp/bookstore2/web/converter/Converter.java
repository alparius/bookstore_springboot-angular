package edu.mpp.bookstore2.web.converter;

import edu.mpp.bookstore2.core.model.BaseEntity;
import edu.mpp.bookstore2.web.dto.BaseDto;

public interface Converter<Model, Dto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}