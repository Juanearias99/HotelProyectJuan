/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connect.MySQLConnection;

import model.Booking;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connect.MySQLConnection;
import java.sql.SQLException;

public class BookingController {

    private MySQLConnection conection;

    public BookingController() throws SQLException {
        this.conection = new MySQLConnection();
    }

    public void createBooking(int id, String dateIn, String dateOut, String reservationStatus, double totalPrice) throws SQLException {
        String createHotel = "INSERT INTO booking(id, dateIn, dateOut, reservationStatus, totalPrice) Values (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(createHotel)) {
            statement.setInt(1, id);
            statement.setString(2, dateIn);
            statement.setString(3, dateOut);
            statement.setString(4, reservationStatus);
            statement.setDouble(5, totalPrice);
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

    public void readBooking(int id) throws SQLException {
        String readSQL = "SELECT * FROM hotel WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readSQL)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("adress"));
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void updateBooking(String dateIn, String dateOut, String reservationStatus, double totalPrice, int id) throws SQLException {
        String updateSQL = "UPDATE booking SET dateIn = ?, dateOut = ?, reservationStatus = ?, totalPrice = ? WHERE id = ?;";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setString(1, dateIn);
            statement.setString(2, dateOut);
            statement.setString(3, reservationStatus);
            statement.setDouble(4, totalPrice);
            statement.setDouble(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void deleteBooking(int id) throws SQLException {
        String deleteSQL = "DELETE FROM booking WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(deleteSQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
    }
}
