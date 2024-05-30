/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dao.*;
import java.sql.Connection;
import connect.MySQLConnection;
import singleton.Singleton;
import exception.DateValidationException;
import exception.IdDuplicateException;
import exception.NumberRoomDuplicateException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Booking;
import model.Hotel;
import model.Room;

/**
 *
 * @author Lenovo
 */
public class RoomDAO {

    MySQLConnection conection;
    private Connection connection;

    public RoomDAO() {
        connection = Singleton.getInstance().getconnection();
        ArrayList<Booking> listBookings = new ArrayList<>();
    }

    public boolean readDuplicateId(long id) throws SQLException {
        String duplicateSQL = "SELECT id FROM room WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(duplicateSQL)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean readDuplicateRoomNumber(int roomNumber) throws SQLException {
        String duplicateSQL = "SELECT roomNumber FROM room WHERE roomNumber = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(duplicateSQL)) {
            statement.setInt(1, roomNumber);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public void createRooms(Room room) throws SQLException, IdDuplicateException {

        if (readDuplicateId(room.getId())) {
            throw new IdDuplicateException();
        }

        String createSQL = "INSERT INTO room(id, roomNumber, typeRoom, priceNight, disponibility, detailsAmenities, id_hotel) Values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(createSQL)) {
            statement.setLong(1, room.getId());
            statement.setInt(2, room.getRoomNumber());
            statement.setString(3, room.getTypeOfRoom());
            statement.setDouble(4, room.getPriceNight());
            statement.setString(5, room.getDisponibility());
            statement.setString(6, room.getDetailsAmenities());
            statement.setLong(7, room.getHotel().getCode());

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

    public Room readRooms(long id) throws SQLException {
        String readSQL = "SELECT * FROM room r JOIN hotel h ON r.id_hotel = h.code WHERE r.id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readSQL)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("typeRoom"));
                System.out.println(rs.getString("disponibility"));
                Room room = new Room(rs.getLong("id"), rs.getInt("roomNumber"),
                        rs.getString("typeRoom"), rs.getDouble("priceNight"),
                        rs.getString("disponibility"), rs.getString("detailsAmenities"),
                        new Hotel(rs.getLong("code"), rs.getString("name"), rs.getString("adress"), rs.getString("city"),
                                rs.getInt("classification"), rs.getInt("Amenities")));
                return room;
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
        return null;
    }

    public ArrayList<Room> readAllRooms() throws SQLException {
        String readSQL = "SELECT * FROM room r JOIN hotel h ON r.id_hotel = h.code";
        ArrayList<Room> roomLists = new ArrayList<>();
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readSQL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("typeRoom"));
                System.out.println(rs.getString("disponibility"));
                Room room = new Room(rs.getLong("id"), rs.getInt("roomNumber"),
                        rs.getString("typeRoom"), rs.getDouble("priceNight"),
                        rs.getString("disponibility"), rs.getString("detailsAmenities"),
                        new Hotel(rs.getLong("code"), rs.getString("name"), rs.getString("adress"), rs.getString("city"),
                                rs.getInt("classification"), rs.getInt("Amenities")));
                roomLists.add(room);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
        return roomLists;
    }

    public void updateRooms(Room room) throws SQLException {
        String updateSQL = "UPDATE room SET roomNumber = ?, typeRoom = ?, priceNight = ?, disponibility = ?, detailsAmenities = ?, id_hotel = ? WHERE id = ?;";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setInt(1, room.getRoomNumber());
            statement.setString(2, room.getTypeOfRoom());
            statement.setDouble(3, room.getPriceNight());
            statement.setString(4, room.getDisponibility());
            statement.setString(5, room.getDetailsAmenities());
            statement.setLong(6, room.getHotel().getCode());
            statement.setLong(7, room.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void deleteRooms(String id) throws SQLException {
        String deleteSQL = "DELETE FROM room WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(deleteSQL)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error" + e);
        }

    }

    public void readRoomsAvailables(String id, String dateIn, String dateOut) throws SQLException, DateValidationException {
        String readAvailableSQL = "SELECT * FROM Reservas "
                + "WHERE numero_habitacion = ? AND "
                + "(fecha_entrada BETWEEN ? AND ? OR fecha_salida BETWEEN ? AND ?)";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readAvailableSQL)) {
            statement.setString(1, id);
            statement.setString(2, dateIn);
            statement.setString(3, dateOut);
            ResultSet rs = statement.executeQuery();
            rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
