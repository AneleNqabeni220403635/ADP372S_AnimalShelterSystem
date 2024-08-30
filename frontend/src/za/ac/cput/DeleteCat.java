package za.ac.cput;

import javax.swing.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeleteCat extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtName;
    private JTextField txtBreed;
    private JTextField txtAge;
    private JTextField txtGender;
    private JTextField txtSize;
    private JTextField txtCageNumber;
    private JComboBox<String> cboOptions;

    public DeleteCat(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblTitle = new JLabel("Delete Cat Record");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(254, 55, 350, 40);
        add(lblTitle);

        String[] options = {"Select Cat", "Option 1", "Option 2"};
        cboOptions = new JComboBox<>(options);
        cboOptions.setBounds(318, 153, 300, 30);
        cboOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) cboOptions.getSelectedItem();
                if (selectedItem != null) {
                    String id = selectedItem.split(" - ")[0];
                    fetchCatDetails(id);
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
        txtName.setEditable(false);
        add(txtName);

        JLabel lblBreed = new JLabel("Breed:");
        lblBreed.setFont(new Font("Dialog", Font.BOLD, 16));
        lblBreed.setForeground(SystemColor.controlLtHighlight);
        lblBreed.setBounds(139, 237, 100, 30);
        add(lblBreed);

        txtBreed = new JTextField();
        txtBreed.setBounds(318, 237, 300, 30);
        txtBreed.setEditable(false);
        add(txtBreed);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Dialog", Font.BOLD, 16));
        lblAge.setForeground(SystemColor.controlLtHighlight);
        lblAge.setBounds(139, 279, 100, 30);
        add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(318, 279, 300, 30);
        txtAge.setEditable(false);
        add(txtAge);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setFont(new Font("Dialog", Font.BOLD, 16));
        lblGender.setForeground(SystemColor.controlLtHighlight);
        lblGender.setBounds(139, 321, 100, 30);
        add(lblGender);

        txtGender = new JTextField();
        txtGender.setBounds(318, 321, 300, 30);
        txtGender.setEditable(false);
        add(txtGender);

        JLabel lblSize = new JLabel("Size:");
        lblSize.setFont(new Font("Dialog", Font.BOLD, 16));
        lblSize.setForeground(SystemColor.controlLtHighlight);
        lblSize.setBounds(139, 361, 100, 30);
        add(lblSize);

        txtSize = new JTextField();
        txtSize.setBounds(318, 363, 300, 30);
        txtSize.setEditable(false);
        add(txtSize);

        JLabel lblCageNumber = new JLabel("Cage Number:");
        lblCageNumber.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCageNumber.setForeground(SystemColor.controlLtHighlight);
        lblCageNumber.setBounds(139, 403, 173, 30);
        add(lblCageNumber);

        txtCageNumber = new JTextField();
        txtCageNumber.setBounds(318, 405, 300, 30);
        txtCageNumber.setEditable(false);
        add(txtCageNumber);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Dialog", Font.BOLD, 16));
        btnDelete.setBounds(150, 500, 150, 40);
        btnDelete.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DeleteCat();
                cardLayout.show(cardPanel, "Cat");
            }
        });
        add(btnDelete);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(472, 500, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Cat");
            }
        });
        add(btnBack);

        JLabel lblCatId = new JLabel("Select Cat:");
        lblCatId.setForeground(SystemColor.controlLtHighlight);
        lblCatId.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCatId.setBounds(139, 152, 100, 30);
        add(lblCatId);

        populateCatIds();
    }

    private void populateCatIds() {
        try {
            URL url = new URL("http://localhost:8080/animalshelter/cat/getall");
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
                cboOptions.removeAllItems();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("catId");
                    String name = jsonObject.optString("name", "N/A");
                    String breed = jsonObject.optString("breed", "N/A");
                    cboOptions.addItem(String.format("%d - %s %s", id, name, breed));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch cat IDs.");
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
                JOptionPane.showMessageDialog(null, "Error: Unable to fetch cat details.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void  DeleteCat() {
        String selectedItem = (String) cboOptions.getSelectedItem();
        if (selectedItem == null) {
            JOptionPane.showMessageDialog(null, "Please select a volunteer ID.");
            return;
        }

        String id = selectedItem.split(" - ")[0];

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);

        try {
            URL url = new URL("http://localhost:8080/animalshelter/cat/delete/"+id);
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
                JOptionPane.showMessageDialog(null, "Cat Deleted successfully.");

                txtName.setText("");
                txtSize.setText("");
                txtBreed.setText("");
                txtAge.setText("");
                txtCageNumber.setText("");
                txtGender.setText("");

                populateCatIds();
            } else {
                JOptionPane.showMessageDialog(null, "Error: Unable to Delete Cat.\nPlease Check Applicant or PetOwner");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

}
