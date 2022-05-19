package services;

import models.Restaurant;
import repository.RestaurantRepository;

import java.util.Map;
import java.util.Scanner;

public class RestaurantService {
    private RestaurantRepository restaurantRepository;

    public RestaurantService() {
        this.restaurantRepository = new RestaurantRepository();
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurantRepository.addRestaurant(restaurant);
        System.out.println("The restaurant was added successfully.");
    }

    public void updateRestaurant(Map<Integer, Restaurant> restaurants, Scanner scanner) {
        System.out.println("Existing restaurants:");
        for (Map.Entry<Integer, Restaurant> me : restaurants.entrySet()) {
            System.out.println(me.getKey() + ". " + me.getValue().getName() + " (rating: " + me.getValue().getRating() + ")");
        }
        System.out.println("Please enter the id of the restaurant.");
        String restaurantid = scanner.nextLine();
        System.out.println("Please enter the new address.");
        String address = scanner.nextLine();
        Restaurant restaurant = restaurants.get(Integer.parseInt(restaurantid));
        restaurantRepository.updateAddress(restaurant, address);
        System.out.println("The address was updated successfully.");
    }

    public void deleteRestaurant(Map<Integer, Restaurant> restaurants, Scanner scanner) {
        System.out.println("Existing restaurants:");
        for (Map.Entry<Integer, Restaurant> me : restaurants.entrySet()) {
            System.out.println(me.getKey() + ". " + me.getValue().getName() + " (rating: " + me.getValue().getRating() + ")");
        }
        System.out.println("Please enter the id of the restaurant.");
        String restaurantid = scanner.nextLine();
        Restaurant restaurant = restaurants.get(Integer.parseInt(restaurantid));
        restaurantRepository.deleteRestaurant(restaurant);
        System.out.println("The restaurant was deleted successfully.");
    }

    public Restaurant  buildRestaurant(Scanner scanner) {
        System.out.println("REGISTER A NEW RESTAURANT");
        System.out.println("Please type the following: name/rating/address");
        String line = scanner.nextLine();
        String[] details = line.split("/");
        Restaurant res1 = new Restaurant(details[0], Float.parseFloat(details[1]), details[2]);
        return res1;
    }

    public Map<Integer, Restaurant> getAllRestaurants() {
        return restaurantRepository.getAllRestaurants();
    }
}