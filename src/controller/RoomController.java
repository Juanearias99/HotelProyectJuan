/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connect.MySQLConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Room;

/**
 *
 * @author Lenovo
 */
public class RoomController {

    private final MySQLConnection conection;

    public RoomController() throws SQLException {
        this.conection = new MySQLConnection();
    }

    public void createRoom(int id, int roomNumber, String typeOfRoom, double priceNight, String disponibility, String detailsAmenities) throws SQLException {
        String createHotel = "INSERT INTO hotel(id, name, adress, classification, amenities) Values (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(createHotel)) {
            statement.setInt(1, id);
            statement.setInt(2, roomNumber);
            statement.setString(3, typeOfRoom);
            statement.setDouble(4, priceNight);
            statement.setString(5, disponibility);
            statement.setString(6, detailsAmenities);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insercion exitosa");
            } else {
                System.out.println("No se pudo insertar los datos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void readUsers(int id) throws SQLException {
        String readSQL = "SELECT * FROM room WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readSQL)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("roomNumber"));
                System.out.println(rs.getString("typeOfRoom"));
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void updateUsers(int roomNumber, String typeOfRoom, double priceNight, String disponibility, String detailsAmenities, int id) throws SQLException {
        String updateSQL = "UPDATE room SET name = ?, adress = ?, classification = ?, amenities = ? WHERE id = ?;";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setInt(1, roomNumber);
            statement.setString(2, typeOfRoom);
            statement.setDouble(3, priceNight);
            statement.setString(4, disponibility);
            statement.setString(5, detailsAmenities);
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void deleteUsers(int id) throws SQLException {
        String deleteSQL = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(deleteSQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
    }
}
