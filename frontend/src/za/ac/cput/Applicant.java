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

    public Applicant(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblApplicantManagement = new JLabel("Animal Shelter Application");
        lblApplicantManagement.setFont(new Font("Dialog", Font.BOLD, 30));
        lblApplicantManagement.setForeground(SystemColor.controlLtHighlight);
        lblApplicantManagement.setBounds(167, 73, 460, 80);
        add(lblApplicantManagement);

        JButton btnCreateApplicant = new JButton("Add New Applicant Record");
        btnCreateApplicant.setBounds(274, 198, 228, 46);
        btnCreateApplicant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the CreateApplicant panel
            	cardPanel.add(new CreateApplicant(cardLayout,cardPanel), "CreateApplicant");
              
                cardLayout.show(cardPanel, "CreateApplicant");
            }
        });
        add(btnCreateApplicant);

        JButton btnUpdateApplicant = new JButton("Update Applicant Record");
        btnUpdateApplicant.setBounds(274, 256, 228, 46);
        btnUpdateApplicant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the CreateApplicant panel
            	  cardPanel.add(new UpdateApplicant(cardLayout,cardPanel), "UpdateApplicant");

                cardLayout.show(cardPanel, "UpdateApplicant");
            }
        });
        add(btnUpdateApplicant);

        JButton btnDeleteApplicant = new JButton("Delete Applicant Record");
        btnDeleteApplicant.setBounds(274, 316, 228, 46);
        btnDeleteApplicant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the CreateApplicant panel
                cardPanel.add(new DeleteApplicant(cardLayout,cardPanel), "DeleteApplicant");
              
                cardLayout.show(cardPanel, "DeleteApplicant");
            }
        });
        add(btnDeleteApplicant);

        JButton btnManageApplicant = new JButton("View Applicant Record");
        btnManageApplicant.setBounds(274, 376, 228, 46);
        btnManageApplicant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the CreateApplicant panel
            	  cardPanel.add(new DisplayApplicant(cardLayout,cardPanel), "DisplayApplicant");
                cardLayout.show(cardPanel, "DisplayApplicant");
            }
        });
        add(btnManageApplicant);

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
