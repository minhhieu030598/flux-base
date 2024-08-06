package com.minhhieu.api.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.minhhieu.commons.service.JwtService;
import com.minhhieu.commons.util.Constant;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter implements WebFilter {
    private static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private static final int TOKEN_START_INDEX = BEARER_TOKEN_PREFIX.length();
    private final JwtService jwtService;

    public AuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var headers = exchange.getRequest().getHeaders();
        final var header = headers.get(HttpHeaders.AUTHORIZATION);
        String authHeader;
        if (header == null || header.isEmpty() || !((authHeader = header.getFirst()).startsWith(BEARER_TOKEN_PREFIX))) {
            return chain.filter(exchange);
        }
        final var token = authHeader.substring(TOKEN_START_INDEX);
        var authentication = jwtService.decode(token);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            if (authentication.getDetails() instanceof DecodedJWT decodedJWT) {
                ServerHttpRequest request = exchange.getRequest().mutate()
                        .header(Constant.X_AUTH_USER, decodedJWT.getPayload())
                        .build();
                return chain.filter(exchange.mutate().request(request).build());
            }
        }
        return chain.filter(exchange);
    }
}
