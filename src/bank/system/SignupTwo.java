package bank.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.DBConnection;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

public class SignupTwo extends javax.swing.JFrame {
    private final int formNo;
    private static final String[] EDUCATION_LEVELS = {"Select", "High School", "Diploma", "Degree", "Postgraduate"};
    private static final String[] INCOME_SOURCES = {"Select", "Salary", "Business", "Investments", "Other"};
    
    
    public SignupTwo(int formNo) {
        if (formNo <= 0) {
            throw new IllegalArgumentException("Invalid form number");
        }
        this.formNo = formNo;
        initComponents();
         
        txtFormNo.setText(String.valueOf(formNo));
        
        // Initialize combo boxes
        initializeComboBoxes();
        
        // Group radio buttons and select default
        setupRadioButtons();
    }

    private void initializeComboBoxes() {
        for (String level : EDUCATION_LEVELS) {
            educationComboBox.addItem(level);
        }
        
        for (String source : INCOME_SOURCES) {
            incomeComboBox.addItem(source);
        }
    }

    private void setupRadioButtons() {
        ButtonGroup raceGroup = new ButtonGroup();
        raceGroup.add(radioBlack);
        raceGroup.add(radioWhite);
        raceGroup.add(radioIndian); 
        raceGroup.add(radioColoured);
        
        // Set default selection
        radioBlack.setSelected(true);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtId = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        nationalityField = new javax.swing.JTextField();
        txtNationality = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        raceGroup = new javax.swing.JLabel();
        radioBlack = new javax.swing.JRadioButton();
        radioWhite = new javax.swing.JRadioButton();
        radioIndian = new javax.swing.JRadioButton();
        radioColoured = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        txtEducation = new javax.swing.JLabel();
        educationComboBox = new javax.swing.JComboBox<>();
        txtIncome = new javax.swing.JLabel();
        incomeComboBox = new javax.swing.JComboBox<>();
        txtMonthlyIncome = new javax.swing.JLabel();
        monthlyIncomeField = new javax.swing.JTextField();
        txtOccupation = new javax.swing.JLabel();
        occupationField = new javax.swing.JTextField();
        nextBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        backBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtFormNo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 10)); // NOI18N
        jLabel1.setText("Page 2: Additional Details");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Personal Information"));

        txtId.setText("ID:");

        nationalityField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nationalityFieldActionPerformed(evt);
            }
        });

        txtNationality.setText("Nationality:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(txtNationality, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idField, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(nationalityField))
                .addContainerGap(310, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(idField)
                    .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nationalityField)
                    .addComponent(txtNationality, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Demographic Section"));

        raceGroup.setText("Race:");

        buttonGroup1.add(radioBlack);
        radioBlack.setText("Black");

        buttonGroup1.add(radioWhite);
        radioWhite.setText("White");

        buttonGroup1.add(radioIndian);
        radioIndian.setText("Indian");

        buttonGroup1.add(radioColoured);
        radioColoured.setText("Coloured");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(raceGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(radioBlack)
                .addGap(42, 42, 42)
                .addComponent(radioWhite)
                .addGap(40, 40, 40)
                .addComponent(radioIndian)
                .addGap(51, 51, 51)
                .addComponent(radioColoured)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(raceGroup)
                    .addComponent(radioBlack)
                    .addComponent(radioWhite)
                    .addComponent(radioIndian)
                    .addComponent(radioColoured))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Economic Information"));

        txtEducation.setText("Education Level:");

        educationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "High School", "Diploma", "Degree", "Postgraduate" }));

        txtIncome.setText("Income Source:");

        incomeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Employment", "Business", "Investments", "Social Grant", "Other" }));

        txtMonthlyIncome.setText("Monthly Income:");

        txtOccupation.setText("Occupation:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEducation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtIncome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMonthlyIncome, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(txtOccupation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(monthlyIncomeField)
                    .addComponent(occupationField)
                    .addComponent(incomeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(educationComboBox, 0, 160, Short.MAX_VALUE))
                .addGap(284, 284, 284))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(educationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEducation, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(incomeComboBox)
                    .addComponent(txtIncome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(monthlyIncomeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtMonthlyIncome, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOccupation, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(occupationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        nextBtn.setText("Next");
        nextBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtnActionPerformed(evt);
            }
        });

        backBtn.setText("Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Form No.");

        txtFormNo.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFormNo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(backBtn)
                                .addGap(18, 18, 18)
                                .addComponent(nextBtn))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFormNo, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextBtn)
                    .addComponent(backBtn))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nationalityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nationalityFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nationalityFieldActionPerformed

    private void nextBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtnActionPerformed
       if (!validateInputs()) {
            return;
        }
        
        try {
            saveFormData();
            navigateToNextForm();
        } catch (SQLException ex) {
            handleDatabaseError(ex);
        }
    }//GEN-LAST:event_nextBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
       dispose();
       new SignupOne().setVisible(true); // Navigate to SignupOne 
    }//GEN-LAST:event_backBtnActionPerformed

    private boolean validateInputs() {
        // Validate required fields
        if (idField.getText().trim().isEmpty()) {
            showError("ID number is required", idField);
            return false;
        }
        
        if (nationalityField.getText().trim().isEmpty()) {
            showError("Nationality is required", nationalityField);
            return false;
        }
        
        if (educationComboBox.getSelectedIndex() == 0) {
            showError("Please select education level", educationComboBox);
            return false;
        }
        
        if (incomeComboBox.getSelectedIndex() == 0) {
            showError("Please select income source", incomeComboBox);
            return false;
        }
        
        // Validate numeric fields
        String incomeText = monthlyIncomeField.getText().trim();
        if (incomeText.isEmpty()) {
            showError("Monthly income is required", monthlyIncomeField);
            return false;
        }
        
        try {
            double income = Double.parseDouble(incomeText);
            if (income < 0) {
                showError("Income cannot be negative", monthlyIncomeField);
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Monthly income must be a number", monthlyIncomeField);
            return false;
        }
        
        if (occupationField.getText().trim().isEmpty()) {
            showError("Occupation is required", occupationField);
            return false;
        }
        
        return true;
    }

    private void saveFormData() throws SQLException {
        String idNumber = idField.getText().trim();
        String nationality = nationalityField.getText().trim();
        String race = getSelectedRace();
        String education = (String) educationComboBox.getSelectedItem();
        String incomeSource = (String) incomeComboBox.getSelectedItem();
        String monthlyIncome = monthlyIncomeField.getText().trim();
        String occupation = occupationField.getText().trim();

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO signuptwo (formno, id_number, nationality, race, " +
                         "education_level, income_source, monthly_income, occupation) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, formNo);
            stmt.setString(2, idNumber);
            stmt.setString(3, nationality);
            stmt.setString(4, race);
            stmt.setString(5, education);
            stmt.setString(6, incomeSource);
            stmt.setString(7, monthlyIncome);
            stmt.setString(8, occupation);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Failed to save details, no rows affected");
            }
        }
    }

    private String getSelectedRace() {
        if (radioBlack.isSelected()) return "Black";
        if (radioWhite.isSelected()) return "White";
        if (radioIndian.isSelected()) return "Indian";
        if (radioColoured.isSelected()) return "Coloured";
        return "Unknown"; // Shouldn't happen due to validation
    }
    
     private void navigateToNextForm() {
        this.dispose();
        new SignupThree(formNo).setVisible(true);
    }

    private void handleDatabaseError(SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, 
            "Error saving data: " + ex.getMessage(), 
            "Database Error", 
            JOptionPane.ERROR_MESSAGE);
    }

    private void showError(String message, java.awt.Component component) {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
        component.requestFocus();
    }

    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, 
                "This form requires a valid form number", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> educationComboBox;
    private javax.swing.JTextField idField;
    private javax.swing.JComboBox<String> incomeComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField monthlyIncomeField;
    private javax.swing.JTextField nationalityField;
    private javax.swing.JButton nextBtn;
    private javax.swing.JTextField occupationField;
    private javax.swing.JLabel raceGroup;
    private javax.swing.JRadioButton radioBlack;
    private javax.swing.JRadioButton radioColoured;
    private javax.swing.JRadioButton radioIndian;
    private javax.swing.JRadioButton radioWhite;
    private javax.swing.JLabel txtEducation;
    private javax.swing.JLabel txtFormNo;
    private javax.swing.JLabel txtId;
    private javax.swing.JLabel txtIncome;
    private javax.swing.JLabel txtMonthlyIncome;
    private javax.swing.JLabel txtNationality;
    private javax.swing.JLabel txtOccupation;
    // End of variables declaration//GEN-END:variables
}
