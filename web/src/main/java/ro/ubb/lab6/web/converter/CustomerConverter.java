package ro.ubb.lab6.web.converter;


import org.springframework.stereotype.Component;
import ro.ubb.lab6.core.model.Customer;
import ro.ubb.lab6.web.dto.CustomerDto;

@Component
public class CustomerConverter extends BaseConverter<Customer, CustomerDto> {

    @Override
    public Customer convertDtoToModel(CustomerDto dto) {

        Customer customer = new Customer(dto.getFirstName(),
                                         dto.getLastName(),
                                         dto.getPhone(),
                                         dto.getCity(),
                                         dto.getStreet(),
                                         dto.getNumber());

        customer.setId(dto.getId());
        return customer;
    }

    @Override
    public CustomerDto convertModelToDto(Customer customer) {

        CustomerDto dto = new CustomerDto(customer.getFirstName(),
                                          customer.getLastName(),
                                          customer.getPhone(),
                                          customer.getCity(),
                                          customer.getStreet(),
                                          customer.getNumber());

        dto.setId(customer.getId());
        return dto;
    }
}
