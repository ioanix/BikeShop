package ro.ubb.lab6.web.dto;


import lombok.*;
import ro.ubb.lab6.core.model.BikeType;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BikeDto extends BaseDto {

    private String name;
    private BikeType type;
    private double price;

}
