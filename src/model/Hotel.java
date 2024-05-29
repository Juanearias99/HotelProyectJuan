/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class Hotel {

    private long code;
    private String name;
    private String adress;
    private String city;
    private int classification;
    private int amenities;

    public Hotel(long code) {
        this.code = code;
    }

    public Hotel(long code, String name, String adress, String city, int classification, int amenities) {
        this.code = code;
        this.name = name;
        this.adress = adress;
        this.city = city;
        this.classification = classification;
        this.amenities = amenities;

    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public int getAmenities() {
        return amenities;
    }

    public void setAmenities(int amenities) {
        this.amenities = amenities;
    }
}
