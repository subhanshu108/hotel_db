package com.hotel.dao;

import com.hotel.db.DatabaseConnection;
import com.hotel.model.Reservation;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
public class ReservationDao {
    public boolean addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (user_id, room_id, check_in, check_out, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reservation.getUserId());
            stmt.setInt(2, reservation.getRoomId());
            stmt.setDate(3, (Date) reservation.getCheckIn());
            stmt.setDate(4, (Date) reservation.getCheckOut());
            stmt.setString(5, reservation.getStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getInt("user_id"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in"),
                        rs.getDate("check_out"),
                        rs.getString("status")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public Reservation getReservationById(int id) {
        String sql = "SELECT * FROM reservations WHERE reservation_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getInt("user_id"),
                        rs.getInt("room_id"),
                        rs.getDate("check_in"),
                        rs.getDate("check_out"),
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateReservationStatus(int reservationId, String status) {
        String sql = "UPDATE reservations SET status = ? WHERE reservation_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, reservationId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void checkOut(int user_id) {
        String sql = "SELECT * FROM reservations WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int roomId = rs.getInt("room_id");
                Date checkIn = rs.getDate("check_in");
                Date checkOut = rs.getDate("check_out");


                PreparedStatement roomStmt = conn.prepareStatement("SELECT room_type_id FROM rooms WHERE room_id = ?");
                roomStmt.setInt(1, roomId);
                ResultSet rsRoom = roomStmt.executeQuery();

                if (rsRoom.next()) {
                    int roomTypeId = rsRoom.getInt("room_type_id");


                    PreparedStatement roomTypeStmt = conn.prepareStatement("SELECT price FROM room_types WHERE room_type_id = ?");
                    roomTypeStmt.setInt(1, roomTypeId);
                    ResultSet rs1 = roomTypeStmt.executeQuery();

                    if (rs1.next()) {
                        double price = rs1.getDouble("price");

                        LocalDate checkInDate = checkIn.toLocalDate();
                        LocalDate checkOutDate = checkOut.toLocalDate();

                        long daysStayed = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
                        double totalCostOfRoom = daysStayed * price;

                        System.out.println("Total Room Cost = " + totalCostOfRoom);

                    }
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
