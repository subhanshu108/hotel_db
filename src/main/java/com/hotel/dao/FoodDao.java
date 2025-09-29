package com.hotel.dao;

import com.hotel.db.DatabaseConnection;
import com.hotel.model.FoodOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodDao {

    public boolean addFoodOrder(FoodOrder food) {
        String sql = "INSERT INTO food_orders (reservation_id, quantity, status, food_item_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, food.getReservationId());
            stmt.setInt(2, food.getQuantity());
            stmt.setString(3, food.getStatus());
            stmt.setInt(4, food.getFoodItem()); // Correctly set food_item_id

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//    public List<FoodOrder> getFoodByReservation(int reservationId) {
//        List<FoodOrder> list = new ArrayList<>();
//        String sql = "SELECT * FROM food_orders WHERE reservation_id = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(1, reservationId);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                list.add(new FoodOrder(
//                       rs.getInt(food_order_id),
//                        rs.getInt(reservation_Id),
//                ));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
    public boolean changeFoodStatus(int reservationId, int answer) throws SQLException {
        String status = (answer == 1) ? "served" : "pending";
        String sql = "UPDATE food_orders SET status = ? WHERE reservation_id = ?";
        try(Connection conn = DatabaseConnection.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setInt(2, reservationId);

            return stmt.executeUpdate() > 0;
        }
    }
}
