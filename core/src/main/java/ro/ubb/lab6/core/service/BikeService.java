package ro.ubb.lab6.core.service;


import ro.ubb.lab6.core.model.Bike;
import ro.ubb.lab6.core.model.BikeType;

import java.util.List;

public interface BikeService {

    List<Bike> findAllBikes();

    Bike findOneBike(Long id);

    Bike updateBikeService(Long id, String name, BikeType bikeType, double price);

    Bike addBikeService(String name, BikeType biketype, double price);

    Bike deleteBikeService(Long id);

    List<Bike> showBikeWithMaxPrice();

    List<Bike> findBikeByName(String name);

    List<Bike> findBikeByType(BikeType bikeType);

    List<Bike> showBikesOrderedByPrice();

    long countByName(String name);
}
