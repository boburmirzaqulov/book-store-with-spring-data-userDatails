package uz.yt.springdata.mapping;

import uz.yt.springdata.dao.Address;
import uz.yt.springdata.dto.AddressDTO;

public class AddressMapping {

    public static AddressDTO toDto(Address address){
        return address == null ? null : new AddressDTO(
                address.getId(),
                address.getRegionId(),
                address.getDistrictId(),
                address.getStreet(),
                address.getHomeNumber(),
                address.getOrient()
        );
    }
}
