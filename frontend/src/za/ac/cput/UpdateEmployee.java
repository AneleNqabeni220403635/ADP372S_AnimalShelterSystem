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

public class UpdateEmployee extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtContactNo;
    private JTextField txtEmailAddress;
    private JComboBox<String> cboOptions;
    private String token;
    private String role;
    private JPasswordField txtPassword;

    public UpdateEmployee(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblTitle = new JLabel("Update Employee Record");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(254, 55, 350, 40);
        add(lblTitle);

        String[] options = {"Select Employee"}; // Initialize with placeholder
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
        add(txtFirstName);

        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblLastName.setForeground(SystemColor.controlLtHighlight);
        lblLastName.setBounds(139, 237, 100, 30);
        add(lblLastName);

        txtLastName = new JTextField();
        txtLastName.setBounds(318, 237, 300, 30);
        add(txtLastName);

        JLabel lblContactNo = new JLabel("Contact No:");
        lblContactNo.setFont(new Font("Dialog", Font.BOLD, 16));
        lblContactNo.setForeground(SystemColor.controlLtHighlight);
        lblContactNo.setBounds(139, 279, 100, 30);
        add(lblContactNo);

        txtContactNo = new JTextField();
        txtContactNo.setBounds(318, 279, 300, 30);
        add(txtContactNo);

        JLabel lblEmailAddress = new JLabel("Email Address:");
        lblEmailAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblEmailAddress.setForeground(SystemColor.controlLtHighlight);
        lblEmailAddress.setBounds(139, 321, 150, 30);
        add(lblEmailAddress);

        txtEmailAddress = new JTextField();
        txtEmailAddress.setBounds(318, 321, 300, 30);
        add(txtEmailAddress);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPassword.setForeground(SystemColor.controlLtHighlight);
        lblPassword.setBounds(139, 363, 150, 30);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setEchoChar('*');
        txtPassword.setFont(new Font("Dialog", Font.BOLD, 16));
        txtPassword.setBounds(318, 363, 300, 30);
        add(txtPassword);

        token = SessionManager.getInstance().getBearerToken();
        getActiveEmployeeRole();

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Dialog", Font.BOLD, 16));
        btnUpdate.setBounds(150, 500, 150, 40);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateEmployee();
            }
        });
        add(btnUpdate);

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
            toggleFieldsEditable(false);

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

                toggleFieldsEditable(role.equals("ADMIN") || jsonObject.optString("username", "").equals(SessionManager.getInstance().getUsername()));
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch employee details.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void updateEmployee() {
        String selectedItem = (String) cboOptions.getSelectedItem();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(null, "Please select an employee ID.");
            return;
        }

        String id = selectedItem.split(" - ")[0]; // Extract ID from the selected item

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", Integer.parseInt(id));
        jsonObject.put("firstName", txtFirstName.getText());
        jsonObject.put("lastName", txtLastName.getText());
        jsonObject.put("contactNo", txtContactNo.getText());
        jsonObject.put("emailAddress", txtEmailAddress.getText());

        String p = new String(txtPassword.getPassword());
        if (!p.isEmpty()){
            jsonObject.put("password", p);
        }

        try {
            URL url = new URL("http://localhost:8080/animalshelter/employee/update");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (java.io.OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonObject.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(null, "Employee updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to update employee.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void getActiveEmployeeRole() {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/employee/findByUsername/" + SessionManager.getInstance().getUsername());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
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

                this.role = jsonObject.optString("role", "");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch role. (" + responseCode + ")");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void toggleFieldsEditable(boolean toggle)
    {
        txtFirstName.setEnabled(toggle);
        txtLastName.setEnabled(toggle);
        txtEmailAddress.setEnabled(toggle);
        txtContactNo.setEnabled(toggle);
        txtPassword.setEnabled(toggle);
    }
}
