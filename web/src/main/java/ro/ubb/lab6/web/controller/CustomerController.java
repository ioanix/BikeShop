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
import java.util.Set;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerConverter customerConverter;


    @RequestMapping(value = "/customers")
    CustomersDto getAllCustomers() {

        List<Customer> customers = customerService.findAllCustomers();

        Set<CustomerDto> customerDtos = customerConverter.convertModelsToDtos(customers);

        CustomersDto customersDto = new CustomersDto(customerDtos);

        return customersDto;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    CustomerDto saveCustomer(@RequestBody CustomerDto dto) {

        //todo: logs

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

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomerService(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
