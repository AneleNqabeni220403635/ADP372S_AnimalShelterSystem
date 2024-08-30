package za.ac.cput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lblStatus;

    public LoginScreen() {
        setTitle("Animal Shelter Login");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Set background color or image
        getContentPane().setBackground(new Color(0, 153, 153)); // Teal color

        // Create a logo label (Optional: Add an image icon here)
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
        getContentPane().add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setBackground(new Color(255, 102, 0)); // Orange color for the button
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
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        // Check hardcoded credentials
        if (username.equals("admin") && password.equals("admin")) {
            // If login is successful, open the main menu
            JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            openMainMenu();
        } else {
            lblStatus.setText("Invalid username or password");
        }
    }

    private void openMainMenu() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setVisible(true);
        dispose(); // Close the login screen
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
