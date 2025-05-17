package bank.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.sql.*;

import db.DBConnection;
import java.text.NumberFormat;
import java.util.Locale;

public class Withdrawal extends JFrame implements ActionListener {

    JTextField t1;
    JButton b1, b2;
    JLabel l1, l2;
    String pin;

    Withdrawal(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 800, 800);
        add(background);

        l1 = new JLabel("MAXIMUM WITHDRAWAL IS R.10,000");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        l2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System", Font.BOLD, 14));

        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.PLAIN, 14));

        b1 = new JButton("WITHDRAW");
        b2 = new JButton("BACK");

        setLayout(null);

        l1.setBounds(150, 280, 400, 30);
        background.add(l1);

        l2.setBounds(150, 318, 400, 20);
        background.add(l2);

        t1.setBounds(150, 350, 300, 30);
        background.add(t1);

        b1.setBounds(300, 400, 150, 30);
        background.add(b1);

        b2.setBounds(300, 450, 150, 30);
        background.add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setSize(800, 800);
        setLocation(500, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String amountStr = t1.getText().trim();
            Date date = new Date();

            if (ae.getSource() == b1) {
                if (amountStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
                    return;
                }

                int amount = Integer.parseInt(amountStr);
                Connection conn = DBConnection.getConnection();

                // Calculate current balance
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

                // Insert withdrawal
                String insertQuery = "INSERT INTO bank(pin, date, type, amount) VALUES (?, ?, ?, ?)";
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
            } else if (ae.getSource() == b2) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Transaction failed due to a system error.");
        }
    }

    public static void main(String[] args) {
        new Withdrawal("").setVisible(true);
    }
}
