package ro.ubb.lab6.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.ubb.lab6.core.model.Bike;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BikesDto {

    private Set<BikeDto> bikes;
}
