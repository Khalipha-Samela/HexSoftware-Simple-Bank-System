package bank.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;

public class Transactions extends JFrame implements ActionListener {

    JLabel l1;
    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin;

    Transactions(String pin) {
        this.pin = pin;

        // Load ATM background image
        JLabel backgroundLabel;
        URL imageUrl = getClass().getClassLoader().getResource("icons/atm.jpg");
        if (imageUrl != null) {
            ImageIcon i1 = new ImageIcon(imageUrl);
            Image i2 = i1.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
            ImageIcon i3 = new ImageIcon(i2);
            backgroundLabel = new JLabel(i3);
        } else {
            System.out.println("Image not found: icons/atm.jpg");
            backgroundLabel = new JLabel();
            backgroundLabel.setBackground(Color.BLACK);
            backgroundLabel.setOpaque(true);
        }
        backgroundLabel.setBounds(0, 0, 800, 800);
        add(backgroundLabel);

        // Heading
        l1 = new JLabel("Please Select Your Transaction");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 14));
        l1.setBounds(200, 280, 400, 30); // Centered in the screen area
        backgroundLabel.add(l1);

        // Button positions
        int btnWidth = 140;
        int btnHeight = 30;
        int leftX = 150;
        int rightX = 300;
        int startY = 330;
        int gapY = 40;

        // Buttons
        b1 = createButton("DEPOSIT", leftX, startY, backgroundLabel);
        b2 = createButton("CASH WITHDRAWAL", rightX, startY, backgroundLabel);
        b3 = createButton("FAST CASH", leftX, startY + gapY, backgroundLabel);
        b4 = createButton("MINI STATEMENT", rightX, startY + gapY, backgroundLabel);
        b5 = createButton("PIN CHANGE", leftX, startY + 2 * gapY, backgroundLabel);
        b6 = createButton("BALANCE ENQUIRY", rightX, startY + 2 * gapY, backgroundLabel);
        b7 = createButton("EXIT", rightX, startY + 3 * gapY, backgroundLabel);

        // Add action listeners
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        // Frame settings
        setLayout(null);
        setSize(800, 800);
        setLocation(500, 0);
        setUndecorated(true);
        setVisible(true);
    }

    private JButton createButton(String text, int x, int y, JLabel backgroundLabel) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 140, 30);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(220, 230, 240));
        btn.setFont(new Font("Tahoma", Font.PLAIN, 10));
        backgroundLabel.add(btn);
        return btn;
    }

    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == b1) {
            setVisible(false);
            new Deposit(pin).setVisible(true);
        } else if (source == b2) {
            setVisible(false);
            new Withdrawal(pin).setVisible(true);
        } else if (source == b3) {
            setVisible(false);
            new FastCash(pin).setVisible(true);
        } else if (source == b4) {
            new MiniStatement(pin).setVisible(true);
        } else if (source == b5) {
            setVisible(false);
            new Pin(pin).setVisible(true);
        } else if (source == b6) {
            setVisible(false);
            new BalanceEnquiry(pin).setVisible(true);
        } else if (source == b7) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Transactions("").setVisible(true);
    }
}
