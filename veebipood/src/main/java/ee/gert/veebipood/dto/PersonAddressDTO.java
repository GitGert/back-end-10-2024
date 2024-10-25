package ee.gert.veebipood.dto;

import ee.gert.veebipood.entity.Address;
import lombok.Data;

@Data
public class PersonAddressDTO {
    private String firstName;
    private String lastName;
    private Address address;
}
