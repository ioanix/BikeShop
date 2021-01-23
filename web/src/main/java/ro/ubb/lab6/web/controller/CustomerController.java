package ro.ubb.lab6.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.lab6.core.model.Customer;
import ro.ubb.lab6.core.service.CustomerService;
import ro.ubb.lab6.web.converter.CustomerConverter;
import ro.ubb.lab6.web.dto.CustomerDto;
import ro.ubb.lab6.web.dto.CustomersDto;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerConverter customerConverter;


    @RequestMapping(value = "/customers")
    CustomersDto getAllCustomers() {

        List<Customer> customers = customerService.findAllCustomers();

        List<CustomerDto> customerDtos = customerConverter.convertModelsToDtos(customers);

        CustomersDto customersDto = new CustomersDto(customerDtos);

        return customersDto;
    }

    @PostMapping(value = "/customers")
    CustomerDto saveCustomer(@RequestBody CustomerDto dto) {

        Customer customer = customerService.addCustomerService(dto.getFirstName(),
                dto.getLastName(),
                dto.getPhone(),
                dto.getCity(),
                dto.getStreet(),
                dto.getNumber());

        CustomerDto customerDto = customerConverter.convertModelToDto(customer);

        return customerDto;
    }

    @RequestMapping(value = "/customers/{id}")
    CustomerDto findCustomerById(@PathVariable Long id) {

        Customer customer = customerService.findOneCustomer(id);

        CustomerDto customerDto = customerConverter.convertModelToDto(customer);

        return customerDto;
    }

    @PutMapping(value = "/customers/{id}")
    CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerDto dto) {

        Customer customer = customerService.updateCustomerService(id,
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPhone(),
                dto.getCity(),
                dto.getStreet(),
                dto.getNumber());

        CustomerDto customerDto = customerConverter.convertModelToDto(customer);

        return customerDto;
    }

    @DeleteMapping(value = "/customers/{id}")
    ResponseEntity<?> deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomerService(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/customers/sortbylastnamedesc")
    CustomersDto getAllCustomersOrderByLastNameDesc() {

        List<Customer> customers = customerService.sortByLastNameDesc();

        List<CustomerDto> customerDtos = customerConverter.convertModelsToDtos(customers);

        CustomersDto customersDto = new CustomersDto(customerDtos);

        return customersDto;
    }

    @RequestMapping(value = "/customers/ascbylastname/{city}")
    CustomersDto findAllByCityOrderByLastNameAsc(@PathVariable String city) {

        List<Customer> customers = customerService.searchCustomersFromASpecificCity(city);

        List<CustomerDto> customerDtos = customerConverter.convertModelsToDtos(customers);

        CustomersDto customersDto = new CustomersDto(customerDtos);

        return customersDto;

    }
}
