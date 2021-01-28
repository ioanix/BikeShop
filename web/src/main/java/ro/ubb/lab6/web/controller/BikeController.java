package ro.ubb.lab6.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.lab6.core.model.Bike;
import ro.ubb.lab6.core.model.BikeType;
import ro.ubb.lab6.core.service.BikeService;
import ro.ubb.lab6.web.converter.BikeConverter;
import ro.ubb.lab6.web.dto.BikeDto;
import ro.ubb.lab6.web.dto.BikesDto;

import java.util.List;

@RestController
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @Autowired
    private BikeConverter bikeConverter;

    private static final Logger log = LoggerFactory.getLogger(BikeController.class);


    @RequestMapping(value = "/bikes")
    BikesDto getAllBikes() {

        log.trace("getAllBikes --- method entered");

        List<Bike> bikes = bikeService.findAllBikes();

        //Collection<BikeDto> bikeDtos = bikeConverter.convertModelsToDtos(bikes);

        List<BikeDto> bikeDtos = bikeConverter.convertModelsToDtos(bikes);

        BikesDto bikesDto = new BikesDto(bikeDtos);

        log.trace("getAllBikes: result = {}", bikesDto.getBikes());

        return bikesDto;
    }

    @PostMapping(value = "/bikes")
    BikeDto saveBike(@RequestBody BikeDto dto) {

        Bike bike = bikeService.addBikeService(dto.getName(), dto.getType(), dto.getPrice());

        BikeDto bikeDto = bikeConverter.convertModelToDto(bike);

        return bikeDto;
    }

    @RequestMapping(value = "/bikes/{id}")
    BikeDto findBikeById(@PathVariable Long id) {

        Bike bike = bikeService.findOneBike(id);

        BikeDto bikeDto = bikeConverter.convertModelToDto(bike);

        return bikeDto;
    }

    @PutMapping(value = "/bikes/{id}")
    BikeDto updateBike(@PathVariable Long id, @RequestBody BikeDto dto) {

        Bike bike = bikeService.updateBikeService(id, dto.getName(), dto.getType(), dto.getPrice());

        BikeDto bikeDto = bikeConverter.convertModelToDto(bike);

        return bikeDto;
    }

    @DeleteMapping(value = "/bikes/{id}")
    ResponseEntity<?> deleteBike(@PathVariable Long id) {

        bikeService.deleteBikeService(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/bikes/name/{name}")
    BikesDto findBikeByName(@PathVariable String name) {

        List<Bike> bikesByName = bikeService.findBikeByName(name);

        //Collection<BikeDto> bikesByNameDtos = bikeConverter.convertModelsToDtos(bikesByName);

        List<BikeDto> bikeDtos = bikeConverter.convertModelsToDtos(bikesByName);

        BikesDto bikesDto = new BikesDto(bikeDtos);

        return bikesDto;
    }

    @RequestMapping(value = "/bikes/type/{type}")
    BikesDto findBikeByType(@PathVariable String type) {

        List<Bike> bikesByType = bikeService.findBikeByType(BikeType.valueOf(type.toUpperCase()));

        //Collection<BikeDto> bikesByTypeDtos = bikeConverter.convertModelsToDtos(bikesByType);

        List<BikeDto> bikeDtos = bikeConverter.convertModelsToDtos(bikesByType);

        BikesDto bikesDto = new BikesDto(bikeDtos);

        return bikesDto;
    }

    @RequestMapping(value = "/bikes/descbyprice")
    BikesDto getAllBikesOrderedByPriceDesc() {

        List<Bike> bikes = bikeService.showBikesOrderedByPrice();

       //Collection<BikeDto> bikeDtos = bikeConverter.convertModelsToDtos(bikes);

        List<BikeDto> bikeDtos = bikeConverter.convertModelsToDtos(bikes);

        BikesDto bikesDto = new BikesDto(bikeDtos);

        return bikesDto;
    }

    @RequestMapping(value = "/bikes/top3")
    BikesDto getTop3OrderedByPrice() {

        List<Bike> bikes = bikeService.showBikeWithMaxPrice();

        //Collection<BikeDto> bikeDtos = bikeConverter.convertModelsToDtos(bikes);
        List<BikeDto> bikeDtos = bikeConverter.convertModelsToDtos(bikes);

        BikesDto bikesDto = new BikesDto(bikeDtos);

        return bikesDto;
    }
}

