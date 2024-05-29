/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import Dao.RoomDAO;
import exception.IdDuplicateException;
import exception.IdValidationException;
import validation.IdValidation;
import model.Room;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class RoomService {

    RoomDAO roomDao;
    IdValidation idVali;

    public RoomService() {
        idVali = new IdValidation();
        roomDao = new RoomDAO();
    }

    public void createRoom(Room room) throws SQLException, IdDuplicateException {
        if (!idVali.validateIdentification(room.getId())) {
            throw new IdValidationException();
        }
        roomDao.createRooms(room);
    }

    public Room readRoom(long id) throws SQLException {
        return roomDao.readRooms(id);
    }

    public void updateRoom(Room room) throws SQLException, IdDuplicateException {
        if (!idVali.validateIdentification(room.getId())) {
            throw new IdValidationException();
        }
        roomDao.updateRooms(room);
    }

    public void deleteRoom(String id) throws SQLException {
        roomDao.deleteRooms(id);
    }
}
