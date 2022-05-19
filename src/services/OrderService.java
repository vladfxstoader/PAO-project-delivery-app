package services;

import models.*;
import repository.OrderRepository;

import java.util.*;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    public void addOrder(Order order) {
        orderRepository.addOrder(order);
        System.out.println("The order was placed successfully.");
    }

    public ArrayList<Order> getAllOrders(Map<Integer, User> users, Map<Integer, Driver> drivers, Map<Integer, Restaurant> restaurants, Map<Integer, Food> foods, Map<Integer, Beverage> beverages) {
        return orderRepository.getAllOrders(users, drivers, restaurants, foods, beverages);
    }

    public void getAllOrdersByRestaurant(Scanner scanner, Map<Integer, User> users, Map<Integer, Driver> drivers, Map<Integer, Restaurant> restaurants, Map<Integer, Food> foods, Map<Integer, Beverage> beverages) {
        System.out.println("Existing restaurants:");
        for (Map.Entry<Integer, Restaurant> me : restaurants.entrySet()) {
            System.out.println(me.getKey() + ". " + me.getValue().getName() + " (rating: " + me.getValue().getRating() + ")");
        }
        System.out.println("Please enter the id of the restaurant.");
        String restaurantid = scanner.nextLine();
        orderRepository.getAllOrdersByRestaurant(Integer.parseInt(restaurantid), users, drivers, restaurants, foods, beverages);
    }

    public void getAllOrdersByUser(Scanner scanner, Map<Integer, User> users, Map<Integer, Driver> drivers, Map<Integer, Restaurant> restaurants, Map<Integer, Food> foods, Map<Integer, Beverage> beverages) {
        System.out.println("Existing users:");
        for (Map.Entry<Integer, User> me : users.entrySet()) {
            System.out.println(me.getKey() + ". " + me.getValue().getName());
        }
        System.out.println("Please enter the id of the user.");
        String userid = scanner.nextLine();
        orderRepository.getAllOrdersByUser(Integer.parseInt(userid), users, drivers, restaurants, foods, beverages);
    }

    public void getAllOrdersByDriver(Scanner scanner, Map<Integer, User> users, Map<Integer, Driver> drivers, Map<Integer, Restaurant> restaurants, Map<Integer, Food> foods, Map<Integer, Beverage> beverages) {
        System.out.println("Existing drivers:");
        for (Map.Entry<Integer, Driver> me : drivers.entrySet()) {
            System.out.println(me.getKey() + ". " + me.getValue().getName());
        }
        System.out.println("Please enter the id of the driver.");
        String driverid = scanner.nextLine();
        orderRepository.getAllOrdersByDriver(Integer.parseInt(driverid), users, drivers, restaurants, foods, beverages);
    }

    public Order buildOrder(Scanner scanner, Map<Integer, User> users, Map<Integer, Driver> drivers, Map<Integer, Restaurant> restaurants, Map<Integer, Food> foods, Map<Integer, Beverage> beverages) {
        System.out.println("PLACE A NEW ORDER");
        Order order = null;
        try {
            System.out.println("Existing users:");
            for (Map.Entry<Integer, User> me : users.entrySet()) {
                System.out.println(me.getKey() + ". " + me.getValue().getName());
            }
            System.out.println("Please enter the id of the user.");
            String userid = scanner.nextLine();
            User user = users.get(Integer.parseInt(userid));
            if (user == null) {
                //System.out.println("The user with the id you just entered does not exist.");
                throw new RuntimeException("The user with the id you just entered does not exist.");
            }
            System.out.println("Existing restaurants:");
            for (Map.Entry<Integer, Restaurant> me : restaurants.entrySet()) {
                System.out.println(me.getKey() + ". " + me.getValue().getName() + " (rating: " + me.getValue().getRating() + ")");
            }
            System.out.println("Please enter the id of the restaurant.");
            String restaurantid = scanner.nextLine();
            Restaurant restaurant = restaurants.get(Integer.parseInt(restaurantid));
            if (restaurant == null) {
                //System.out.println("The restaurant with the id you just entered does not exist.");
                throw new RuntimeException("The restaurant with the id you just entered does not exist.");
            }
            System.out.println("Do you want to order food? (yes/no)");
            Food food = null;
            String foodoption = scanner.nextLine();
            if (Objects.equals(foodoption, "yes")) {
                System.out.println("Food available:");
                for (Map.Entry<Integer, Food> me : foods.entrySet()) {
                    System.out.println(me.getKey() + ". " + me.getValue());
                }
                System.out.println("Please enter the id of the food.");
                String foodid = scanner.nextLine();
                Food foodaux = foods.get(Integer.parseInt(foodid));
                if (foodaux == null) {
                    //System.out.println("The food with the id you just entered does not exist.");
                    throw new RuntimeException("The food with the id you just entered does not exist.");
                } else {
                    food = foodaux;
                }
            }
            System.out.println("Do you want to order a beverage? (yes/no)");
            Beverage beverage = null;
            String beverageoption = scanner.nextLine();
            if (Objects.equals(beverageoption, "yes")) {
                System.out.println("Beverages available:");
                for (Map.Entry<Integer, Beverage> me : beverages.entrySet()) {
                    System.out.println(me.getKey() + ". " + me.getValue());
                }
                System.out.println("Please enter the id of the beverage.");
                String beverageid = scanner.nextLine();
                Beverage beverageaux = beverages.get(Integer.parseInt(beverageid));
                if (beverageaux == null) {
                    //System.out.println("The beverage with the id you just entered does not exist.");
                    throw new RuntimeException("The beverage with the id you just entered does not exist.");
                } else {
                    beverage = beverageaux;
                }
            }
            Random rand = new Random();
            Integer driverId = rand.nextInt(drivers.size()) + 1;
            Driver driver = drivers.get(driverId);
            if (beverage != null || food != null) {
                order = new Order(user, restaurant, driver, food, beverage);
                System.out.println("The order was placed successfully.");
            }
            else {
                throw new RuntimeException("The order could not be placed.");
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return order;
    }

}

