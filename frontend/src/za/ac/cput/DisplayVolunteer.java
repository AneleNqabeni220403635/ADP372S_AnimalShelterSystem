package za.ac.cput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class DisplayVolunteer extends JPanel {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> cmbVolunteerId;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtContactNo;
    private JTextField txtEmailAddress;
    private JTextField txtStreetAddress;
    private JTextField txtAvailability;
    private JTable tblAllVolunteers;
    private JScrollPane scrollPane;

    public DisplayVolunteer(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblTitle = new JLabel("Display Volunteer Record");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(239, 53, 350, 40);
        add(lblTitle);

        JRadioButton rbtnSingleRecord = new JRadioButton("Single Record");
        rbtnSingleRecord.setFont(new Font("Dialog", Font.BOLD, 16));
        rbtnSingleRecord.setForeground(SystemColor.controlLtHighlight);
        rbtnSingleRecord.setBackground(new Color(0, 128, 128));
        rbtnSingleRecord.setBounds(150, 115, 150, 30);
        rbtnSingleRecord.setSelected(true);
        add(rbtnSingleRecord);

        JRadioButton rbtnAllRecords = new JRadioButton("All Records");
        rbtnAllRecords.setFont(new Font("Dialog", Font.BOLD, 16));
        rbtnAllRecords.setForeground(SystemColor.controlLtHighlight);
        rbtnAllRecords.setBackground(new Color(0, 128, 128));
        rbtnAllRecords.setBounds(318, 115, 150, 30);
        add(rbtnAllRecords);

        ButtonGroup group = new ButtonGroup();
        group.add(rbtnSingleRecord);
        group.add(rbtnAllRecords);

        JLabel lblVolunteerId = new JLabel("Select Volunteer ID:");
        lblVolunteerId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblVolunteerId.setForeground(SystemColor.controlLtHighlight);
        lblVolunteerId.setBounds(150, 155, 150, 30);
        add(lblVolunteerId);

        cmbVolunteerId = new JComboBox<>();
        cmbVolunteerId.setBounds(318, 155, 300, 30);
        cmbVolunteerId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rbtnSingleRecord.isSelected()) {
                    String selectedItem = (String) cmbVolunteerId.getSelectedItem();
                    if (selectedItem != null) {
                        String id = selectedItem.split(" - ")[0];
                        fetchVolunteerDetails(id);
                    }
                }
            }
        });
        add(cmbVolunteerId);

        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblFirstName.setForeground(SystemColor.controlLtHighlight);
        lblFirstName.setBounds(150, 195, 100, 30);
        add(lblFirstName);

        txtFirstName = new JTextField();
        txtFirstName.setBounds(318, 195, 300, 30);
        txtFirstName.setEditable(false);
        add(txtFirstName);

        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblLastName.setForeground(SystemColor.controlLtHighlight);
        lblLastName.setBounds(150, 235, 100, 30);
        add(lblLastName);

        txtLastName = new JTextField();
        txtLastName.setBounds(318, 235, 300, 30);
        txtLastName.setEditable(false);
        add(txtLastName);

        JLabel lblContactNo = new JLabel("Contact No:");
        lblContactNo.setFont(new Font("Dialog", Font.BOLD, 16));
        lblContactNo.setForeground(SystemColor.controlLtHighlight);
        lblContactNo.setBounds(150, 275, 100, 30);
        add(lblContactNo);

        txtContactNo = new JTextField();
        txtContactNo.setBounds(318, 275, 300, 30);
        txtContactNo.setEditable(false);
        add(txtContactNo);

        JLabel lblEmailAddress = new JLabel("Email Address:");
        lblEmailAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblEmailAddress.setForeground(SystemColor.controlLtHighlight);
        lblEmailAddress.setBounds(150, 315, 130, 30);
        add(lblEmailAddress);

        txtEmailAddress = new JTextField();
        txtEmailAddress.setBounds(318, 315, 300, 30);
        txtEmailAddress.setEditable(false);
        add(txtEmailAddress);

        JLabel lblStreetAddress = new JLabel("Street Address:");
        lblStreetAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblStreetAddress.setForeground(SystemColor.controlLtHighlight);
        lblStreetAddress.setBounds(150, 355, 130, 30);
        add(lblStreetAddress);

        txtStreetAddress = new JTextField();
        txtStreetAddress.setBounds(318, 355, 300, 30);
        txtStreetAddress.setEditable(false);
        add(txtStreetAddress);

        JLabel lblAvailability = new JLabel("Availability:");
        lblAvailability.setFont(new Font("Dialog", Font.BOLD, 16));
        lblAvailability.setForeground(SystemColor.controlLtHighlight);
        lblAvailability.setBounds(150, 395, 130, 30);
        add(lblAvailability);

        txtAvailability = new JTextField();
        txtAvailability.setBounds(318, 395, 300, 30);
        txtAvailability.setEditable(false);
        add(txtAvailability);

        JButton btnDisplay = new JButton("Display");
        btnDisplay.setFont(new Font("Dialog", Font.BOLD, 16));
        btnDisplay.setBounds(150, 450, 150, 40);
        btnDisplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (rbtnSingleRecord.isSelected()) {
                    DisplayVolunteer();
                } else {
                    fetchAllVolunteers();
                }
            }
        });
        add(btnDisplay);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(472, 450, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Volunteer");
            }
        });
        add(btnBack);

        tblAllVolunteers = new JTable();
//        scrollPane = new JScrollPane(tblAllVolunteers);
////        lblVolunteerId.setBounds(150, 155, 150, 30);
//        scrollPane.setBounds(150, 150, 600, 200);
//        scrollPane.setVisible(false);


        populateVolunteerIds();


        rbtnSingleRecord.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtFirstName.setVisible(true);
                txtLastName.setVisible(true);
                txtContactNo.setVisible(true);
                txtEmailAddress.setVisible(true);
                txtStreetAddress.setVisible(true);
                txtAvailability.setVisible(true);
                cmbVolunteerId.setVisible(true);
                scrollPane.setVisible(false);
            }
        });

        rbtnAllRecords.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtFirstName.setVisible(false);
                txtLastName.setVisible(false);
                txtContactNo.setVisible(false);
                txtEmailAddress.setVisible(false);
                txtStreetAddress.setVisible(false);
                txtAvailability.setVisible(false);
                cmbVolunteerId.setVisible(false);
                scrollPane.setVisible(true);
            }
        });
    }

    private void populateVolunteerIds() {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/volunteer/getall");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                }

                JSONArray jsonArray = new JSONArray(response.toString());
                cmbVolunteerId.removeAllItems();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("id");
                    String firstName = jsonObject.getString("firstName");
                    String lastName = jsonObject.getString("lastName");
                    cmbVolunteerId.addItem(String.format("%d - %s %s", id, firstName, lastName));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch volunteer IDs.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void fetchVolunteerDetails(String id) {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/volunteer/read/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                }

                JSONObject jsonObject = new JSONObject(response.toString());
                txtFirstName.setText(jsonObject.getString("firstName"));
                txtLastName.setText(jsonObject.getString("lastName"));
                txtContactNo.setText(jsonObject.getString("contactNo"));
                txtEmailAddress.setText(jsonObject.getString("emailAddress"));
                txtStreetAddress.setText(jsonObject.getString("streetAddress"));
                txtAvailability.setText(jsonObject.getString("availability"));
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch volunteer details.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void fetchAllVolunteers() {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/volunteer/getall");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }
                }

                JSONArray jsonArray = new JSONArray(response.toString());
                String[] columnNames = {"ID", "First Name", "Last Name", "Contact No", "Email Address", "Street Address", "Availability"};
                Object[][] data = new Object[jsonArray.length()][columnNames.length];

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    data[i][0] = jsonObject.getInt("id");
                    data[i][1] = jsonObject.getString("firstName");
                    data[i][2] = jsonObject.getString("lastName");
                    data[i][3] = jsonObject.getString("contactNo");
                    data[i][4] = jsonObject.getString("emailAddress");
                    data[i][5] = jsonObject.getString("streetAddress");
                    data[i][6] = jsonObject.getString("availability");
                }

                tblAllVolunteers.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch all volunteers.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void DisplayVolunteer() {
        String selectedItem = (String) cmbVolunteerId.getSelectedItem();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(null, "Please select a volunteer ID.");
            return;
        }

        String id = selectedItem.split(" - ")[0];
        try {
            URL url = new URL("http://localhost:8080/animalshelter/volunteer/read/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(null, "Volunteer Displayed successfully.");
                txtFirstName.setText("");
                txtLastName.setText("");
                txtContactNo.setText("");
                txtEmailAddress.setText("");
                txtStreetAddress.setText("");
                txtAvailability.setText("");
                populateVolunteerIds();
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to Display volunteer.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}
