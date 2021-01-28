package ro.ubb.lab6.web.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CustomerDto extends BaseDto {

    private String firstName;
    private String lastName;
    private String phone;
    private String city;
    private String street;
    private String number;
    //private Set<Long> bikes;
}
