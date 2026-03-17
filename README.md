# 🏦 RK Bank — Java Console Banking Application

A complete, menu-driven banking system built with **Java** and **OOP principles**.  
All data is stored in-memory using `HashMap<Integer, User>` — no database required.

---

## 📁 Project Structure

```
ATM CONSOLE/
├── src/
│   ├── Main.java                   ← Entry point, all menus & navigation
│   ├── model/
│   │   └── User.java               ← Customer account entity
│   ├── service/
│   │   └── RKBank.java             ← Core banking operations (HashMap storage)
│   └── util/
│       └── ConsoleUtils.java       ← Pretty-print helpers & ANSI colours
└── out/                            ← Compiled .class files (auto-generated)
```

---

## ⚙️ How to Compile & Run

### Step 1 — Open a terminal in the project root (`ATM CONSOLE/`)

### Step 2 — Compile
```bash
javac -d out src\Main.java src\model\User.java src\service\RKBank.java src\util\ConsoleUtils.java
```

### Step 3 — Run
```bash
java -cp out Main
```

---

## 🚀 Features

### 👤 User Features
| Feature            | Description                                                  |
|--------------------|--------------------------------------------------------------|
| Register           | Create account with name, password, initial deposit          |
| Login              | Secure login with account number + password                  |
| Check Balance      | View current balance + daily withdrawal usage                |
| Deposit            | Add funds with transaction record                            |
| Withdraw           | Deduct funds with balance & daily-limit validation           |
| Transfer           | Send money to another account (with self-transfer guard)     |
| Transaction History| Full timestamped log of all operations                       |

### 🛠️ Admin Features
| Feature              | Description                                 |
|----------------------|---------------------------------------------|
| View All Users       | Tabular list of all accounts                |
| Search by Account#   | Detailed view of a single account           |
| Delete Account       | Permanently remove an account (with confirm)|
| Freeze / Unfreeze    | Toggle account active status                |
| Total Bank Balance   | Sum of all customer balances                |

---

## 🔐 Admin Credentials (hardcoded)
```
Username : admin
Password : admin123
```

---

## 🧪 Demo Accounts (pre-seeded)
| Account # | Name          | Password  | Balance     |
|-----------|---------------|-----------|-------------|
| 1001      | Ravi Kumar    | ravi123   | ₹25,000.00  |
| 1002      | Priya Sharma  | priya456  | ₹15,000.00  |
| 1003      | Arjun Mehta   | arjun789  | ₹50,000.00  |

---

## 🎯 OOP Concepts Demonstrated
- **Encapsulation** — All fields are `private`, accessed via getters/setters
- **Modular Design** — Separate classes for Model, Service, Utility, and Main
- **ArrayList** — Transaction history stored per user
- **HashMap** — O(1) account lookup by account number
- **Timestamps** — Every transaction is recorded with `dd-MM-yyyy HH:mm:ss`
- **Daily Limit** — ₹50,000 per-day withdrawal cap with carry tracking
