package za.ac.cput;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PetOwner extends JPanel {

    private static final long serialVersionUID = 1L;

    public PetOwner(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        ImagePanel imagePanel = new ImagePanel("src/za/ac/cput/images/shelter.png");
        imagePanel.setBounds(10, 50, 120, 90);
        add(imagePanel);

        JLabel lblPetOwnerManagement = new JLabel("Animal Shelter Application");
        lblPetOwnerManagement.setFont(new Font("Dialog", Font.BOLD, 30));
        lblPetOwnerManagement.setForeground(SystemColor.controlLtHighlight);
        lblPetOwnerManagement.setBounds(167, 73, 460, 80);
        add(lblPetOwnerManagement);

        JButton btnCreatePetOwner = new JButton("Add New PetOwner");
        btnCreatePetOwner.setBounds(274, 198, 228, 46);
        btnCreatePetOwner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardPanel.add(new CreatePetOwner(cardLayout,cardPanel), "CreatePetOwner");

                cardLayout.show(cardPanel, "CreatePetOwner");
            }
        });
        add(btnCreatePetOwner);

        JButton btnUpdatePetOwner = new JButton("Update PetOwner ");
        btnUpdatePetOwner.setBounds(274, 256, 228, 46);
        btnUpdatePetOwner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardPanel.add(new UpdatePetOwner(cardLayout,cardPanel), "UpdatePetOwner");

                cardLayout.show(cardPanel, "UpdatePetOwner");
            }
        });
        add(btnUpdatePetOwner);

        JButton btnDeletePetOwner = new JButton("Delete PetOwner");
        btnDeletePetOwner.setBounds(274, 316, 228, 46);
        btnDeletePetOwner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardPanel.add(new DeletePetOwner(cardLayout,cardPanel), "DeletePetOwner");

                cardLayout.show(cardPanel, "DeletePetOwner");
            }
        });
        add(btnDeletePetOwner);

        JButton btnManagePetOwner = new JButton("View PetOwner");
        btnManagePetOwner.setBounds(274, 376, 228, 46);
        btnManagePetOwner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardPanel.add(new DisplayPetOwner(cardLayout,cardPanel), "DisplayPetOwner");
                cardLayout.show(cardPanel, "DisplayPetOwner");
            }
        });
        add(btnManagePetOwner);

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

