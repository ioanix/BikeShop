package ro.ubb.lab6.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.lab6.core.model.Bike;
import ro.ubb.lab6.core.model.BikeType;
import ro.ubb.lab6.core.repository.BikeRepository;
import ro.ubb.lab6.core.validators.Validator;

import java.util.List;

@Service
public class BikeServiceImpl implements BikeService {

    private static final Logger log = LoggerFactory.getLogger(BikeServiceImpl.class);

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    @Qualifier("bikeValidator")
    private Validator<Bike> validator;


    @Override
    public List<Bike> findAllBikes() {

        log.trace("findAllBikes --- method entered");

        List<Bike> result = bikeRepository.findAll();
        validator.validateList(result);

        log.trace("findAllBikes: result = {}", result);

        return result;
    }

    @Override
    public Bike findOneBike(Long id) {

        log.trace(("findById --- method entered"));

        Bike result = bikeRepository.findById(id).orElseThrow();

        log.trace("findById: result = {}", result);

        return result;
    }

    @Transactional
    @Override
    public Bike updateBikeService(Long id, String name, BikeType bikeType, double price) {

        Bike existingBike = bikeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("The bike id does not exist"));

        log.trace("update: id = {}, existingBike = {}", id, existingBike);

        existingBike.setName(name);
        existingBike.setType(bikeType);
        existingBike.setPrice(price);

        log.trace("update: result = {}", existingBike);

        return existingBike;
    }

    @Override
    public Bike addBikeService(String name, BikeType biketype, double price) {

        Bike bike = new Bike(name, biketype, price);
        validator.validate(bike);

        log.trace("save: bike = {}", bike);

        bikeRepository.save(bike);

        return bike;
    }

    @Override
    public Bike deleteBikeService(Long id) {

        Bike existingBike = this.bikeRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("The id does not exist"));

        log.trace("delete: id = {}", id);

        bikeRepository.deleteById(id);

        return existingBike;
    }

    @Override
    public List<Bike> showBikeWithMaxPrice() {

        log.trace("showBikeWithMaxPrice -- method entered");

        List<Bike> bikes = bikeRepository.findTop3ByOrderByPriceDesc();

        log.trace("showBikeWithMaxPrice: result = {}", bikes);

        return bikes;
    }

    @Override
    public List<Bike> findBikeByName(String name) {

        log.trace("findBikeByName -- method entered");

        List<Bike> bikesByName = bikeRepository.findBikeByName(name);
        validator.validateList(bikesByName);

        log.trace("findBikeByName: result = {}", bikesByName);

        return bikesByName;

    }

    @Override
    public List<Bike> findBikeByType(BikeType bikeType) {

        log.trace("findBikeByType -- method entered");

        List<Bike> bikesByType = bikeRepository.findBikeByType(bikeType);

        log.trace("findBikeByType: result = {}", bikesByType);

        return bikesByType;
    }

    @Override
    public List<Bike> showBikesOrderedByPrice() {

        log.trace("showBikesOrderedByPrice -- method entered");

        List<Bike> bikes = bikeRepository.findAllByOrderByPriceDesc();

        log.trace("showBikesOrderedByPrice: result = {}", bikes);

        return bikes;

    }
}
