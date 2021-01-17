package ro.ubb.lab6.core.model;

import lombok.*;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Customer extends BaseEntity<Long> {

    private String firstName;
    private String lastName;
    private String phone;
    private String city;
    private String street;
    private String number;

}
