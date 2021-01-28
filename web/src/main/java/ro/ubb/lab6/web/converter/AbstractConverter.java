package ro.ubb.lab6.web.converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<Model, Dto>
        implements Converter<Model, Dto> {

//    public List<Long> convertModelsToIDs(Set<Model> models) {
//        return models.stream()
//                .map(model -> model.getId())
//                .collect(Collectors.toList());
//    }
//
//    public List<Long> convertDTOsToIDs(Set<Dto> dtos) {
//        return dtos.stream()
//                .map(dto -> dto.getId())
//                .collect(Collectors.toList());
//    }

    public List<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toList());
    }

    public List<Model> convertDtosToModel(Collection<Dto> dtos) {
        return dtos.stream()
                .map(this::convertDtoToModel)
                .collect(Collectors.toList());
    }
}
