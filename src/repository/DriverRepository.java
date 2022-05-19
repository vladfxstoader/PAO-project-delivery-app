package repository;

import config.DatabaseConnection;
import models.Driver;
import models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DriverRepository {

    public void addDriver(Driver driver) {
        String query = "insert into driver values (null, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, driver.getName());
            statement.setDouble(2, driver.getAge());
            statement.setString(3, driver.getEmail());
            statement.setString(4, driver.getPhoneNumber());
            statement.setString(5, String.valueOf(driver.getRating()));
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updatePhoneNumber(Driver driver, String phoneNumber) {
        String query = "update `driver` set `phoneNumber` = ? where `id` = ?;";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(query)) {
            statement.setString(1, phoneNumber);
            statement.setDouble(2, driver.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteDriver(Driver driver) {
        String query = "delete from `driver` where `id` = ?;";
        try(PreparedStatement statement = DatabaseConnection.getInstance().prepareStatement(query)) {
            statement.setDouble(1, driver.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Map<Integer, Driver> getAllDrivers() {
        Map<Integer, Driver> map = new HashMap<Integer, Driver>();
        String query = "select * from driver";
        try{
            PreparedStatement preparedStatement = DatabaseConnection.getInstance().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Driver driver = new Driver();
                Integer id = resultSet.getInt(1);
                driver.setId(id);
                driver.setName(resultSet.getString(2));
                driver.setAge(resultSet.getInt(3));
                driver.setEmail(resultSet.getString(4));
                driver.setPhoneNumber(resultSet.getString(5));
                driver.setRating(resultSet.getFloat(6));
                map.put(id, driver);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
}
