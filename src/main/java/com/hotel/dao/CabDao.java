package com.hotel.dao;

import com.hotel.db.DatabaseConnection;
import com.hotel.model.CabService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CabDao {

    public boolean addCabService(CabService cab) {
        String sql = "INSERT INTO cab_services (reservation_id, pickup_location, drop_location, pickup_time, cost, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cab.getReservationId());
            stmt.setString(2, cab.getPickupLocation());
            stmt.setString(3, cab.getDropLocation());
            stmt.setTimestamp(4, cab.getPickupTime());
            stmt.setDouble(5, cab.getCost());
            stmt.setString(6, cab.getStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CabService> getCabByReservation(int reservationId) {
        List<CabService> list = new ArrayList<>();
        String sql = "SELECT * FROM cab_services WHERE reservation_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new CabService(
                        rs.getInt("cab_service_id"),
                        rs.getInt("reservation_id"),
                        rs.getString("pickup_location"),
                        rs.getString("drop_location"),
                        rs.getTimestamp("pickup_time"),
                        rs.getDouble("cost"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
