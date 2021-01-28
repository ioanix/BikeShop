package ro.ubb.lab6.core.repository;


import ro.ubb.lab6.core.model.Bike;
import ro.ubb.lab6.core.model.BikeType;

import java.util.List;

public interface BikeRepository extends BikeShopRepository<Bike, Long> {

    List<Bike> findBikeByName(String name);

    List<Bike> findBikeByType(BikeType bikeType);

    List<Bike> findAllByOrderByPriceDesc();

    List<Bike> findTop3ByOrderByPriceDesc();

//    @Query(value = "select b from bike b inner join sales s on b.id = s.bike_id where s.customer_id = ?1", nativeQuery = true)
//    Set<Bike> findByCustomerId(Long id);
}
