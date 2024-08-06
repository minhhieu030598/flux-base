package com.minhhieu.commons.util;

import com.minhhieu.commons.exception.BusinessException;
import com.minhhieu.commons.security.AuthAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class Authentications {
    private Authentications() {
    }

    public static Long requireAccountId() {
        return requireAccountId(SecurityContextHolder.getContext().getAuthentication());
    }

    public static Long requireAccountId(Authentication authentication) {
        return requireAccount(authentication).getId();
    }

    public static AuthAccount requireAccount() {
        return requireAccount(SecurityContextHolder.getContext().getAuthentication());
    }

    public static AuthAccount requireAccount(Authentication authentication) {
        var principal = authentication.getPrincipal();
        if (principal instanceof AuthAccount authAccount) {
            return authAccount;
        }
        throw new BusinessException(ErrorCode.FORBIDDEN, "You don't have permission to access this resource. Require logged user");
    }

    public static Optional<AuthAccount> getAccount() {
        return getAccount(SecurityContextHolder.getContext().getAuthentication());
    }

    public static Optional<AuthAccount> getAccount(Authentication authentication) {
        var principal = authentication.getPrincipal();
        return principal instanceof AuthAccount authAccount ? Optional.of(authAccount) : Optional.empty();
    }

    public static Optional<Long> getAccountId() {
        return getAccountId(SecurityContextHolder.getContext().getAuthentication());
    }

    public static Optional<Long> getAccountId(Authentication authentication) {
        var principal = authentication.getPrincipal();
        return principal instanceof AuthAccount authAccount ? Optional.of(authAccount.getId()) : Optional.empty();
    }
}
