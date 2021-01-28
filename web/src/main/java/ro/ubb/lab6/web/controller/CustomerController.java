package ro.ubb.lab6.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.lab6.core.model.Customer;
import ro.ubb.lab6.core.model.Sale;
import ro.ubb.lab6.core.service.CustomerService;
import ro.ubb.lab6.web.converter.BikeConverter;
import ro.ubb.lab6.web.converter.CustomerConverter;
import ro.ubb.lab6.web.converter.SaleConverter;
import ro.ubb.lab6.web.dto.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerConverter customerConverter;

    @Autowired
    private BikeConverter bikeConverter;

    @Autowired
    private SaleConverter saleConverter;

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);


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

        //Collection<CustomerDto> customerDtos = customerConverter.convertModelsToDtos(customers);
        List<CustomerDto> customerDtos = customerConverter.convertModelsToDtos(customers);

        CustomersDto customersDto = new CustomersDto(customerDtos);

        return customersDto;
    }

    @RequestMapping(value = "/customers/ascbylastname/{city}")
    CustomersDto findAllByCityOrderByLastNameAsc(@PathVariable String city) {

        List<Customer> customers = customerService.searchCustomersFromASpecificCity(city);

        //Collection<CustomerDto> customerDtos = customerConverter.convertModelsToDtos(customers);
        List<CustomerDto> customerDtos = customerConverter.convertModelsToDtos(customers);

        CustomersDto customersDto = new CustomersDto(customerDtos);

        return customersDto;

    }

    @PostMapping(value = "/customers/buy")
    ResponseEntity<?> buyBike(@RequestBody SaleDto sale) {

        log.trace("sale={}", sale);

        if (!customerService.buyBike(sale.getC_id(), sale.getB_id(), sale.getSaleDate())) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(value = "/customers/shopping-list/{id}")
    BikesDto getShoppingListForCustomer(@PathVariable Long id) {

        log.trace("getShoppingListForCustomer -- method entered");
        log.trace("id = {}", id);

        List<BikeDto> bikesForCustomerDtos = bikeConverter.convertModelsToDtos(customerService.getAllBikesForCustomer(id));

        BikesDto bikesDto = new BikesDto(bikesForCustomerDtos);

        log.trace("bikesDto = {}", bikesDto);

        return bikesDto;

    }

    @RequestMapping(value = "/customers/sales")
    SalesDto showAllSales() {

        log.trace("showAllSales -- method entered");

        List<Sale> sales = customerService.getAllSales();
        log.trace("sales = {}", sales);
        log.trace("sales.size = {}", sales.size());

        //SalesDto salesDto = new SalesDto(saleConverter.convertModelsToDtos(sales));
        SalesDto salesDto = new SalesDto(sales);
        log.trace("salesDto = {}", salesDto);

        return salesDto;
    }

//    @PutMapping(value = "/customers/shopping-list/delete/{b_id}")
//    ResponseEntity<?> deleteBikeForCustomer(@RequestBody Long c_id, @PathVariable Long b_id) {
//
//        log.trace("deleteBikeForCustomer -- method entered");
//        log.trace("c_id = {}, b_id = {}", c_id, b_id);
//
//        if(!customerService.deleteBikeForCustomer(c_id, b_id)) {
//
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
