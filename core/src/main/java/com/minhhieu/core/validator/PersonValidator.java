package com.minhhieu.core.validator;

import com.minhhieu.commons.exception.BusinessException;
import com.minhhieu.core.model.PersonStatus;
import com.minhhieu.core.model.request.CreatePersonRequest;
import com.minhhieu.commons.util.ErrorCode;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PersonValidator {

    public PersonValidator() {

    }

    public Mono<CreatePersonRequest> validate(CreatePersonRequest request) {
        if (!PersonStatus.contain(request.getStatus())) {
            return Mono.error(new BusinessException(ErrorCode.INVALID_PARAMETERS, "Status is invalid, value: " + request.getStatus()));
        }
        return Mono.just(request);
    }
}
