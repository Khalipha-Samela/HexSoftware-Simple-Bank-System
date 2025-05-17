package bank.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

import db.DBConnection;
import java.text.NumberFormat;
import java.util.Locale;

public class FastCash extends JFrame implements ActionListener {

    JLabel l1;
    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin;

    FastCash(String pin) {
        this.pin = pin;

        // Set background image scaled to 800x800
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 800, 800);
        add(background);

        l1 = new JLabel("SELECT WITHDRAWAL AMOUNT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setBounds(175, 280, 400, 30);
        background.add(l1);

        // Buttons
        b1 = new JButton("R 100");
        b2 = new JButton("R 500");
        b3 = new JButton("R 1000");
        b4 = new JButton("R 2000");
        b5 = new JButton("R 5000");
        b6 = new JButton("R 10000");
        b7 = new JButton("BACK");

        // Set bounds for buttons in 3 rows
        b1.setBounds(140, 315, 120, 30);
        b2.setBounds(310, 315, 120, 30);
        b3.setBounds(140, 355, 120, 30);
        b4.setBounds(310, 355, 120, 30);
        b5.setBounds(140, 395, 120, 30);
        b6.setBounds(310, 395, 120, 30);
        b7.setBounds(310, 450, 120, 30);

        // Add buttons to background
        background.add(b1);
        background.add(b2);
        background.add(b3);
        background.add(b4);
        background.add(b5);
        background.add(b6);
        background.add(b7);

        // Action listeners
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        // Frame setup
        setLayout(null);
        setSize(800, 800);
        setLocation(500, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String buttonText = ((JButton) ae.getSource()).getText();
            if (buttonText.equals("BACK")) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
                return;
            }

            String amountStr = buttonText.substring(3).trim(); // Remove "R " prefix
            int amount = Integer.parseInt(amountStr);
            Date date = new Date();

            Connection conn = DBConnection.getConnection();

            // Fetch current balance
            String balanceQuery = "SELECT type, amount FROM bank WHERE pin = ?";
            PreparedStatement balanceStmt = conn.prepareStatement(balanceQuery);
            balanceStmt.setString(1, pin);
            ResultSet rs = balanceStmt.executeQuery();

            int balance = 0;
            while (rs.next()) {
                String type = rs.getString("type");
                int amt = Integer.parseInt(rs.getString("amount"));
                if (type.equals("Deposit")) {
                    balance += amt;
                } else {
                    balance -= amt;
                }
            }

            if (balance < amount) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            // Perform withdrawal
            String insertQuery = "INSERT INTO bank (pin, date, type, amount) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
            insertStmt.setString(1, pin);
            insertStmt.setString(2, date.toString());
            insertStmt.setString(3, "Withdrawal");
            insertStmt.setString(4, amountStr);
            insertStmt.executeUpdate();

            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "ZA"));
            String formattedAmount = formatter.format(amount);
            JOptionPane.showMessageDialog(null, "Amount " + formattedAmount + " Debited Successfully");

            setVisible(false);
            new Transactions(pin).setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Transaction failed due to a system error.");
        }
    }

    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }
}
