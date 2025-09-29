# Hotel Management System

A simple console-based **Hotel Management System** implemented in **Java** using **JDBC** and **MySQL**. This system allows users to register, log in, make reservations, order services (laundry, food, cab), and checkout while calculating total bills automatically.  

## Table of Contents

- [Features](#features)  
- [Technologies](#technologies)  
- [Database Setup](#database-setup)  
- [Project Structure](#project-structure)  
- [Getting Started](#getting-started)  
- [Usage](#usage)  
- [License](#license)  

---

## Features

### User Features
- User registration and login  
- Make room reservations  
- Order food, laundry, and cab services  
- Checkout with automatic bill calculation  

### Admin Features
- Add, update, and delete rooms  
- Manage room availability  
- View reservations and user details  

### Billing
- Calculates room cost based on room type and stay duration  
- Adds food, laundry, and cab service costs  
- Updates service statuses upon checkout  
- Marks rooms as available after checkout  

---

## Technologies

- **Language:** Java  
- **Database:** MySQL  
- **Database Connection:** JDBC  
- **Build Tool:** Maven / IntelliJ IDEA (optional)  

---

## Database Setup

1. Create a MySQL database:

```sql
CREATE DATABASE hotel;
USE hotel;
-- Users
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(role_id)
);

-- Roles
CREATE TABLE roles (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL
);

-- Rooms
CREATE TABLE rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_number INT NOT NULL,
    room_type_id INT NOT NULL,
    status ENUM('available','occupied') DEFAULT 'available',
    FOREIGN KEY (room_type_id) REFERENCES room_types(room_type_id)
);

-- Room Types
CREATE TABLE room_types (
    room_type_id INT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(50),
    price DECIMAL(10,2),
    description VARCHAR(255)
);

-- Reservations
CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    room_id INT NOT NULL,
    check_in DATE NOT NULL,
    check_out DATE NOT NULL,
    status ENUM('booked','checked_in','checked_out','cancelled') DEFAULT 'booked',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);

-- Food Items
CREATE TABLE food_items (
    food_item_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    price DECIMAL(10,2)
);

-- Food Orders
CREATE TABLE food_orders (
    food_order_id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_id INT NOT NULL,
    food_item_id INT NOT NULL,
    quantity INT DEFAULT 1,
    status ENUM('pending','served') DEFAULT 'pending',
    FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id),
    FOREIGN KEY (food_item_id) REFERENCES food_items(food_item_id)
);

-- Laundry Services
CREATE TABLE laundry_services (
    laundry_id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_id INT NOT NULL,
    service_description VARCHAR(255),
    cost DECIMAL(10,2),
    status ENUM('pending','completed') DEFAULT 'pending',
    FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id)
);

-- Cab Services
CREATE TABLE cab_services (
    cab_service_id INT AUTO_INCREMENT PRIMARY KEY,
    reservation_id INT NOT NULL,
    pickup_location VARCHAR(255),
    drop_location VARCHAR(255),
    pickup_time DATETIME,
    cost DECIMAL(10,2),
    status ENUM('booked','completed','cancelled') DEFAULT 'booked',
    FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id)
);
```






## Project-Structure
hotel-management/
├── src/
│   ├── com.hotel.Main.java
│   ├── com.hotel.dao/
│   ├── com.hotel.model/
│   ├── com.hotel.service/
├── pom.xml
└── README.md


## Getting Started

Clone the repository:

git clone https://github.com/yourusername/hotel-management.git
cd hotel-management


Set up your database connection in DatabaseConnection.java. Example:

private static final String URL = "jdbc:mysql://localhost:3306/hotel";
private static final String USER = "root";
private static final String PASSWORD = "password";


Build and run the project:

mvn clean compile exec:java

Usage

Register a new user.

Login as a user or admin.

Navigate the menu to:

Make reservations

Order food, laundry, or cab services

Checkout and get the total bill

Admins can manage rooms and reservations.
