/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class Room {

    private long id;
    private int roomNumber;
    private String typeOfRoom;
    private double priceNight;
    private String disponibility;
    private String detailsAmenities;

    public Room(long id) {
        this.id = id;
    }

    public Room(long id, int roomNumber, String typeOfRoom, double priceNight, String disponibility, String detailsAmenities) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.typeOfRoom = typeOfRoom;
        this.priceNight = priceNight;
        this.disponibility = disponibility;
        this.detailsAmenities = detailsAmenities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getTypeOfRoom() {
        return typeOfRoom;
    }

    public void setTypeOfRoom(String typeOfRoom) {
        this.typeOfRoom = typeOfRoom;
    }

    public double getPriceNight() {
        return priceNight;
    }

    public void setPriceNight(double priceNight) {
        this.priceNight = priceNight;
    }

    public String getDisponibility() {
        return disponibility;
    }

    public void setDisponibility(String disponibility) {
        this.disponibility = disponibility;
    }

    public String getDetailsAmenities() {
        return detailsAmenities;
    }

    public void setDetailsAmenities(String detailsAmenities) {
        this.detailsAmenities = detailsAmenities;
    }

}
