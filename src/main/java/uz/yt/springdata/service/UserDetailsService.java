package uz.yt.springdata.service;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    @Override
    public UserInfoDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findFirstByUsername(username);

        UserInfoDTO userInfoDTO = null;
        if (user.isPresent()) {
            User u = user.get();
            userInfoDTO = new UserInfoDTO();
            userInfoDTO.setFirstName(u.getFirstName());
            userInfoDTO.setId(u.getId());
            userInfoDTO.setAccount(u.getAccount());
            userInfoDTO.setLastName(u.getLastName());
            userInfoDTO.setPassword(u.getPassword());
            userInfoDTO.setPhoneNumber(u.getPhoneNumber());
            userInfoDTO.setUsername(u.getUsername());

            List<Authorities> auth = authoritiesRepository.findAllByUsername(username);
            Set<UserPermissions> permissions = auth.stream()
                    .map(a -> UserPermissions.valueOf(a.getAuthority()))
                    .collect(Collectors.toSet());

            userInfoDTO.setPermissions(permissions);
        }

        return userInfoDTO;
    }
}
