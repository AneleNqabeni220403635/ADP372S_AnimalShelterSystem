package za.ac.cput;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateMedicalRecord extends JPanel {

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

    public CreateMedicalRecord(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(new Color(0, 128, 128));

        JLabel lblTitle = new JLabel("Create A New Medical Record");
        lblTitle.setFont(new Font("Dialog", Font.BOLD, 24));
        lblTitle.setForeground(SystemColor.controlLtHighlight);
        lblTitle.setBounds(218, 43, 450, 40);
        add(lblTitle);

        JLabel lblMedication = new JLabel("Medication:");
        lblMedication.setFont(new Font("Dialog", Font.BOLD, 16));
        lblMedication.setForeground(SystemColor.controlLtHighlight);
        lblMedication.setBounds(150, 114, 119, 30);
        add(lblMedication);

        txtName = new JTextField();
        txtName.setBounds(304, 114, 300, 30);
        add(txtName);

        JLabel lblBehaviour = new JLabel("Behaviour:");
        lblBehaviour.setFont(new Font("Dialog", Font.BOLD, 16));
        lblBehaviour.setForeground(SystemColor.controlLtHighlight);
        lblBehaviour.setBounds(150, 154, 100, 30);
        add(lblBehaviour);

        cboBehaviour = new JComboBox<>(new String[]{"Select Behaviour", "Aggressive", "Friendly", "Shy"});
        cboBehaviour.setBounds(304, 154, 300, 30);
        add(cboBehaviour);

        JLabel lblAge = new JLabel("Age:");
        lblAge.setFont(new Font("Dialog", Font.BOLD, 16));
        lblAge.setForeground(SystemColor.controlLtHighlight);
        lblAge.setBounds(150, 194, 119, 30);
        add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(304, 194, 300, 30);
        add(txtAge);

        JLabel lblCurrentDate = new JLabel("Current Date:");
        lblCurrentDate.setFont(new Font("Dialog", Font.BOLD, 16));
        lblCurrentDate.setForeground(SystemColor.controlLtHighlight);
        lblCurrentDate.setBounds(150, 234, 136, 30);
        add(lblCurrentDate);

        txtGender = new JTextField();
        txtGender.setBounds(304, 234, 300, 30);
        txtGender.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        txtGender.setEditable(false);
        add(txtGender);

        JLabel lblNextCheckup = new JLabel("Next Checkup:");
        lblNextCheckup.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNextCheckup.setForeground(SystemColor.controlLtHighlight);
        lblNextCheckup.setBounds(150, 274, 136, 30);
        add(lblNextCheckup);


        SpinnerDateModel model = new SpinnerDateModel();
        model.setValue(new Date());
        spinnerNextCheckup = new JSpinner(model); // Initialize the spinner
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerNextCheckup, "yyyy-MM-dd");
        spinnerNextCheckup.setEditor(editor);
        spinnerNextCheckup.setBounds(304, 274, 300, 30);
        add(spinnerNextCheckup);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setFont(new Font("Dialog", Font.BOLD, 16));
        lblDescription.setForeground(SystemColor.controlLtHighlight);
        lblDescription.setBounds(150, 314, 136, 30);
        add(lblDescription);

        txtDescription = new JTextField();
        txtDescription.setBounds(304, 314, 300, 60);
        add(txtDescription);

        rdbtnCat = new JRadioButton("Cat");
        rdbtnCat.setFont(new Font("Dialog", Font.BOLD, 16));
        rdbtnCat.setForeground(SystemColor.controlLtHighlight);
        rdbtnCat.setBackground(new Color(0, 128, 128));
        rdbtnCat.setBounds(150, 386, 100, 30);
        rdbtnCat.setSelected(true);
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
        rdbtnDog.setBounds(252, 386, 100, 30);
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
        cboCat.setBounds(304, 422, 300, 30);
        cboCat.setEnabled(true);
        add(cboCat);

        cboDog = new JComboBox<>(new String[]{"Select Dog", "Beagle", "Bulldog", "Labrador"});
        cboDog.setBounds(304, 462, 300, 30);
        cboDog.setEnabled(false);
        add(cboDog);

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Dialog", Font.BOLD, 16));
        btnAdd.setBounds(150, 504, 150, 40);
        add(btnAdd);

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Dialog", Font.BOLD, 16));
        btnBack.setBounds(454, 502, 150, 40);
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
