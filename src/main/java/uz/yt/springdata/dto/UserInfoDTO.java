package uz.yt.springdata.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.yt.springdata.auth.UserPermissions;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserInfoDTO extends UserDTO implements UserDetails{
    private Set<UserPermissions> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions
                .stream()
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPermissions(Set<UserPermissions> permissions) {
        this.permissions = permissions;
    }
}
