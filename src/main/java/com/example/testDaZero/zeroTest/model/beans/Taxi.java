package com.example.testDaZero.zeroTest.model.beans;

public class Taxi {
    private String licensePlate;
    private String ownerName;
    private String ownerSurname;
    private boolean available;

    public Taxi(String licensePlate, String ownerName, String ownerSurname, boolean available) {
        this.licensePlate = licensePlate;
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.available=available;
    }
    public String getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getOwnerSurname() {
        return ownerSurname;
    }
    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname = ownerSurname;
    }
    public boolean isAvailable() {
        return available;
    }


}
