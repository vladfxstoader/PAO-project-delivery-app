package services;

import models.User;
import repository.UserRepository;

import java.util.Map;
import java.util.Scanner;

public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public void addUser(User user) {
        userRepository.addUser(user);
    }

    public void deleteUser(Map<Integer, User> users, Scanner scanner) {
        System.out.println("Existing users:");
        for (Map.Entry<Integer, User> me : users.entrySet()) {
            System.out.println(me.getKey() + ". " + me.getValue().getName());
        }
        System.out.println("Please enter the id of the user.");
        String userid = scanner.nextLine();
        User user = users.get(Integer.parseInt(userid));
        userRepository.deleteUser(user);
        System.out.println("The user was deleted successfully.");
    }

    public void updateUser(Map<Integer, User> users, Scanner scanner) {
        System.out.println("Existing users:");
        for (Map.Entry<Integer, User> me : users.entrySet()) {
            System.out.println(me.getKey() + ". " + me.getValue().getName());
        }
        System.out.println("Please enter the id of the user.");
        String userid = scanner.nextLine();
        System.out.println("Please enter the new email.");
        String email = scanner.nextLine();
        User user = users.get(Integer.parseInt(userid));
        userRepository.updateEmail(user, email);
        System.out.println("The email was updated successfully.");
    }

    public User buildUser(Scanner scanner) {
        System.out.println("REGISTER A NEW USER");
        System.out.println("Please type the following: name/age/e-mail/phone number/address");
        String line = scanner.nextLine();
        String[] details = line.split("/");
        return new User(details[0], Integer.parseInt(details[1]),details[2],details[3],details[4]);
    }

    public Map<Integer, User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
