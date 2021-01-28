package ro.ubb.lab6.core.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class Customer extends BaseEntity<Long> {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String phone;
    @NonNull
    private String city;
    @NonNull
    private String street;
    @NonNull
    private String number;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch =
            FetchType.EAGER)
    //@JsonManagedReference(value = "customer-bikes")
    private List<Sale> sales = new ArrayList<>();

    private static final Logger log = LoggerFactory.getLogger(Customer.class);


    public Set<Bike> getBikesList() {

        sales = sales == null ? new ArrayList<>() : sales;

        return this.sales.stream()
                .map(Sale::getBike)
                .collect(Collectors.toSet());
    }

    public void addBike(Bike bike) {

        log.trace("addBike -- method entered");

        Sale sale = new Sale();

        sale.setBike(bike);
        sale.setCustomer(this);

        sales.add(sale);
        bike.getSales().add(sale);

    }

    public void removeBike(Bike bike) {

        bike.setSales(null);
        this.getBikesList().remove(bike);
    }

    public void addBikes(Set<Bike> bikes) {

        bikes.forEach(this::addBike);
    }

    public void addDate(Bike bike, Date saleDate) {

        Sale sale = new Sale();

        sale.setCustomer(this);
        sale.setSaleDate(saleDate);
        sale.setBike(bike);

        sales.add(sale);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer that = (Customer) o;

        return firstName.equals(that.firstName) && lastName.equals(that.lastName) && city.equals(that.city);
    }

    @Override
    public int hashCode() {
        return firstName.hashCode();
    }

}
