package com.hotel.model;

public class Room {
    private int roomId;
    private String roomNumber;
    private int roomTypeId;
    private String status;

    public Room(int roomId, String roomNumber, int roomTypeId, String status) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomTypeId = roomTypeId;
        this.status = status;
    }

    public Room(String roomNumber, int roomTypeId, String status) {
        this.roomNumber = roomNumber;
        this.roomTypeId = roomTypeId;
        this.status = status;
    }

    // Getters and setters
    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public int getRoomTypeId() { return roomTypeId; }
    public void setRoomTypeId(int roomTypeId) { this.roomTypeId = roomTypeId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
