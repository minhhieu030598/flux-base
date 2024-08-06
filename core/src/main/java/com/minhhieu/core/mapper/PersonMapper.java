package com.minhhieu.core.mapper;

import com.minhhieu.core.model.Person;
import com.minhhieu.core.model.request.CreatePersonRequest;
import com.minhhieu.commons.model.response.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.OffsetDateTime;

@Mapper
public interface PersonMapper {

    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "createdAt")
    Person map(Long id, CreatePersonRequest source, OffsetDateTime createdAt);

    PersonResponse map(Person person);

}
