package com.hotel.model;
public class FoodOrder {
    private int foodOrderId;
    private int reservationId;
    private int quantity;
    private int foodItemId; // renamed from getFoodItem
    private String status;

    public FoodOrder(int foodOrderId, int reservationId, int foodItemId, int quantity, String status) {
        this.foodOrderId = foodOrderId;
        this.reservationId = reservationId;
        this.foodItemId = foodItemId;
        this.quantity = quantity;
        this.status = status;
    }

    public FoodOrder(int reservationId, int quantity, String status, int foodItemId) {
        this.reservationId = reservationId;
        this.foodItemId = foodItemId;
        this.quantity = quantity;
        this.status = status;
    }

    // Getters and setters
    public int getFoodOrderId() { return foodOrderId; }
    public void setFoodOrderId(int foodOrderId) { this.foodOrderId = foodOrderId; }

    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }

    public int getFoodItem() { return foodItemId; }
    public void setFoodItem(int foodItemId) { this.foodItemId = foodItemId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
