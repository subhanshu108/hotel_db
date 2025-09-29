package com.hotel.model;

public class RoomType {
    private int roomTypeId;
    private String typeName;
    private double price;
    private String description;

    public RoomType(int roomTypeId, String typeName, double price, String description) {
        this.roomTypeId = roomTypeId;
        this.typeName = typeName;
        this.price = price;
        this.description = description;
    }

    // Getters and setters
    public int getRoomTypeId() { return roomTypeId; }
    public void setRoomTypeId(int roomTypeId) { this.roomTypeId = roomTypeId; }
    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
