package za.ac.cput;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

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

public class UpdateApplicant extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtName;
    private JTextField txtBreed;
    private JTextField txtAge;
    private JTextField txtGender;
    private JTextField txtSize;
    private JTextField txtCageNumber;
    private JTextField txtStatus;
    private JComboBox<String> cboPetOwner;
    private JComboBox<String> cboCat;
    private JComboBox<String> cboDog;
    private JRadioButton rdbtnCat;
    private JRadioButton rdbtnDog;
    private JLabel lblApplicationDate;
    private ButtonGroup petTypeGroup;
    private JComboBox<String> cboApplicantID;
    
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
    

    public UpdateApplicant(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblTitle = new JLabel("Update New Applicant Record");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(209, 84, 427, 40);
        add(lblTitle);

        JLabel lblApplicantID = new JLabel("Applicant ID:");
        lblApplicantID.setFont(new Font("Dialog", Font.BOLD, 16));
        lblApplicantID.setForeground(SystemColor.controlLtHighlight);
        lblApplicantID.setBounds(150, 140, 150, 30);
        add(lblApplicantID);

        String[] applicantIDs = {"Select Applicant Id","ID 1", "ID 2", "ID 3"}; // Example data
        cboApplicantID = new JComboBox<>(applicantIDs);
        cboApplicantID.setBounds(318, 140, 300, 30);
        cboApplicantID.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    String selectedItem = (String) cboApplicantID.getSelectedItem();
                    if (selectedItem != null) {
                        String id = selectedItem.split(" - ")[0]; // Extract ID from the selected item
                        fetchApplicantDetails(id);
                    }
                }
            
        });
        add(cboApplicantID);

        JLabel lblPetOwner = new JLabel("Pet Owner:");
        lblPetOwner.setFont(new Font("Dialog", Font.BOLD, 16));
        lblPetOwner.setForeground(SystemColor.controlLtHighlight);
        lblPetOwner.setBounds(150, 184, 150, 30);
        add(lblPetOwner);

        String[] petOwners = {"Owner 1", "Owner 2", "Owner 3"}; // Example data
        cboPetOwner = new JComboBox<>(petOwners);
        cboPetOwner.setBounds(318, 185, 300, 30);
        cboPetOwner.setEnabled(false); // Initially disabled
        add(cboPetOwner);

        rdbtnCat = new JRadioButton("Cat");
        rdbtnCat.setForeground(SystemColor.controlLtHighlight);
        rdbtnCat.setBackground(new Color(0, 128, 128));
        rdbtnCat.setBounds(148, 223, 100, 30);
        rdbtnCat.setEnabled(false); // Initially disabled
        rdbtnCat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleDropdowns(true);
            }
        });
        add(rdbtnCat);

        rdbtnDog = new JRadioButton("Dog");
        rdbtnDog.setForeground(SystemColor.controlLtHighlight);
        rdbtnDog.setBackground(new Color(0, 128, 128));
        rdbtnDog.setBounds(252, 223, 100, 30);
        rdbtnDog.setEnabled(false); // Initially disabled
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
        lblCat.setBounds(150, 265, 100, 30);
        add(lblCat);

        String[] cats = {"Tom", "Whiskers", "Fluffy"}; // Example data
        cboCat = new JComboBox<>(cats);
        cboCat.setBounds(318, 265, 300, 30);
        cboCat.setEnabled(false); // Initially disabled
        add(cboCat);

        JLabel lblDog = new JLabel("Select Dog:");
        lblDog.setFont(new Font("Dialog", Font.BOLD, 16));
        lblDog.setForeground(SystemColor.controlLtHighlight);
        lblDog.setBounds(153, 307, 133, 30);
        add(lblDog);

        String[] dogs = {"Rex", "Buddy", "Max"}; // Example data
        cboDog = new JComboBox<>(dogs);
        cboDog.setBounds(318, 307, 300, 30);
        cboDog.setEnabled(false); // Initially disabled
        add(cboDog);

        JLabel lblApplicationDate = new JLabel("Application Date:");
        lblApplicationDate.setFont(new Font("Dialog", Font.BOLD, 16));
        lblApplicationDate.setForeground(SystemColor.controlLtHighlight);
        lblApplicationDate.setBounds(150, 339, 150, 30);
        add(lblApplicationDate);

        this.lblApplicationDate = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        this.lblApplicationDate.setFont(new Font("Dialog", Font.PLAIN, 16));
        this.lblApplicationDate.setForeground(SystemColor.controlLtHighlight);
        this.lblApplicationDate.setBounds(318, 339, 300, 30);
        date=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        add(this.lblApplicationDate);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setFont(new Font("Dialog", Font.BOLD, 16));
        lblStatus.setForeground(SystemColor.controlLtHighlight);
        lblStatus.setBounds(150, 379, 100, 30);
        add(lblStatus);

        txtStatus = new JTextField();
        txtStatus.setBounds(318, 381, 300, 30);
        txtStatus.setText("Approved");
        txtStatus.setEnabled(false); // Initially disabled
        add(txtStatus);

        JButton btnAdd = new JButton("Update");
        btnAdd.setFont(new Font("Dialog", Font.BOLD, 16));
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedPetType = rdbtnCat.isSelected() ? "cat" : "dog";
                    JSONObject petJson = new JSONObject();
                    System.out.print(selectedPetType.equals("cat"));
                    if (selectedPet.equals("cat")) {
                        	System.out.println(petFinalId+""+petName+""+petBreed+""+petCageNo+""+petGender+""+petsize+""+petAge);
                          	 CatClass cat1=new CatClass(petFinalId, petName, petBreed,petCageNo, petGender, petsize, petAge);
                                 System.out.print("Cat Breed"+cat1.getBreed());
                              PetOwnerClass pet=new PetOwnerClass(petOwnerId,petOwnerfirstName , petOwnerlastName, petOwnercontactNo, petOwneremailAddress, petOwnerstreetAddress);
                                  System.out.println("pet owner"+pet.getEmailAddress());
                                                 
                                  ApplicantClass or=new ApplicantClass(pet,date,null,cat1,"Approved");
                                  // Send request to create owner record
                                  System.out.println("Object"+or.getCat().getId());
                                  System.out.println("Object"+or.getPetOwner().getContactNo());
                                  System.out.println("Object"+or.getReturnDate());
                                  System.out.println("Or Object"+or);
                                  
                                 int check= DeleteApplicant();
                                 if(check==1) {
                                  String url1 = "http://localhost:8080/animalshelter/applicant/update";
                                  System.out.println("Object: " + or);
                                  sendRequest(url1, or);
                                  
                                 }
                              
                        
                    } 
                    else
                    {	
                             	System.out.println(petFinalId+""+petName+""+petBreed+""+petCageNo+""+petGender+""+petsize+""+petAge);
                               	 DogClass cat1=new DogClass(petFinalId, petName, petBreed,petCageNo, petGender, petsize, petAge);
                                      System.out.print("Cat Breed"+cat1.getBreed());
                                      PetOwnerClass pet=new PetOwnerClass(petOwnerId,petOwnerfirstName , petOwnerlastName, petOwnercontactNo, petOwneremailAddress, petOwnerstreetAddress);
                                       System.out.println("pet owner"+pet.getEmailAddress());
                                                      
                                       ApplicantClass or=new ApplicantClass(pet,date,cat1,null,"Approved");
                                       int check=DeleteApplicant();
                                       
                                       if(check==1) {
                                           String url1 = "http://localhost:8080/animalshelter/applicant/update";
                                           System.out.println("Object: " + or);
                                           sendRequest(url1, or);
                                          
                                          }
                                       
                             }
                    }
                catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        btnAdd.setBounds(150, 442, 150, 40);
        add(btnAdd);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(468, 442, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Applicant"); // Change "Applicant" to the actual name of the main panel
            }
        });
        add(btnBack);

        rdbtnCat.setSelected(true);
        toggleDropdowns(true);

        setFieldsEnabled(false);
        fetchApplicantData();
    }

    private void toggleDropdowns(boolean showCat) {
        cboCat.setEnabled(showCat);
        cboDog.setEnabled(!showCat);
    }

    private void setFieldsEnabled(boolean enabled) {
        cboPetOwner.setEnabled(enabled);
        rdbtnCat.setEnabled(enabled);
        rdbtnDog.setEnabled(enabled);
        cboCat.setEnabled(enabled && rdbtnCat.isSelected());
        cboDog.setEnabled(enabled && rdbtnDog.isSelected());
        txtStatus.setEnabled(enabled);
    }
    
    
private void fetchApplicantData() {
    try {
        URL url = new URL("http://localhost:8080/animalshelter/applicant/readStatus/Pending"); // Endpoint to get applicant data
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
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
            
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                applicantId = jsonObject.optString("id", "No ID");
                applicationDate = jsonObject.optString("applicationDate", "No Date");
                applicationStatus = jsonObject.optString("status", "No Status");

                JSONObject petOwner = jsonObject.optJSONObject("petOwner");
                if (petOwner != null) {
                    petOwnerId = petOwner.optString("id", "No ID");
                    petOwnerfirstName = petOwner.optString("firstName", "No First Name");
                    petOwnerlastName = petOwner.optString("lastName", "No Last Name");
                    petOwnercontactNo = petOwner.optString("contactNo", "No Contact No");
                    petOwneremailAddress = petOwner.optString("emailAddress", "No Email");
                    petOwnerstreetAddress = petOwner.optString("streetAddress", "No Street Address");
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
                    selectedPet="cat";
                } else if (dogId != null) {
                    petName = dogId.optString("name", "No Name");
                    petsize = dogId.optString("size", "No Size");
                    petAge = dogId.optString("age", "No Age");
                    petGender = dogId.optString("gender", "No Gender");
                    petBreed = dogId.optString("breed", "No Breed");
                    petCageNo = dogId.optString("cageNumber", "No Cage Number");
                    petFinalId = dogId.optString("dogId", "No Dog ID");
                    selectedPet="dog";
                }

                cboApplicantID.addItem(String.format("%s - %s - %s", applicantId, applicationDate,applicationStatus));
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error: Unable to fetch applicant data.");
        }
    } 
    
    catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
    
    
}

private void fetchApplicantDetails(String id) {
    try {
        URL url = new URL("http://localhost:8080/animalshelter/applicant/read/" + id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
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

            JSONObject jsonObject = new JSONObject(response.toString()); // Parse as JSONObject

            cboApplicantID.removeAllItems();
            cboCat.removeAllItems();
            cboDog.removeAllItems();
            cboPetOwner.removeAllItems();

            applicantId = jsonObject.optString("id", "No ID");
            applicationDate = jsonObject.optString("applicationDate", "No Date");
            applicationStatus = jsonObject.optString("status", "No Status");

            JSONObject petOwner = jsonObject.optJSONObject("petOwner");
            if (petOwner != null) {
                petOwnerId = petOwner.optString("id", "No ID");
                petOwnerfirstName = petOwner.optString("firstName", "No First Name");
                petOwnerlastName = petOwner.optString("lastName", "No Last Name");
                petOwnercontactNo = petOwner.optString("contactNo", "No Contact No");
                petOwneremailAddress = petOwner.optString("emailAddress", "No Email");
                petOwnerstreetAddress = petOwner.optString("streetAddress", "No Street Address");
                System.out.print(String.format("%s - %s - %s", petOwnerId, petOwnerfirstName, petOwnerlastName));
                cboPetOwner.addItem(String.format("%s - %s - %s", petOwnerId, petOwnerfirstName, petOwnerlastName));
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
                selectedPet="cat";
                cboCat.addItem(String.format("%s - %s - %s", petFinalId, petName, petsize));
            } else if (dogId != null) {
                petName = dogId.optString("name", "No Name");
                petsize = dogId.optString("size", "No Size");
                petAge = dogId.optString("age", "No Age");
                petGender = dogId.optString("gender", "No Gender");
                petBreed = dogId.optString("breed", "No Breed");
                petCageNo = dogId.optString("cageNumber", "No Cage Number");
                petFinalId = dogId.optString("dogId", "No Dog ID");
                selectedPet="dog";
                cboDog.addItem(String.format("%s - %s - %s", petFinalId, petName, petsize));
            }

            cboApplicantID.addItem(String.format("%s - %s - %s", applicantId, applicationDate, applicationStatus));
        } else {
            JOptionPane.showMessageDialog(null, "Error: Unable to fetch applicant details.");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
}
private String sendRequest(String url, ApplicantClass or) throws Exception {
    URL url1 = new URL(url);
    HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
    conn.setRequestMethod("PUT");
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

    System.out.println("request"+jsonString);
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
        JOptionPane.showMessageDialog(null, "Applicant updated successfully!");
        return response.toString();
    }
}


private int DeleteApplicant() {


    // Create JSON object with only the ID
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", applicantId); // Include only the ID

    try {
        URL url = new URL("http://localhost:8080/animalshelter/applicant/delete/"+applicantId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
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
