package za.ac.cput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sale extends JPanel {

    private static final long serialVersionUID = 1L;

    public Sale(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        ImagePanel imagePanel = new ImagePanel("src/za/ac/cput/images/shelter.png");
        imagePanel.setBounds(50, 50, 100, 80);
        add(imagePanel);

        JLabel lblSaleManagement = new JLabel("Animal Shelter Application");
        lblSaleManagement.setFont(new Font("Dialog", Font.BOLD, 30));
        lblSaleManagement.setForeground(SystemColor.controlLtHighlight);
        lblSaleManagement.setBounds(167, 73, 460, 80);
        add(lblSaleManagement);

        JButton btnCreateSale = new JButton("Add Sale");
        btnCreateSale.setBounds(274, 198, 228, 46);
        btnCreateSale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                cardPanel.add(new CreateSale(cardLayout,cardPanel), "CreateSale");

                cardLayout.show(cardPanel, "CreateSale");
            }
        });
        add(btnCreateSale);

        JButton btnUpdateSale = new JButton("View Owner Details");
        btnUpdateSale.setBounds(274, 256, 228, 46);
        btnUpdateSale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardPanel.add(new DisplayOwnerRecord(cardLayout,cardPanel), "DisplayOwnerRecord");
                cardLayout.show(cardPanel, "DisplayOwnerRecord");
            }
        });
        add(btnUpdateSale);

        JButton btnManageSale = new JButton("View Sale Details");
        btnManageSale.setBounds(274, 316, 228, 46);
        btnManageSale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Switch to the CreateSale panel
                cardPanel.add(new DisplaySale(cardLayout,cardPanel), "DisplaySale");
                cardLayout.show(cardPanel, "DisplaySale");
            }
        });
        add(btnManageSale);

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
