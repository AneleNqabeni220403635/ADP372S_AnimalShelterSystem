package za.ac.cput.UserManagement;

import org.json.JSONObject;
import za.ac.cput.helper.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserManagementUI  extends JPanel {

    JTextField txtNewUsername;
    JPasswordField txtPassword;
    JComboBox<String> cmbRole;

    private String token;
    public UserManagementUI(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));
        token = SessionManager.getInstance().getBearerToken();

        // Title
        var lblTitle = new JLabel("User Management", JLabel.CENTER);
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(209, 80, 427, 40);
        add(lblTitle);

        // Username
        var lblNewUsername = new JLabel("Username:");
        lblNewUsername.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewUsername.setForeground(SystemColor.controlLtHighlight);
        lblNewUsername.setBounds(150, 150, 135, 30);
        add(lblNewUsername);

        txtNewUsername = new JTextField();
        txtNewUsername.setBounds(250, 150, 200, 30);
        add(txtNewUsername);

        // Password Label
        var lblNewPassword = new JLabel("Password:");
        lblNewPassword.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewPassword.setForeground(SystemColor.controlLtHighlight);
        lblNewPassword.setBounds(150, 200, 135, 30);
        add(lblNewPassword);

        // Password
        txtPassword = new JPasswordField();
        txtPassword.setBounds(250, 200, 200, 30);
        txtPassword.setEchoChar('*');
        add(txtPassword);

        // Role
        var lblRole = new JLabel("Select Role:");
        lblRole.setFont(new Font("Dialog", Font.BOLD, 16));
        lblRole.setForeground(SystemColor.controlLtHighlight);
        lblRole.setBounds(150, 250, 135, 30);
        add(lblRole);

        cmbRole = new JComboBox<>(new String[]{"ADMIN", "USER"});
        cmbRole.setBounds(250, 250, 200, 30);
        add(cmbRole);

        // Register User
        var btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Dialog", Font.BOLD, 16));
        btnRegister.setBounds(150, 300, 150, 40);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser(txtNewUsername.getText(),  new String(txtPassword.getPassword()), (String)cmbRole.getSelectedItem());
            }
        });
        add(btnRegister);

        var separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setForeground(Color.lightGray);
        add(separator);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                separator.setBounds(0, 350, getWidth(), 1);
            }
        });
    }

    private void registerUser(String username, String password, String role) {
        String json = String.format("{\"username\":\"%s\",\"password\":\"%s\",\"role\":\"%s\"}",
                username,
                password,
                role
        );

        try {
            URL url = new URL("http://localhost:8080/animalshelter/usercredential/register");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
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
                JOptionPane.showMessageDialog(null, "User registered successfully!");
                cmbRole.setSelectedIndex(0);
                txtNewUsername.setText("");
                txtPassword.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Error: Register user. (" + responseCode +")");
            }
        }
        catch (Exception err) {
            err.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error when registering user: " + err.getMessage());
        }
    }
}
