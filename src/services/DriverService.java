package services;

import models.Driver;
import repository.DriverRepository;

import java.util.Map;
import java.util.Scanner;

public class DriverService {
    private DriverRepository driverRepository;

    public DriverService() {
        this.driverRepository = new DriverRepository();
    }

    public void addDriver(Driver driver) {
        driverRepository.addDriver(driver);
        System.out.println("The driver was added successfully.");
    }

    public void deleteDriver(Map<Integer, Driver> drivers, Scanner scanner) {
        System.out.println("Existing drivers:");
        for (Map.Entry<Integer, Driver> me : drivers.entrySet()) {
            System.out.println(me.getKey() + ". " + me.getValue().getName());
        }
        System.out.println("Please enter the id of the driver.");
        String driverid = scanner.nextLine();
        Driver driver = drivers.get(Integer.parseInt(driverid));
        driverRepository.deleteDriver(driver);
        System.out.println("The driver was deleted successfully.");
    }

    public void updateDriver(Map<Integer, Driver> drivers, Scanner scanner) {
        System.out.println("Existing drivers:");
        for (Map.Entry<Integer, Driver> me : drivers.entrySet()) {
            System.out.println(me.getKey() + ". " + me.getValue().getName());
        }
        System.out.println("Please enter the id of the driver.");
        String driverid = scanner.nextLine();
        System.out.println("Please enter the new phone number.");
        String phoneNumber = scanner.nextLine();
        Driver driver = drivers.get(Integer.parseInt(driverid));
        driverRepository.updatePhoneNumber(driver, phoneNumber);
        System.out.println("The phone number was updated successfully.");
    }

    public Driver buildDriver(Scanner scanner) {
        System.out.println("REGISTER A NEW DRIVER");
        System.out.println("Please type the following: name/age/e-mail/phone number/rating");
        String line = scanner.nextLine();
        String[] details = line.split("/");
        return new Driver(details[0], Integer.parseInt(details[1]), details[2], details[3], Float.parseFloat(details[4]));
    }

    public Map<Integer, Driver> getAllDrivers() {
        return driverRepository.getAllDrivers();
    }
}
