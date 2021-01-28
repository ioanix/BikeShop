package ro.ubb.lab6.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.lab6.core.model.Sale;
import ro.ubb.lab6.web.dto.SaleDto;

@Component
public class SaleConverter extends AbstractConverter<Sale, SaleDto>{

//    public List<SaleDto> convertModelsToDtos(Collection<Sale> models) {
//        return models.stream()
//                .map(model -> convertModelToDto(model))
//                .collect(Collectors.toList());
//    }


    @Override
    public Sale convertDtoToModel(SaleDto saleDto) {

        return null;
    }

    public SaleDto convertModelToDto(Sale sale) {

        SaleDto saleDto = new SaleDto(sale.getBike().getId(), sale.getCustomer().getId(), sale.getSaleDate());

        return saleDto;
    }
}
