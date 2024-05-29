/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dao.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.Connection;
import connect.MySQLConnection;

import model.Booking;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connect.MySQLConnection;
import singleton.Singleton;
import exception.DateValidationException;
import exception.IdDuplicateException;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Hotel;

 public class BookingDAO {

    MySQLConnection conection;
    private Connection connection;

    public BookingDAO() {
        connection = Singleton.getInstance().getconnection();
    }

    public boolean readDuplicateCode(long id) throws SQLException {
        String readIdSQL = "SELECT id FROM booking WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readIdSQL)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean readValidationDates(String dateIn, String dateOut) throws SQLException {
        String readDatesSQL = "INSERT dateIn, dateOut FROM booking WHERE dateIn = ?, dateOut = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readDatesSQL)) {
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createBooking(Booking booking) throws SQLException, IdDuplicateException, DateValidationException {

        if (readDuplicateCode(booking.getId())) {
            throw new IdDuplicateException();

        }

        if (readValidationDates(booking.getDateIn(), booking.getDateOut())) {
            throw new DateValidationException();
        }

        String createSQL = "INSERT INTO booking(id, identification, hotel, room, dateIn, dateOut, totalPrice) Values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(createSQL)) {
            statement.setLong(1, booking.getId());
            statement.setLong(2, booking.getUser().getId());
            statement.setLong(3, booking.getHotel().getCode());
            statement.setLong(4, booking.getRoom().getId());
            statement.setString(5, booking.getDateIn());
            statement.setString(6, booking.getDateOut());
            statement.setDouble(7, booking.getTotalPrice());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Insercion exitosa");
            } else {
                System.out.println("No se pudo insertar los datos");
            }
            //En el catch agregar el DateValidationException y llmarlo a la ventana//
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void readBooking(long id) throws SQLException {
        String readSQL = "SELECT * FROM hotel WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(readSQL)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("totalPrice"));
                System.out.println(rs.getString("reservationStatus"));
                
            }
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void updateBooking(Booking booking) throws SQLException, DateValidationException {
        String updateSQL = "UPDATE booking SET identification = ?, hotel = ?, room = ?, dateIn = ?, dateOut = ?, reservationStatus = ?, totalPrice = ? WHERE id = ?;";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(updateSQL)) {
            statement.setLong(1, booking.getUser().getId());
            statement.setLong(2, booking.getHotel().getCode());
            statement.setLong(3, booking.getRoom().getId());
            statement.setString(4, booking.getDateIn());
            statement.setString(5, booking.getDateOut());
            statement.setString(6, booking.getReservationStatus());
            statement.setDouble(7, booking.getTotalPrice());
            statement.setLong(8, booking.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error" + e);
        }
    }

    public void deleteBooking(String id) throws SQLException {
        String deleteSQL = "DELETE FROM booking WHERE id = ?";
        try (PreparedStatement statement = MySQLConnection.conectarMySQL().prepareStatement(deleteSQL)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
    }

    public void pdfBookings() {
        Document document = new Document();
        try {
            String rute = System.getProperty("user.home");
            PdfWriter.getInstance(document, new FileOutputStream(rute + "C:/Users/Lenovo/OneDrive/Documents/HotelProyectJuan/Reports_PDF"));
            document.open();

            Paragraph title = new Paragraph("Reporte de reservas");
            document.add(title);

            PdfPTable table = new PdfPTable(8);
            table.addCell("id");
            table.addCell("usuario");
            table.addCell("hotel");
            table.addCell("fecha entrada");
            table.addCell("fecha salida");
            table.addCell("estado");
            table.addCell("precio total");
            document.add(table);
            document.close();
            JOptionPane.showMessageDialog(null, "Reporte creado.");
        } catch (DocumentException | HeadlessException | FileNotFoundException e) {

        }
    }
}