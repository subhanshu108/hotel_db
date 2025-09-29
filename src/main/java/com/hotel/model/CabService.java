package com.hotel.model;

import java.sql.*;

public class CabService {
    private int cabServiceId;
    private int reservationId;
    private String pickupLocation;
    private String dropLocation;
    private Timestamp pickupTime;
    private double cost;
    private String status;

    public CabService(int cabServiceId, int reservationId, String pickupLocation, String dropLocation, Timestamp pickupTime, double cost, String status) {
        this.cabServiceId = cabServiceId;
        this.reservationId = reservationId;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.pickupTime = pickupTime;
        this.cost = cost;
        this.status = status;
    }

    public CabService(int reservationId, String pickupLocation, String dropLocation, Timestamp pickupTime, double cost, String status) {
        this.reservationId = reservationId;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.pickupTime = pickupTime;
        this.cost = cost;
        this.status = status;
    }

    // Getters and setters
    public int getCabServiceId() { return cabServiceId; }
    public void setCabServiceId(int cabServiceId) { this.cabServiceId = cabServiceId; }
    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }
    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }
    public String getDropLocation() { return dropLocation; }
    public void setDropLocation(String dropLocation) { this.dropLocation = dropLocation; }
    public java.sql.Timestamp getPickupTime() { return pickupTime; }
    public void setPickupTime(Timestamp pickupTime) { this.pickupTime = pickupTime; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
