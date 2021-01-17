package ro.ubb.lab6.core.validators;


import ro.ubb.lab6.core.exception.BikeShopException;
import ro.ubb.lab6.core.model.BikeType;

import java.util.Arrays;

public class CategoryValidator {

    public static void validate(String category) {

        String message = "";

        boolean anyMatch = Arrays.stream(BikeType.values())
                .anyMatch(bikeType -> bikeType.getBikeType().equalsIgnoreCase(category));

        message += !anyMatch ? "The category you entered is not valid. Please enter: e.g. citybike, mountainbike, electricbike" : "";

        if (!message.equals("")) {

            throw new BikeShopException(message);
        }
    }
}
