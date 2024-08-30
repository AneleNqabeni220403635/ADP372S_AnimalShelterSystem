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

public class UpdateVolunteer extends JPanel {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> cmbVolunteerId;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtContactNo;
    private JTextField txtEmailAddress;
    private JTextField txtStreetAddress;
    private JTextField txtAvailability;

    public UpdateVolunteer(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblTitle = new JLabel("Update Volunteer Record");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(239, 53, 350, 40);
        add(lblTitle);

        JLabel lblVolunteerId = new JLabel("Select Volunteer ID:");
        lblVolunteerId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblVolunteerId.setForeground(SystemColor.controlLtHighlight);
        lblVolunteerId.setBounds(150, 115, 150, 30);
        add(lblVolunteerId);

        // ComboBox to select Volunteer ID
        cmbVolunteerId = new JComboBox<>();
        cmbVolunteerId.setBounds(318, 115, 300, 30);
        cmbVolunteerId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) cmbVolunteerId.getSelectedItem();
                if (selectedItem != null) {
                    String id = selectedItem.split(" - ")[0]; // Extract ID from the selected item
                    fetchVolunteerDetails(id);
                }
            }
        });
        add(cmbVolunteerId);

        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblFirstName.setForeground(SystemColor.controlLtHighlight);
        lblFirstName.setBounds(150, 155, 100, 30);
        add(lblFirstName);

        txtFirstName = new JTextField();
        txtFirstName.setBounds(318, 155, 300, 30);
        add(txtFirstName);

        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblLastName.setForeground(SystemColor.controlLtHighlight);
        lblLastName.setBounds(150, 195, 100, 30);
        add(lblLastName);

        txtLastName = new JTextField();
        txtLastName.setBounds(318, 195, 300, 30);
        add(txtLastName);

        JLabel lblContactNo = new JLabel("Contact No:");
        lblContactNo.setFont(new Font("Dialog", Font.BOLD, 16));
        lblContactNo.setForeground(SystemColor.controlLtHighlight);
        lblContactNo.setBounds(150, 235, 100, 30);
        add(lblContactNo);

        txtContactNo = new JTextField();
        txtContactNo.setBounds(318, 237, 300, 30);
        add(txtContactNo);

        JLabel lblEmailAddress = new JLabel("Email Address:");
        lblEmailAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblEmailAddress.setForeground(SystemColor.controlLtHighlight);
        lblEmailAddress.setBounds(150, 279, 130, 30);
        add(lblEmailAddress);

        txtEmailAddress = new JTextField();
        txtEmailAddress.setBounds(318, 279, 300, 30);
        add(txtEmailAddress);

        JLabel lblStreetAddress = new JLabel("Street Address:");
        lblStreetAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblStreetAddress.setForeground(SystemColor.controlLtHighlight);
        lblStreetAddress.setBounds(150, 321, 130, 30);
        add(lblStreetAddress);

        txtStreetAddress = new JTextField();
        txtStreetAddress.setBounds(318, 321, 300, 30);
        add(txtStreetAddress);

        JLabel lblAvailability = new JLabel("Availability:");
        lblAvailability.setFont(new Font("Dialog", Font.BOLD, 16));
        lblAvailability.setForeground(SystemColor.controlLtHighlight);
        lblAvailability.setBounds(150, 361, 130, 30);
        add(lblAvailability);

        txtAvailability = new JTextField();
        txtAvailability.setBounds(318, 363, 300, 30);
        add(txtAvailability);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Dialog", Font.BOLD, 16));
        btnUpdate.setBounds(150, 500, 150, 40);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateVolunteer();
            }
        });
        add(btnUpdate);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(472, 500, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Volunteer");
            }
        });
        add(btnBack);

        // Populate ComboBox with volunteer IDs
        populateVolunteerIds();
    }

    private void populateVolunteerIds() {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/volunteer/getall"); // Endpoint to get volunteer IDs
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

                // Assuming the response is a JSON array of volunteer objects
                JSONArray jsonArray = new JSONArray(response.toString());
                cmbVolunteerId.removeAllItems(); // Clear previous items
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("id"); // Get ID as an integer
                    String firstName = jsonObject.getString("firstName");
                    String lastName = jsonObject.getString("lastName");
                    // Format: "ID - FirstName LastName"
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

    private void updateVolunteer() {
        String selectedItem = (String) cmbVolunteerId.getSelectedItem();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(null, "Please select a volunteer ID.");
            return;
        }
        
        String id = selectedItem.split(" - ")[0]; // Extract ID from the selected item

        // Create JSON object from form data
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id); // Include the ID
        jsonObject.put("firstName", txtFirstName.getText());
        jsonObject.put("lastName", txtLastName.getText());
        jsonObject.put("contactNo", txtContactNo.getText());
        jsonObject.put("emailAddress", txtEmailAddress.getText());
        jsonObject.put("streetAddress", txtStreetAddress.getText());
        jsonObject.put("availability", txtAvailability.getText());

        try {
            URL url = new URL("http://localhost:8080/animalshelter/volunteer/update");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (java.io.OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonObject.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(null, "Volunteer updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to update volunteer.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }


}
