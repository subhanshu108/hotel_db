package com.hotel.dao;

import com.hotel.db.DatabaseConnection;
import com.hotel.model.LaundryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LaundryDao {

    public boolean addLaundry(LaundryService laundry) {
        String sql = "INSERT INTO laundry_services (reservation_id, service_description, cost, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, laundry.getReservationId());
            stmt.setString(2, laundry.getServiceDescription());
            stmt.setDouble(3, laundry.getCost());
            stmt.setString(4, laundry.getStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<LaundryService> getLaundryByReservation(int reservationId) {
        List<LaundryService> list = new ArrayList<>();
        String sql = "SELECT * FROM laundry_services WHERE reservation_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reservationId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new LaundryService(
                        rs.getInt("laundry_id"),
                        rs.getInt("reservation_id"),
                        rs.getString("service_description"),
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
