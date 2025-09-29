package com.hotel.service;

import com.hotel.dao.FoodDao;
import com.hotel.model.FoodOrder;

import java.sql.SQLException;
import java.util.List;

public class FoodServiceManager {
    private final FoodDao foodDao = new FoodDao();

    public boolean addFoodOrder(int reservationId, int quantity, int foodItemId) {
        // Status is always "pending" when creating a new order
        FoodOrder food = new FoodOrder(reservationId, quantity, "pending", foodItemId);
        return foodDao.addFoodOrder(food);
    }

//    public List<FoodOrder> getFoodOrdersByReservation(int reservationId) {
//        return foodDao.getFoodByReservation(reservationId);
//    }

    public boolean changeFoodStatus(int reservationId, int answer) throws SQLException {
        return foodDao.changeFoodStatus(reservationId,answer);

    }
}
