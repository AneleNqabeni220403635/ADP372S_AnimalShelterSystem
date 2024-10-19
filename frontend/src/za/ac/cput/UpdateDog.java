package za.ac.cput;

import javax.swing.*;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.helper.SessionManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateDog extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtName;
    private JTextField txtBreed;
    private JTextField txtAge;
    private JTextField txtGender;
    private JTextField txtSize;
    private JTextField txtCageNumber;
    private JComboBox<String> cboOptions;
    private String token;

    public UpdateDog(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        ImagePanel imagePanel = new ImagePanel("src/za/ac/cput/images/Dogs.png");
        imagePanel.setBounds(600, 50, 150, 100);
        add(imagePanel);

        JLabel lblTitle = new JLabel("Update Dog Details");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(254, 55, 350, 40);
        add(lblTitle);

        String[] options = {"Select Dog", "Option 1", "Option 2"};
        cboOptions = new JComboBox<>(options);
        cboOptions.setBounds(318, 153, 300, 30);
        cboOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) cboOptions.getSelectedItem();
                if (selectedItem != null) {
                    String id = selectedItem.split(" - ")[0];
                    fetchDogDetails(id);
                }
            }
        });
        add(cboOptions);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblName.setForeground(SystemColor.controlLtHighlight);
        lblName.setBounds(139, 195, 100, 30);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(318, 195, 300, 30);
        add(txtName);

        JLabel lblBreed = new JLabel("Breed:");
        lblBreed.setFont(new Font("Dialog", Font.BOLD, 16));
        lblBreed.setForeground(SystemColor.controlLtHighlight);
        lblBreed.setBounds(139, 237, 100, 30);
        add(lblBreed);

        txtBreed = new JTextField();
        txtBreed.setBounds(318, 237, 300, 30);
        add(txtBreed);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Dialog", Font.BOLD, 16));
        lblAge.setForeground(SystemColor.controlLtHighlight);
        lblAge.setBounds(139, 279, 100, 30);
        add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(318, 279, 300, 30);
        add(txtAge);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setFont(new Font("Dialog", Font.BOLD, 16));
        lblGender.setForeground(SystemColor.controlLtHighlight);
        lblGender.setBounds(139, 321, 100, 30);
        add(lblGender);

        txtGender = new JTextField();
        txtGender.setBounds(318, 321, 300, 30);
        add(txtGender);

        JLabel lblSize = new JLabel("Size:");
        lblSize.setFont(new Font("Dialog", Font.BOLD, 16));
        lblSize.setForeground(SystemColor.controlLtHighlight);
        lblSize.setBounds(139, 361, 100, 30);
        add(lblSize);

        txtSize = new JTextField();
        txtSize.setBounds(318, 363, 300, 30);
        add(txtSize);

        JLabel lblCageNumber = new JLabel("Cage Number:");
        lblCageNumber.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCageNumber.setForeground(SystemColor.controlLtHighlight);
        lblCageNumber.setBounds(139, 403, 173, 30);
        add(lblCageNumber);

        txtCageNumber = new JTextField();
        txtCageNumber.setBounds(318, 405, 300, 30);
        add(txtCageNumber);

        token = SessionManager.getInstance().getBearerToken();

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Dialog", Font.BOLD, 16));
        btnUpdate.setBounds(150, 500, 150, 40);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateDog();
                cardLayout.show(cardPanel, "Dog");
            }
        });
        add(btnUpdate);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(472, 500, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Dog");
            }
        });
        add(btnBack);

        JLabel lblDogId = new JLabel("Select Dog:");
        lblDogId.setForeground(SystemColor.controlLtHighlight);
        lblDogId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblDogId.setBounds(139, 152, 100, 30);
        add(lblDogId);

        populateDogIds();
    }

    private void populateDogIds() {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/dog/getall");
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
                cboOptions.removeAllItems();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("dogId");
                    String name = jsonObject.optString("name", "N/A");
                    String breed = jsonObject.optString("breed", "N/A");
                    cboOptions.addItem(String.format("%d - %s %s", id, name, breed));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch Dog IDs.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void fetchDogDetails(String id) {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/dog/read/" + id);
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

                txtName.setText(jsonObject.optString("name", ""));
                txtSize.setText(jsonObject.optString("size", ""));
                txtAge.setText(String.valueOf(jsonObject.optInt("age", 0)));
                txtGender.setText(jsonObject.optString("gender", ""));
                txtBreed.setText(jsonObject.optString("breed", ""));
                txtCageNumber.setText(String.valueOf(jsonObject.optInt("cageNumber", 0)));

            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch Dog details.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void updateDog() {
        String selectedItem = (String) cboOptions.getSelectedItem();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(null, "Please select a Dog ID.");
            return;
        }

        String id = selectedItem.split(" - ")[0];

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("DogId", Integer.parseInt(id));
        jsonObject.put("name", txtName.getText());
        jsonObject.put("size", txtSize.getText());
        jsonObject.put("age", Integer.parseInt(txtAge.getText()));
        jsonObject.put("gender", txtGender.getText());
        jsonObject.put("breed", txtBreed.getText());
        jsonObject.put("cageNumber", Integer.parseInt(txtCageNumber.getText()));

        try {
            URL url = new URL("http://localhost:8080/animalshelter/dog/update");
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
                JOptionPane.showMessageDialog(null, "Dog updated successfully.");

            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to update Dog.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}
