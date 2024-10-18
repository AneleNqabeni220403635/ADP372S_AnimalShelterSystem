package za.ac.cput;

import org.json.JSONObject;
import za.ac.cput.helper.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class LoginScreen extends JFrame {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lblStatus;

    public LoginScreen() {
        setTitle("Animal Shelter Login");
        setSize(450, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        ImagePanel imagePanel = new ImagePanel();
        imagePanel.setBounds(0, 0, 45, 25);  // Adjust size as needed
        getContentPane().add(imagePanel);

        getContentPane().setBackground(new Color(0, 153, 153));

        JLabel lblLogo = new JLabel("Animal Shelter");
        lblLogo.setFont(new Font("Arial", Font.BOLD, 24));
        lblLogo.setForeground(Color.WHITE);
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBounds(50, 20, 350, 40);
        getContentPane().add(lblLogo);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Arial", Font.BOLD, 16));
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setBounds(50, 80, 100, 25);
        getContentPane().add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsername.setBounds(150, 80, 220, 25);
        getContentPane().add(txtUsername);
        txtUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Arial", Font.BOLD, 16));
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setBounds(50, 130, 100, 25);
        getContentPane().add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setBounds(150, 130, 220, 25);
        txtPassword.addActionListener(e -> performLogin());
        getContentPane().add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(255, 102, 0));
        btnLogin.setBounds(150, 180, 100, 35);
        getContentPane().add(btnLogin);

        lblStatus = new JLabel("");
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 14));
        lblStatus.setForeground(Color.RED);
        lblStatus.setBounds(50, 230, 350, 25);
        getContentPane().add(lblStatus);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        try {
            String username = txtUsername.getText();
            String password = new String(txtPassword.getPassword());

            URL url = new URL("http://localhost:8080/animalshelter/usercredential/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            JSONObject loginDetails = new JSONObject();
            loginDetails.put("username", username);
            loginDetails.put("password", password);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = loginDetails.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {

                String token = getResponseContent(connection);
                SessionManager.getInstance().setBearerToken(token);

                openMainMenu();
            } else {
                System.out.println("Response Code: " + responseCode);
                JOptionPane.showMessageDialog(null, "Invalid username or password");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String getResponseContent(HttpURLConnection connection) throws Exception {
        try (java.io.BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }

    private void openMainMenu() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginScreen frame = new LoginScreen();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
