package models;

import java.util.Objects;

public class Order implements Comparable<Order> {
    private User user;
    private Restaurant restaurant;
    private Driver driver;
    private Food food;
    private Beverage beverage;

    public Order(User user, Restaurant restaurant, Driver driver, Food food, Beverage beverage) {
        this.user = user;
        this.restaurant = restaurant;
        this.driver = driver;
        this.food = food;
        this.beverage = beverage;
    }

    public Order() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(user, order.user) && Objects.equals(restaurant, order.restaurant) && Objects.equals(driver, order.driver) && Objects.equals(food, order.food) && Objects.equals(beverage, order.beverage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, restaurant, driver, food, beverage);
    }

    @Override
    public String toString() {
        String foodstring = "";
        if (food != null) {
            foodstring = "     food: " + food;
        }

        String beveragestring = "";
        if (beverage != null) {
            beveragestring = "     beverage: " + beverage;
        }

        return "Order placed by " + user.getName() +
                ", from " + restaurant.getName() +
                ", delivered by " + driver.getName() +
               "\n" + foodstring + beveragestring;
    }

    @Override
    public int compareTo(Order o) {
        return this.restaurant.getName().compareTo(o.restaurant.getName());
    }
}
