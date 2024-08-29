package za.ac.cput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Profile extends JPanel {

    private static final long serialVersionUID = 1L;

    public Profile(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblProfileManagement = new JLabel("Animal Shelter Application");
        lblProfileManagement.setFont(new Font("Dialog", Font.BOLD, 30));
        lblProfileManagement.setForeground(SystemColor.controlLtHighlight);
        lblProfileManagement.setBounds(167, 73, 460, 80);
        add(lblProfileManagement);

        JButton btnCreateProfile = new JButton("Add New Profile Record");
        btnCreateProfile.setBounds(274, 198, 228, 46);
        add(btnCreateProfile);

        JButton btnUpdateProfile = new JButton("Update Profile Record");
        btnUpdateProfile.setBounds(274, 256, 228, 46);
        add(btnUpdateProfile);

        JButton btnDeleteProfile = new JButton("Delete Profile Record");
        btnDeleteProfile.setBounds(274, 316, 228, 46);
        add(btnDeleteProfile);

        JButton btnManageProfile = new JButton("View Profile Record");
        btnManageProfile.setBounds(274, 376, 228, 46);
        add(btnManageProfile);

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
