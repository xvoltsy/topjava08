package ru.javawebinar.topjava.model;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
