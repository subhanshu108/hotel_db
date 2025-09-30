package com.hotel.service;

import com.hotel.dao.ReservationDao;
import com.hotel.model.Reservation;

import java.sql.Date;
import java.util.List;

public class ReservationService {
    private final ReservationDao reservationDao = new ReservationDao();
    private final RoomService roomService = new RoomService();

    public boolean makeReservation(int userId, int roomId, Date checkIn, Date checkOut) {

        var room = roomService.getRoomById(roomId);
        System.out.println(room);
        if (room == null || !room.getStatus().equalsIgnoreCase("available")) {
            return false;
        }

        Reservation reservation = new Reservation(userId, roomId, checkIn, checkOut, "booked");
        boolean success = reservationDao.addReservation(reservation);

        if (success) {

            roomService.updateRoomStatus(roomId, "occupied");
        }

        return success;
    }
    public void checkOut(int user_id) {
            reservationDao.checkOut(user_id);
    }

    public List<Reservation> getAllReservations() {
        return reservationDao.getAllReservations();
    }

    public boolean updateReservationStatus(int reservationId, String status) {
        return reservationDao.updateReservationStatus(reservationId, status);
    }
}
