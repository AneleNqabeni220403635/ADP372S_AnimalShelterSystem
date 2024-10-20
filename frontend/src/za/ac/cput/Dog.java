package za.ac.cput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dog extends JPanel {

    private static final long serialVersionUID = 1L;

    public Dog(CardLayout cardLayout, JPanel cardPanel,MainMenu mainMenu) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        ImagePanel imagePanel = new ImagePanel("src/za/ac/cput/images/Edited puppies2.png");
        imagePanel.setBounds(600, 50, 150, 100);
        add(imagePanel);

        JLabel lblDogManagement = new JLabel("Animal Shelter Application");
        lblDogManagement.setFont(new Font("Dialog", Font.BOLD, 30));
        lblDogManagement.setForeground(SystemColor.controlLtHighlight);
        lblDogManagement.setBounds(167, 73, 460, 80);
        add(lblDogManagement);

        JButton btnCreateDog = new JButton("Add A Dog");
        btnCreateDog.setBounds(274, 198, 228, 46);
        btnCreateDog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(new CreateDog(cardLayout,cardPanel), "CreateDog");
                cardLayout.show(cardPanel, "CreateDog");
            }
        });
        add(btnCreateDog);

        JButton btnUpdateDog = new JButton("Update Dog Details");
        btnUpdateDog.setBounds(274, 256, 228, 46);
        btnUpdateDog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(new UpdateDog(cardLayout,cardPanel), "UpdateDog");

                cardLayout.show(cardPanel, "UpdateDog");
            }
        });
        add(btnUpdateDog);

        JButton btnDeleteDog = new JButton("Delete Dog Details");
        btnDeleteDog.setBounds(274, 316, 228, 46);
        btnDeleteDog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(new DeleteDog(cardLayout,cardPanel), "DeleteDog");

                cardLayout.show(cardPanel, "DeleteDog");
            }
        });
        add(btnDeleteDog);

        JButton btnManageDog = new JButton("View Dog/(s)");
        btnManageDog.setBounds(274, 376, 228, 46);
        btnManageDog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(new DisplayDog(cardLayout,cardPanel,mainMenu), "DisplayDog");
                cardLayout.show(cardPanel, "DisplayDog");
            }
        });
        add(btnManageDog);

        ImagePanel imagePanel1 = new ImagePanel("src/za/ac/cput/images/shelter.png");
        imagePanel1.setBounds(60, mainMenu.getHeight() - 170, 120, 90);
        add(imagePanel1);

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
