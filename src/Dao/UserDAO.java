/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dao.*;
import java.sql.Connection;
import java.sql.ResultSet;
import connect.MySQLConnection;
import singleton.Singleton;
import exception.EmailDuplicateException;
import exception.IdDuplicateException;
import exception.IdValidationException;
import exception.PhoneDuplicateException;
import model.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import validation.EmailValidation;

/**
 *
 * @author Lenovo
 */
public class UserDAO {

    MySQLConnection conection;
    private Connection connection;

    public UserDAO() {

        connection = Singleton.getInstance().getconnection();

    }

    public boolean readDuplicateId(long id) {
        String checkIdSQL = "SELECT id FROM user WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(checkIdSQL)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean readDuplicateEmail(String email) {
        String readEmailSQL = "SELECT email FROM user WHERE email = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readEmailSQL)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean readDuplicatePhone(String phoneNumber) {
        String readPhoneSQL = "SELECT phoneNumber FROM user WHERE phoneNumber = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readPhoneSQL)) {
            statement.setString(1, phoneNumber);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validatingAtSign(String email) {
        String validatingSQL = "SELECT email FROM user WHERE email = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(validatingSQL)) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createUsers(User user) throws SQLException, IdDuplicateException, EmailDuplicateException, PhoneDuplicateException {

        if (readDuplicateId(user.getId())) {
            throw new IdDuplicateException();
        }

        if (readDuplicateEmail(user.getEmail())) {
            throw new EmailDuplicateException();
        }

        if (readDuplicatePhone(user.getPhoneNumber())) {
            throw new PhoneDuplicateException();
        }

        String createSQL = "INSERT INTO user(id, username, email, password, phoneNumber, rol) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(createSQL)) {
            statement.setLong(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhoneNumber());
            statement.setString(6, user.getRol());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insercion exitosa");
            } else {
                System.out.println("No se pudo insertar los datos");

            }
        } catch (SQLException e) {
            System.out.println("Ocurrio un error al realizar la insercion en la base de datos");
            e.printStackTrace();
        }
    }

    public User readUsers(long id) throws SQLException {
        String readSQL = "SELECT * FROM user WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readSQL)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("username"));
                System.out.println(rs.getString("email"));
                User user = new User(rs.getLong("id"), rs.getString("username"),
                        rs.getString("email"), rs.getString("password"),
                        rs.getString("phoneNumber"), rs.getString("rol"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
        return null;
    }

    public void updateUsers(User user) throws SQLException {
        String updateSQL = "UPDATE user SET username = ?, email = ?, password = ?, phoneNumber = ?, rol = ? WHERE id = ?;";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getRol());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void deleteUsers(long id) throws SQLException {
        String deleteSQL = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(deleteSQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
    }

    public User authenticUser(String email, String password) throws SQLException {
        User user = null;
        String authenticSQL = "SELECT * FROM user WHERE email = ? AND password = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(authenticSQL)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                long id = rs.getLong("id");
                String username = rs.getString("username");
                String phoneNumber = rs.getString("phoneNumber");
                String rol = rs.getString("rol");
                user = new User(id, username, email, password, phoneNumber, rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
