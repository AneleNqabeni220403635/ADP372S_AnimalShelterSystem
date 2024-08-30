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

public class CreateDog extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtName;
    private JTextField txtBreed;
    private JTextField txtAge;
    private JTextField txtGender;
    private JTextField txtSize;
    private JTextField txtCageNumber;

    public CreateDog(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblTitle = new JLabel("Create New Dog Record");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(305, 86, 350, 40);
        add(lblTitle);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblName.setForeground(SystemColor.controlLtHighlight);
        lblName.setBounds(150, 155, 100, 30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(318, 155, 300, 30);
        add(txtName);

        JLabel lblBreed = new JLabel("Breed:");
        lblBreed.setFont(new Font("Dialog", Font.BOLD, 16));
        lblBreed.setForeground(SystemColor.controlLtHighlight);
        lblBreed.setBounds(150, 195, 100, 30);
        add(lblBreed);

        txtBreed = new JTextField();
        txtBreed.setBounds(318, 195, 300, 30);
        add(txtBreed);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Dialog", Font.BOLD, 16));
        lblAge.setForeground(SystemColor.controlLtHighlight);
        lblAge.setBounds(150, 235, 100, 30);
        add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(318, 237, 300, 30);
        add(txtAge);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setFont(new Font("Dialog", Font.BOLD, 16));
        lblGender.setForeground(SystemColor.controlLtHighlight);
        lblGender.setBounds(150, 279, 100, 30);
        add(lblGender);

        txtGender = new JTextField();
        txtGender.setBounds(318, 279, 300, 30);
        add(txtGender);

        JLabel lblSize = new JLabel("Size:");
        lblSize.setFont(new Font("Dialog", Font.BOLD, 16));
        lblSize.setForeground(SystemColor.controlLtHighlight);
        lblSize.setBounds(150, 321, 100, 30);
        add(lblSize);

        txtSize = new JTextField();
        txtSize.setBounds(318, 321, 300, 30);
        add(txtSize);

        JLabel lblCageNumber = new JLabel("Cage Number:");
        lblCageNumber.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCageNumber.setForeground(SystemColor.controlLtHighlight);
        lblCageNumber.setBounds(150, 361, 173, 30);
        add(lblCageNumber);

        txtCageNumber = new JTextField();
        txtCageNumber.setBounds(318, 363, 300, 30);
        add(txtCageNumber);

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Dialog", Font.BOLD, 16));
        btnAdd.setBounds(150, 500, 150, 40);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Proceed with HTTP request if all validations pass
                try {
                    // Create JSON string manually
                    String jsonInputString = String.format(
                        "{\"name\":\"%s\",\"size\":\"%s\",\"age\":\"%s\",\"gender\":\"%s\",\"breed\":\"%s\",\"cageNumber\":\"%s\"}",
                        txtName.getText(),
                        txtSize.getText(),
                        txtAge.getText(),
                        txtGender.getText(),
                        txtBreed.getText(),
                        txtCageNumber.getText()
                    );

                    // Set up HTTP connection to send data to backend
                    URL url = new URL("http://localhost:8080/animalshelter/dog/create"); // Replace with your actual endpoint
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json; utf-8");
                    connection.setDoOutput(true);

                    // Send JSON input string to the backend
                    try (OutputStream os = connection.getOutputStream()) {
                        byte[] input = jsonInputString.getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }

                    // Check for successful response
                    int responseCode = connection.getResponseCode();
                    StringBuilder response = new StringBuilder();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                            String line;
                            while ((line = in.readLine()) != null) {
                                response.append(line);
                            }
                        }

                        // Parse JSON response to extract ID
                        JSONObject jsonResponse = new JSONObject(response.toString());
                        String id = jsonResponse.optString("dogId", "No ID returned");

                        JOptionPane.showMessageDialog(null, "Dog created successfully!\nDog ID: " + id);
                        cardLayout.show(cardPanel, "Dog");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Unable to create Dog.");
                        cardLayout.show(cardPanel, "Dog");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                    cardLayout.show(cardPanel, "Dog");
                }
            }
        });
        add(btnAdd);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(472, 500, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Dog"); // Change "Dog" to the actual name of the panel you want to go back to
            }
        });
        add(btnBack);
    }
}
