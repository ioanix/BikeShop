package ro.ubb.lab6.web.converter;


import ro.ubb.lab6.core.model.BaseEntity;
import ro.ubb.lab6.web.dto.BaseDto;


public interface ConverterBaseEntity<Model extends BaseEntity<Long>, Dto extends BaseDto>
        extends Converter<Model, Dto> {


}

