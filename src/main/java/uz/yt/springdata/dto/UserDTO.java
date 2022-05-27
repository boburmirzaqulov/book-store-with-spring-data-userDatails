package uz.yt.springdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    protected Integer id;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected BigDecimal account;
    protected String username;
    protected String password;

}

