package ro.ubb.lab6.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.lab6.core.model.Bike;
import ro.ubb.lab6.web.dto.BikeDto;


@Component
public class BikeConverter extends AbstractConverterBaseEntityConverter<Bike, BikeDto> {

    @Override
    public Bike convertDtoToModel(BikeDto dto) {

        Bike bike = new Bike(dto.getName(), dto.getType(), dto.getPrice());
        bike.setId(dto.getId());

        return bike;
    }

    @Override
    public BikeDto convertModelToDto(Bike bike) {

        BikeDto dto = new BikeDto(bike.getName(), bike.getType(), bike.getPrice());
        dto.setId(bike.getId());

        return dto;
    }
}
