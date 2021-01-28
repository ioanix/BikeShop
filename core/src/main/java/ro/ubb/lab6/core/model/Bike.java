package ro.ubb.lab6.core.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Bike extends BaseEntity<Long> {

    @NonNull
    private String name;

    //@Enumerated(EnumType.STRING)
    @NonNull
    private BikeType type;

    @NonNull
    private double price;

    @OneToMany(mappedBy = "bike", cascade = CascadeType.ALL, fetch =
            FetchType.EAGER)
    //@JsonManagedReference
    private List<Sale> sales = new ArrayList<>();


    public Set<Customer> getCustomerList() {

        sales = sales == null ? new ArrayList<>() : sales;

        return sales.stream()
                .map(Sale::getCustomer)
                .collect(Collectors.toSet());
    }

    public Set<Long> getCustomerIds() {

        Set<Customer> customers = getCustomerList();

        return customers.stream()
                .map(customer -> customer.getId())
                .collect(Collectors.toSet());
    }

    public void addCustomer(Customer customer) {

        Sale sale = new Sale();

        sale.setCustomer(customer);
        sale.setBike(this);

        sales.add(sale);
    }

    public void addDate(Customer customer, Date saleDate) {

        Sale sale = new Sale();

        sale.setCustomer(customer);
        sale.setSaleDate(saleDate);
        sale.setBike(this);

        sales.add(sale);
    }

    @Override
    public String toString() {
        return "Bike{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bike that = (Bike) o;

        return name.equals(that.name) && String.valueOf(type).equals(String.valueOf(that.type));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
