package ro.ubb.lab6.core.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import ro.ubb.lab6.core.model.Customer;

import java.util.List;


public interface CustomerRepository extends BikeShopRepository<Customer, Long> {

    List<Customer> findByCityOrderByLastNameAsc(String city);

    @Query("FROM Customer WHERE city = ?1")
    List<Customer> findAllByCity(String city, Pageable pageable);

    List<Customer> findAllByOrderByLastNameDesc();
}
