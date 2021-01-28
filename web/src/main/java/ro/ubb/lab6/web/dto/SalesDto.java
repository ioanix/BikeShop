package ro.ubb.lab6.web.dto;


import lombok.*;
import ro.ubb.lab6.core.model.Sale;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SalesDto {

    private List<Sale> sales;


}
