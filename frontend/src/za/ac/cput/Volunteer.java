package za.ac.cput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Volunteer extends JPanel {

    private static final long serialVersionUID = 1L;

    public Volunteer(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        ImagePanel imagePanel = new ImagePanel("src/za/ac/cput/images/shelter.png");
        imagePanel.setBounds(50, 50, 100, 100);
        add(imagePanel);

        JLabel lblVolunteerManagement = new JLabel("Animal Shelter Application");
        lblVolunteerManagement.setFont(new Font("Dialog", Font.BOLD, 30));
        lblVolunteerManagement.setForeground(SystemColor.controlLtHighlight);
        lblVolunteerManagement.setBounds(167, 73, 460, 80);
        add(lblVolunteerManagement);

        JButton btnCreateVolunteer = new JButton("Add New Volunteer");
        btnCreateVolunteer.setBounds(274, 198, 228, 46);
        btnCreateVolunteer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(cardPanel, "CreateVolunteer");
            }
        });
        add(btnCreateVolunteer);

        JButton btnUpdateVolunteer = new JButton("Update Volunteer Details");
        btnUpdateVolunteer.setBounds(274, 256, 228, 46);
        btnUpdateVolunteer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardLayout.show(cardPanel, "UpdateVolunteer");
            }
        });
        add(btnUpdateVolunteer);

        JButton btnDeleteVolunteer = new JButton("Delete Volunteer Details");
        btnDeleteVolunteer.setBounds(274, 316, 228, 46);
        btnDeleteVolunteer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the CreateVolunteer panel
                cardLayout.show(cardPanel, "DeleteVolunteer");
            }
        });
        add(btnDeleteVolunteer);

        JButton btnManageVolunteer = new JButton("View Volunteer");
        btnManageVolunteer.setBounds(274, 376, 228, 46);
        btnManageVolunteer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the CreateVolunteer panel
                cardLayout.show(cardPanel, "DisplayVolunteer");
            }
        });
        add(btnManageVolunteer);

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


