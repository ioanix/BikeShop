package ro.ubb.lab6.core.service;

import ro.ubb.lab6.core.model.Bike;
import ro.ubb.lab6.core.model.Customer;
import ro.ubb.lab6.core.model.Sale;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface CustomerService {

    List<Customer> findAllCustomers();

    Customer findOneCustomer(Long id);

    Customer updateCustomerService(Long id, String firstName, String lastName, String phone, String city, String street, String number);

    Customer addCustomerService(String firstName, String lastName, String phone, String city, String street, String number);

    Customer deleteCustomerService(Long id);

    List<Customer> searchCustomersFromASpecificCity(String city);

    List<Customer> sortByLastNameDesc();

    boolean buyBike(Long c_id, Long b_id, Date saleDate);

    Set<Bike> getAllBikesForCustomer(Long id);

    List<Sale> getAllSales();

//    boolean deleteBikeForCustomer(Long c_id, Long b_id);
}
