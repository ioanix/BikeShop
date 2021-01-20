package ro.ubb.lab6.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.lab6.core.model.Customer;
import ro.ubb.lab6.core.repository.CustomerRepository;
import ro.ubb.lab6.core.validators.Validator;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    @Qualifier("customerValidator")
    private Validator<Customer> validator;


    @Override
    public List<Customer> findAllCustomers() {

        log.trace("findAllCustomers --- method entered");

        List<Customer> result = customerRepository.findAll();
        validator.validateList(result);

        log.trace("findAllCustomers: result = {}", result);

        return result;

    }

    @Override
    public Customer findOneCustomer(Long id) {

        log.trace(("findById --- method entered"));

        Customer result = customerRepository.findById(id).orElseThrow();

        log.trace("findById: result = {}", result);

        return result;
    }

    @Transactional
    @Override
    public Customer updateCustomerService(Long id, String firstName, String lastName, String phone, String city, String street, String number) {

        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("The customer id does not exist"));

        log.trace("update: id = {}, existingCustomer = {}", id, existingCustomer);

        existingCustomer.setFirstName(firstName);
        existingCustomer.setLastName(lastName);
        existingCustomer.setPhone(phone);
        existingCustomer.setCity(city);
        existingCustomer.setStreet(street);
        existingCustomer.setNumber(number);

        log.trace("update: result = {}", existingCustomer);

        return existingCustomer;
    }

    @Override
    public Customer addCustomerService(String firstName, String lastName, String phone, String city, String street, String number) {

        Customer customer = new Customer(firstName, lastName, phone, city, street, number);

        validator.validate(customer);
        customerRepository.save(customer);

        log.trace("save: customer = {}", customer);

        return customer;
    }

    @Override
    public Customer deleteCustomerService(Long id) {

        Customer existingCustomer = this.customerRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("The id does not exist"));

        log.trace("delete: id = {}", id);

        customerRepository.deleteById(id);

        return existingCustomer;
    }

    @Override
    public List<Customer> searchCustomersFromASpecificCity(String city) {

        log.trace("searchCustomersFromASpecificCity --- method entered");

        List<Customer> customersByCity = customerRepository.findByCityOrderByLastNameAsc(city);
        validator.validateList(customersByCity);

        log.trace("searchCustomersFromASpecificCity: result = {}", customersByCity);

        return customersByCity;
    }

    @Override
    public List<Customer> sortByLastNameDesc() {

        log.trace("sortedByLastNameDesc -- method entered");

        List<Customer> customersSorted = customerRepository.findAllByOrderByLastNameDesc();
        validator.validateList(customersSorted);

        log.trace("sortedByLastNameDesc: result = {}", customersSorted);

        return customersSorted;
    }
}
