package com.minhhieu.core.thirdparty.impl;

import com.minhhieu.commons.model.filter.PersonFilter;
import com.minhhieu.commons.model.response.Response;
import com.minhhieu.commons.thirdparty.PersonClient;
import com.minhhieu.core.ApplicationTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
class PersonClientImplTest extends ApplicationTest {
    @Autowired
    private PersonClient personClient;

    @Test
    void getPersons() {
        var personsMono = personClient.getPersons(new PersonFilter().setName("ANh"), 0, 10);

        StepVerifier.create(personsMono)
                .expectNextMatches(res -> {
                    assertEquals(Response.PREFIX + 200, res.getMeta().getCode());
                    assertEquals(1, res.getData().size());
                    var person = res.getData().getFirst();
                    assertTrue(person.getName().toLowerCase().contains("ANh".toLowerCase()));
                    return true;
                })
                .verifyComplete();
    }
}