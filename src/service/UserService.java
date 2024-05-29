/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Dao.UserDAO;
import exception.EmailDuplicateException;
import exception.EmailValidationException;
import exception.IdDuplicateException;
import exception.IdValidationException;
import exception.PhoneDuplicateException;
import exception.PhoneValidationException;
import java.sql.SQLException;
import validation.EmailValidation;
import validation.IdValidation;
import validation.NumberValidation;
import model.User;

/**
 *
 * @author Lenovo
 */
public class UserService {

    UserDAO userDao;
    IdValidation idVali;
    EmailValidation emailVali;
    NumberValidation numberVali;

    public UserService() {
        userDao = new UserDAO();
        idVali = new IdValidation();
        emailVali = new EmailValidation();
        numberVali = new NumberValidation();
    }

    public void createUser(User user) throws SQLException, IdDuplicateException, EmailDuplicateException, PhoneDuplicateException {
        if (!idVali.validateIdentification(user.getId())) {
            throw new IdValidationException();
        }
        if (!emailVali.validateEmail(user.getEmail())) {
            throw new EmailValidationException();
        }
        if (!numberVali.validatePhoneNumbers(user.getPhoneNumber())) {
            throw new PhoneValidationException();
        }

        userDao.createUsers(user);
    }

    public User readUser(long id) throws SQLException {
        return userDao.readUsers(id);
    }

    public void updateUser(User user) throws SQLException, EmailDuplicateException, PhoneDuplicateException {
        if (!emailVali.validateEmail(user.getEmail())) {
            throw new EmailValidationException();
        }
        if (!numberVali.validatePhoneNumbers(user.getPhoneNumber())) {
            throw new PhoneValidationException();
        }
        userDao.updateUsers(user);
    }

    public void deleteUser(long id) throws SQLException{
        userDao.deleteUsers(id);
    }    
}
