package za.ac.cput;

import javax.swing.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import za.ac.cput.helper.SessionManager;

public class DeleteEmployee extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtContactNo;
    private JTextField txtEmailAddress;
    private JComboBox<String> cboOptions;
    private String token;

    public DeleteEmployee(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        ImagePanel imagePanel = new ImagePanel("src/za/ac/cput/images/shelter.png");
        imagePanel.setBounds(50, 50, 90, 90);
        add(imagePanel);

        JLabel lblTitle = new JLabel("Delete Employee Record");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(254, 55, 350, 40);
        add(lblTitle);

        String[] options = {"Select Employee"};
        cboOptions = new JComboBox<>(options);
        cboOptions.setBounds(318, 153, 300, 30);
        cboOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) cboOptions.getSelectedItem();
                if (selectedItem != null) {
                    String id = selectedItem.split(" - ")[0];
                    fetchEmployeeDetails(id);
                }
            }
        });
        add(cboOptions);

        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblFirstName.setForeground(SystemColor.controlLtHighlight);
        lblFirstName.setBounds(139, 195, 100, 30);
        add(lblFirstName);

        txtFirstName = new JTextField();
        txtFirstName.setBounds(318, 195, 300, 30);
        txtFirstName.setEditable(false);
        add(txtFirstName);

        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblLastName.setForeground(SystemColor.controlLtHighlight);
        lblLastName.setBounds(139, 237, 100, 30);
        add(lblLastName);

        txtLastName = new JTextField();
        txtLastName.setBounds(318, 237, 300, 30);
        txtLastName.setEditable(false);
        add(txtLastName);

        JLabel lblContactNo = new JLabel("Contact No:");
        lblContactNo.setFont(new Font("Dialog", Font.BOLD, 16));
        lblContactNo.setForeground(SystemColor.controlLtHighlight);
        lblContactNo.setBounds(139, 279, 100, 30);
        add(lblContactNo);

        txtContactNo = new JTextField();
        txtContactNo.setBounds(318, 279, 300, 30);
        txtContactNo.setEditable(false);
        add(txtContactNo);

        JLabel lblEmailAddress = new JLabel("Email Address:");
        lblEmailAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblEmailAddress.setForeground(SystemColor.controlLtHighlight);
        lblEmailAddress.setBounds(139, 321, 150, 30);
        add(lblEmailAddress);

        txtEmailAddress = new JTextField();
        txtEmailAddress.setBounds(318, 321, 300, 30);
        txtEmailAddress.setEditable(false);
        add(txtEmailAddress);

        token = SessionManager.getInstance().getBearerToken();

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Dialog", Font.BOLD, 16));
        btnDelete.setBounds(150, 500, 150, 40);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });
        add(btnDelete);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(472, 500, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Employee");
            }
        });
        add(btnBack);

        JLabel lblEmployeeId = new JLabel("Select Employee:");
        lblEmployeeId.setForeground(SystemColor.controlLtHighlight);
        lblEmployeeId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblEmployeeId.setBounds(139, 152, 150, 30);
        add(lblEmployeeId);

        populateEmployeeIds();
    }

    private void populateEmployeeIds() {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/employee/getall");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token);
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
                cboOptions.removeAllItems(); // Clear previous items
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("id");
                    String firstName = jsonObject.optString("firstName", "N/A");
                    String lastName = jsonObject.optString("lastName", "N/A");
                    cboOptions.addItem(String.format("%d - %s %s", id, firstName, lastName));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch employee IDs.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void fetchEmployeeDetails(String id) {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/employee/read/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token);
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

                txtFirstName.setText(jsonObject.optString("firstName", ""));
                txtLastName.setText(jsonObject.optString("lastName", ""));
                txtContactNo.setText(jsonObject.optString("contactNo", ""));
                txtEmailAddress.setText(jsonObject.optString("emailAddress", ""));

            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch employee details.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }


    private void deleteEmployee() {
        String selectedItem = (String) cboOptions.getSelectedItem();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(null, "Please select a Employee ID.");
            return;
        }

        String id = selectedItem.split(" - ")[0]; // Extract ID from the selected item

        // Create JSON object with only the ID
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id); // Include only the ID

        try {
            URL url = new URL("http://localhost:8080/animalshelter/employee/delete/"+id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (java.io.OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonObject.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(null, "Employee deleted successfully.");
                // Optionally, clear the text fields after deletion
                txtFirstName.setText("");
                txtLastName.setText("");
                txtEmailAddress.setText("");
                txtContactNo.setText("");


            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to delete employee.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}
