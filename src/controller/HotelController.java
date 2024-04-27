/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Hotel;
import java.sql.ResultSet;
import connect.MySQLConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connect.MySQLConnection;

/**
 *
 * @author Lenovo
 */
public class HotelController {

    private final MySQLConnection conection;

    public HotelController() throws SQLException {
        this.conection = new MySQLConnection();
    }

    public void createHotel(int id, String name, String adress, String classification, String amenities, String images) throws SQLException {
        String createHotel = "INSERT INTO hotel(id, name, adress, classification, amenities, images) Values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(createHotel)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, adress);
            statement.setString(4, classification);
            statement.setString(5, amenities);
            statement.setString(6, images);

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

    public void readHotel(int id) throws SQLException {
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

    public void updateHotel(String name, String adress, String classification, String amenities, int id) throws SQLException {
        String updateSQL = "UPDATE hotel SET name = ?, adress = ?, classification = ?, amenities = ? WHERE id = ?;";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setString(1, name);
            statement.setString(2, adress);
            statement.setString(3, classification);
            statement.setString(4, amenities);
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void deleteHotel(int id) throws SQLException {
        String deleteSQL = "DELETE FROM hotel WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(deleteSQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
    }
}

