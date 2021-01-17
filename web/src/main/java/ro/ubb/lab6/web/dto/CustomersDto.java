package ro.ubb.lab6.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.ubb.lab6.core.model.Customer;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomersDto {

    private Set<CustomerDto> customers;
}
