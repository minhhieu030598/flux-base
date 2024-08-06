package com.minhhieu.commons.security;

import com.minhhieu.commons.util.Constant;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Log4j2
public class XAuthFilter extends OncePerRequestWebFilter {
    private final XAuthDecoder authenticationDecoder;

    public XAuthFilter(XAuthDecoder authenticationDecoder) {
        super("XAuthFilter");
        this.authenticationDecoder = authenticationDecoder;
    }

    @Override
    public Mono<Void> extendFilter(ServerWebExchange exchange, WebFilterChain chain) {
        var headers = exchange.getRequest().getHeaders();
        final var header = headers.get(Constant.X_AUTH_USER);

        String authHeader;
        if (header == null || header.isEmpty() || (authHeader = header.getFirst()) == null) {
            return chain.filter(exchange);
        }
        var authentication = authenticationDecoder.decode(authHeader);
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        return chain.filter(exchange);
    }

}
