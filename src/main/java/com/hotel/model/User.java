package com.hotel.model;

public class User {
    private int userId;
    private String username;
    private String password; // hashed password
    private String email;
    private int roleId;// 1 = Admin, 2 = User (example)
    private long aadhar;

    public User(int userId, String username, String password, String email, int roleId, long aadhar) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
        this.aadhar = aadhar;
    }

    public User(String username, String password, String email, int roleId, long aadhar) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
        this.aadhar = aadhar;
    }

    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getRoleId() { return roleId; }
    public void setRoleId(int roleId) { this.roleId = roleId; }
    public long getAadhar() { return aadhar; }
    public void setAadhar(long aadhar) {
        this.aadhar = aadhar;
    }
}
