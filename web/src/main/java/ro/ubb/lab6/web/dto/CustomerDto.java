package ro.ubb.lab6.web.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CustomerDto extends BaseDto {

    private String firstName;
    private String lastName;
    private String phone;
    private String city;
    private String street;
    private String number;
}
