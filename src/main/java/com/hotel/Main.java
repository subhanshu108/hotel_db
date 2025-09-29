package com.hotel;

import com.hotel.model.User;
import com.hotel.service.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;


public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService userService = new UserService();
    private static final RoomTypeService roomTypeService = new RoomTypeService();
    private static final RoomService roomService = new RoomService();
    private static final ReservationService reservationService = new ReservationService();
    private static final LaundryServiceManager laundryService = new LaundryServiceManager();
    private static final FoodServiceManager foodService = new FoodServiceManager();
    private static final CabServiceManager cabService = new CabServiceManager();

    public static void main(String[] args) throws SQLException {
        while (true) {
            System.out.println("\n=== HOTEL MANAGEMENT SYSTEM ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void register() {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        // Loop until a valid email is entered
        String email;
        while (true) {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (isValidEmail(email)) {
                break;
            } else {
                System.out.println("Invalid email format. Please enter a valid email.");
            }
        }

        String password = readPassword("Enter password for registration:");
        if (password == null) {
            System.out.println("Registration cancelled.");
            return;
        }

        // default role = user (2)
        boolean success = userService.registerUser(username, email, password, 2);
        System.out.println(success ? "User registered successfully!" : "Registration failed!");
    }

    private static void login() throws SQLException {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        String password = readPassword("Enter password to login:");
        if (password == null) {
            System.out.println("Login cancelled.");
            return;
        }

        User user = userService.loginUser(username, password);
        if (user == null) {
            System.out.println("Invalid credentials!");
            return;
        }

        if (user.getRoleId() == 1) {
            adminMenu(user);
        } else {
            userMenu(user);
        }
    }

    /**
     * Reads a password either from System.console() (no echo) or, if console is null,
     * opens a Swing dialog with JPasswordField so the user cannot see the typed characters.
     * Returns null if the user cancels the dialog.
     */
    private static String readPassword(String prompt) {
        // Try to use the system console first (works when running from a real terminal)
        if (System.console() != null) {
            System.out.print(prompt + " ");
            char[] pwdChars = System.console().readPassword();
            return pwdChars == null ? null : new String(pwdChars);
        }

        // Fallback for IDEs (IntelliJ/Eclipse) where System.console() is null:
        JPasswordField passwordField = new JPasswordField();
        Object[] dialogContent = {prompt, passwordField};
        int option = JOptionPane.showConfirmDialog(
                null,
                dialogContent,
                "Password Input",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (option == JOptionPane.OK_OPTION) {
            char[] pwd = passwordField.getPassword();
            return pwd == null ? null : new String(pwd);
        } else {
            return null; // user cancelled
        }
    }

    // Simple email validation using regex
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    // ===== ADMIN MENU =====
    private static void adminMenu(User admin) throws SQLException {
        while (true) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. List all users");
            System.out.println("2. Change user role");
            System.out.println("3. Manage room types");
            System.out.println("4. Manage rooms");
            System.out.println("5. Change food order");
            System.out.println("6. Logout");
            System.out.print("Choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> listUsers();
                case 2 -> changeUserRole();
                case 3 -> manageRoomTypes();
                case 4 -> manageRooms();
                case 5 -> {
                    try {
                        changeFoodOrder();
                    } catch (SQLException e) {
                        System.out.println("Error updating food: " + e.getMessage());
                    }
                }
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void listUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println("\nUsers:");
        for (User u : users) {
            System.out.printf("%d: %s (Role ID: %d)%n", u.getUserId(), u.getUsername(), u.getRoleId());
        }
    }

    private static void changeUserRole() {
        System.out.print("Enter User ID: ");
        int userId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new Role ID (1=Admin, 2=User): ");
        int roleId = Integer.parseInt(scanner.nextLine());
        boolean success = userService.changeUserRole(userId, roleId);
        System.out.println(success ? "Role updated!" : "Failed to update role.");
    }

    private static void changeFoodOrder() throws SQLException {
        System.out.print("Enter Reservation ID: ");
        int reservationId = Integer.parseInt(scanner.nextLine());
        System.out.println("Is the food done? 1. Yes 2. No");
        int answer = Integer.parseInt(scanner.nextLine());
        boolean success = foodService.changeFoodStatus(reservationId, answer);
        System.out.println(success ? "Food updated!" : "Failed to update food.");
    }

    private static void manageRoomTypes() {
        System.out.println("1. Add Room Type");
        System.out.println("2. List Room Types");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1 -> {
                System.out.print("Type Name: ");
                String name = scanner.nextLine();
                System.out.print("Price: ");
                double price = Double.parseDouble(scanner.nextLine());
                System.out.print("Description: ");
                String desc = scanner.nextLine();
                boolean success = roomTypeService.addRoomType(name, price, desc);
                System.out.println(success ? "Room type added!" : "Failed to add room type.");
            }
            case 2 -> {
                var list = roomTypeService.getAllRoomTypes();
                for (var r : list) {
                    System.out.printf("%d: %s - %.2f - %s%n",
                            r.getRoomTypeId(), r.getTypeName(), r.getPrice(), r.getDescription());
                }
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void manageRooms() {
        System.out.println("1. Add Room");
        System.out.println("2. List Rooms");
        System.out.print("Choice: ");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1 -> {
                System.out.print("Room Number: ");
                String number = scanner.nextLine();
                System.out.print("Room Type ID: ");
                int typeId = Integer.parseInt(scanner.nextLine());
                System.out.print("Status (available/occupied): ");
                String status = scanner.nextLine();
                boolean success = roomService.addRoom(number, typeId, status);
                System.out.println(success ? "Room added!" : "Failed to add room.");
            }
            case 2 -> {
                var list = roomService.getAllRooms();
                for (var r : list) {
                    System.out.printf("%d: %s - Type ID: %d - Status: %s%n",
                            r.getRoomId(), r.getRoomNumber(), r.getRoomTypeId(), r.getStatus());
                }
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    // ===== USER MENU =====
    private static void userMenu(User user) {
        while (true) {
            System.out.println("\n=== USER MENU ===");
            System.out.println("1. Make Reservation");
            System.out.println("2. Order Laundry");
            System.out.println("3. Order Food");
            System.out.println("4. Book Cab Service");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> makeReservation(user);
                case 2 -> orderLaundry(user);
                case 3 -> orderFood(user);
                case 4 -> bookCab(user);
                case 5 -> checkOut(user);
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    private static void checkOut(User user) {
        int userId = user.getUserId();
        reservationService.checkOut(userId);
    }

    private static void makeReservation(User user) {
        System.out.print("Room ID: ");
        int roomId = Integer.parseInt(scanner.nextLine());
        System.out.print("Check-in date (yyyy-mm-dd): ");
        Date checkIn = Date.valueOf(scanner.nextLine());
        System.out.print("Check-out date (yyyy-mm-dd): ");
        Date checkOut = Date.valueOf(scanner.nextLine());
        boolean success = reservationService.makeReservation(user.getUserId(), roomId, checkIn, checkOut);
        System.out.println(success ? "Reservation successful!" : "Failed to reserve room.");
    }

    private static void orderLaundry(User user) {
        System.out.print("Reservation ID: ");
        int resId = Integer.parseInt(scanner.nextLine());
        System.out.print("Service Description: ");
        String desc = scanner.nextLine();
        System.out.print("Cost: ");
        double cost = Double.parseDouble(scanner.nextLine());

        boolean success = laundryService.addLaundryService(resId, desc, cost);
        System.out.println(success ? "Laundry service ordered!" : "Failed to order laundry.");
    }

    private static void orderFood(User user) {
        System.out.print("Reservation ID: ");
        int resId = Integer.parseInt(scanner.nextLine());

        System.out.print("Food Item (1. Pizza, 2. Burger, 3. Rice): ");
        int food_item_id = Integer.parseInt(scanner.nextLine());

        System.out.print("Quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());

        boolean success = foodService.addFoodOrder(resId, qty, food_item_id);
        System.out.println(success ? "Food ordered!" : "Failed to order food.");
    }

    private static void bookCab(User user) {
        System.out.print("Reservation ID: ");
        int resId = Integer.parseInt(scanner.nextLine());
        System.out.print("Pickup Location: ");
        String pickup = scanner.nextLine();
        System.out.print("Drop Location: ");
        String drop = scanner.nextLine();
        System.out.print("Pickup Time (yyyy-mm-dd hh:mm:ss): ");
        Timestamp pickupTime = Timestamp.valueOf(scanner.nextLine());
        System.out.print("Cost: ");
        double cost = Double.parseDouble(scanner.nextLine());

        boolean success = cabService.addCabService(resId, pickup, drop, pickupTime, cost);
        System.out.println(success ? "Cab booked!" : "Failed to book cab.");
    }
}