package bank.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

import db.DBConnection;
import java.text.NumberFormat;
import java.util.Locale;

public class MiniStatement extends JFrame implements ActionListener {

    JButton exitButton;
    JLabel cardLabel, balanceLabel;
    JTextPane transactionPane;
    JScrollPane scrollPane;
    String pin;

    MiniStatement(String pin) {
        super("Mini Statement");
        this.pin = pin;

        getContentPane().setBackground(Color.WHITE);
        setSize(400, 600);
        setLocation(20, 20);
        setLayout(null);

        JLabel bankLabel = new JLabel("Easy Bank");
        bankLabel.setBounds(150, 20, 100, 20);
        bankLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(bankLabel);

        cardLabel = new JLabel();
        cardLabel.setBounds(20, 80, 300, 20);
        cardLabel.setForeground(Color.BLACK);
        cardLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(cardLabel);

        // Scrollable transactionPane
        transactionPane = new JTextPane();
        transactionPane.setContentType("text/html");
        transactionPane.setEditable(false);
        transactionPane.setFont(new Font("Monospaced", Font.PLAIN, 12));

        scrollPane = new JScrollPane(transactionPane);
        scrollPane.setBounds(20, 120, 340, 250);
        add(scrollPane);

        balanceLabel = new JLabel();
        balanceLabel.setBounds(20, 400, 300, 20);
        balanceLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
        add(balanceLabel);

        // Fetch and display card number
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT card_number FROM signupthree WHERE pin = ?");
            stmt.setString(1, pin);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String cardNumber = rs.getString("card_number");
                String maskedCard = cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12);
                cardLabel.setText("Card Number: " + maskedCard);
            } else {
                cardLabel.setText("Card Number: Not Found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            cardLabel.setText("Error fetching card number.");
        }

        // Fetch and display transactions and balance
        try {
            int balance = 0;
            Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bank WHERE pin = ?");
            stmt.setString(1, pin);
            ResultSet rs = stmt.executeQuery();

            StringBuilder transactions = new StringBuilder("<html>");
            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");

                transactions.append(date)
                            .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                            .append(type)
                            .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                            .append(amount)
                            .append("<br><br>");

                if (type.equals("Deposit")) {
                    balance += Integer.parseInt(amount);
                } else {
                    balance -= Integer.parseInt(amount);
                }
            }
            transactions.append("</html>");

            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "ZA"));
            String formattedBalance = formatter.format(balance);

            transactionPane.setText(transactions.toString());
            balanceLabel.setText("Your total Balance is " + formattedBalance);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error retrieving transaction history.");
        }

        exitButton = new JButton("Exit");
        exitButton.setBounds(20, 500, 100, 25);
        exitButton.addActionListener(this);
        add(exitButton);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new MiniStatement("").setVisible(true);
    }
}
