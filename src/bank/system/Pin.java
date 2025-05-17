package bank.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import db.DBConnection;

public class Pin extends JFrame implements ActionListener {
    private String cardNumber;
    private String formno;
    private JPasswordField newPinField, confirmPinField;
    private JButton changeBtn, backBtn;
    private JLabel titleLabel, newPinLabel, confirmPinLabel;

    Pin(String cardNumber) {
        this.cardNumber = cardNumber;
        initializeUI();
        setupDatabaseConnection();
    }

    private void initializeUI() {
        setTitle("Change PIN");
        setSize(800, 800);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background image
        ImageIcon backgroundIcon = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image scaledImage = backgroundIcon.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaledImage));
        background.setLayout(null);
        add(background);

        // Labels
        titleLabel = createLabel("CHANGE YOUR PIN", 200, 280, 400, 35);
        newPinLabel = createLabel("New PIN:", 150, 320, 150, 35);
        confirmPinLabel = createLabel("Confirm PIN:", 150, 358, 170, 35);

        // Password fields
        newPinField = createPasswordField(270, 325, 180, 25);
        confirmPinField = createPasswordField(270, 360, 180, 25);

        // Buttons
        changeBtn = createButton("CHANGE", 300, 400, 150, 30);
        backBtn = createButton("BACK", 300, 440, 150, 30);

        // Add components to background
        background.add(titleLabel);
        background.add(newPinLabel);
        background.add(confirmPinLabel);
        background.add(newPinField);
        background.add(confirmPinField);
        background.add(changeBtn);
        background.add(backBtn);

        setVisible(true);
    }

    private JLabel createLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("System", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, width, height);
        return label;
    }

    private JPasswordField createPasswordField(int x, int y, int width, int height) {
        JPasswordField field = new JPasswordField();
        field.setFont(new Font("Raleway", Font.PLAIN, 14));
        field.setBounds(x, y, width, height);
        return field;
    }

    private JButton createButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(this);
        return button;
    }

    private void setupDatabaseConnection() {
        try (Connection conn = DBConnection.getConnection()) {
            // Get formno and current PIN from database
            String query = "SELECT formno, pin FROM login WHERE card_number = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, cardNumber);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        this.formno = rs.getString("formno");
                        newPinField.putClientProperty("currentPin", rs.getString("pin"));
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error connecting to database", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == changeBtn) {
            handlePinChange();
        } else if (ae.getSource() == backBtn) {
            navigateToTransactions();
        }
    }

    private void handlePinChange() {
        String newPin = new String(newPinField.getPassword()).trim();
        String confirmPin = new String(confirmPinField.getPassword()).trim();
        String currentPin = (String) newPinField.getClientProperty("currentPin");

        if (!validatePinInput(newPin, confirmPin, currentPin)) {
            return;
        }

        if (updatePinInDatabase(newPin)) {
            JOptionPane.showMessageDialog(this, "PIN changed successfully!");
            navigateToTransactions(newPin);
        }
    }

    private boolean validatePinInput(String newPin, String confirmPin, String currentPin) {
        if (newPin.isEmpty() || confirmPin.isEmpty()) {
            showError("Please fill in both PIN fields");
            return false;
        }

        if (!newPin.equals(confirmPin)) {
            showError("Entered PINs do not match");
            return false;
        }

        if (!newPin.matches("\\d{4}")) {
            showError("PIN must be a 4-digit number");
            return false;
        }

        if (newPin.equals(currentPin)) {
            showError("New PIN must be different from current PIN");
            return false;
        }

        return true;
    }

    private boolean updatePinInDatabase(String newPin) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            try {
                // Update login table
                String loginUpdate = "UPDATE login SET pin = ? WHERE card_number = ?";
                try (PreparedStatement ps = conn.prepareStatement(loginUpdate)) {
                    ps.setString(1, newPin);
                    ps.setString(2, cardNumber);
                    ps.executeUpdate();
                }

                // Update bank table - using formno
                String bankUpdate = "UPDATE bank SET pin = ? WHERE formno = ?";
                try (PreparedStatement ps = conn.prepareStatement(bankUpdate)) {
                    ps.setString(1, newPin);
                    ps.setString(2, formno);
                    ps.executeUpdate();
                }

                // Update signupthree table - using formno
                String signupUpdate = "UPDATE signupthree SET pin = ? WHERE formno = ?";
                try (PreparedStatement ps = conn.prepareStatement(signupUpdate)) {
                    ps.setString(1, newPin);
                    ps.setString(2, formno);
                    ps.executeUpdate();
                }

                conn.commit(); // Commit transaction
                return true;

            } catch (SQLException e) {
                conn.rollback(); // Rollback on error
                showError("Failed to update PIN: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            showError("Database connection error: " + e.getMessage());
            return false;
        }
    }

    private void navigateToTransactions() {
        String currentPin = (String) newPinField.getClientProperty("currentPin");
        navigateToTransactions(currentPin);
    }

    private void navigateToTransactions(String pin) {
        this.dispose();
        new Transactions(pin).setVisible(true);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Pin("1234567890123456"));
    }
}