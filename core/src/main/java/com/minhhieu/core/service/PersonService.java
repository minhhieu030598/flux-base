package com.minhhieu.core.service;

import com.minhhieu.commons.model.filter.PersonFilter;
import com.minhhieu.core.model.request.CreatePersonRequest;
import com.minhhieu.core.model.request.UpdatePersonRequest;
import com.minhhieu.commons.model.response.PersonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface PersonService {
    Mono<Page<PersonResponse>> getPersons(PersonFilter filter, Pageable pageable);

    Mono<Void> create(CreatePersonRequest request);

    Mono<PersonResponse> update(UpdatePersonRequest request);

    Mono<Void> delete(Long id);
}
