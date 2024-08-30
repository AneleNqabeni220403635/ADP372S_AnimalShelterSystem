package za.ac.cput;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class DisplayOwnerRecord extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    // Constructor that accepts CardLayout and JPanel
    public DisplayOwnerRecord(CardLayout cardLayout, JPanel cardPanel) {
        setBackground(new Color(0, 128, 128));

        // Initialize table model with column names
        tableModel = new DefaultTableModel(
                new String[]{"Owner Name", "Cat Name", "Dog Name", "Taken Date", "Return Date"}, 0
        );
        setLayout(null);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setEnabled(false);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBounds(61, 95, 678, 310);
        add(scrollPane);

        JLabel lblOwnerRecord = new JLabel("Owner Record");
        lblOwnerRecord.setForeground(new Color(255, 255, 255));
        lblOwnerRecord.setFont(new Font("Dialog", Font.BOLD, 20));
        lblOwnerRecord.setBounds(311, 51, 181, 24);
        add(lblOwnerRecord);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(590, 429, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Owner"); // Change "Owner" to the actual name of the main panel
            }
        });
        add(btnBack);

        // Load owner records data
        loadOwnerRecordsData();
    }

    private void loadOwnerRecordsData() {
        SwingUtilities.invokeLater(() -> {
            try {
                // API URL to fetch all owner records
                URL url = new URL("http://localhost:8080/animalshelter/ownerRecord/getall");
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

                    // Parse the response into JSON array
                    JSONArray ownerRecordsArray = new JSONArray(response.toString());
                    tableModel.setRowCount(0); // Clear existing rows

                    // Populate table model with owner records data
                    for (int i = 0; i < ownerRecordsArray.length(); i++) {
                        JSONObject ownerRecordObject = ownerRecordsArray.getJSONObject(i);

                        String ownerName = getOwnerName(ownerRecordObject.optJSONObject("petOwner"));
                        String catName = getCatName(ownerRecordObject.optJSONObject("cat"));
                        String dogName = getDogName(ownerRecordObject.optJSONObject("dog"));
                        String takenDate = ownerRecordObject.optString("takenDate", "N/A");
                        String returnDate = ownerRecordObject.optString("returnDate", "N/A");

                        // Add row to the table model
                        tableModel.addRow(new Object[]{ownerName, catName, dogName, takenDate, returnDate});
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Unable to fetch owner records data. Response code: " + responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        });
    }

    private String getOwnerName(JSONObject ownerJson) {
        if (ownerJson != null) {
            String firstName = ownerJson.optString("firstName", "N/A");
            String lastName = ownerJson.optString("lastName", "N/A");
            return firstName + " " + lastName;
        }
        return "N/A";
    }

    private String getCatName(JSONObject catJson) {
        if (catJson != null) {
            String catId=catJson.optString("catId", "N/A");
            String catName=catJson.optString("name", "N/A");
            return catId+" - "+catName;
        }
        return "N/A";
    }

    private String getDogName(JSONObject dogJson) {
        if (dogJson != null) {

            String dogId=dogJson.optString("dogId", "N/A");
            String dogName=dogJson.optString("name", "N/A");
            return dogId+" - "+dogName;
        }
        return "N/A";
    }
}
