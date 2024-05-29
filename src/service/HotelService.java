/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Dao.HotelDAO;
import exception.CodeExistingException;
import exception.CodeValidationException;
import validation.CodeValidation;
import java.sql.SQLException;
import model.Hotel;

/**
 *
 * @author Lenovo
 */
public class HotelService {

    HotelDAO hotelDao;
    CodeValidation codeVali;

    public HotelService() {
        hotelDao = new HotelDAO();
        codeVali = new CodeValidation();
    }

    public void createHotel(Hotel hotel) throws SQLException, CodeExistingException {
        if (!codeVali.validationCode(hotel.getCode())) {
            throw new CodeValidationException();
        }
        hotelDao.createHotel(hotel);
    }

    public Hotel readHotel(long code) throws SQLException {
       return hotelDao.readHotel(code);
    }
    
    public void updateHotel(Hotel hotel) throws SQLException, CodeExistingException{
        if(!codeVali.validationCode(hotel.getCode())){
            throw new CodeValidationException();
        }
        hotelDao.updateHotel(hotel);
    }
    
    public void deleteHotel(long id) throws SQLException{
        hotelDao.deleteHotel(id);
    }
    
    public void pdfHotels(){
        hotelDao.pdfHotels();
    }
}
