package com.minhhieu.core.handler;//package com.minhhieu.core.controller;
//
//import com.minhhieu.commons.model.filter.PersonFilter;
//import com.minhhieu.core.model.request.CreatePersonRequest;
//import com.minhhieu.commons.model.response.PersonResponse;
//import com.minhhieu.core.service.PersonService;
//import com.minhhieu.commons.model.response.Response;
//import com.minhhieu.core.validator.PersonValidator;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/controller-persons")
//public class PersonController {
//    private final PersonService personService;
//    private final PersonValidator personValidator;
//
//    public PersonController(PersonService personService, PersonValidator personValidator) {
//        this.personService = personService;
//        this.personValidator = personValidator;
//    }
//
//    @GetMapping
//    public Mono<Response<List<PersonResponse>>> getPersons(@RequestParam(defaultValue = "0") int page,
//                                                           @RequestParam(defaultValue = "20") int size,
//                                                           PersonFilter filter) {
//        return personService.getPersons(filter, PageRequest.of(page, size))
//                .map(Response::ok);
//    }
//
////    @PostMapping
////    public Mono<Response<Void>> create(ServerRequest serverRequest) {
////        return serverRequest.bodyToMono(CreatePersonRequest.class)
////                .flatMap(personService::create)
////                .map(Response::ok);
////    }
//}
