package ro.ubb.lab6.core.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sales")
@IdClass(BikeCustomerPk.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="customer")
public class Sale implements Serializable {

    @Id
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bike_id")
    //@JsonBackReference
    private Bike bike;

    @Id
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    //@JsonBackReference
    private Customer customer;

    @Column(name = "sale_date")
    private Date saleDate;

    @Override
    public String toString() {
        return "Sale{" +
                "bike=" + bike +
                ", customer=" + customer +
                ", saleDate=" + saleDate +
                '}';
    }
}
