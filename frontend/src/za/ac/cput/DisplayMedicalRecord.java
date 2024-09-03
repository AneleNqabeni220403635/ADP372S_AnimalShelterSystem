package za.ac.cput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayMedicalRecord extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtName;
    private JTextField txtAge;
    private JTextField txtGender;
    private JTextField txtDescription;
    private JRadioButton rdbtnCat;
    private JRadioButton rdbtnDog;
    private JComboBox<String> cboCat;
    private JComboBox<String> cboDog;
    private JComboBox<String> cboBehaviour;
    private JSpinner spinnerNextCheckup;
    private JComboBox<String> cboMedicalRecordID;

    public DisplayMedicalRecord(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblTitle = new JLabel("Display Medical Record");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(270, 36, 450, 40);
        add(lblTitle);

        JLabel lblMedicalRecordID = new JLabel("Record ID:");
        lblMedicalRecordID.setFont(new Font("Dialog", Font.BOLD, 16));
        lblMedicalRecordID.setForeground(SystemColor.controlLtHighlight);
        lblMedicalRecordID.setBounds(150, 86, 119, 30);
        add(lblMedicalRecordID);

        cboMedicalRecordID = new JComboBox<>(new String[]{"Select Record ID", "Record1", "Record2", "Record3"});
        cboMedicalRecordID.setBounds(296, 86, 300, 30);
        add(cboMedicalRecordID);

        JLabel lblMedication = new JLabel("Medication:");
        lblMedication.setFont(new Font("Dialog", Font.BOLD, 16));
        lblMedication.setForeground(SystemColor.controlLtHighlight);
        lblMedication.setBounds(150, 126, 119, 30);
        add(lblMedication);

        txtName = new JTextField();
        txtName.setEnabled(false);
        txtName.setEditable(false);
        txtName.setBounds(296, 126, 300, 30);
        add(txtName);

        JLabel lblBehaviour = new JLabel("Behaviour:");
        lblBehaviour.setFont(new Font("Dialog", Font.BOLD, 16));
        lblBehaviour.setForeground(SystemColor.controlLtHighlight);
        lblBehaviour.setBounds(150, 166, 100, 30);
        add(lblBehaviour);

        cboBehaviour = new JComboBox<>(new String[]{"Select Behaviour", "Aggressive", "Friendly", "Shy"});
        cboBehaviour.setEnabled(false);
        cboBehaviour.setBounds(296, 166, 300, 30);
        add(cboBehaviour);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Dialog", Font.BOLD, 16));
        lblAge.setForeground(SystemColor.controlLtHighlight);
        lblAge.setBounds(150, 206, 119, 30);
        add(lblAge);

        txtAge = new JTextField();
        txtAge.setEnabled(false);
        txtAge.setEditable(false);
        txtAge.setBounds(296, 206, 300, 30);
        add(txtAge);

        JLabel lblCurrentDate = new JLabel("Current Date:");
        lblCurrentDate.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCurrentDate.setForeground(SystemColor.controlLtHighlight);
        lblCurrentDate.setBounds(150, 246, 136, 30);
        add(lblCurrentDate);

        txtGender = new JTextField();
        txtGender.setEnabled(false);
        txtGender.setBounds(296, 246, 300, 30);
        txtGender.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        txtGender.setEditable(false);
        add(txtGender);

        JLabel lblNextCheckup = new JLabel("Next Checkup:");
        lblNextCheckup.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNextCheckup.setForeground(SystemColor.controlLtHighlight);
        lblNextCheckup.setBounds(150, 293, 136, 30);
        add(lblNextCheckup);

        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(new Date());
        spinnerNextCheckup = new JSpinner(model);
        spinnerNextCheckup.setEnabled(false);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerNextCheckup, "yyyy-MM-dd");
        spinnerNextCheckup.setEditor(editor);
        spinnerNextCheckup.setBounds(296, 293, 300, 30);
        add(spinnerNextCheckup);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setFont(new Font("Dialog", Font.BOLD, 16));
        lblDescription.setForeground(SystemColor.controlLtHighlight);
        lblDescription.setBounds(150, 344, 136, 30);
        add(lblDescription);

        txtDescription = new JTextField();
        txtDescription.setEnabled(false);
        txtDescription.setEditable(false);
        txtDescription.setBounds(296, 333, 300, 60);
        add(txtDescription);

        rdbtnCat = new JRadioButton("Cat");
        rdbtnCat.setEnabled(false);
        rdbtnCat.setFont(new Font("Dialog", Font.BOLD, 16));
        rdbtnCat.setForeground(SystemColor.controlLtHighlight);
        rdbtnCat.setBackground(new Color(0, 128, 128));
        rdbtnCat.setBounds(150, 398, 100, 30);
        rdbtnCat.setSelected(true);
        rdbtnCat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                togglePetSelection(true);
            }
        });
        add(rdbtnCat);

        rdbtnDog = new JRadioButton("Dog");
        rdbtnDog.setEnabled(false);
        rdbtnDog.setFont(new Font("Dialog", Font.BOLD, 16));
        rdbtnDog.setForeground(SystemColor.controlLtHighlight);
        rdbtnDog.setBackground(new Color(0, 128, 128));
        rdbtnDog.setBounds(244, 399, 100, 30);
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
        cboCat.setBounds(296, 435, 300, 30);
        cboCat.setEnabled(false);
        add(cboCat);

        cboDog = new JComboBox<>(new String[]{"Select Dog", "Beagle", "Bulldog", "Labrador"});
        cboDog.setBounds(296, 475, 300, 30);
        cboDog.setEnabled(false);
        add(cboDog);



        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(436, 517, 150, 40);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "MedicalRecord");
            }
        });
        add(btnBack);

        togglePetSelection(true);
    }

    private void togglePetSelection(boolean isCatSelected) {
        cboCat.setEnabled(isCatSelected);
        cboDog.setEnabled(!isCatSelected);
    }
}
