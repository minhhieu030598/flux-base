package com.minhhieu.commons.security;

import com.dslplatform.json.JsonReader;
import com.minhhieu.commons.util.Json;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Base64;
import java.util.List;

public class XAuthDecoder {
    private static final JsonReader.ReadObject<AuthAccount> authAccount = Json.findReader(AuthAccount.class);

    public XAuthDecoder() {
    }

    public Authentication decode(String token) {
        byte[] decoded = Base64.getUrlDecoder().decode(token);
        var principal = Json.decode(decoded, authAccount);
        return new UsernamePasswordAuthenticationToken(principal, token, List.of());
    }
}
