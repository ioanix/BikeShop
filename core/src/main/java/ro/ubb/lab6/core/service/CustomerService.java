package ro.ubb.lab6.core.service;

import ro.ubb.lab6.core.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomers();

    Customer findOneCustomer(Long id);

    Customer updateCustomerService(Long id, String firstName, String lastName, String phone, String city, String street, String number);

    Customer addCustomerService(String firstName, String lastName, String phone, String city, String street, String number);

    Customer deleteCustomerService(Long id);

    List<Customer> searchCustomersFromASpecificCity(String city);

    List<Customer> findAllByCity(int pageNumber, String city);

    List<Customer> sortByLastNameDesc();
}
