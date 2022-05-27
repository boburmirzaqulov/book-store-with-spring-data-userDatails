package uz.yt.springdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @NonNull
    private Integer id;
    private Integer regionId;
    private Integer districtId;
    private String street;
    private String homeNumber;
    private String orient;

}
