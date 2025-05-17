#HexSoftware Simple Bank System

This is a Java Swing-based desktop banking application that simulates a basic ATM system. It allows users to register, log in, and perform common banking operations like deposits, withdrawals, checking balance, and viewing mini-statements.

---

## Features

- User Signup (3-step form)
- PIN-based Login
- Deposit & Withdraw Funds
- Fast Cash (Quick Withdraw)
- View Mini Statement
- Balance Enquiry
- Change PIN
- Transactions Dashboard
- MySQL database integration via JDBC

---

##Technologies Used

- Java (JDK 8+)
- Swing for GUI
- JDBC for database connection
- MySQL database

##Database Configuration Instructions
-Copy "config_sample.properties" and rename it to "config.properties".
-Open "config.properties" and update it with your database credentials:
   - db.url=jdbc:mysql://localhost:3306/bank_system
   - db.username: Your MySQL username.
   - db.password: Your MySQL password.



## How It Works

### 1. **User Signup**
- Users go through 3 pages (`SignupOne`, `SignupTwo`, `SignupThree`) to enter personal, contact, and account details.
- A unique PIN is generated and stored in the database.

### 2. **Login**
- Users enter their PIN in `LoginForm.java`.
- Successful login redirects to the `Transactions` dashboard.

### 3. **Transactions Menu**
- Users can:
  - Check Balance (`BalanceEnquiry.java`)
  - Deposit Funds (`Deposit.java`)
  - Withdraw Funds (`Withdrawal.java`)
  - Use Fast Cash (`FastCash.java`) – Quick withdrawal in fixed amounts
  - View Mini Statement (`MiniStatement.java`)
  - Change PIN (`Pin.java`)
  - Exit application

### 4. **Database**
- All transactions (type, amount, date) are saved under the user's PIN.
- Stored in a table called `bank`.

---

##Sample Use Case

1. Start the app:  
   ```bash
   javac -d bin src/**/*.java
   java bank.system.LoginForm

2. Register as a new user.

3. Login using your generated PIN.

4. Use the dashboard to:
-Deposit R500
-Withdraw R200
-View your current balance and transaction history.

