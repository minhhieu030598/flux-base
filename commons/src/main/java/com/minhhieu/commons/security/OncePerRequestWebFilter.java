package com.minhhieu.commons.security;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static java.lang.StringTemplate.STR;

@Log4j2
public abstract class OncePerRequestWebFilter implements WebFilter {
    private final String filterApplied;

    public OncePerRequestWebFilter(String filterAlreadyApplied) {
        this.filterApplied = filterAlreadyApplied;
    }

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (exchange.getAttribute(filterApplied) != null) {
            return chain.filter(exchange);
        }
        log.info(STR."""
                Request pass through \{filterApplied}
                """);
        exchange.getAttributes().put(filterApplied, true);
        return extendFilter(exchange, chain);
    }

    public abstract Mono<Void> extendFilter(ServerWebExchange exchange, WebFilterChain chain) throws IOException;
}
