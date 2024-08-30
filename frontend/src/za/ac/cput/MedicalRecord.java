package za.ac.cput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicalRecord extends JPanel {

    private static final long serialVersionUID = 1L;

    public MedicalRecord(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblMedicalRecordManagement = new JLabel("Animal Shelter Application");
        lblMedicalRecordManagement.setFont(new Font("Dialog", Font.BOLD, 30));
        lblMedicalRecordManagement.setForeground(SystemColor.controlLtHighlight);
        lblMedicalRecordManagement.setBounds(167, 73, 460, 80);
        add(lblMedicalRecordManagement);

        JButton btnCreateMedicalRecord = new JButton("Add New Medical Record Record");
        btnCreateMedicalRecord.setBounds(274, 198, 228, 46);
        btnCreateMedicalRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the CreateMedicalRecord panel
                cardLayout.show(cardPanel, "CreateMedicalRecord");
            }
        });
        add(btnCreateMedicalRecord);

        JButton btnUpdateMedicalRecord = new JButton("Update Medical Record");
        btnUpdateMedicalRecord.setBounds(274, 256, 228, 46);
        btnUpdateMedicalRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the CreateMedicalRecord panel
                cardLayout.show(cardPanel, "UpdateMedicalRecord");
            }
        });
        add(btnUpdateMedicalRecord);

        JButton btnDeleteMedicalRecord = new JButton("Delete Medical Record");
        btnDeleteMedicalRecord.setBounds(274, 316, 228, 46);
        btnDeleteMedicalRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the CreateMedicalRecord panel
                cardLayout.show(cardPanel, "DeleteMedicalRecord");
            }
        });
        add(btnDeleteMedicalRecord);

        JButton btnManageMedicalRecord = new JButton("View Medical Record");
        btnManageMedicalRecord.setBounds(274, 376, 228, 46);
        btnManageMedicalRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the CreateMedicalRecord panel
                cardLayout.show(cardPanel, "DisplayMedicalRecord");
            }
        });
        add(btnManageMedicalRecord);

        JButton btnBack = new JButton("Back ");
        btnBack.setBounds(637, 500, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "MainMenu");
            }
        });
        add(btnBack);
    }
}
