package repository;

import config.DatabaseConnection;
import models.Food;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FoodRepository {

    public void addFood(Food food) {
        String query = "insert into food values (null, ?, ?, ?, ?, ?);";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, food.getName());
            statement.setDouble(2, food.getPrice());
            statement.setString(3, String.valueOf(food.getIsVegan()));
            statement.setString(4, String.valueOf(food.getPrepareTime()));
            statement.setString(5, String.valueOf(food.getSize()));
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updatePrice(Food food, Float price) {
        String query = "update `food` set `price` = ? where `id` = ?;";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(query)) {
            statement.setFloat(1, price);
            statement.setInt(2, food.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteFood(Food food) {
        String query = "delete from `food` where `id` = ?;";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(query)) {
            statement.setDouble(1, food.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Map<Integer, Food> getAllFood() {
        Map<Integer, Food> map = new HashMap<Integer, Food>();
        String query = "select * from food;";
        try{
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Food food = new Food();
                Integer id = resultSet.getInt(1);
                food.setId(id);
                food.setName(resultSet.getString(2));
                food.setPrice(resultSet.getFloat(3));
                food.setIsVegan(resultSet.getBoolean(4));
                food.setPrepareTime(resultSet.getInt(5));
                food.setSize(resultSet.getInt(6));
                map.put(id, food);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

}
