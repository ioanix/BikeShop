package ro.ubb.lab6.core.repository;


import ro.ubb.lab6.core.model.Bike;
import ro.ubb.lab6.core.model.BikeType;

import java.util.List;

public interface BikeRepository extends BikeShopRepository<Bike, Long> {

    List<Bike> findBikeByName(String name);

    List<Bike> findBikeByType(BikeType bikeType);

    long countByName(String name);

    List<Bike> findAllByOrderByPriceDesc();

    List<Bike> findTop3ByOrderByPriceDesc();
}
