package bank.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import db.DBConnection;
import java.text.NumberFormat;
import java.util.Locale;

class BalanceEnquiry extends JFrame implements ActionListener {

    JButton b1;
    JLabel balanceLabel;
    String pin;

    BalanceEnquiry(String pin) {
        this.pin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 800, 800);
        add(background);

        balanceLabel = new JLabel();
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(new Font("System", Font.BOLD, 16));
        balanceLabel.setBounds(150, 300, 400, 35);
        background.add(balanceLabel);

        b1 = new JButton("BACK");
        b1.setBounds(300, 410, 150, 35);
        background.add(b1);

        b1.addActionListener(this);

        setLayout(null);
        setSize(800, 800);
        setUndecorated(true);
        setLocation(500, 0);
        setVisible(true);

        displayBalance();
    }

    private void displayBalance() {
        int balance = 0;

        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT type, amount FROM bank WHERE pin = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, pin);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");
                int amount = Integer.parseInt(rs.getString("amount"));

                if (type.equals("Deposit")) {
                    balance += amount;
                } else {
                    balance -= amount;
                }
            }

            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "ZA"));
            String formattedBalance = formatter.format(balance);
            balanceLabel.setText("Your Current Account Balance is " + formattedBalance);


        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to retrieve balance due to system error.");
        }
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pin).setVisible(true);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("").setVisible(true);
    }
}
