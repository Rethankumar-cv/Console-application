package store;

import model.User;
import java.util.HashMap;

public class BankStore {

    public HashMap<Integer, User> users = new HashMap<>();
    public int accountCounter = 1001;

    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "admin123";
}
