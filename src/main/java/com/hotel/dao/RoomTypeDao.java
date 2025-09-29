package com.hotel.dao;

import com.hotel.db.DatabaseConnection;
import com.hotel.model.RoomType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RoomTypeDao {
    public boolean addRoomType(RoomType roomType) {
        String sql = "INSERT INTO room_types (type_name, price, description) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, roomType.getTypeName());
            stmt.setDouble(2, roomType.getPrice());
            stmt.setString(3, roomType.getDescription());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<RoomType> getAllRoomTypes() {
        List<RoomType> types = new ArrayList<>();
        String sql = "SELECT * FROM room_types";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                types.add(new RoomType(
                        rs.getInt("room_type_id"),
                        rs.getString("type_name"),
                        rs.getDouble("price"),
                        rs.getString("description")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    public RoomType getRoomTypeById(int id) {
        String sql = "SELECT * FROM room_types WHERE room_type_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new RoomType(
                        rs.getInt("room_type_id"),
                        rs.getString("type_name"),
                        rs.getDouble("price"),
                        rs.getString("description")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
