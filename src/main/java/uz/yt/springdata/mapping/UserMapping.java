package uz.yt.springdata.mapping;

import uz.yt.springdata.dao.User;
import uz.yt.springdata.dto.UserDTO;

public class UserMapping {

    public static User toEntity(UserDTO userDTO) {


        return userDTO == null ? null :
                new User(userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getPhoneNumber(),
                userDTO.getAccount(),
                userDTO.getPassword(),
                userDTO.getUsername()
        );

    }

    public static UserDTO toDto(User user) {
        return user == null ? null :
                new UserDTO(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPhoneNumber(),
                        user.getAccount(),
                        user.getUsername(),
                        user.getPassword()
                );
    }
}
