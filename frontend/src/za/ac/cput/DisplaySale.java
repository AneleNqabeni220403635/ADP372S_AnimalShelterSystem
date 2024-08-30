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

public class DisplaySale extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public DisplaySale(CardLayout cardLayout, JPanel cardPanel) {
        setBackground(new Color(0, 128, 128));

        tableModel = new DefaultTableModel(
                new String[]{"Sale ID", "Applicant Name", "Employee Name", "Sale Date", "Amount"}, 0
        );
        setLayout(null);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setEnabled(false);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setBounds(61, 95, 678, 310);
        add(scrollPane);

        JLabel lblSaleRecord = new JLabel("Sale Record");
        lblSaleRecord.setForeground(new Color(255, 255, 255));
        lblSaleRecord.setFont(new Font("Dialog", Font.BOLD, 20));
        lblSaleRecord.setBounds(311, 51, 181, 24);
        add(lblSaleRecord);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(590, 429, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Sale");
            }
        });
        add(btnBack);

        loadSalesData();
    }

    private void loadSalesData() {
        SwingUtilities.invokeLater(() -> {
            try {

                URL url = new URL("http://localhost:8080/animalshelter/sale/getall");
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

                    JSONArray salesArray = new JSONArray(response.toString());
                    tableModel.setRowCount(0);
                    for (int i = 0; i < salesArray.length(); i++) {
                        JSONObject saleObject = salesArray.getJSONObject(i);

                        String saleId = saleObject.optString("id", "N/A");
                        String applicantName = getApplicantName(saleObject.optJSONObject("applicant"));
                        String employeeName = getEmployeeName(saleObject.optJSONObject("employee"));
                        String saleDate = saleObject.optString("saleDate", "N/A");
                        double amount = saleObject.optDouble("price", 0.0);

                        tableModel.addRow(new Object[]{saleId, applicantName, employeeName, saleDate, amount});
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Unable to fetch sales data. Response code: " + responseCode);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        });
    }

    private String getApplicantName(JSONObject applicantJson) {
        if (applicantJson != null) {
            String applicantId = applicantJson.optString("id", "N/A");
            String applicationDate = applicantJson.optString("applicationDate", "N/A");
            return applicantId + " - " + applicationDate;

        }
        return "N/A";
    }

    private String getEmployeeName(JSONObject employeeJson) {
        if (employeeJson != null) {
            String firstName = employeeJson.optString("firstName", "N/A");
            String lastName = employeeJson.optString("lastName", "N/A");
            return firstName + " " + lastName;
        }
        return "N/A";
    }
}
