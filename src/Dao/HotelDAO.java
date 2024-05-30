/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dao.*;
import java.sql.Connection;
import model.Hotel;
import java.sql.ResultSet;
import connect.MySQLConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import connect.MySQLConnection;
import singleton.Singleton;
import exception.CodeExistingException;
import exception.IdDuplicateException;
import java.io.FileOutputStream;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class HotelDAO {

    MySQLConnection conection;
    private Connection connection;

    public HotelDAO() {
        connection = Singleton.getInstance().getconnection();
    }

    public boolean readDuplicateId(long code) throws SQLException {
        String verifySQL = "SELECT code From hotel WHERE code = ? ";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(verifySQL)) {
            statement.setLong(1, code);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createHotel(Hotel hotel) throws SQLException, CodeExistingException {

        if (readDuplicateId(hotel.getCode())) {
            throw new CodeExistingException();
        }

        String createHotel = "INSERT INTO hotel(code, name, adress, city, classification, amenities) Values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(createHotel)) {
            statement.setLong(1, hotel.getCode());
            statement.setString(2, hotel.getName());
            statement.setString(3, hotel.getAdress());
            statement.setString(4, hotel.getCity());
            statement.setInt(5, hotel.getClassification());
            statement.setInt(6, hotel.getAmenities());
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

    public Map<String, Object> select() {

        Map<String, Object> result = new HashMap<>();

        String selectSQL = "SELECT name, adress, classification, amenities, city FROM hotel";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(selectSQL)) {

            ResultSet rs = statement.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();

            List<String> columnNames = new ArrayList<>();

            for (int i = 1; i <= numColumns; i++) {
                String columnName = rsmd.getColumnName(i);

                if (columnName.equals("Name")) {
                    columnNames.add("Hotel");
                } else if (columnName.equals("City")) {
                    columnNames.add("Ciudad");
                } else if (columnName.equals("Adress")) {
                    columnNames.add("Direccion");
                } else if (columnName.equals("classification")) {
                    columnNames.add("Clasificacion");
                } else if (columnName.equals("Amenities")) {
                    columnNames.add("comodidades");
                } else if (columnName.equals("City")) {
                    columnNames.add("Ciudad");
                } else {

                    columnNames.add(columnName);
                }
            }

            List<List<Object>> tableData = new ArrayList<>();

            while (rs.next()) {
                List<Object> rowData = new ArrayList<>();

                for (int i = 1; i <= numColumns; i++) {
                    rowData.add(rs.getObject(i));
                }

                tableData.add(rowData);
            }

            result.put("numColumns", numColumns);
            result.put("columnNames", columnNames);
            result.put("tableData", tableData);
        } catch (SQLException e) {
            System.out.println("Error ocurrido mientras selecciona en la base de datos");
            e.printStackTrace();
        }

        return result;
    }

    public Hotel readHotel(long code) throws SQLException {
        String readSQL = "SELECT * FROM hotel WHERE code = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readSQL)) {
            statement.setLong(1, code);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("city"));
                Hotel hotel = new Hotel(
                        rs.getLong("code"),
                        rs.getString("name"),
                        rs.getString("adress"),
                        rs.getString("city"),
                        rs.getInt("classification"),
                        rs.getInt("amenities"));
                return hotel;
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
        return null;
    }

    public ArrayList<Hotel> readAllHotel() throws SQLException {
        String readSQL = "SELECT * FROM hotel ";
        ArrayList<Hotel>hotelLists = new ArrayList<>();
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readSQL)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("city"));
                Hotel hotel = new Hotel(
                        rs.getLong("code"),
                        rs.getString("name"),
                        rs.getString("adress"),
                        rs.getString("city"),
                        rs.getInt("classification"),
                        rs.getInt("amenities"));
                hotelLists.add(hotel);
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
        return hotelLists;
    }

    public void updateHotel(Hotel hotel) throws SQLException {
        String updateSQL = "UPDATE hotel SET name = ?, adress = ?, city = ?, classification = ?, amenities = ? WHERE code = ?;";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getAdress());
            statement.setString(3, hotel.getCity());
            statement.setInt(4, hotel.getClassification());
            statement.setInt(5, hotel.getAmenities());
            statement.setLong(6, hotel.getCode());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void deleteHotel(long code) throws SQLException {
        String deleteSQL = "DELETE FROM hotel WHERE code = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(deleteSQL)) {
            statement.setLong(1, code);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
    }

    public List<Hotel> getAllHotels() throws SQLException {
        List<Hotel> hotels = new ArrayList<>();
        String sql = "SELECT * FROM hotel";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                long code = resultSet.getLong("code");
                String name = resultSet.getString("name");
                String adress = resultSet.getString("adress");
                String city = resultSet.getString("city");
                int classification = resultSet.getInt("classification");
                int amenities = resultSet.getInt("amenities");
                hotels.add(new Hotel(code, name, adress, city, classification, amenities));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los hoteles: " + e.getMessage());
            throw e;
        }
        return hotels;
    }

    public void pdfHotels() {

        Document document = new Document();
        try {
            String rute = System.getProperty("user.home");
            PdfWriter.getInstance(document, new FileOutputStream(rute + "/Documents/HoyelProyectJuan/Reports_PDF/report/hotels.pdf"));
            document.open();

            Paragraph title = new Paragraph("Reporte de Hoteles");
            document.add(title);

            PdfPTable table = new PdfPTable(6);
            table.addCell("code");
            table.addCell("name");
            table.addCell("adress");
            table.addCell("city");
            table.addCell("classification");
            table.addCell("amenities");
            document.add(table);
            document.close();
            JOptionPane.showMessageDialog(null, "Reporte creado.");
        } catch (DocumentException | HeadlessException | FileNotFoundException e) {

        }
    }
}
