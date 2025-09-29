package com.hotel.service;

import com.hotel.dao.RoomDao;
import com.hotel.model.Room;

import java.util.List;

public class RoomService {
    private final RoomDao roomDao = new RoomDao();

    public boolean addRoom(String number, int roomTypeId, String status) {
        Room room = new Room(number, roomTypeId, status);
        return roomDao.addRoom(room);
    }

    public List<Room> getAllRooms() {
        return roomDao.getAllRooms();
    }

    public Room getRoomById(int id) {
        return roomDao.getRoomById(id);
    }

    public boolean updateRoomStatus(int roomId, String status) {
        return roomDao.updateRoomStatus(roomId, status);
    }
}
