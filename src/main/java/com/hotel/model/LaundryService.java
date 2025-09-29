package com.hotel.model;

public class LaundryService {
    private int laundryId;
    private int reservationId;
    private String serviceDescription;
    private double cost;
    private String status;

    public LaundryService(int laundryId, int reservationId, String serviceDescription, double cost, String status) {
        this.laundryId = laundryId;
        this.reservationId = reservationId;
        this.serviceDescription = serviceDescription;
        this.cost = cost;
        this.status = status;
    }

    public LaundryService(int reservationId, String serviceDescription, double cost, String status) {
        this.reservationId = reservationId;
        this.serviceDescription = serviceDescription;
        this.cost = cost;
        this.status = status;
    }

    // Getters and setters
    public int getLaundryId() { return laundryId; }
    public void setLaundryId(int laundryId) { this.laundryId = laundryId; }
    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }
    public String getServiceDescription() { return serviceDescription; }
    public void setServiceDescription(String serviceDescription) { this.serviceDescription = serviceDescription; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
