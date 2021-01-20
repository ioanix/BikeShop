package ro.ubb.lab6.core.repository;

import org.springframework.data.jpa.repository.Query;
import ro.ubb.lab6.core.model.Customer;

import java.util.List;


public interface CustomerRepository extends BikeShopRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c where c.city =?1 order by c.lastName asc")
    List<Customer> findByCityOrderByLastNameAsc(String city);

    @Query("SELECT c FROM Customer c order by c.lastName desc")
    List<Customer> findAllByOrderByLastNameDesc();
}
