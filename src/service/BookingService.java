/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Dao.BookingDAO;
import exception.DateDuplicateException;
import exception.DateValidationException;
import exception.IdDuplicateException;
import exception.IdValidationException;
import validation.DateValidation;
import java.sql.SQLException;
import model.Booking;
import validation.IdValidation;

/**
 *
 * @author Lenovo
 */
public class BookingService {
    
    BookingDAO bookingDao;
    IdValidation idVali;
    DateValidation dateVali;
    
    public BookingService() {
        bookingDao = new BookingDAO();
        idVali = new IdValidation();
        dateVali = new DateValidation();
    }
    
    public void createBooking(Booking booking) throws SQLException, IdDuplicateException, DateDuplicateException {
        
        if (idVali.validateIdentification(booking.getId())) {
            throw new IdValidationException();
            
        }
        
        if (!dateVali.validateDate(booking.getDateIn())) {
            throw new DateValidationException();
        }
        if (!dateVali.validateDate(booking.getDateOut())) {
            throw new DateValidationException();
        }
        bookingDao.createBooking(booking);
    }
    
    public void readBooking(long id) throws SQLException {
        bookingDao.readBooking(id);
    }
    
    public void updateBooking(Booking booking) throws SQLException, IdDuplicateException, DateDuplicateException {
        
        if (!idVali.validateIdentification(booking.getId())) {
            throw new IdValidationException();
        }
        
        if (!dateVali.validateDate(booking.getDateIn())) {
            throw new DateValidationException();
        }
        if (!dateVali.validateDate(booking.getDateOut())) {
            throw new DateValidationException();
            
        }
        bookingDao.updateBooking(booking);
    }
    
    public void deleteBooking(String code) throws SQLException {
        bookingDao.deleteBooking(code);
    }
    public void pdfBookings(){
        bookingDao.pdfBookings();
    }
}
