package za.ac.cput;

import javax.swing.*;

import org.json.*;
import za.ac.cput.helper.SessionManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

public class CreateVolunteer extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtContactNo;
    private JTextField txtEmailAddress;
    private JTextField txtStreetAddress;
    private JTextField txtAvailability;
    private String token;

    public CreateVolunteer(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblTitle = new JLabel("Create New Volunteer Record");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(248, 82, 350, 40);
        add(lblTitle);

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

        token = SessionManager.getInstance().getBearerToken();

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Dialog", Font.BOLD, 16));
        btnAdd.setBounds(150, 500, 150, 40);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                resetFieldColors();


                boolean valid = true;
                StringBuilder errorMessage = new StringBuilder();

                if (!isValidName(txtFirstName.getText())) {
                    txtFirstName.setBackground(Color.PINK);
                    errorMessage.append("First Name must be alphabets and no longer than 15 characters.\n");
                    valid = false;
                }

                if (!isValidName(txtLastName.getText())) {
                    txtLastName.setBackground(Color.PINK);
                    errorMessage.append("Last Name must be alphabets and no longer than 15 characters.\n");
                    valid = false;
                }

                if (!isValidContactNo(txtContactNo.getText())) {
                    txtContactNo.setBackground(Color.PINK);
                    errorMessage.append("Contact No must be numeric and up to 10 digits.\n");
                    valid = false;
                }

                if (!isValidEmail(txtEmailAddress.getText())) {
                    txtEmailAddress.setBackground(Color.PINK);
                    errorMessage.append("Invalid email address format.\n");
                    valid = false;
                }

                if (txtStreetAddress.getText().length() > 25) {
                    txtStreetAddress.setBackground(Color.PINK);
                    errorMessage.append("Street Address cannot be longer than 25 characters.\n");
                    valid = false;
                }

                if (txtAvailability.getText().length() > 8) {
                    txtAvailability.setBackground(Color.PINK);
                    errorMessage.append("Availability cannot be longer than 8 characters.\n");
                    valid = false;
                }

                if (!valid) {
                    JOptionPane.showMessageDialog(null, errorMessage.toString());
                    return;
                }

                // Proceed with HTTP request if all validations pass
                try {
                    // Create JSON string manually
                    String jsonInputString = String.format(
                            "{\"firstName\":\"%s\",\"lastName\":\"%s\",\"contactNo\":\"%s\",\"emailAddress\":\"%s\",\"streetAddress\":\"%s\",\"availability\":\"%s\"}",
                            txtFirstName.getText(),
                            txtLastName.getText(),
                            txtContactNo.getText(),
                            txtEmailAddress.getText(),
                            txtStreetAddress.getText(),
                            txtAvailability.getText()
                    );


                    URL url = new URL("http://localhost:8080/animalshelter/volunteer/create");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Authorization", "Bearer " + token);
                    connection.setRequestProperty("Content-Type", "application/json; utf-8");
                    connection.setDoOutput(true);


                    try (OutputStream os = connection.getOutputStream()) {
                        byte[] input = jsonInputString.getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }


                    int responseCode = connection.getResponseCode();
                    StringBuilder response = new StringBuilder();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                            String line;
                            while ((line = in.readLine()) != null) {
                                response.append(line);
                            }
                        }

                        JSONObject jsonResponse = new JSONObject(response.toString());
                        String id = jsonResponse.optString("id", "No ID returned");

                        JOptionPane.showMessageDialog(null, "Volunteer created successfully!\nVolunteer ID: " + id);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Unable to create volunteer.");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });
        add(btnAdd);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(472, 500, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Volunteer");
            }
        });
        add(btnBack);
    }

    private void resetFieldColors() {
        txtFirstName.setBackground(Color.WHITE);
        txtLastName.setBackground(Color.WHITE);
        txtContactNo.setBackground(Color.WHITE);
        txtEmailAddress.setBackground(Color.WHITE);
        txtStreetAddress.setBackground(Color.WHITE);
        txtAvailability.setBackground(Color.WHITE);
    }

    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]{1,15}");
    }

    private boolean isValidContactNo(String contactNo) {
        return contactNo.matches("\\d{1,10}");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }
}


