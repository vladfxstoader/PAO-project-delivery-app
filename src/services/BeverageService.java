package services;

import models.Beverage;
import models.Food;
import repository.BeverageRepository;

import java.util.Map;
import java.util.Scanner;

public class BeverageService {
    private BeverageRepository beverageRepository;

    public BeverageService() {
        this.beverageRepository = new BeverageRepository();
    }

    public void addBeverage(Beverage beverage) {
        beverageRepository.addBeverage(beverage);
        System.out.println("The beverage was added successfully.");
    }

    public Beverage buildBeverage(Scanner scanner) {
        System.out.println("Please type the following: name/price/is alcoholic (true or false)/mililiters");
        String str = scanner.nextLine();
        String[] details = str.split("/");
        Beverage b = new Beverage(details[0], Float.parseFloat(details[1]),Boolean.parseBoolean(details[2]),Integer.parseInt(details[3]));
        return b;
    }

    public void updateBeverage(Map<Integer, Beverage> beverages, Scanner scanner) {
        System.out.println("Beverages available:");
        for (Map.Entry<Integer, Beverage> me : beverages.entrySet()) {
            System.out.println(me.getKey() + ". " + me.getValue());
        }
        System.out.println("Please enter the id of the beverage.");
        String beverageid = scanner.nextLine();
        System.out.println("Please enter the new price.");
        Float price = Float.parseFloat(scanner.nextLine());
        Beverage beverage = beverages.get(Integer.parseInt(beverageid));
        beverageRepository.updatePrice(beverage, price);
        System.out.println("The price was updated successfully.");
    }

    public void deleteBeverage(Map<Integer, Beverage> beverages, Scanner scanner) {
        System.out.println("Beverages available:");
        for (Map.Entry<Integer, Beverage> me : beverages.entrySet()) {
            System.out.println(me.getKey() + ". " + me.getValue());
        }
        System.out.println("Please enter the id of the beverage.");
        String beverageid = scanner.nextLine();
        Beverage beverage = beverages.get(Integer.parseInt(beverageid));
        beverageRepository.deleteBeverage(beverage);
        System.out.println("The beverage was deleted successfully.");
    }

    public void getId(Beverage beverage) {
        beverageRepository.getId(beverage);
    }

    public Map<Integer, Beverage> getAllBeverages() {
        return beverageRepository.getAllBeverages();
    }
}
