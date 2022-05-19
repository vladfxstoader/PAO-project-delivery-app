package services;

import models.Food;
import repository.FoodRepository;

import java.util.Map;
import java.util.Scanner;

public class FoodService {
    private FoodRepository foodRepository;

    public FoodService() {
        this.foodRepository = new FoodRepository();
    }

    public void addFood(Food food) {
        foodRepository.addFood(food);
        System.out.println("The food was added successfully.");
    }

    public Food buildFood(Scanner scanner) {
        System.out.println("Please type the following: name/price/is vegan (true or false)/preparation time/size");
        String str = scanner.nextLine();
        String[] details = str.split("/");
        return new Food(details[0],Float.parseFloat(details[1]),Boolean.parseBoolean(details[2]),Integer.parseInt(details[3]),Integer.parseInt(details[4]));
    }

    public void updateFood(Map<Integer, Food> food, Scanner scanner) {
        System.out.println("Food available:");
        for (Map.Entry<Integer, Food> me : food.entrySet()) {
            System.out.println(me.getKey() + ". " + me.getValue());
        }
        System.out.println("Please enter the id of the food.");
        String foodid = scanner.nextLine();
        System.out.println("Please enter the new price.");
        Float price = Float.parseFloat(scanner.nextLine());
        Food foodaux = food.get(Integer.parseInt(foodid));
        foodRepository.updatePrice(foodaux, price);
        System.out.println("The price was updated successfully.");
    }

    public void deleteFood(Map<Integer, Food> food, Scanner scanner) {
        System.out.println("Food available:");
        for (Map.Entry<Integer, Food> me : food.entrySet()) {
            System.out.println(me.getKey() + ". " + me.getValue());
        }
        System.out.println("Please enter the id of the food.");
        String foodid = scanner.nextLine();
        Food foodaux = food.get(Integer.parseInt(foodid));
        foodRepository.deleteFood(foodaux);
        System.out.println("The food was deleted successfully.");
    }

    public Map<Integer, Food> getAllFood() {
        return foodRepository.getAllFood();
    }
}
