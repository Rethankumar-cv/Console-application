package service;

import model.User;
import store.BankStore;

import java.util.Scanner;

public class UserService {

    private BankStore store;
    private Scanner sc;

    public UserService(BankStore store, Scanner sc) {
        this.store = store;
        this.sc = sc;
    }

    public void register() {
        System.out.println("\n--- Register New Account ---");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Set password: ");
        String password = sc.nextLine();

        System.out.print("Initial deposit amount: Rs.");
        double deposit = Double.parseDouble(sc.nextLine());

        if (deposit < 0) {
            System.out.println("ERROR: Deposit cannot be negative.");
            return;
        }

        int accNo = store.accountCounter;
        store.accountCounter++;

        User user = new User(accNo, name, password, deposit);
        store.users.put(accNo, user);

        System.out.println("Account created! Your account number is: " + accNo);
    }

    public User login() {
        System.out.println("\n--- User Login ---");

        System.out.print("Account Number: ");
        int accNo = Integer.parseInt(sc.nextLine());

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = store.users.get(accNo);

        if (user == null) {
            System.out.println("ERROR: Account not found.");
            return null;
        }

        if (!user.getPassword().equals(password)) {
            System.out.println("ERROR: Wrong password.");
            return null;
        }

        if (!user.isActive()) {
            System.out.println("ERROR: Account is frozen. Contact admin.");
            return null;
        }

        System.out.println("Login successful! Welcome, " + user.getName());
        return user;
    }

    public void checkBalance(User user) {
        System.out.println("\n--- Account Balance ---");
        System.out.println("Name    : " + user.getName());
        System.out.println("Account : " + user.getAccountNumber());
        System.out.println("Balance : Rs." + user.getBalance());
    }

    public void deposit(User user) {
        System.out.println("\n--- Deposit Money ---");
        System.out.println("Accepted notes: Rs.100, Rs.200, Rs.500");

        System.out.print("Number of Rs.100 notes: ");
        int notes100 = Integer.parseInt(sc.nextLine());

        System.out.print("Number of Rs.200 notes: ");
        int notes200 = Integer.parseInt(sc.nextLine());

        System.out.print("Number of Rs.500 notes: ");
        int notes500 = Integer.parseInt(sc.nextLine());

        if (notes100 < 0 || notes200 < 0 || notes500 < 0) {
            System.out.println("ERROR: Note count cannot be negative.");
            return;
        }

        double total = (notes100 * 100) + (notes200 * 200) + (notes500 * 500);

        if (total == 0) {
            System.out.println("ERROR: Please insert at least one note.");
            return;
        }

        System.out.println("Rs.100 x " + notes100 + " = Rs." + (notes100 * 100));
        System.out.println("Rs.200 x " + notes200 + " = Rs." + (notes200 * 200));
        System.out.println("Rs.500 x " + notes500 + " = Rs." + (notes500 * 500));
        System.out.println("Total deposited: Rs." + total);

        user.deposit(total);
        user.addTransaction("Deposited Rs." + total + " | Balance: Rs." + user.getBalance());

        System.out.println("New Balance: Rs." + user.getBalance());
    }

    public void withdraw(User user) {
        System.out.println("\n--- Withdraw Money ---");
        System.out.print("Enter amount: Rs.");
        double amount = Double.parseDouble(sc.nextLine());

        if (amount <= 0) {
            System.out.println("ERROR: Amount must be greater than 0.");
            return;
        }

        if (user.getBalance() < amount) {
            System.out.println("ERROR: Insufficient balance. Available: Rs." + user.getBalance());
            return;
        }

        user.withdraw(amount);
        user.addTransaction("Withdrew Rs." + amount + " | Balance: Rs." + user.getBalance());
        System.out.println("Rs." + amount + " withdrawn. Remaining: Rs." + user.getBalance());
    }

    public void transfer(User sender) {
        System.out.println("\n--- Transfer Money ---");

        System.out.print("Receiver Account Number: ");
        int receiverAccNo = Integer.parseInt(sc.nextLine());

        if (sender.getAccountNumber() == receiverAccNo) {
            System.out.println("ERROR: Cannot transfer to your own account.");
            return;
        }

        User receiver = store.users.get(receiverAccNo);

        if (receiver == null) {
            System.out.println("ERROR: Receiver account not found.");
            return;
        }

        if (!receiver.isActive()) {
            System.out.println("ERROR: Receiver account is frozen.");
            return;
        }

        System.out.print("Enter amount: Rs.");
        double amount = Double.parseDouble(sc.nextLine());

        if (amount <= 0) {
            System.out.println("ERROR: Amount must be greater than 0.");
            return;
        }

        if (sender.getBalance() < amount) {
            System.out.println("ERROR: Insufficient balance. Available: Rs." + sender.getBalance());
            return;
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        sender.addTransaction("Transferred Rs." + amount + " to Account#" + receiverAccNo
                + " | Balance: Rs." + sender.getBalance());
        receiver.addTransaction("Received Rs." + amount + " from Account#" + sender.getAccountNumber()
                + " | Balance: Rs." + receiver.getBalance());

        System.out.println("Rs." + amount + " transferred to Account#" + receiverAccNo);
        System.out.println("Your balance: Rs." + sender.getBalance());
    }

    public void showHistory(User user) {
        System.out.println("\n--- Transaction History ---");

        if (user.getTransactions().isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        for (int i = 0; i < user.getTransactions().size(); i++) {
            System.out.println((i + 1) + ". " + user.getTransactions().get(i));
        }
    }
}
