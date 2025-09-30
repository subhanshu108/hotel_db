package com.hotel.service;

import com.hotel.dao.UserDao;
import com.hotel.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UserService {

    private final UserDao userDao = new UserDao();


    public boolean registerUser(String username, String email, String plainPassword, int roleId, long aadhar) {
        String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
        User user = new User(0, username, hashedPassword, email, roleId, aadhar);
        return userDao.addUser(user);
    }


    public User loginUser(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }


    public boolean changeUserRole(int userId, int newRoleId) {
        return userDao.updateUserRole(userId, newRoleId);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
