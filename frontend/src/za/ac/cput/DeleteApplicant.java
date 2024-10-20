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
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeleteApplicant extends JPanel {

    private static final long serialVersionUID = 1L;
    private JComboBox<String> cboApplicantID;
    private JLabel lblPetOwnerData;
    private JLabel lblCat;
    private JLabel lblCatData;
    private JLabel lblDog;
    private JLabel lblDogData;
    private JLabel lblApplicationDateData;
    private JLabel lblStatusData;
    private String token;

    String applicantId;
    String applicationDate;
    String applicationStatus;
    String petOwnerId;
    String petOwnerfirstName;
    String petOwnerlastName;
    String petOwnercontactNo;
    String petOwneremailAddress;
    String petOwnerstreetAddress;

    String petName;
    String petsize;
    String petAge;
    String petGender;
    String petBreed;
    String petCageNo;
    String petFinalId;

    String date;
    String selectedPet;


    public DeleteApplicant(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        // Title Label
        JLabel lblTitle = new JLabel("Delete Application", JLabel.CENTER);
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(209, 84, 427, 40);
        add(lblTitle);

        // Applicant ID Dropdown
        JLabel lblApplicantID = new JLabel("Applicant ID:");
        lblApplicantID.setFont(new Font("Dialog", Font.BOLD, 16));
        lblApplicantID.setForeground(SystemColor.controlLtHighlight);
        lblApplicantID.setBounds(150, 140, 150, 30);
        add(lblApplicantID);

        cboApplicantID = new JComboBox<>();
        cboApplicantID.setBounds(318, 140, 300, 30);
        cboApplicantID.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) cboApplicantID.getSelectedItem();
                if (selectedItem != null && !selectedItem.isEmpty()) {
                    String id = selectedItem.split(" - ")[0];
                    fetchApplicantDetails(id);
                    toggleDropdowns(selectedPet.toLowerCase().equals("cat"));
                }
            }

        });
        add(cboApplicantID);

        // Pet Owner
        JLabel lblPetOwner = new JLabel("Pet Owner:");
        lblPetOwner.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPetOwner.setForeground(SystemColor.controlLtHighlight);
        lblPetOwner.setBounds(150, 180, 150, 30);
        add(lblPetOwner);

        lblPetOwnerData = new JLabel();
        lblPetOwnerData.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblPetOwnerData.setForeground(SystemColor.controlLtHighlight);
        lblPetOwnerData.setBounds(318, 180, 300, 30);
        add(lblPetOwnerData);

        lblCat = new JLabel("Cat:");
        lblCat.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCat.setForeground(SystemColor.controlLtHighlight);
        lblCat.setBounds(150, 220, 150, 30);
        add(lblCat);

        lblCatData = new JLabel();
        lblCatData.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblCatData.setForeground(SystemColor.controlLtHighlight);
        lblCatData.setBounds(318, 220, 300, 30);
        add(lblCatData);

        lblDog = new JLabel("Dog:");
        lblDog.setFont(new Font("Dialog", Font.BOLD, 16));
        lblDog.setForeground(SystemColor.controlLtHighlight);
        lblDog.setBounds(150, 220, 150, 30);
        lblDog.setVisible(false);
        add(lblDog);

        lblDogData = new JLabel();
        lblDogData.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblDogData.setForeground(SystemColor.controlLtHighlight);
        lblDogData.setBounds(318, 220, 300, 30);
        add(lblDogData);

        JLabel lblApplicationDate = new JLabel("Application Date:");
        lblApplicationDate.setFont(new Font("Dialog", Font.BOLD, 16));
        lblApplicationDate.setForeground(SystemColor.controlLtHighlight);
        lblApplicationDate.setBounds(150, 260, 150, 30);
        add(lblApplicationDate);

        lblApplicationDateData = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        lblApplicationDateData.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblApplicationDateData.setForeground(SystemColor.controlLtHighlight);
        lblApplicationDateData.setBounds(318, 260, 300, 30);
        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        add(lblApplicationDateData);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setFont(new Font("Dialog", Font.BOLD, 16));
        lblStatus.setForeground(SystemColor.controlLtHighlight);
        lblStatus.setBounds(150, 300, 100, 30);
        add(lblStatus);

        lblStatusData = new JLabel();
        lblStatusData.setBounds(318, 300, 300, 30);
        lblStatusData.setFont(new Font("Dialog", Font.PLAIN, 16));
        lblStatusData.setForeground(SystemColor.controlLtHighlight);
        lblStatusData.setText("");
        add(lblStatusData);

        token = SessionManager.getInstance().getBearerToken();

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Dialog", Font.BOLD, 16));
        btnDelete.addActionListener(e -> {
            try {
                if (selectedPet.equals("cat")) {
                    CatClass cat1 = new CatClass(petFinalId, petName, petBreed, petCageNo, petGender, petsize, petAge);
                    PetOwnerClass pet = new PetOwnerClass(petOwnerId, petOwnerfirstName, petOwnerlastName, petOwnercontactNo, petOwneremailAddress, petOwnerstreetAddress);

                    ApplicantClass or = new ApplicantClass(pet, date, null, cat1, "Pending");

                    int check = DeleteApplicant();
                    if (check == 1) {
                        JOptionPane.showMessageDialog(null, "Applicant deleted successfully!");
                        setupData();
                    }
                } else {
                    DogClass cat1 = new DogClass(petFinalId, petName, petBreed, petCageNo, petGender, petsize, petAge);
                    System.out.print("Cat Breed" + cat1.getBreed());
                    PetOwnerClass pet = new PetOwnerClass(petOwnerId, petOwnerfirstName, petOwnerlastName, petOwnercontactNo, petOwneremailAddress, petOwnerstreetAddress);
                    System.out.println("pet owner" + pet.getEmailAddress());

                    ApplicantClass or = new ApplicantClass(pet, date, cat1, null, "pending");
                    int check = DeleteApplicant();

                    if (check == 1) {
                        JOptionPane.showMessageDialog(null, "Applicant deleted successfully!");
                        setupData();
                    }
                }
                toggleDropdowns(selectedPet.equals("cat"));
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        });

        btnDelete.setBounds(150, 442, 150, 40);
        add(btnDelete);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(468, 442, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Applicant");
            }
        });
        add(btnBack);
        ImagePanel imgPanel = new ImagePanel();
        imgPanel.setBounds(40, 40, 120, 90);
        add(imgPanel);
        setupData();
    }


    private void setupData()
    {
        fetchApplicantData();
    }

    private void toggleDropdowns(boolean showCat) {
        lblCat.setVisible(showCat);
        lblCatData.setVisible(showCat);
        lblDog.setVisible(!showCat);
        lblDogData.setVisible(!showCat);
    }


    private void fetchApplicantData() {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/applicant/readStatus/Pending");
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
                cboApplicantID.removeAllItems();

                if (!jsonArray.isEmpty()) {
                    cboApplicantID.addItem("");
                }

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    // Extract Applicant Data
                    applicantId = jsonObject.optString("id", "No ID");
                    applicationDate = jsonObject.optString("applicationDate", "No Date");
                    applicationStatus = jsonObject.optString("status", "No Status");

                    // Extract Pet Owner Data
                    JSONObject petOwner = jsonObject.optJSONObject("petOwner");
                    if (petOwner != null) {
                        petOwnerId = petOwner.optString("id", "No ID");
                        petOwnerfirstName = petOwner.optString("firstName", "No First Name");
                        petOwnerlastName = petOwner.optString("lastName", "No Last Name");
                        petOwnercontactNo = petOwner.optString("contactNo", "No Contact No");
                        petOwneremailAddress = petOwner.optString("emailAddress", "No Email");
                        petOwnerstreetAddress = petOwner.optString("streetAddress", "No Street Address");
                    }

                    // Extract Pet Data (Cat or Dog)
                    JSONObject catId = jsonObject.optJSONObject("catId");
                    JSONObject dogId = jsonObject.optJSONObject("dogId");

                    if (catId != null) {
                        petName = catId.optString("name", "No Name");
                        petsize = catId.optString("size", "No Size");
                        petAge = catId.optString("age", "No Age");
                        petGender = catId.optString("gender", "No Gender");
                        petBreed = catId.optString("breed", "No Breed");
                        petCageNo = catId.optString("cageNumber", "No Cage Number");
                        petFinalId = catId.optString("catId", "No Cat ID");
                        selectedPet = "cat";
                    } else if (dogId != null) {
                        petName = dogId.optString("name", "No Name");
                        petsize = dogId.optString("size", "No Size");
                        petAge = dogId.optString("age", "No Age");
                        petGender = dogId.optString("gender", "No Gender");
                        petBreed = dogId.optString("breed", "No Breed");
                        petCageNo = dogId.optString("cageNumber", "No Cage Number");
                        petFinalId = dogId.optString("dogId", "No Dog ID");
                        selectedPet = "dog";
                    }

                    cboApplicantID.addItem(String.format("%s - %s - %s - %s %s",
                            applicantId,
                            applicationDate,
                            applicationStatus,
                            petOwnerfirstName,
                            petOwnerlastName));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch applicant data.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void fetchApplicantDetails(String id) {

        try {
            URL url = new URL("http://localhost:8080/animalshelter/applicant/read/" + id);
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

                lblCatData.setText("");
                lblDogData.setText("");
                lblPetOwnerData.setText("");

                applicantId = jsonObject.optString("id", "No ID");
                applicationDate = jsonObject.optString("applicationDate", "No Date");
                applicationStatus = jsonObject.optString("status", "No Status");
                lblStatusData.setText(applicationStatus);

                JSONObject petOwner = jsonObject.optJSONObject("petOwner");
                if (petOwner != null) {
                    petOwnerId = petOwner.optString("id", "No ID");
                    petOwnerfirstName = petOwner.optString("firstName", "No First Name");
                    petOwnerlastName = petOwner.optString("lastName", "No Last Name");
                    petOwnercontactNo = petOwner.optString("contactNo", "No Contact No");
                    petOwneremailAddress = petOwner.optString("emailAddress", "No Email");
                    petOwnerstreetAddress = petOwner.optString("streetAddress", "No Street Address");
                    lblPetOwnerData.setText(String.format("%s - %s - %s", petOwnerId, petOwnerfirstName, petOwnerlastName));
                }


                JSONObject catId = jsonObject.optJSONObject("catId");
                JSONObject dogId = jsonObject.optJSONObject("dogId");

                if (catId != null) {
                    petName = catId.optString("name", "No Name");
                    petsize = catId.optString("size", "No Size");
                    petAge = catId.optString("age", "No Age");
                    petGender = catId.optString("gender", "No Gender");
                    petBreed = catId.optString("breed", "No Breed");
                    petCageNo = catId.optString("cageNumber", "No Cage Number");
                    petFinalId = catId.optString("catId", "No Cat ID");
                    selectedPet = "cat";
                    lblCatData.setText(String.format("%s - %s - %s", petFinalId, petName, petsize));
                } else if (dogId != null) {
                    petName = dogId.optString("name", "No Name");
                    petsize = dogId.optString("size", "No Size");
                    petAge = dogId.optString("age", "No Age");
                    petGender = dogId.optString("gender", "No Gender");
                    petBreed = dogId.optString("breed", "No Breed");
                    petCageNo = dogId.optString("cageNumber", "No Cage Number");
                    petFinalId = dogId.optString("dogId", "No Dog ID");
                    selectedPet = "dog";
                    lblDogData.setText(String.format("%s - %s - %s", petFinalId, petName, petsize));
                }
                //cboApplicantID.addItem(String.format("%s - %s - %s", applicantId, applicationDate, applicationStatus));
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch applicant details.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }


    private int DeleteApplicant() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", applicantId);

        try {
            URL url = new URL("http://localhost:8080/animalshelter/applicant/delete/" + applicantId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (java.io.OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonObject.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        return 0;
    }
}
