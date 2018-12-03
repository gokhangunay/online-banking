package com.banking.user.domain.security;

import org.springframework.security.core.GrantedAuthority;

// Granted authority yetkilendirme icin kullaniyoruz. Yetkilendirme dogruluk kontrolu

public class Authority implements GrantedAuthority {

    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
