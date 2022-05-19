package repository;

import config.DatabaseConnection;
import models.Food;
import models.Restaurant;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RestaurantRepository {

    public void addRestaurant(Restaurant restaurant) {
        String query = "insert into restaurant values (null, ?, ?, ?);";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, restaurant.getName());
            statement.setDouble(2, restaurant.getRating());
            statement.setString(3, restaurant.getAddress());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteRestaurant(Restaurant restaurant) {
        String query = "delete from `restaurant` where `id` = ?;";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(query)) {
            statement.setDouble(1, restaurant.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateAddress(Restaurant restaurant, String address) {
        String query = "update `restaurant` set `address` = ? where `id` = ?;";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, address);
            statement.setInt(2, restaurant.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Map<Integer, Restaurant> getAllRestaurants() {
        Map<Integer, Restaurant> map = new HashMap<Integer, Restaurant>();
        String query = "select * from restaurant;";
        try{
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Restaurant restaurant = new Restaurant();
                Integer id = resultSet.getInt(1);
                restaurant.setId(id);
                restaurant.setName(resultSet.getString(2));
                restaurant.setRating(resultSet.getFloat(3));
                restaurant.setAddress(resultSet.getString(4));
                map.put(id, restaurant);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

}
