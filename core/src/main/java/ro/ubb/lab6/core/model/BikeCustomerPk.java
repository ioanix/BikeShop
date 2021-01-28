package ro.ubb.lab6.core.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class BikeCustomerPk implements Serializable {

    private Bike bike;
    private Customer customer;
}
