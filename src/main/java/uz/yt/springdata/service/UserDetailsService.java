package uz.yt.springdata.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.yt.springdata.auth.UserPermissions;
import uz.yt.springdata.dao.Authorities;
import uz.yt.springdata.dao.User;
import uz.yt.springdata.dto.UserInfoDTO;
import uz.yt.springdata.repository.AuthoritiesRepository;
import uz.yt.springdata.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    private final AuthoritiesRepository authoritiesRepository;

    @Override
    public UserInfoDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findFirstByUsername(username);

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        user.ifPresent(u -> {
            userInfoDTO.setFirstName(u.getFirstName());
            userInfoDTO.setId(u.getId());
            userInfoDTO.setAccount(u.getAccount());
            userInfoDTO.setLastName(u.getLastName());
            userInfoDTO.setPassword(u.getPassword());
            userInfoDTO.setPhoneNumber(u.getPhoneNumber());
            userInfoDTO.setUsername(u.getUsername());

            List<Authorities> auth = authoritiesRepository.findAllByUsername(username);
            Set<GrantedAuthority> permissions = auth.stream()
                    .map(a -> new SimpleGrantedAuthority(a.getAuthority()))
                    .collect(Collectors.toSet());

            userInfoDTO.setPermissions(permissions);
        });
        return userInfoDTO;
    }
}
