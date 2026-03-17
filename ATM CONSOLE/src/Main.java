import model.User;
import service.AdminService;
import service.UserService;
import store.BankStore;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static BankStore store = new BankStore();
    static UserService userService = new UserService(store, sc);
    static AdminService adminService = new AdminService(store, sc);

    public static void main(String[] args) {
        store.users.put(1001, new User(1001, "Ravi Kumar", "ravi123", 25000));
        store.users.put(1002, new User(1002, "Priya Sharma", "priya456", 15000));
        store.accountCounter = 1003;

        System.out.println("===================================");
        System.out.println("       WELCOME TO RK BANK          ");
        System.out.println("===================================");
        System.out.println("Demo: 1001 / ravi123");
        System.out.println("Demo: 1002 / priya456");
        System.out.println("Admin: admin / admin123");

        while (true) {
            System.out.println("\n===================================");
            System.out.println("            MAIN MENU             ");
            System.out.println("===================================");
            System.out.println("1. Register");
            System.out.println("2. User Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 1) {
                userService.register();
            } else if (choice == 2) {
                handleUserSession();
            } else if (choice == 3) {
                handleAdminSession();
            } else if (choice == 4) {
                System.out.println("Thank you for using RK Bank. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Enter 1-4.");
            }
        }

        sc.close();
    }

    static void handleUserSession() {
        User user = userService.login();
        if (user == null) {
            return;
        }

        while (true) {
            System.out.println("\n===================================");
            System.out.println("   USER MENU - " + user.getName());
            System.out.println("===================================");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 1) {
                userService.checkBalance(user);
            } else if (choice == 2) {
                userService.deposit(user);
            } else if (choice == 3) {
                userService.withdraw(user);
            } else if (choice == 4) {
                userService.transfer(user);
            } else if (choice == 5) {
                userService.showHistory(user);
            } else if (choice == 6) {
                System.out.println("Logged out.");
                break;
            } else {
                System.out.println("Invalid choice. Enter 1-6.");
            }
        }
    }

    static void handleAdminSession() {
        boolean loggedIn = adminService.login();
        if (!loggedIn) {
            return;
        }

        while (true) {
            System.out.println("\n===================================");
            System.out.println("           ADMIN MENU             ");
            System.out.println("===================================");
            System.out.println("1. View All Users");
            System.out.println("2. Search User");
            System.out.println("3. Delete User");
            System.out.println("4. Freeze / Unfreeze Account");
            System.out.println("5. Total Bank Balance");
            System.out.println("6. Logout");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 1) {
                adminService.viewAllUsers();
            } else if (choice == 2) {
                adminService.searchUser();
            } else if (choice == 3) {
                adminService.deleteUser();
            } else if (choice == 4) {
                adminService.toggleFreeze();
            } else if (choice == 5) {
                adminService.viewTotalBalance();
            } else if (choice == 6) {
                System.out.println("Admin logged out.");
                break;
            } else {
                System.out.println("Invalid choice. Enter 1-6.");
            }
        }
    }
}
