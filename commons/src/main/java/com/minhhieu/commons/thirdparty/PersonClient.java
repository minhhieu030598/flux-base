package com.minhhieu.commons.thirdparty;

import com.minhhieu.commons.model.filter.PersonFilter;
import com.minhhieu.commons.model.response.PersonResponse;
import com.minhhieu.commons.model.response.Response;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PersonClient {
    Mono<Response<List<PersonResponse>>> getPersons(PersonFilter filter, Integer page, Integer size);
}
