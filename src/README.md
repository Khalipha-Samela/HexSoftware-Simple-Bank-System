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

##Before running the code
1. Download jcalender- 1.4 and mysql-connector-j-9.1.0.
2. Add them to the project liberaries folder under "Add JAR/Folder".

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

##Database Queries 

1. Create database with bank_system in mysql.
   -create database bank_system;

2. Select the database you just created.
   -use bank_system;

3. Create Table: signup
CREATE TABLE signup (
    formNo VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100),
    mother_name VARCHAR(100),
    gender VARCHAR(10),
    dob DATE,
    email VARCHAR(100),
    marital_status VARCHAR(20),
    address VARCHAR(255),
    city VARCHAR(100),
    zip_code VARCHAR(20)
);

4. Create Table: signuptwo
CREATE TABLE signuptwo (
    formNo VARCHAR(20),
    id_number VARCHAR(13),
    nationality VARCHAR(100),
    race VARCHAR(50),
    education_level VARCHAR(100),
    income_source VARCHAR(100),
    monthly_income VARCHAR(50),
    occupation VARCHAR(100)
);

5. Create Table: signupthree
CREATE TABLE signupthree (
    formNo VARCHAR(20) PRIMARY KEY,
    account_type VARCHAR(50),
    card_number VARCHAR(20),
    pin VARCHAR(255),
    services TEXT
);

6. Create Table: login
CREATE TABLE login (
    formNo VARCHAR(20),
    card_number VARCHAR(25),
    pin VARCHAR(10)
);

7. Create Table: bank
CREATE TABLE bank (
    pin VARCHAR(10),
    date VARCHAR(50),
    type VARCHAR(20),
    amount VARCHAR(20),
    formNo VARCHAR(20)
);
