package ro.ubb.lab6.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.lab6.core.model.Bike;
import ro.ubb.lab6.core.service.BikeService;
import ro.ubb.lab6.web.converter.BikeConverter;
import ro.ubb.lab6.web.dto.BikeDto;
import ro.ubb.lab6.web.dto.BikesDto;

import java.util.List;
import java.util.Set;

@RestController
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @Autowired
    private BikeConverter bikeConverter;


    @RequestMapping(value="/bikes")
    BikesDto getAllBikes() {

        //TODO: logs
        List<Bike> bikes = bikeService.findAllBikes();

        Set<BikeDto> bikeDtos= bikeConverter.convertModelsToDtos(bikes);

        BikesDto bikesDto = new BikesDto(bikeDtos);

        return bikesDto;
    }

    @RequestMapping(value = "/bikes", method = RequestMethod.POST)
    BikeDto saveBike(@RequestBody BikeDto dto) {

        //todo: logs

        Bike bike = bikeService.addBikeService(dto.getName(), dto.getType(), dto.getPrice());

        BikeDto bikeDto = bikeConverter.convertModelToDto(bike);

        return bikeDto;
    }

    @RequestMapping(value="/bikes/{id}")
    BikeDto findBikeById(@PathVariable Long id) {

        Bike bike = bikeService.findOneBike(id);

        BikeDto bikeDto = bikeConverter.convertModelToDto(bike);

        return bikeDto;
    }

    @RequestMapping(value = "/bikes/{id}", method = RequestMethod.PUT)
    BikeDto updateBike(@PathVariable Long id, @RequestBody BikeDto dto) {

        Bike bike = bikeService.updateBikeService(id, dto.getName(), dto.getType(), dto.getPrice());

        BikeDto bikeDto = bikeConverter.convertModelToDto(bike);

        return bikeDto;
    }

    @RequestMapping(value = "/bikes/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteBike(@PathVariable Long id) {

        bikeService.deleteBikeService(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
