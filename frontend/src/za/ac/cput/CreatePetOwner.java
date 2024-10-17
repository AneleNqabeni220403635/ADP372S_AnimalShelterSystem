package za.ac.cput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;
import za.ac.cput.helper.SessionManager;

public class CreatePetOwner extends JPanel{

    private static final long serialVersionUID = 1L;
    private String token;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtContactNo;
    private JTextField txtEmailAddress;
    private JTextField txtStreetAddress;
    private JTextField txtCurrentDate;
    String petName;
    String petsize;
    String petAge;
    String petGender;
    String petBreed;
    String petCageNo;
    String petFinalId;

    public CreatePetOwner(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblTitle = new JLabel("Create New PetOwner Record");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(222, 81, 396, 40);
        add(lblTitle);

        JLabel lblFirstName = new JLabel("First Name:");
        lblFirstName.setFont(new Font("Dialog", Font.BOLD, 16));
        lblFirstName.setForeground(SystemColor.controlLtHighlight);
        lblFirstName.setBounds(150, 155, 119, 30);
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
        lblContactNo.setBounds(150, 235, 119, 30);
        add(lblContactNo);

        txtContactNo = new JTextField();
        txtContactNo.setBounds(318, 237, 300, 30);
        add(txtContactNo);

        JLabel lblEmailAddress = new JLabel("Email Address:");
        lblEmailAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblEmailAddress.setForeground(SystemColor.controlLtHighlight);
        lblEmailAddress.setBounds(150, 279, 136, 30);
        add(lblEmailAddress);

        txtEmailAddress = new JTextField();
        txtEmailAddress.setBounds(318, 279, 300, 30);
        add(txtEmailAddress);

        JLabel lblStreetAddress = new JLabel("Street Address:");
        lblStreetAddress.setFont(new Font("Dialog", Font.BOLD, 16));
        lblStreetAddress.setForeground(SystemColor.controlLtHighlight);
        lblStreetAddress.setBounds(150, 321, 150, 30);
        add(lblStreetAddress);

        txtStreetAddress = new JTextField();
        txtStreetAddress.setBounds(318, 321, 300, 30);
        add(txtStreetAddress);

        JLabel lblCurrentDate = new JLabel("Current Date:");
        lblCurrentDate.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCurrentDate.setForeground(SystemColor.controlLtHighlight);
        lblCurrentDate.setBounds(150, 363, 150, 30);
        add(lblCurrentDate);

        txtCurrentDate = new JTextField();
        txtCurrentDate.setBounds(318, 363, 300, 30);
        txtCurrentDate.setText(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        txtCurrentDate.setEditable(false);
        add(txtCurrentDate);

        token = SessionManager.getInstance().getBearerToken();

        ButtonGroup petGroup = new ButtonGroup();

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Dialog", Font.BOLD, 16));
        btnAdd.setBounds(162, 432, 150, 40);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    String firstName = txtFirstName.getText();
                    String lastName = txtLastName.getText();
                    String contactNo = txtContactNo.getText();
                    String emailAddress = txtEmailAddress.getText();
                    String streetAddress = txtStreetAddress.getText();

                    String selectedPetId = "";

                    String petId = selectedPetId.split(" - ")[0];  // Extract just the ID


                    String petOwnerJson = String.format(
                            "{\"firstName\":\"%s\",\"lastName\":\"%s\",\"contactNo\":\"%s\",\"emailAddress\":\"%s\",\"streetAddress\":\"%s\"}",
                            firstName, lastName, contactNo, emailAddress, streetAddress
                    );

                    String responseJson = sendRequest1("http://localhost:8080/animalshelter/petOwner/create", petOwnerJson);
                    if (responseJson != null) {

                        JSONObject responseObject = new JSONObject(responseJson);
                        String createdPetId = String.valueOf(responseObject.optInt("id"));
                        System.out.println(createdPetId);
                        String petOwnerFirstName=responseObject.optString("firstName");
                        System.out.println(petOwnerFirstName);
                        String petOwnerLasttName=responseObject.optString("lastName");
                        String petOwnerContactNo=responseObject.optString("contactNo");
                        String petOwnerEmailAddress=responseObject.optString("emailAddress");
                        String petOwnerStreetAddress=responseObject.optString("streetAddress");


                        JOptionPane.showMessageDialog(null, "Created Pet Owner record.");


                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to create Pet Owner record.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred while creating the Pet Owner record.");
                }
            }
        });
        add(btnAdd);

        JButton btnCancel = new JButton("Back");
        btnCancel.setFont(new Font("Dialog", Font.BOLD, 16));
        btnCancel.setBounds(464, 432, 150, 40);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Cat");
            }
        });
        add(btnCancel);

        togglePetSelection(true);
    }

    private void togglePetSelection(boolean isCat) {
        if (isCat) {
            fetchCatData();
        } else {
            fetchDogData();
        }
    }

    private void fetchCatData() {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/cat/getall");
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

                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch cat IDs.");
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

                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch dog IDs.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }


    private String sendRequest(String url, OwnerRecordClass ownerRecord) throws Exception {
        URL url1 = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + token );
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        JSONObject jsonObject = new JSONObject();
        JSONObject petOwnerJson = new JSONObject();
        petOwnerJson.put("id", ownerRecord.getPetOwner().getId());
        petOwnerJson.put("firstName", ownerRecord.getPetOwner().getFirstName());
        petOwnerJson.put("lastName", ownerRecord.getPetOwner().getLastName());
        petOwnerJson.put("contactNo", ownerRecord.getPetOwner().getContactNo());
        petOwnerJson.put("emailAddress", ownerRecord.getPetOwner().getEmailAddress());
        petOwnerJson.put("streetAddress", ownerRecord.getPetOwner().getStreetAddress());

        JSONObject catJson = null;
        if (ownerRecord.getCat() != null) {
            catJson = new JSONObject();
            catJson.put("catId", ownerRecord.getCat().getId());
            catJson.put("name", ownerRecord.getCat().getName());
            catJson.put("breed", ownerRecord.getCat().getBreed());
            catJson.put("cageNumber", ownerRecord.getCat().getCageNumber());
            catJson.put("gender", ownerRecord.getCat().getGender());
            catJson.put("size", ownerRecord.getCat().getSize());
            catJson.put("age", ownerRecord.getCat().getAge());
        }

        JSONObject dogJson = null;
        if (ownerRecord.getDog() != null) {
            dogJson = new JSONObject();
            dogJson.put("dogId", ownerRecord.getDog().getId());
            dogJson.put("name", ownerRecord.getDog().getName());
            dogJson.put("breed", ownerRecord.getDog().getBreed());
            dogJson.put("cageNumber", ownerRecord.getDog().getCageNumber());
            dogJson.put("gender", ownerRecord.getDog().getGender());
            dogJson.put("size", ownerRecord.getDog().getSize());
            dogJson.put("age", ownerRecord.getDog().getAge());
        }

        jsonObject.put("petOwner", petOwnerJson);
        jsonObject.put("cat", catJson != null ? catJson : JSONObject.NULL);
        jsonObject.put("dog", dogJson != null ? dogJson : JSONObject.NULL);
        jsonObject.put("takenDate", ownerRecord.getTakenDate());
        jsonObject.put("returnDate", ownerRecord.getReturnDate());

        String jsonString = jsonObject.toString();

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }

    private String sendRequest1(String urlString, String petOwnerJson) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(petOwnerJson == null ? "GET" : "POST");
        conn.setRequestProperty("Authorization", "Bearer " + token );
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        if (petOwnerJson != null) {
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = petOwnerJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }
}