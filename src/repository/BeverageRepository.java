package repository;

import config.DatabaseConnection;
import models.Beverage;
import models.Food;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BeverageRepository {

    public void addBeverage(Beverage beverage) {
        String query = "insert into beverage values (null, ?, ?, ?, ?);";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, beverage.getName());
            statement.setDouble(2, beverage.getPrice());
            statement.setString(3, String.valueOf(beverage.getAlcoholic()));
            statement.setString(4, String.valueOf(beverage.getMl()));
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updatePrice(Beverage beverage, Float price) {
        String query = "update `beverage` set `price` = ? where `id` = ?;";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(query)) {
            statement.setFloat(1, price);
            statement.setInt(2, beverage.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteBeverage(Beverage beverage) {
        String query = "delete from `beverage` where `id` = ?;";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(query)) {
            statement.setDouble(1, beverage.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Map<Integer, Beverage> getAllBeverages() {
        Map map = new HashMap<Integer, Beverage>();
        String query = "select * from beverage;";
        try{
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Beverage beverage = new Beverage();
                Integer id = resultSet.getInt(1);
                beverage.setId(id);
                beverage.setName(resultSet.getString(2));
                beverage.setPrice(resultSet.getFloat(3));
                beverage.setAlcoholic(resultSet.getBoolean(4));
                beverage.setMl(resultSet.getInt(5));
                map.put(id, beverage);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public void getId(Beverage beverage) {
        String query = "select * from beverage b where b.name = ? and b.price = ? and b.alcoholic = ? and b.ml = ?;";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, beverage.getName());
            statement.setDouble(2, beverage.getPrice());
            statement.setString(3, String.valueOf(beverage.getAlcoholic()));
            statement.setString(4, String.valueOf(beverage.getMl()));

            ResultSet result = statement.executeQuery();

            while(result.next()) {
                int beverageId = result.getInt(1);
                System.out.println(beverageId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
