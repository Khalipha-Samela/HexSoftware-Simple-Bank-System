package bank.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;

import db.DBConnection;
import java.text.NumberFormat;
import java.util.Locale;

public class Deposit extends JFrame implements ActionListener {

    JTextField t1;
    JButton b1, b2;
    JLabel l1;
    String pin;

    Deposit(String pin) {
        this.pin = pin;

        // Load and scale ATM image to 800x800
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 800, 800);
        add(l3);

        // Label
        l1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 14));
        l1.setBounds(140, 290, 400, 30);
        l3.add(l1);

        // Text field
        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.PLAIN, 14));
        t1.setBounds(145, 350, 300, 25);
        l3.add(t1);

        // Deposit button
        b1 = new JButton("DEPOSIT");
        b1.setBounds(300, 400, 150, 30);
        b1.setFocusPainted(false);
        l3.add(b1);

        // Back button
        b2 = new JButton("BACK");
        b2.setBounds(300, 440, 150, 30);
        b2.setFocusPainted(false);
        l3.add(b2);

        // Add listeners
        b1.addActionListener(this);
        b2.addActionListener(this);

        // Frame setup
        setLayout(null);
        setSize(800, 800);
        setLocation(500, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String amount = t1.getText().trim();
            Date date = new Date();

            if (ae.getSource() == b1) {
                if (amount.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Deposit");
                } else {
                    Connection conn = DBConnection.getConnection();
                    String query = "INSERT INTO bank(pin, date, type, amount) VALUES (?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, pin);
                    pstmt.setString(2, date.toString());
                    pstmt.setString(3, "Deposit");
                    pstmt.setString(4, amount);
                    pstmt.executeUpdate();

                    
                    double amountValue = Double.parseDouble(amount);
                    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "ZA"));
                    String formattedAmount = formatter.format(amountValue);
                    
                    JOptionPane.showMessageDialog(null, formattedAmount + " Deposited Successfully");
                    setVisible(false);
                    new Transactions(pin).setVisible(true);
                }
            } else if (ae.getSource() == b2) {
                setVisible(false);
                new Transactions(pin).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Deposit failed due to a system error.");
        }
    }

    public static void main(String[] args) {
        new Deposit("").setVisible(true);
    }
}
