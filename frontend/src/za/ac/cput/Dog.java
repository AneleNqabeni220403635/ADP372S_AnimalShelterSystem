package za.ac.cput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dog extends JPanel {

    private static final long serialVersionUID = 1L;

    public Dog(CardLayout cardLayout, JPanel cardPanel, MainMenu mainMenu) {

        setLayout(new GridBagLayout());
        setBackground(new Color(0, 128, 128));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        ImagePanel imagePanel1 = new ImagePanel("src/za/ac/cput/images/shelter.png");
        imagePanel1.setPreferredSize(new Dimension(100, 100));

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(20, 20, 20, 0);
        add(imagePanel1, gbc);

        JLabel lblDogManagement = new JLabel("Animal Shelter Application");
        lblDogManagement.setFont(new Font("Dialog", Font.BOLD, 30));
        lblDogManagement.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(lblDogManagement, gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBackground(new Color(0, 128, 128));

        JButton btnAddDog = new JButton("Add A Dog");
        JButton btnUpdateDog = new JButton("Update Dog Details");
        JButton btnDeleteDog = new JButton("Delete Dog Details");
        JButton btnViewDogs = new JButton("View Dog(s)");

        buttonPanel.add(btnAddDog);
        buttonPanel.add(btnUpdateDog);
        buttonPanel.add(btnDeleteDog);
        buttonPanel.add(btnViewDogs);

        gbc.gridx = 1;
        gbc.gridy = 1;


        gbc.gridwidth = 1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gbc);

        JButton btnSignOff = new JButton("Back");
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 20, 20);
        add(btnSignOff, gbc);

        cardLayout.show(cardPanel, "CreateDog");

        btnAddDog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    cardPanel.add(new CreateDog(cardLayout,cardPanel), "CreateDog");
                cardLayout.show(cardPanel, "CreateDog");
            }
        });

        btnUpdateDog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navigate to Update Dog Screen
                cardPanel.add(new UpdateDog(cardLayout,cardPanel), "UpdateDog");
                cardLayout.show(cardPanel, "UpdateDog");
            }
        });

        btnDeleteDog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navigate to Delete Dog Screen
                cardPanel.add(new DeleteDog(cardLayout,cardPanel), "DeleteDog");
                cardLayout.show(cardPanel, "DeleteDog");
            }
        });

        btnViewDogs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navigate to View Dogs Screen
                cardPanel.add(new DisplayDog(cardLayout,cardPanel,mainMenu), "DisplayDog");
                cardLayout.show(cardPanel, "DisplayDog");
            }
        });

        btnSignOff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Logic for signing off
                cardLayout.show(cardPanel, "MainMenu");
            }
        });
    }
}
