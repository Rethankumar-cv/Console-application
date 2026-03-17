package service;

import model.User;
import store.BankStore;

import java.util.Scanner;

public class AdminService {

    private BankStore store;
    private Scanner sc;

    public AdminService(BankStore store, Scanner sc) {
        this.store = store;
        this.sc = sc;
    }

    public boolean login() {
        System.out.println("\n--- Admin Login ---");

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        if (username.equals(BankStore.ADMIN_USERNAME) && password.equals(BankStore.ADMIN_PASSWORD)) {
            System.out.println("Admin login successful!");
            return true;
        }

        System.out.println("ERROR: Wrong admin credentials.");
        return false;
    }

    public void viewAllUsers() {
        System.out.println("\n--- All Users ---");

        if (store.users.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (User user : store.users.values()) {
            System.out.println("Account#: " + user.getAccountNumber()
                    + " | Name: " + user.getName()
                    + " | Balance: Rs." + user.getBalance()
                    + " | Status: " + (user.isActive() ? "ACTIVE" : "FROZEN"));
        }
    }

    public void searchUser() {
        System.out.println("\n--- Search User ---");
        System.out.print("Enter Account Number: ");
        int accNo = Integer.parseInt(sc.nextLine());

        User user = store.users.get(accNo);

        if (user == null) {
            System.out.println("ERROR: Account not found.");
            return;
        }

        System.out.println("Account# : " + user.getAccountNumber());
        System.out.println("Name     : " + user.getName());
        System.out.println("Balance  : Rs." + user.getBalance());
        System.out.println("Status   : " + (user.isActive() ? "ACTIVE" : "FROZEN"));
    }

    public void deleteUser() {
        System.out.println("\n--- Delete User ---");
        System.out.print("Enter Account Number: ");
        int accNo = Integer.parseInt(sc.nextLine());

        if (!store.users.containsKey(accNo)) {
            System.out.println("ERROR: Account not found.");
            return;
        }

        System.out.print("Type YES to confirm: ");
        String confirm = sc.nextLine();

        if (!confirm.equals("YES")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        store.users.remove(accNo);
        System.out.println("Account #" + accNo + " deleted.");
    }

    public void toggleFreeze() {
        System.out.println("\n--- Freeze / Unfreeze Account ---");
        System.out.print("Enter Account Number: ");
        int accNo = Integer.parseInt(sc.nextLine());

        User user = store.users.get(accNo);

        if (user == null) {
            System.out.println("ERROR: Account not found.");
            return;
        }

        if (user.isActive()) {
            user.setActive(false);
            System.out.println("Account #" + accNo + " is now FROZEN.");
        } else {
            user.setActive(true);
            System.out.println("Account #" + accNo + " is now ACTIVE.");
        }
    }

    public void viewTotalBalance() {
        System.out.println("\n--- Bank Summary ---");
        double total = 0;

        for (User user : store.users.values()) {
            total = total + user.getBalance();
        }

        System.out.println("Total Accounts : " + store.users.size());
        System.out.println("Total Balance  : Rs." + total);
    }
}
