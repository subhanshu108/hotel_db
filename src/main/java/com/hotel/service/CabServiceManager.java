package com.hotel.service;

import com.hotel.dao.CabDao;
import com.hotel.model.CabService;

import java.sql.Timestamp;
import java.util.List;

public class CabServiceManager {
    private final CabDao cabDao = new CabDao();

    public boolean addCabService(int reservationId, String pickupLocation, String dropLocation, Timestamp pickupTime, double cost) {
        CabService cab = new CabService(reservationId, pickupLocation, dropLocation, pickupTime, cost, "booked");
        return cabDao.addCabService(cab);
    }

    public List<CabService> getCabServicesByReservation(int reservationId) {
        return cabDao.getCabByReservation(reservationId);
    }
}
