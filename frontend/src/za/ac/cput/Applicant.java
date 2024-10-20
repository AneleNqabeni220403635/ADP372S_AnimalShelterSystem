package za.ac.cput;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class Applicant extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int widthBtn = 228;
    private static final int heightBtn = 46;

    public Applicant(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblApplicantManagement = new JLabel("Application for Adoption", JLabel.CENTER);
        lblApplicantManagement.setFont(new Font("Dialog", Font.BOLD, 30));
        lblApplicantManagement.setForeground(SystemColor.controlLtHighlight);
        lblApplicantManagement.setBounds(167, 73, 460, 80);
        add(lblApplicantManagement);

        JButton btnCreateApplicant = new JButton("Capture Applicant");
        btnCreateApplicant.setBounds(274, 198, widthBtn, heightBtn);
        btnCreateApplicant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
                cardLayout.show(cardPanel, "CreateApplicant");
            }
        });
        add(btnCreateApplicant);

        JButton btnUpdateApplicant = new JButton("Update Applicant");
        btnUpdateApplicant.setBounds(274, 256, widthBtn, heightBtn);
        btnUpdateApplicant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "UpdateApplicant");
            }
        });
        add(btnUpdateApplicant);

        JButton btnDeleteApplicant = new JButton("Remove Applicant");
        btnDeleteApplicant.setBounds(274, 316, widthBtn, heightBtn);
        btnDeleteApplicant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
                cardLayout.show(cardPanel, "DeleteApplicant");
            }
        });
        add(btnDeleteApplicant);

        JButton btnManageApplicant = new JButton("View Applicant");
        btnManageApplicant.setBounds(274, 376, widthBtn, heightBtn);
        btnManageApplicant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "DisplayApplicant");
            }
        });
        add(btnManageApplicant);

        JButton btnBack = new JButton("Back ");
        btnBack.setBounds(637, 500, 150, 40);
        btnBack.addActionListener(e -> {
            cardLayout.show(cardPanel, "MainMenu");
            cardPanel.repaint();
        });
        add(btnBack);


        ImagePanel imgPanel = new ImagePanel();
        imgPanel.setBounds(40, 40, 120, 90);
        add(imgPanel);
    }
}
