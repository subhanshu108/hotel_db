package com.hotel.service;

import com.hotel.dao.LaundryDao;
import com.hotel.model.LaundryService;

import java.util.List;

public class LaundryServiceManager {
    private final LaundryDao laundryDao = new LaundryDao();

    public boolean addLaundryService(int reservationId, String description, double cost) {
        LaundryService laundry = new LaundryService(reservationId, description, cost, "pending");
        return laundryDao.addLaundry(laundry);
    }

    public List<LaundryService> getLaundryByReservation(int reservationId) {
        return laundryDao.getLaundryByReservation(reservationId);
    }
}
