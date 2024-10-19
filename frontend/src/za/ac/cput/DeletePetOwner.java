package za.ac.cput;


import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import za.ac.cput.helper.SessionManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeletePetOwner extends JPanel {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> cboPetOwnerId;
    private JTextField txtName;
    private JTextField txtBreed;
    private JTextField txtAge;
    private JTextField txtGender;
    private JTextField txtSize;
    private JRadioButton rdbtnCat;
    private JRadioButton rdbtnDog;
    private JComboBox<String> cboCat;
    private JComboBox<String> cboDog;
    private String token;

    String petName;
    String petsize;
    String petAge;
    String petGender;
    String petBreed;
    String petCageNo;
    String petFinalId;

    String ownerFirstName;
    String ownerLastName;
    String ownerContactName;
    String ownerEmail;
    String ownerStreet;


    public DeletePetOwner(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        ImagePanel imagePanel = new ImagePanel("src/za/ac/cput/images/shelter.png");
        imagePanel.setBounds(50, 50, 150, 80);
        add(imagePanel);

        JLabel lblTitle = new JLabel("Delete PetOwner Record");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(222, 81, 396, 40);
        add(lblTitle);

        JLabel lblPetOwnerId = new JLabel("Pet Owner ID:");
        lblPetOwnerId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPetOwnerId.setForeground(SystemColor.controlLtHighlight);
        lblPetOwnerId.setBounds(150, 135, 119, 30);
        add(lblPetOwnerId);

        cboPetOwnerId = new JComboBox<>(new String[]{"Select ID"});
        cboPetOwnerId.setBounds(318, 135, 300, 30);
        cboPetOwnerId.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) cboPetOwnerId.getSelectedItem();
                if (selectedItem != null) {
                    String id = selectedItem.split(" - ")[0];
                    fetchPetOwnerById(id);
                }
            }
        });
        add(cboPetOwnerId);

        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblFirstName.setForeground(SystemColor.controlLtHighlight);
        lblFirstName.setBounds(150, 175, 119, 30);
        add(lblFirstName);

        txtName = new JTextField();
        txtName.setBounds(318, 175, 300, 30);
        txtName.setEditable(false);
        add(txtName);

        JLabel lblLastName = new JLabel("Last Name:");
        lblLastName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblLastName.setForeground(SystemColor.controlLtHighlight);
        lblLastName.setBounds(150, 215, 100, 30);
        add(lblLastName);

        txtBreed = new JTextField();
        txtBreed.setBounds(318, 215, 300, 30);
        txtBreed.setEditable(false);
        add(txtBreed);

        JLabel lblContactNo = new JLabel("Contact No:");
        lblContactNo.setFont(new Font("Dialog", Font.BOLD, 16));
        lblContactNo.setForeground(SystemColor.controlLtHighlight);
        lblContactNo.setBounds(150, 255, 119, 30);
        add(lblContactNo);

        txtAge = new JTextField();
        txtAge.setBounds(318, 255, 300, 30);
        txtAge.setEditable(false);
        add(txtAge);

        JLabel lblEmailAddress = new JLabel("Email Address:");
        lblEmailAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblEmailAddress.setForeground(SystemColor.controlLtHighlight);
        lblEmailAddress.setBounds(150, 295, 136, 30);
        add(lblEmailAddress);

        txtGender = new JTextField();
        txtGender.setBounds(318, 295, 300, 30);
        txtGender.setEditable(false);
        add(txtGender);

        JLabel lblStreetAddress = new JLabel("Street Address");
        lblStreetAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblStreetAddress.setForeground(SystemColor.controlLtHighlight);
        lblStreetAddress.setBounds(150, 335, 136, 30);
        add(lblStreetAddress);

        txtSize = new JTextField();
        txtSize.setBounds(318, 335, 300, 30);
        txtSize.setEditable(false);
        add(txtSize);

        rdbtnCat = new JRadioButton("Cat");
        rdbtnCat.setFont(new Font("Dialog", Font.BOLD, 16));
        rdbtnCat.setForeground(SystemColor.controlLtHighlight);
        rdbtnCat.setBackground(new Color(0, 128, 128));
        rdbtnCat.setBounds(150, 375, 100, 30);
        rdbtnCat.setSelected(true); // Default selection
        rdbtnCat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                togglePetSelection(true);
            }
        });
        add(rdbtnCat);

        rdbtnDog = new JRadioButton("Dog");
        rdbtnDog.setFont(new Font("Dialog", Font.BOLD, 16));
        rdbtnDog.setForeground(SystemColor.controlLtHighlight);
        rdbtnDog.setBackground(new Color(0, 128, 128));
        rdbtnDog.setBounds(250, 375, 100, 30);
        rdbtnDog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                togglePetSelection(false);
            }
        });
        add(rdbtnDog);

        ButtonGroup petGroup = new ButtonGroup();
        petGroup.add(rdbtnCat);
        petGroup.add(rdbtnDog);

        cboCat = new JComboBox<>(new String[]{"Select Cat", "Persian", "Siamese", "Maine Coon"});
        cboCat.setBounds(318, 404, 300, 30);
        cboCat.setEnabled(true);
        add(cboCat);


        cboDog = new JComboBox<>(new String[]{"Select Dog", "Beagle", "Bulldog", "Labrador"});
        cboDog.setBounds(318, 446, 300, 30);
        cboDog.setEnabled(false);
        add(cboDog);

        token = SessionManager.getInstance().getBearerToken();


        JButton btnAdd = new JButton("Delete");
        btnAdd.setFont(new Font("Dialog", Font.BOLD, 16));
        btnAdd.setBounds(150, 500, 150, 40);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletePetOwner();
            }
        });
        add(btnAdd);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(472, 500, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "PetOwner");
            }
        });
        add(btnBack);


        fetchPetOwnerData();
        togglePetSelection(true);
    }

    private void togglePetSelection(boolean isCatSelected) {
        cboCat.setEnabled(isCatSelected);
        cboDog.setEnabled(!isCatSelected);
        if (isCatSelected) {
            fetchCatData();
        } else {
            fetchDogData();
        }
    }

    private void fetchPetOwnerData() {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/petOwner/getall");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token );
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
                cboPetOwnerId.removeAllItems();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("id");
                    String firstName = jsonObject.optString("firstName", "N/A");
                    ownerFirstName=jsonObject.optString("firstName", "N/A");
                    txtName.setText(ownerFirstName);
                    String lastName = jsonObject.optString("lastName", "N/A");

                    ownerLastName=jsonObject.optString("lastName", "N/A");
                    txtBreed.setText(lastName);
                    ownerContactName=jsonObject.optString("contactNumber", "N/A");
                    txtAge.setText(ownerContactName);
                    ownerEmail=jsonObject.optString("emailAddress", "N/A");
                    txtGender.setText(ownerEmail);
                    ownerStreet=jsonObject.optString("streetAddress", "N/A");
                    txtSize.setText(ownerStreet);
                    cboPetOwnerId.addItem(String.format("%d - %s %s", id, firstName, lastName));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch petOwner IDs.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void fetchCatData() {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/cat/getall");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token );
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
                cboCat.removeAllItems();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("catId");
                    String name = jsonObject.optString("name", "N/A");
                    petName=jsonObject.optString("name", "N/A");
                    String breed = jsonObject.optString("breed", "N/A");
                    petBreed=jsonObject.optString("breed", "N/A");
                    petCageNo=jsonObject.optString("cageNumber", "N/A");
                    petGender=jsonObject.optString("gender", "N/A");
                    petsize=jsonObject.optString("size", "N/A");
                    petAge=jsonObject.optString("age", "N/A");
                    petFinalId=String.valueOf(jsonObject.opt("catId"));
                    cboCat.addItem(String.format("%d - %s %s", id, name, breed));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch cat IDs.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void fetchPetOwnerById(String ownerid) {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/petOwner/read/" + ownerid);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token );
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
                int id = jsonObject.getInt("id");
                String firstName = jsonObject.optString("firstName", "N/A");
                ownerFirstName=jsonObject.optString("firstName", "N/A");
                txtName.setText(ownerFirstName);
                String lastName = jsonObject.optString("lastName", "N/A");

                ownerLastName=jsonObject.optString("lastName", "N/A");
                txtBreed.setText(lastName);
                ownerContactName=jsonObject.optString("contactNumber", "N/A");
                txtAge.setText(ownerContactName);
                ownerEmail=jsonObject.optString("emailAddress", "N/A");
                txtGender.setText(ownerEmail);
                ownerStreet=jsonObject.optString("streetAddress", "N/A");
                txtSize.setText(ownerStreet);// Convert integer to string

            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch cat details.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }


    private void fetchDogData() {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/dog/getall");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + token );
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
                cboDog.removeAllItems();
                for (int i = 0; i < jsonArray.length(); i++) {



                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("dogId");
                    String name = jsonObject.optString("name", "N/A");
                    petName=jsonObject.optString("name", "N/A");
                    String breed = jsonObject.optString("breed", "N/A");
                    petBreed=jsonObject.optString("breed", "N/A");
                    petCageNo=jsonObject.optString("cageNumber", "N/A");
                    petGender=jsonObject.optString("gender", "N/A");
                    petsize=jsonObject.optString("size", "N/A");
                    petAge=jsonObject.optString("age", "N/A");
                    petFinalId=String.valueOf(jsonObject.opt("dogId"));
                    cboDog.addItem(String.format("%d - %s %s", id, name, breed));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch dog IDs.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void deletePetOwner() {
        String selectedItem = (String) cboPetOwnerId.getSelectedItem();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(null, "Please select a volunteer ID.");
            return;
        }

        String id = selectedItem.split(" - ")[0];


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);

        try {
            URL url = new URL("http://localhost:8080/animalshelter/petOwner/delete/"+id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Authorization", "Bearer " + token );
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (java.io.OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonObject.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                JOptionPane.showMessageDialog(null, "PetOwner Deleted successfully.");

                txtName.setText("");
                txtBreed.setText("");
                txtAge.setText("");
                txtSize.setText("");
                txtGender.setText("");

                fetchPetOwnerData();
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to Delete PetOwner.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }


}
