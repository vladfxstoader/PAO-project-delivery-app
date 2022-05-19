package main;

import models.*;
import services.*;

import java.io.IOException;
import java.util.*;

public class Application {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws IOException {

        UserService userService = new UserService();
        DriverService driverService = new DriverService();
        FoodService foodService = new FoodService();
        BeverageService beverageService = new BeverageService();
        RestaurantService restaurantService = new RestaurantService();
        OrderService orderService = new OrderService();
        AuditService auditService = new AuditService();
        Scanner scanner = new Scanner(System.in);

        Map<Integer, Beverage> beverages = beverageService.getAllBeverages();
        Map<Integer, Food> foods = foodService.getAllFood();
        Map<Integer, User> users = userService.getAllUsers();
        Map<Integer, Driver> drivers = driverService.getAllDrivers();
        Map<Integer, Restaurant> restaurants = restaurantService.getAllRestaurants();
        ArrayList<Order> orders = orderService.getAllOrders(users, drivers, restaurants, foods, beverages);
        Collections.sort(orders);

        System.out.println(ANSI_BLUE + "WELCOME TO OUR DELIVERY APP!" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "Please choose one of the following options:" + ANSI_RESET);

        while(true) {
            System.out.println();
            System.out.println();
            System.out.println(ANSI_BLUE + "MAIN MENU" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "1. Display menu" + ANSI_RESET);
            System.out.println(ANSI_WHITE + "2. Add menu" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "3. Update menu" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "4. Delete menu" + ANSI_RESET);
            System.out.println(ANSI_RED + "0. Exit" + ANSI_RESET);
            System.out.print("Your option is: ");
            String option = scanner.nextLine();
            switch(option) {
                case "0" : {
                    System.out.println(ANSI_BLUE + "Goodbye!" + ANSI_RESET);
                    System.exit(0);
                }
                case "1" : {
                    int ok = 1;
                    while (ok == 1) {
                        System.out.println();
                        System.out.println();
                        System.out.println(ANSI_GREEN + "1. Display all users");
                        System.out.println("2. Display all drivers");
                        System.out.println("3. Display all restaurants");
                        System.out.println("4. Display all products");
                        System.out.println("5. Display all orders");
                        System.out.println("6. Display orders placed by an user");
                        System.out.println("7. Display orders delivered by a driver");
                        System.out.println("8. Display orders prepared by a restaurant");
                        System.out.println("0. Go back to main menu" + ANSI_RESET);
                        System.out.print("Your option is: ");
                        String displayOption = scanner.nextLine();
                        switch (displayOption) {
                            case "0": {
                                ok = 0;
                                break;
                            }
                            case "1": {
                                for (Map.Entry<Integer, User> me : users.entrySet()) {
                                    System.out.println(me.getKey() + ". " + me.getValue());
                                }
                                auditService.addAction("Print all users");
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "2": {
                                for (Map.Entry<Integer, Driver> me : drivers.entrySet()) {
                                    System.out.println(me.getKey() + ". " + me.getValue());
                                }
                                auditService.addAction("Print all drivers");
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "3": {
                                for (Map.Entry<Integer, Restaurant> me : restaurants.entrySet()) {
                                    System.out.println(me.getKey() + ". " + me.getValue());
                                }
                                auditService.addAction("Print all restaurants");
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "4": {
                                for (Map.Entry<Integer, Food> me : foods.entrySet()) {
                                    System.out.println(me.getKey() + ". " + me.getValue());
                                }
                                for (Map.Entry<Integer, Beverage> me : beverages.entrySet()) {
                                    System.out.println(me.getKey() + ". " + me.getValue());
                                }
                                auditService.addAction("Print all products");
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "5": {
                                for (Order o : orders) {
                                    System.out.println(o);
                                }
                                auditService.addAction("Print all orders");
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "6" : {
                                orderService.getAllOrdersByUser(scanner, users, drivers, restaurants, foods, beverages);
                                auditService.addAction("Print all orders placed by an user");
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "7" : {
                                orderService.getAllOrdersByDriver(scanner, users, drivers, restaurants, foods, beverages);
                                auditService.addAction("Print all orders delivered by a driver");
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "8" : {
                                orderService.getAllOrdersByRestaurant(scanner, users, drivers, restaurants, foods, beverages);
                                auditService.addAction("Print all orders prepared by a restaurant");
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            default: {
                                System.out.println("Your option is invalid. Please try again.");
                            }
                        }
                    }
                    break;
                }
                case "2" : {
                    int ok = 1;
                    while (ok == 1) {
                        System.out.println();
                        System.out.println();
                        System.out.println(ANSI_WHITE + "1. Register a new user");
                        System.out.println("2. Register a new driver");
                        System.out.println("3. Enter a new restaurant");
                        System.out.println("4. Enter a new product");
                        System.out.println("5. Place a new order");
                        System.out.println("0. Go back to main menu" + ANSI_RESET);
                        System.out.print("Your option is: ");
                        String addOption = scanner.nextLine();
                        switch (addOption) {
                            case "0" : {
                                ok = 0;
                                break;
                            }
                            case "1" : {
                                userService.addUser(userService.buildUser(scanner));
                                auditService.addAction("Add a new user");
                                users = userService.getAllUsers();
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "2" : {
                                driverService.addDriver(driverService.buildDriver(scanner));
                                auditService.addAction("Add a new driver");
                                drivers = driverService.getAllDrivers();
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "3" : {
                                restaurantService.addRestaurant(restaurantService.buildRestaurant(scanner));
                                auditService.addAction("Add a new restaurant");
                                restaurants = restaurantService.getAllRestaurants();
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "4" : {
                                System.out.println("Please enter the type of product: food/beverage");
                                String productoption = scanner.nextLine();
                                switch (productoption) {
                                    case "food" : {
                                        foodService.addFood(foodService.buildFood(scanner));
                                        foods = foodService.getAllFood();
                                        break;
                                    }
                                    case "beverage" : {
                                        beverageService.addBeverage(beverageService.buildBeverage(scanner));
                                        beverages = beverageService.getAllBeverages();
                                        break;
                                    }
                                    default:
                                        System.out.println("You have entered a wrong type of product.");
                                }
                                auditService.addAction("Add a new product");
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "5" : {
                                orderService.addOrder(orderService.buildOrder(scanner, users, drivers, restaurants, foods, beverages));
                                auditService.addAction("Add a new order");
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            default: {
                                System.out.println("Your option is invalid. Please try again.");
                            }
                        }
                    }
                    break;
                }
                case "3" : {
                    int ok = 1;
                    while (ok == 1) {
                        System.out.println();
                        System.out.println();
                        System.out.println(ANSI_PURPLE + "1. Update email of an user");
                        System.out.println("2. Update phone number of a driver");
                        System.out.println("3. Update price of a product");
                        System.out.println("4. Update address of a restaurant");
                        System.out.println("0. Go back to main menu" + ANSI_RESET);
                        System.out.print("Your option is: ");
                        String updateOption = scanner.nextLine();
                        switch (updateOption) {
                            case "0" : {
                                ok = 0;
                                break;
                            }
                            case "1" : {
                                userService.updateUser(users, scanner);
                                auditService.addAction("Update email of an user");
                                System.out.println("Press enter to continue...");
                                users = userService.getAllUsers();
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "2" : {
                                driverService.updateDriver(drivers, scanner);
                                auditService.addAction("Update phone number of a driver");
                                System.out.println("Press enter to continue...");
                                drivers = driverService.getAllDrivers();
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "3" : {
                                System.out.println("Please enter the type of product: food/beverage");
                                String productoption = scanner.nextLine();
                                switch (productoption) {
                                    case "food" : {
                                        foodService.updateFood(foods, scanner);
                                        foods = foodService.getAllFood();
                                        break;
                                    }
                                    case "beverage" : {
                                        beverageService.updateBeverage(beverages, scanner);
                                        beverages = beverageService.getAllBeverages();
                                        break;
                                    }
                                    default:
                                        System.out.println("You have entered a wrong type of product.");
                                }
                                auditService.addAction("Update price of a product");
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "4" : {
                                restaurantService.updateRestaurant(restaurants, scanner);
                                auditService.addAction("Update address of a restaurant");
                                System.out.println("Press enter to continue...");
                                restaurants = restaurantService.getAllRestaurants();
                                String aux = scanner.nextLine();
                                break;
                            }
                            default : {
                                System.out.println("Your option is invalid. Please try again.");
                            }
                        }
                    }
                    break;
                }
                case "4" : {
                    int ok = 1;
                    while (ok == 1) {
                        System.out.println();
                        System.out.println();
                        System.out.println(ANSI_YELLOW + "1. Delete an user");
                        System.out.println("2. Delete a driver");
                        System.out.println("3. Delete a product");
                        System.out.println("4. Delete a restaurant");
                        System.out.println("0. Go back to main menu" + ANSI_RESET);
                        System.out.print("Your option is: ");
                        String updateOption = scanner.nextLine();
                        switch (updateOption) {
                            case "0" : {
                                ok = 0;
                                break;
                            }
                            case "1" : {
                                userService.deleteUser(users, scanner);
                                auditService.addAction("Delete an user");
                                System.out.println("Press enter to continue...");
                                users = userService.getAllUsers();
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "2" : {
                                driverService.deleteDriver(drivers, scanner);
                                auditService.addAction("Delete a driver");
                                System.out.println("Press enter to continue...");
                                drivers = driverService.getAllDrivers();
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "3" : {
                                System.out.println("Please enter the type of product: food/beverage");
                                String productoption = scanner.nextLine();
                                switch (productoption) {
                                    case "food" : {
                                        foodService.deleteFood(foods, scanner);
                                        foods = foodService.getAllFood();
                                        break;
                                    }
                                    case "beverage" : {
                                        beverageService.deleteBeverage(beverages, scanner);
                                        beverages = beverageService.getAllBeverages();
                                        break;
                                    }
                                    default:
                                        System.out.println("You have entered a wrong type of product.");
                                }
                                auditService.addAction("Delete a product");
                                System.out.println("Press enter to continue...");
                                String aux = scanner.nextLine();
                                break;
                            }
                            case "4" : {
                                restaurantService.deleteRestaurant(restaurants, scanner);
                                auditService.addAction("Delete a restaurant");
                                System.out.println("Press enter to continue...");
                                restaurants = restaurantService.getAllRestaurants();
                                String aux = scanner.nextLine();
                                break;
                            }
                            default : {
                                System.out.println("Your option is invalid. Please try again.");
                            }
                        }
                    }
                    break;
                }
                default : {
                    System.out.println("Your option is invalid. Please try again.");
                }
            }

        }
    }
    }
