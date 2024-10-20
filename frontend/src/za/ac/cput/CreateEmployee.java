package za.ac.cput;

import javax.swing.*;
import org.json.JSONObject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import za.ac.cput.helper.SessionManager;

public class CreateEmployee extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtContactNo;
    private JTextField txtEmailAddress;
    private String token;
    JTextField txtNewUsername;
    JPasswordField txtPassword;
    JComboBox<String> cmbRole;

    public CreateEmployee (CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        ImagePanel imagePanel = new ImagePanel("src/za/ac/cput/images/profile.png");
        imagePanel.setBounds(50, 50, 90, 90);
        add(imagePanel);

        JLabel lblTitle = new JLabel("Create New Employee Record:", JLabel.CENTER);
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(240, 81, 350, 40);
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

        // Username
        var lblNewUsername = new JLabel("Username:");
        lblNewUsername.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewUsername.setForeground(SystemColor.controlLtHighlight);
        lblNewUsername.setBounds(150, 320, 135, 30);
        add(lblNewUsername);

        txtNewUsername = new JTextField();
        txtNewUsername.setBounds(318, 320, 300, 30);
        add(txtNewUsername);

        // Password Label
        var lblNewPassword = new JLabel("Password:");
        lblNewPassword.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewPassword.setForeground(SystemColor.controlLtHighlight);
        lblNewPassword.setBounds(150, 360, 135, 30);
        add(lblNewPassword);

        // Password
        txtPassword = new JPasswordField();
        txtPassword.setBounds(318, 360, 300, 30);
        txtPassword.setEchoChar('*');
        add(txtPassword);

        // Role
        var lblRole = new JLabel("Select Role:");
        lblRole.setFont(new Font("Dialog", Font.BOLD, 16));
        lblRole.setForeground(SystemColor.controlLtHighlight);
        lblRole.setBounds(150, 400, 135, 30);
        add(lblRole);

        cmbRole = new JComboBox<>(new String[]{"ADMIN", "USER"});
        cmbRole.setBounds(318, 400, 300, 30);
        add(cmbRole);

        token = SessionManager.getInstance().getBearerToken();

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Dialog", Font.BOLD, 16));
        btnAdd.setBounds(152, 440, 150, 40);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    String jsonInputString = String.format(
                            "{\"firstName\":\"%s\",\"lastName\":\"%s\",\"contactNo\":\"%s\",\"emailAddress\":\"%s\",\"username\":\"%s\",\"password\":\"%s\",\"role\":\"%s\"}",
                            txtFirstName.getText(),
                            txtLastName.getText(),
                            txtContactNo.getText(),
                            txtEmailAddress.getText(),
                            txtNewUsername.getText(),
                            new String(txtPassword.getPassword()),
                            cmbRole.getSelectedItem()
                    );


                    URL url = new URL("http://localhost:8080/animalshelter/employee/create"); // Update to correct endpoint
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

                        // Parse JSON response to extract employeeId
                        JSONObject jsonResponse = new JSONObject(response.toString());
                        String employeeId = jsonResponse.optString("id", "No ID returned");

                        JOptionPane.showMessageDialog(null, "Employee was created successfully!\nEmployee ID: " + employeeId);
                        cardLayout.show(cardPanel, "Employee");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Unable to create Employee/s.");
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
        btnBack.setBounds(468, 440, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Employee");
            }
        });
        add(btnBack);
    }
}
