package za.ac.cput;

import za.ac.cput.helper.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeleteSale extends JPanel {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> cboSaleId;
    private JComboBox<String> cboEmployee;
    private JComboBox<String> cboPetOwner;
    private JComboBox<String> cboCat;
    private JComboBox<String> cboDog;
    private JRadioButton rdbtnCat;
    private JRadioButton rdbtnDog;
    private JLabel lblSaleDate;
    private JTextField txtPrice;
    private ButtonGroup petTypeGroup;
    private String token;

    public DeleteSale(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblTitle = new JLabel("Delete Sales Entry");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(209, 50, 427, 40);
        add(lblTitle);

        JLabel lblSaleId = new JLabel("Sale ID:");
        lblSaleId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblSaleId.setForeground(SystemColor.controlLtHighlight);
        lblSaleId.setBounds(150, 110, 135, 30);
        add(lblSaleId);

        String[] saleIds = {"Sale 1", "Sale 2", "Sale 3"}; // Example data
        cboSaleId = new JComboBox<>(saleIds);
        cboSaleId.setBounds(318, 110, 300, 30);
        add(cboSaleId);

        JLabel lblEmployee = new JLabel("Employee:");
        lblEmployee.setFont(new Font("Dialog", Font.BOLD, 16));
        lblEmployee.setForeground(SystemColor.controlLtHighlight);
        lblEmployee.setBounds(150, 150, 135, 30);
        add(lblEmployee);

        String[] employees = {"Employee 1", "Employee 2", "Employee 3"};
        cboEmployee = new JComboBox<>(employees);
        cboEmployee.setBounds(318, 150, 300, 30);
        add(cboEmployee);

        JLabel lblPetOwner = new JLabel("Pet Owner:");
        lblPetOwner.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPetOwner.setForeground(SystemColor.controlLtHighlight);
        lblPetOwner.setBounds(150, 190, 135, 30);
        add(lblPetOwner);

        String[] petOwners = {"Owner 1", "Owner 2", "Owner 3"};
        cboPetOwner = new JComboBox<>(petOwners);
        cboPetOwner.setBounds(318, 190, 300, 30);
        add(cboPetOwner);

        rdbtnCat = new JRadioButton("Cat");
        rdbtnCat.setForeground(SystemColor.controlLtHighlight);
        rdbtnCat.setBackground(new Color(0, 128, 128));
        rdbtnCat.setBounds(150, 230, 100, 30);
        rdbtnCat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleDropdowns(true);
            }
        });
        add(rdbtnCat);

        rdbtnDog = new JRadioButton("Dog");
        rdbtnDog.setForeground(SystemColor.controlLtHighlight);
        rdbtnDog.setBackground(new Color(0, 128, 128));
        rdbtnDog.setBounds(250, 230, 100, 30);
        rdbtnDog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleDropdowns(false);
            }
        });
        add(rdbtnDog);

        petTypeGroup = new ButtonGroup();
        petTypeGroup.add(rdbtnCat);
        petTypeGroup.add(rdbtnDog);

        JLabel lblCat = new JLabel("Select Cat:");
        lblCat.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCat.setForeground(SystemColor.controlLtHighlight);
        lblCat.setBounds(150, 270, 100, 30);
        add(lblCat);

        String[] cats = {"Tom", "Whiskers", "Fluffy"}; // Example data
        cboCat = new JComboBox<>(cats);
        cboCat.setBounds(318, 270, 300, 30);
        cboCat.setEnabled(false); // Initially disabled
        add(cboCat);

        JLabel lblDog = new JLabel("Select Dog:");
        lblDog.setFont(new Font("Dialog", Font.BOLD, 16));
        lblDog.setForeground(SystemColor.controlLtHighlight);
        lblDog.setBounds(150, 310, 135, 30);
        add(lblDog);

        String[] dogs = {"Rex", "Buddy", "Max"};
        cboDog = new JComboBox<>(dogs);
        cboDog.setBounds(318, 310, 300, 30);
        cboDog.setEnabled(false); // Initially disabled
        add(cboDog);

        JLabel lblSaleDate = new JLabel("Sale Date:");
        lblSaleDate.setFont(new Font("Dialog", Font.BOLD, 16));
        lblSaleDate.setForeground(SystemColor.controlLtHighlight);
        lblSaleDate.setBounds(150, 350, 150, 30);
        add(lblSaleDate);

        lblSaleDate = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        lblSaleDate.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblSaleDate.setForeground(SystemColor.controlLtHighlight);
        lblSaleDate.setBounds(318, 350, 300, 30);
        add(lblSaleDate);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPrice.setForeground(SystemColor.controlLtHighlight);
        lblPrice.setBounds(150, 390, 100, 30);
        add(lblPrice);

        txtPrice = new JTextField();
        txtPrice.setBounds(318, 390, 300, 30);
        add(txtPrice);
        token = SessionManager.getInstance().getBearerToken();

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Dialog", Font.BOLD, 16));
        btnDelete.setBounds(150, 460, 150, 40);
        add(btnDelete);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(468, 460, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Sale");
            }
        });
        add(btnBack);

        rdbtnCat.setSelected(true);
        toggleDropdowns(true);
    }

    private void toggleDropdowns(boolean showCat) {
        cboCat.setEnabled(showCat);
        cboDog.setEnabled(!showCat);
    }
}
