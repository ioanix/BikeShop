package ro.ubb.lab6.client.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import ro.ubb.lab6.core.exception.BikeShopException;
import ro.ubb.lab6.core.model.BikeType;
import ro.ubb.lab6.web.dto.BikeDto;
import ro.ubb.lab6.web.dto.BikesDto;
import ro.ubb.lab6.web.dto.CustomerDto;
import ro.ubb.lab6.web.dto.CustomersDto;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.System.out;

public class Console {

    @Autowired
    private RestTemplate restTemplate;

    private Scanner in = new Scanner(System.in);

    private String urlBike = "http://localhost:8080/api/bikes";
    private String urlCustomer = "http://localhost:8080/api/customers";


    private void showMainMenu() {

        System.out.println("1. Bikes");
        System.out.println("2. Customers");
        //System.out.println("3. Sales");
        System.out.println("x. Exit");
        System.out.println("Option: ");
    }

    private void showBikesMenu() {

        System.out.println("1. Add bike");
        System.out.println("2. Print all bikes");
        System.out.println("3. Search bike by id");
        System.out.println("4. Delete bike");
        System.out.println("5. Update bike");
        System.out.println("6. Show top 3 most expensive bikes");
        System.out.println("7. Search bike by name");
        System.out.println("8. Search bike by type");
        System.out.println("9. Show bikes ordered by price");
        System.out.println("10. Return to the main menu");
        System.out.println("Option: ");

    }

    private void showCustomersMenu() {

        System.out.println("1. Add customer");
        System.out.println("2. Print all customers");
        System.out.println("3. Search customer by id");
        System.out.println("4. Delete customer");
        System.out.println("5. Update customer");
        System.out.println("6. Show customers from a specific city");
        System.out.println("7. Show customers by last name sorted in descending order");
        System.out.println("8. Return to the main menu");
        System.out.println("Option: ");

    }

    private void bikes_menu() {

        while (true) {

            this.showBikesMenu();

            String option = in.nextLine();
            switch (option) {

                case "1":
                    this.handleAddBike();
                    break;
                case "2":
                    this.handleShowAllBikes();
                    break;
                case "3":
                    this.handleSearchBikeByID();
                    break;
                case "4":
                    this.handleDeleteBikeByID();
                    break;
                case "5":
                    this.handleUpdateBike();
                    break;
                case "6":
                    this.handleShowMostExpensiveBike();
                    break;
                case "7":
                    this.handleSearchBikeByName();
                    break;
                case "8":
                    this.handleSearchBikeByType();
                    break;
                case "9":
                    this.handleShowBikesOrderedByPrice();
                    break;
                case "10":
                    return;

                default:
                    System.out.println("Invalid order");
                    break;
            }
        }
    }

    private void handleShowMostExpensiveBike() {

        BikesDto bikeDtos = restTemplate.getForObject(urlBike + "/top3", BikesDto.class);

        bikeDtos.getBikes().forEach(out::println);
    }

    private void handleSearchBikeByType() {

        try {

            out.println("Enter the type: ");
            String type = in.nextLine();

            boolean anyMatch = Arrays.stream(BikeType.values())
                    .anyMatch(bikeType -> bikeType.getBikeType().equalsIgnoreCase(type.toUpperCase()));

            if (!anyMatch) {

                System.out.println("The category you entered is not valid. Please enter: e.g. citybike, mountainbike, electricbike");
                return;
            }

            BikeType bikeType = BikeType.valueOf(type.toUpperCase());

            BikesDto bikeDtos = restTemplate.getForObject(urlBike + "/type/{type}", BikesDto.class, bikeType);

            bikeDtos.getBikes().forEach(out::println);

        } catch (BikeShopException e) {

            out.println(e.getMessage());

        }
    }

    private void handleShowBikesOrderedByPrice() {

        BikesDto bikeDtos = restTemplate.getForObject(urlBike + "/descbyprice", BikesDto.class);

        bikeDtos.getBikes().forEach(out::println);

    }


    private void handleUpdateBike() {

        try {

            System.out.println("Id :");
            Long id = in.nextLong();
            in.nextLine();

            System.out.println("Name: ");
            String name = in.nextLine();

            System.out.println("Bike type: ");
            BikeType type = BikeType.valueOf(in.nextLine().toUpperCase());

            System.out.println("Price: ");
            double price = in.nextDouble();
            in.nextLine();

            BikeDto update = new BikeDto(name, type, price);
            update.setId(id);

            restTemplate.put(urlBike + "/{id}", update, id);

            Optional.ofNullable(update).ifPresentOrElse(b -> System.out.println("Bike with id " + id + " updated successfully!"),
                    () -> System.out.println("Nothing to update"));

        } catch (Exception ignored) {

        }
    }

    private void handleSearchBikeByName() {

        try {

            out.println("Enter the name: ");
            String name = in.nextLine();

            BikesDto bikes = restTemplate.getForObject(urlBike + "/name/{name}", BikesDto.class, name);

            bikes.getBikes().forEach(out::println);

        } catch (BikeShopException e) {

            out.println(e.getMessage());

        }
    }

    private void handleDeleteBikeByID() {

        try {

            System.out.println("Enter the id of the bike you want to delete: ");
            Long id = in.nextLong();
            in.nextLine();

            restTemplate.delete(urlBike + "/{id}", id);
            //out.println(restTemplate.getForEntity(urlBike + "/{id}", Bike.class, id));
            out.println("Bike with id " + id + " was deleted");

        } catch (Exception e) {

            System.out.println("Error occured, the id does not exist");
        }
    }

    private void handleAddBike() {

        try {

            System.out.println("Name: ");
            String name = in.nextLine();

            System.out.println("Bike type: ");

            String type = in.nextLine();
            boolean anyMatch = Arrays.stream(BikeType.values())
                    .anyMatch(bikeType -> bikeType.getBikeType().equalsIgnoreCase(type.toUpperCase()));

            if (!anyMatch) {

                System.out.println("The category you entered is not valid. Please enter: e.g. citybike, mountainbike, electricbike");
                return;
            }

            BikeType bikeType = BikeType.valueOf(type.toUpperCase());

            System.out.println("Price: ");
            double price = in.nextDouble();
            in.nextLine();

            BikeDto newBike = new BikeDto(name, bikeType, price);

            BikeDto savedBike = restTemplate.postForObject(urlBike, newBike, BikeDto.class);

            Optional.ofNullable(savedBike).ifPresentOrElse(b -> System.out.println("Bike was added successfully."),
                    () -> System.out.println("Could not add bike"));

        } catch (Exception e) {

            out.println(e.getMessage());
        }
    }

    private void handleSearchBikeByID() {

        try {

            System.out.println("Id: ");
            Long id = in.nextLong();
            in.nextLine();

            BikeDto bike = restTemplate.getForObject(urlBike + "/{id}", BikeDto.class, id);

            out.println(bike);

        } catch (Exception e) {

            System.out.println("Error occured, the id does not exist");
        }
    }

    private void handleShowAllBikes() {

        System.out.println("getting bikes...");
        BikesDto bikes = restTemplate.getForObject(urlBike, BikesDto.class);

        bikes.getBikes().forEach(System.out::println);

    }

    private void customers_menu() {

        while (true) {

            this.showCustomersMenu();

            String option = in.nextLine();
            switch (option) {

                case "1":
                    handleAddCustomer();
                    break;
                case "2":
                    handleShowAllCustomers();
                    break;
                case "3":
                    handleSearchCustomerById();
                    break;
                case "4":
                    handleDeleteCustomerByID();
                    break;
                case "5":
                    handleUpdateCustomer();
                    break;
                case "6":
                    handleSearchCustomersFromSpecificCity();
                    break;
                case "7":
                    handleShowCustomersOrderedByLastNameDesc();
                    break;
                case "8": {
                    return;
                }
                default:
                    System.out.println("Invalid order");
                    break;
            }
        }
    }

    private void handleShowCustomersOrderedByLastNameDesc() {

        try {

            CustomersDto customerDtos = restTemplate.getForObject(urlCustomer + "/sortbylastnamedesc", CustomersDto.class);

            customerDtos.getCustomers().forEach(out::println);

        } catch (BikeShopException bex) {

            out.println(bex.getMessage());
        }
    }

    private void handleSearchCustomersFromSpecificCity() {

        try {

            System.out.println("Please enter city: ");
            String city = in.nextLine();

            CustomersDto customersDto = restTemplate.getForObject(urlCustomer + "/ascbylastname/{city}", CustomersDto.class, city);

            customersDto.getCustomers().forEach(out::println);

        } catch (BikeShopException bex) {

            out.println(bex.getMessage());
        }
    }

    private void handleUpdateCustomer() {

        try {

            System.out.println("Id: ");
            Long id = in.nextLong();
            in.nextLine();

            System.out.println("First name: ");
            String firstName = in.nextLine();

            System.out.println("Last name: ");
            String lastName = in.nextLine();

            System.out.println("Phone: ");
            String phone = in.nextLine();

            System.out.println("City: ");
            String city = in.nextLine();

            System.out.println("Street: ");
            String street = in.nextLine();

            System.out.println("Number: ");
            String number = in.nextLine();

            CustomerDto update = new CustomerDto(
                    firstName,
                    lastName,
                    phone,
                    city,
                    street,
                    number);
            update.setId(id);

            restTemplate.put(urlCustomer + "/{id}", update, id);

            Optional.ofNullable(update).ifPresentOrElse(c -> System.out.println("Customer with id " + id + " updated successfully!"),
                    () -> System.out.println("Nothing to update"));

        } catch (Exception bex) {

            System.out.println(bex.getMessage());
        }

    }

    private void handleDeleteCustomerByID() {

        try {

            System.out.println("Enter the id of the customer you want to delete: ");
            Long id = in.nextLong();
            in.nextLine();

            restTemplate.delete(urlCustomer + "/{id}", id);
            out.println("Customer with id " + id + " was deleted.");

        } catch (Exception e) {

            System.out.println("Error occurred, the id does not exist");
        }
    }

    private void handleShowAllCustomers() {

        System.out.println("getting customers...");
        CustomersDto customers = restTemplate.getForObject(urlCustomer, CustomersDto.class);

        customers.getCustomers()
                .forEach(out::println);
    }

    private void handleSearchCustomerById() {

        System.out.println("Customer id: ");
        Long id = in.nextLong();
        in.nextLine();

        try {

            CustomerDto customer = restTemplate.getForObject(urlCustomer + "/{id}", CustomerDto.class, id);
            out.println(customer);

        } catch (Exception e) {

            System.out.println("Error occurred, the id does not exist");
        }

    }

    private void handleAddCustomer() {

        try {

            System.out.println("First name: ");
            String firstName = in.nextLine();

            System.out.println("Last name: ");
            String lastName = in.nextLine();

            System.out.println("Phone: ");
            String phone = in.nextLine();

            System.out.println("City: ");
            String city = in.nextLine();

            System.out.println("Street: ");
            String street = in.nextLine();

            System.out.println("Number: ");
            String number = in.nextLine();

            CustomerDto newCustomer = new CustomerDto(
                    firstName,
                    lastName,
                    phone,
                    city,
                    street,
                    number
            );

            CustomerDto savedCustomer = restTemplate.postForObject(urlCustomer, newCustomer, CustomerDto.class);

            Optional.ofNullable(savedCustomer).ifPresentOrElse(c -> System.out.println("Customer was added successfully."),
                    () -> System.out.println("Could not add customer"));

        } catch (Exception e) {

            out.println(e.getMessage());
        }
    }

    public void runConsole() {

        while (true) {

            showMainMenu();

            String option = in.nextLine();
            switch (option) {
                case "1":
                    this.bikes_menu();
                    break;

                case "2":
                    this.customers_menu();
                    break;

                //case "3" -> this.sales_menu();

                case "x": {
                    return;
                }

                default:
                    System.out.println("Invalid order");
                    break;
            }
        }
    }
}
