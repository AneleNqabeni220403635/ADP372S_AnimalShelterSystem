package za.ac.cput;

import org.json.*;
import za.ac.cput.helper.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateApplicant extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtStatus;
    private JComboBox<String> cboPetOwner;
    private JComboBox<String> cboCat;
    private JComboBox<String> cboDog;
    private JRadioButton rdbtnCat;
    private JRadioButton rdbtnDog;
    private JLabel lblApplicationDate;
    private ButtonGroup petTypeGroup;
    private String token;

    String petName;
    String petsize;
    String petAge;
    String petGender;
    String petBreed;
    String petCageNo;
    String petFinalId;

    String ownerId;
    String ownerFirstName;
    String ownerLastName;
    String ownerContactName;
    String ownerEmail;
    String ownerStreet;
    String date;


    public CreateApplicant(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblTitle = new JLabel("Capture Applicant", JLabel.CENTER);
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(209, 84, 427, 40);
        add(lblTitle);

        JLabel lblPetOwner = new JLabel("Pet Owner:");
        lblPetOwner.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPetOwner.setForeground(SystemColor.controlLtHighlight);
        lblPetOwner.setBounds(150, 155, 135, 30);
        add(lblPetOwner);

        String[] petOwners = {};
        cboPetOwner = new JComboBox<>(petOwners);
        cboPetOwner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) cboPetOwner.getSelectedItem();
                if (selectedItem != null) {
                    String id = selectedItem.split(" - ")[0];
                    fetchPetOwnerById(id);
                }
            }

        });
        cboPetOwner.setBounds(318, 155, 300, 30);
        add(cboPetOwner);

        rdbtnCat = new JRadioButton("Cat");
        rdbtnCat.setForeground(SystemColor.controlLtHighlight);
        rdbtnCat.setBackground(new Color(0, 128, 128));
        rdbtnCat.setBounds(150, 195, 100, 30);
        rdbtnCat.setSelected(true);
        rdbtnCat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleDropdowns(true);
            }
        });
        add(rdbtnCat);

        rdbtnDog = new JRadioButton("Dog");
        rdbtnDog.setForeground(SystemColor.controlLtHighlight);
        rdbtnDog.setBackground(new Color(0, 128, 128));
        rdbtnDog.setBounds(250, 195, 100, 30);
        rdbtnDog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleDropdowns(false);
            }
        });
        add(rdbtnDog);

        petTypeGroup = new ButtonGroup();
        petTypeGroup.add(rdbtnCat);
        petTypeGroup.add(rdbtnDog);

        JLabel lblCat = new JLabel("Select Cat:");
        lblCat.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCat.setForeground(SystemColor.controlLtHighlight);
        lblCat.setBounds(150, 235, 100, 30);
        add(lblCat);

        String[] cats = {};
        cboCat = new JComboBox<>(cats);
        cboCat.setBounds(318, 235, 300, 30);
        cboCat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) cboCat.getSelectedItem();
                if (selectedItem != null) {
                    String id = selectedItem.split(" - ")[0];
                    fetchCatDetails(id);
                }
            }

        });
        cboCat.setEnabled(false);
        add(cboCat);

        JLabel lblDog = new JLabel("Select Dog:");
        lblDog.setFont(new Font("Dialog", Font.BOLD, 16));
        lblDog.setForeground(SystemColor.controlLtHighlight);
        lblDog.setBounds(150, 275, 135, 30);
        add(lblDog);

        String[] dogs = {};
        cboDog = new JComboBox<>(dogs);
        cboDog.setBounds(318, 275, 300, 30);
        cboDog.setBounds(318, 275, 300, 30);
        cboDog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) cboDog.getSelectedItem();
                if (selectedItem != null) {
                    String id = selectedItem.split(" - ")[0];
                    fetchDogDetails(id);
                }
            }

        });
        cboDog.setEnabled(false);
        add(cboDog);

        JLabel lblApplicationDate = new JLabel("Application Date:");
        lblApplicationDate.setFont(new Font("Dialog", Font.BOLD, 16));
        lblApplicationDate.setForeground(SystemColor.controlLtHighlight);
        lblApplicationDate.setBounds(150, 315, 150, 30);
        add(lblApplicationDate);

        this.lblApplicationDate = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        this.lblApplicationDate.setFont(new Font("Dialog", Font.PLAIN, 16));
        this.lblApplicationDate.setForeground(SystemColor.controlLtHighlight);
        this.lblApplicationDate.setBounds(318, 315, 300, 30);
        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        add(this.lblApplicationDate);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setFont(new Font("Dialog", Font.BOLD, 16));
        lblStatus.setForeground(SystemColor.controlLtHighlight);
        lblStatus.setBounds(150, 355, 100, 30);
        add(lblStatus);

        txtStatus = new JTextField();
        txtStatus.setBounds(318, 355, 300, 30);
        txtStatus.setText("Pending");
        txtStatus.setEditable(false);
        add(txtStatus);

        token = SessionManager.getInstance().getBearerToken();

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Dialog", Font.BOLD, 16));
        btnAdd.setBounds(150, 442, 150, 40);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedPetType = rdbtnCat.isSelected() ? "cat" : "dog";
                    JSONObject petJson = new JSONObject();
                    if (selectedPetType.equals("cat")) {
                        CatClass cat = new CatClass(petFinalId, petName, petBreed, petCageNo, petGender, petsize, petAge);

                        if (cat != null) {
                            petJson.put("catId", cat.getId());
                            petJson.put("name", cat.getName());
                            petJson.put("breed", cat.getBreed());
                            petJson.put("cageNumber", cat.getCageNumber());
                            petJson.put("gender", cat.getGender());
                            petJson.put("size", cat.getSize());
                            petJson.put("age", cat.getAge());
                        }

                        URL url = new URL("http://localhost:8080/animalshelter/applicant/readCatId/" + cat.getId());
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setRequestProperty("Authorization", "Bearer " + token);
                        connection.setRequestProperty("Content-Type", "application/json; utf-8");
                        connection.setDoOutput(true);

                        String jsonString = petJson.toString();

                        try (OutputStream os = connection.getOutputStream()) {
                            byte[] input = jsonString.getBytes("utf-8");
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

                            String jsonResponse = response.toString();
                            if (!jsonResponse.isEmpty()) {
                                JSONObject jsonObj = new JSONObject(jsonResponse);
                                String status = jsonObj.optString("status");

                                if (!(status == null) || !status.isEmpty()) {
                                    String petOwnerId = jsonObj.optJSONObject("petOwner").optString("id", "No ID returned");
                                    String petFirstName = jsonObj.optJSONObject("petOwner").optString("firstName", "");
                                    String petLastName = jsonObj.optJSONObject("petOwner").optString("lastName", "");
                                    JOptionPane.showMessageDialog(null, "Applicant for this cat already exists for Pet Owner with Id: " + petOwnerId + " - " + petFirstName + " " + petLastName);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error: Failed to check if application exists for this cat.");
                                }
                            } else {
                                PetOwnerClass pet = new PetOwnerClass(ownerId, ownerFirstName, ownerLastName, ownerContactName, ownerEmail, ownerStreet);

                                ApplicantClass or = new ApplicantClass(pet, date, null, cat, "pending");

                                String url1 = "http://localhost:8080/animalshelter/applicant/create";
                                sendRequest(url1, or);
                                JOptionPane.showMessageDialog(null, "Applicant created successfully!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Unable to create applicant. Response Code: " + responseCode);
                        }
                    } else {
                        DogClass dog = new DogClass(petFinalId, petName, petBreed, petCageNo, petGender, petsize, petAge);

                        if (dog != null) {
                            petJson.put("dogId", dog.getId());
                            petJson.put("name", dog.getName());
                            petJson.put("breed", dog.getBreed());
                            petJson.put("cageNumber", dog.getCageNumber());
                            petJson.put("gender", dog.getGender());
                            petJson.put("size", dog.getSize());
                            petJson.put("age", dog.getAge());
                        }

                        URL url = new URL("http://localhost:8080/animalshelter/applicant/readDogId/" + dog.getId());
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setRequestProperty("Authorization", "Bearer " + token);
                        connection.setRequestProperty("Content-Type", "application/json; utf-8");
                        connection.setDoOutput(true);

                        String jsonString = petJson.toString();

                        try (OutputStream os = connection.getOutputStream()) {
                            byte[] input = jsonString.getBytes("utf-8");
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

                            String jsonResponse = response.toString();
                            if (!jsonResponse.isEmpty()) {
                                JSONObject jsonObj = new JSONObject(jsonResponse);
                                String status = jsonObj.optString("status");

                                if (!(status == null) || !status.isEmpty()) {
                                    String petOwnerId = jsonObj.optJSONObject("petOwner").optString("id", "No ID returned");
                                    String petFirstName = jsonObj.optJSONObject("petOwner").optString("firstName", "");
                                    String petLastName = jsonObj.optJSONObject("petOwner").optString("lastName", "");
                                    JOptionPane.showMessageDialog(null, "Applicant for this dog already exists for Pet Owner with Id: " + petOwnerId +" - " + petFirstName + " " + petLastName);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error: Failed to check if application exists for this cat.");
                                }
                            } else {
                                PetOwnerClass petOwner = new PetOwnerClass(ownerId, ownerFirstName, ownerLastName, ownerContactName, ownerEmail, ownerStreet);
                                ApplicantClass applicant = new ApplicantClass(petOwner, date, dog, null, "pending");

                                String url1 = "http://localhost:8080/animalshelter/applicant/create";
                                sendRequest(url1, applicant);
                                JOptionPane.showMessageDialog(null, "Applicant created successfully!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Unable to create applicant. Response Code: " + responseCode);
                        }
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
        btnBack.setBounds(468, 442, 150, 40);
        btnBack.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Applicant");
            }
        });
        add(btnBack);

        ImagePanel imgPanel = new ImagePanel();
        imgPanel.setBounds(40, 40, 120, 90);
        add(imgPanel);

        fetchPetOwnerData();
        togglePetSelection(true);
    }

    private void toggleDropdowns(boolean showCat) {
        cboCat.setEnabled(showCat);
        cboDog.setEnabled(!showCat);
        if (showCat) {
            fetchCatData();
        } else {
            fetchDogData();
        }
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

            URL url = new URL("http://localhost:8080/animalshelter/petOwner/getall"); // Endpoint to get cat IDs
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
                cboPetOwner.removeAllItems(); // Clear previous items
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("id");

                    String firstName = jsonObject.optString("firstName", "N/A");
                    ownerFirstName = jsonObject.optString("firstName", "N/A");
                    String lastName = jsonObject.optString("lastName", "N/A");
                    ownerContactName = jsonObject.optString("contactNumber", "N/A");
                    ownerEmail = jsonObject.optString("emailAddress", "N/A");
                    ownerStreet = jsonObject.optString("streetAddress", "N/A");
                    cboPetOwner.addItem(String.format("%d - %s %s", id, firstName, lastName));
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
                cboCat.removeAllItems(); // Clear previous items
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("catId");
                    String name = jsonObject.optString("name", "N/A");
                    String breed = jsonObject.optString("breed", "N/A");
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
                int id = jsonObject.getInt("id");
                ownerId = ownerid;
                ownerFirstName = jsonObject.optString("firstName", "N/A");
                ownerLastName = jsonObject.optString("lastName", "N/A");
                ownerContactName = jsonObject.optString("contactNumber", "N/A");
                ownerEmail = jsonObject.optString("emailAddress", "N/A");
                ownerStreet = jsonObject.optString("streetAddress", "N/A");

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
                cboDog.removeAllItems(); // Clear previous items
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("dogId");
                    String name = jsonObject.optString("name", "N/A");
                    String breed = jsonObject.optString("name", "N/A");
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

    private void fetchCatDetails(String id) {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/cat/read/" + id);
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
                petFinalId = id;
                petName = jsonObject.optString("name", "");
                petsize = jsonObject.optString("size", "");
                petAge = String.valueOf(jsonObject.optInt("age", 0)); // Convert integer to string
                petGender = jsonObject.optString("gender", "");
                petBreed = jsonObject.optString("breed", "");
                petCageNo = String.valueOf(jsonObject.optInt("cageNumber", 0)); // Convert integer to string

            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch cat details.");
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
                petFinalId = id;
                petName = jsonObject.optString("name", "");
                petsize = jsonObject.optString("size", "");
                petAge = String.valueOf(jsonObject.optInt("age", 0));
                petGender = jsonObject.optString("gender", "");
                petBreed = jsonObject.optString("breed", "");
                petCageNo = String.valueOf(jsonObject.optInt("cageNumber", 0));

            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch cat details.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private String sendRequest(String url, ApplicantClass or) throws Exception {
        URL url1 = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + token);
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        JSONObject jsonObject = new JSONObject();
        JSONObject petOwnerJson = new JSONObject();
        petOwnerJson.put("id", or.getPetOwner().getId());
        petOwnerJson.put("firstName", or.getPetOwner().getFirstName());
        petOwnerJson.put("lastName", or.getPetOwner().getLastName());
        petOwnerJson.put("contactNo", or.getPetOwner().getContactNo());
        petOwnerJson.put("emailAddress", or.getPetOwner().getEmailAddress());
        petOwnerJson.put("streetAddress", or.getPetOwner().getStreetAddress());

        JSONObject catJson = null;
        if (or.getCat() != null) {
            catJson = new JSONObject();
            catJson.put("catId", or.getCat().getId());
            catJson.put("name", or.getCat().getName());
            catJson.put("breed", or.getCat().getBreed());
            catJson.put("cageNumber", or.getCat().getCageNumber());
            catJson.put("gender", or.getCat().getGender());
            catJson.put("size", or.getCat().getSize());
            catJson.put("age", or.getCat().getAge());
        }

        JSONObject dogJson = null;
        if (or.getDog() != null) {
            dogJson = new JSONObject();
            dogJson.put("dogId", or.getDog().getId());
            dogJson.put("name", or.getDog().getName());
            dogJson.put("breed", or.getDog().getBreed());
            dogJson.put("cageNumber", or.getDog().getCageNumber());
            dogJson.put("gender", or.getDog().getGender());
            dogJson.put("size", or.getDog().getSize());
            dogJson.put("age", or.getDog().getAge());
        }

        jsonObject.put("petOwner", petOwnerJson);
        jsonObject.put("applicationDate", or.getApplicationDate());
        jsonObject.put("dogId", dogJson != null ? dogJson : JSONObject.NULL);
        jsonObject.put("catId", catJson != null ? catJson : JSONObject.NULL);
        jsonObject.put("status", or.getReturnDate());

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
}
