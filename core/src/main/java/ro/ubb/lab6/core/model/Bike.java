package ro.ubb.lab6.core.model;


import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Bike extends BaseEntity<Long>{

    private String name;
    private BikeType type;
    private double price;
}
