package ro.ubb.lab6.core.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Bike extends BaseEntity<Long>{

    private String name;

    @Enumerated(EnumType.STRING)
    private BikeType type;

    private double price;
}
