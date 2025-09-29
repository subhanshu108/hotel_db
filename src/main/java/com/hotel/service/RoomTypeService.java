package com.hotel.service;

import com.hotel.dao.RoomTypeDao;
import com.hotel.model.RoomType;

import java.util.List;

public class RoomTypeService {
    private final RoomTypeDao roomTypeDao = new RoomTypeDao();

    public boolean addRoomType(String name, double price, String description) {
        RoomType roomType = new RoomType(0, name, price, description);
        return roomTypeDao.addRoomType(roomType);
    }

    public List<RoomType> getAllRoomTypes() {
        return roomTypeDao.getAllRoomTypes();
    }

    public RoomType getRoomTypeById(int id) {
        return roomTypeDao.getRoomTypeById(id);
    }
}
