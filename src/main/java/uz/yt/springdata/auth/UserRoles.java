package uz.yt.springdata.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static uz.yt.springdata.auth.UserPermissions.*;

public enum UserRoles {
    GUEST(Set.of(READ_BOOK, READ_DELIVERY, CREATE_ORDER_ITEMS, READ_ORDERS), "GUEST"),
    ADMIN(Set.of(CREATE, UPDATE, READ, DELETE), "ADMIN"),
    MODERATOR(Set.of(CREATE, UPDATE, READ), "MODERATOR"),
    BOOK_MANAGER(Set.of(CREATE_BOOK, READ), "BOOK_MANAGER"),
    SALES_MANAGER(Set.of(CREATE_ORDERS, READ, CREATE_DELIVERY), "SALES_MANAGER");

    private final Set<UserPermissions> permissions;
    private final String name;

    UserRoles(Set<UserPermissions> permissions, String name) {
        this.permissions = permissions;
        this.name = name;
    }

    public Set<SimpleGrantedAuthority> getPermissions() {
        Set<SimpleGrantedAuthority> authorities =
                permissions.stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name));

        return authorities;
    }
}
