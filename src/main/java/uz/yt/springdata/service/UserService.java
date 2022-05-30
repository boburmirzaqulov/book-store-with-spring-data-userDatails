package uz.yt.springdata.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import uz.yt.springdata.auth.UserPermissions;
import uz.yt.springdata.auth.UserRoles;
import uz.yt.springdata.dao.User;
import uz.yt.springdata.dto.ResponseDTO;
import uz.yt.springdata.dto.UserDTO;
import uz.yt.springdata.dto.UserInfoDTO;
import uz.yt.springdata.dto.ValidatorDTO;
import uz.yt.springdata.helper.Validator;
import uz.yt.springdata.helper.constants.AppResponseCode;
import uz.yt.springdata.helper.constants.AppResponseMessages;
import uz.yt.springdata.mapping.UserMapping;
import uz.yt.springdata.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JdbcUserDetailsManager userDetailsManager;

    public ResponseDTO<UserDTO> addUser(UserDTO userDTO) {

        List<ValidatorDTO> errors = Validator.validateUser(userDTO);
        try {

            if (errors.size() > 0) {
                return new ResponseDTO<>(false,
                        AppResponseCode.VALIDATION_ERROR,
                        AppResponseMessages.VALIDATION_ERROR,
                        null, errors);
            }

            boolean exists = userRepository.existsByUsername(userDTO.getUsername());
            if (exists) {
                return new ResponseDTO<>(false, 1,
                        String.format("This username: %s is already taken",
                                userDTO.getUsername()), null, null);
            }

            User user = UserMapping.toEntity(userDTO);


            User savedUser = userRepository.save(user);

            return new ResponseDTO<>(true,
                    AppResponseCode.OK,
                    AppResponseMessages.OK,
                    UserMapping.toDto(savedUser), errors);

        } catch (Exception e) {
            return new ResponseDTO<>(false,
                    AppResponseCode.DATABASE_ERROR,
                    AppResponseMessages.DATABASE_ERROR,
                    null,
                    errors);
        }

    }

    public ResponseDTO<UserDTO> getUserById(Integer id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            return new ResponseDTO<>(false,
                    AppResponseCode.NOT_FOUND,
                    AppResponseMessages.NOT_FOUND, null, null);
        }
        UserDTO userDTO = UserMapping.toDto(optionalUser.get());
        return new ResponseDTO<>(true, AppResponseCode.OK,
                AppResponseMessages.OK, userDTO, null);
    }

    public ResponseDTO<Page<User>> getAllUsersPage(Integer size, Integer page) {

        PageRequest pageable = PageRequest.of(page, size);

        Page<User> all = userRepository.findAll(pageable);
        return new ResponseDTO<>(true,
                AppResponseCode.OK,
                AppResponseMessages.OK,
                all,
                null);


    }

    public ResponseDTO<UserDTO> editUser(UserDTO userDTO) {

        if (userDTO == null || userDTO.getId() == null) {
            return new ResponseDTO<>(false, AppResponseCode.VALIDATION_ERROR,
                    AppResponseMessages.VALIDATION_ERROR,
                    null, null);
        }

        List<ValidatorDTO> errors = Validator.validateUser(userDTO);
        try {

            if (errors.size() > 0) {
                return new ResponseDTO<>(false,
                        AppResponseCode.VALIDATION_ERROR,
                        AppResponseMessages.VALIDATION_ERROR,
                        null, errors);
            }

            Optional<User> optionalUser = userRepository.findById(userDTO.getId());
            if (!optionalUser.isPresent()){
                return new ResponseDTO<>(false, AppResponseCode.NOT_FOUND,
                        AppResponseMessages.NOT_FOUND,null);
            }

            boolean exists = userRepository.existsByUsernameAndIdNot(userDTO.getUsername(), userDTO.getId());
            if (exists) {
                return new ResponseDTO<>(false, 1,
                        String.format("This username: %s is already taken",
                                userDTO.getUsername()), null, null);
            }

            User user = UserMapping.toEntity(userDTO);

            User savedUser = userRepository.save(user);

            return new ResponseDTO<>(true,
                    AppResponseCode.OK,
                    AppResponseMessages.OK,
                    UserMapping.toDto(savedUser), errors);

        } catch (Exception e) {
            return new ResponseDTO<>(false,
                    AppResponseCode.DATABASE_ERROR,
                    AppResponseMessages.DATABASE_ERROR,
                    null,
                    errors);
        }


    }

    public ResponseDTO<UserDTO> deleteUser(Integer id) {

        try {
            Optional<User> optionalUser = userRepository.findById(id);
            if (!optionalUser.isPresent()){
                return new ResponseDTO<>(false, AppResponseCode.NOT_FOUND,
                        AppResponseMessages.NOT_FOUND,null);
            }

           userRepository.deleteById(id);

            return new ResponseDTO<>(true,
                    AppResponseCode.OK,
                    AppResponseMessages.OK,
                    UserMapping.toDto(optionalUser.get()));
        } catch (Exception e) {
            return new ResponseDTO<>(false,
                    AppResponseCode.DATABASE_ERROR,
                    AppResponseMessages.DATABASE_ERROR,
                    null);
        }

    }
}
