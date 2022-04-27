import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UI {
    /**object variables*/
    private JPanel panel;
    private JButton addProjectButton;
    private JTabbedPane tabbedPane;
    private JComboBox projectTypeJComboBox;
    private JLabel classificationLabel;
    private JTextField classificationTextField;
    private JButton editProjectButton;
    private JTextField managerTextField;
    private JTextField locationTextField;
    private JTextField customerIDTextField;
    private JLabel projectTypeLabel;
    private JLabel budgetLabel;
    private JLabel managerLabel;
    private JLabel locationLabel;
    private JLabel newNotRenovationLabel;
    private JLabel customerIDLabel;
    private JLabel estimatedDurationLabel;
    private JLabel estimatedStartLabel;
    private JLabel actualStartLabel;
    private JLabel actualEndLabel;
    private JLabel accumulatedCostLabel;
    private JPanel searchJPanel;
    private JPanel addOrEditJPanel;
    private JCheckBox newNotRenovationCheckBox;
    private JSpinner estimatedDurationSpinner;
    private JSpinner accumulatedCostSpinner;
    private JTextField typeTextField;
    private JSpinner floodingRiskSpinner;
    private JTextField buildingMaterialTextField;
    private JSpinner sizeSpinner;
    private JSpinner bedroomsSpinner;
    private JSpinner bathroomsSpinner;
    private JSpinner landSizeAcresSpinner;
    private JCheckBox garageCheckBox;
    private JTextField roofTextField;
    private JSpinner widthSpinner;
    private JSpinner spanSpinner;
    private JTextField overlayTextField;
    private JCheckBox natureReserveCheckBox;
    private JTextField compositionTextField;
    private JTextField excavatingTextField;
    private JCheckBox safetyTunnelCheckBox;
    private JSpinner lengthSpinner;
    private JSpinner groundStabilitySpinner;
    private JLabel typeLabel;
    private JLabel floodingRiskLabel;
    private JLabel buildingMaterialLabel;
    private JLabel sizeLabel;
    private JLabel unitOfSizeLabel;
    private JLabel bedroomsLabel;
    private JLabel landSizeAcresLabel;
    private JLabel bathroomsLabel;
    private JLabel garageLabel;
    private JLabel roofLabel;
    private JLabel widthLabel;
    private JLabel spanLabel;
    private JLabel overlayLabel;
    private JLabel compositionLabel;
    private JLabel natureReserveLabel;
    private JLabel excavatingLabel;
    private JLabel safetyTunnelLabel;
    private JLabel lengthLabel;
    private JLabel groundStabilityLabel;
    private JSpinner budgetSpinner;
    private JComboBox projectTypeSearchJComboBox;
    private JButton projectTypeSearchButton;
    private JButton costRangeSearchButton;
    private JButton projectManagerSearchButton;
    private JSpinner maxCostRangeSearchSpinner;
    private JSpinner minCostRangeSearchSpinner;
    private JTextField projectManagerSearchTextField;
    private JTextArea searchResultsTextArea;
    private JLabel minCostRangeSearchLabel;
    private JLabel maxCostRangeSearchLabel;
    private JSpinner estimatedStartSpinner;
    private JSpinner actualStartSpinner;
    private JSpinner actualEndSpinner;
    ProjectManager PM = new ProjectManager();

    /**main method: program start*/
    public static void main(String[] args) {
        //ProjectManager PM = new ProjectManager(); //added this to UI object variables instead, so I can access it in UI constructor for pre_fill method
        UI GUI = new UI();
    }

    /**object constructor*/
    public UI() {
        setLAndF();
        setupGUI(); //this initialises all the GUI components (variables)
        JFrame frame = new JFrame("Jenga Design Ltd. Portfolio Manager");
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        pre_fill(PM);

        projectTypeJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //enable or disable project fields based on selected project type
                enableOrDisableProjectSpecificFields();
                //clear fields when a different project type has been selected
                clearAllProjectFields();
            }
        });
        editProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //constructing the inserted object and changing object in project manager if it exists
                //constructing the object and adding it to the projects array list
                Project newProject = generateProjectFromFields();

                //check that fields are correct format, if not block entry and let user know with a JOptionPane
                if (newProject.isCorrect()){
                    //editing project
                    editProject(newProject);

                    //clearing fields once a project has been edited
                    clearAllProjectFields();
                }
            }
        });
        addProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //constructing the object and adding it to the projects array list
                Project newProject = generateProjectFromFields();

                //check that fields are correct format, if not block entry and let user know with a JOptionPane
                if (newProject.isCorrect()){
                    addProject(newProject);

                    //clearing fields once a project has been added
                    clearAllProjectFields();
                }
            }
        });
        projectTypeSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //search for projects of this type
                searchProjectsByType();
            }
        });
        projectManagerSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchProjectsByManager();
            }
        });
        costRangeSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchProjectsByCostRange();
            }
        });
    }

    /**object methods*/

    private void searchProjectsByCostRange() {
        int minCost = (int) minCostRangeSearchSpinner.getValue();
        int maxCost = (int) maxCostRangeSearchSpinner.getValue();
        searchResultsTextArea.setText(PM.searchByMinAndMaxCost(minCost, maxCost));
    }

    private void searchProjectsByManager() {
        String searchInput = projectManagerSearchTextField.getText();
        searchResultsTextArea.setText(PM.searchByManager(searchInput));

    }

    private void searchProjectsByType() {
        String selectedType = (String) projectTypeSearchJComboBox.getSelectedItem();
        searchResultsTextArea.setText(PM.searchByType(selectedType));
    }

    private Project generateProjectFromFields() {
        Project project = null; //project that will be generated

        //new project property values
        String selectedProjectType = (String) projectTypeJComboBox.getSelectedItem();
        String projectType = selectedProjectType;
        String typeClassification = classificationTextField.getText();
        int budget = (int) budgetSpinner.getValue();
        String projectManager = managerTextField.getText();
        String location = locationTextField.getText();
        boolean newNotRenovation = newNotRenovationCheckBox.isSelected();
        String customerID = customerIDTextField.getText();
        //checking if customer ID is unique
        int estimatedDuration = (int) estimatedDurationSpinner.getValue();
        Calendar estimatedStart = toCalendar((Date) estimatedStartSpinner.getValue());
        Calendar actualStart = toCalendar((Date) actualStartSpinner.getValue());
        Calendar actualEnd = toCalendar((Date) actualEndSpinner.getValue());
        int accumulatedCost = (int) accumulatedCostSpinner.getValue();
        //project type specific property values
        //house
        String type = typeTextField.getText();
        int floodingRisk = (int) floodingRiskSpinner.getValue();
        String buildingMaterial = buildingMaterialTextField.getText();
        int size = (int) sizeSpinner.getValue();
        int bedrooms = (int) bedroomsSpinner.getValue();
        double landSizeDouble = (double) landSizeAcresSpinner.getValue();
        float landSize = (float) landSizeDouble;
        int bathrooms = (int) bathroomsSpinner.getValue();
        boolean garage = garageCheckBox.isSelected();
        String roof = roofTextField.getText();
        //bridge extra properties
        int width = (int) widthSpinner.getValue();
        String overlay = overlayTextField.getText();
        int span = (int) spanSpinner.getValue();
        //tunnel extra properties
        String excavating = excavatingTextField.getText();
        boolean safetyTunnel = safetyTunnelCheckBox.isSelected();
        int length = (int) lengthSpinner.getValue();
        int groundStructuralStability = (int) groundStabilitySpinner.getValue();
        //land extra properties
        String currentComposition = compositionTextField.getText();
        boolean natureReserve = natureReserveCheckBox.isSelected();

        if (selectedProjectType.equals("House")){
            //create and add house object to Project Manager array list
            project = new House(projectType, typeClassification, budget, projectManager, location, newNotRenovation,
                    customerID, estimatedDuration, estimatedStart, actualStart, actualEnd, accumulatedCost,
                    type, floodingRisk, buildingMaterial, size, bedrooms, landSize, bathrooms, garage, roof);
        } else if (selectedProjectType.equals("Bridge")){
            //create and add bridge object to Project Manager array list
            project = new Bridge(projectType, typeClassification, budget, projectManager, location, newNotRenovation, customerID, estimatedDuration, estimatedStart, actualStart, actualEnd, accumulatedCost,
                    type, floodingRisk, buildingMaterial, width, overlay, span);
        } else if (selectedProjectType.equals("Tunnel")){
            //create and add tunnel object to Project Manager array list
            project = new Tunnel(projectType, typeClassification, budget, projectManager, location, newNotRenovation, customerID, estimatedDuration, estimatedStart, actualStart, actualEnd, accumulatedCost,
                    width, overlay, excavating, safetyTunnel, length, groundStructuralStability);
        } else if (selectedProjectType.equals("Land")){
            //create and add land object to Project Manager arrayList
            project = new Land(projectType, typeClassification, budget, projectManager, location, newNotRenovation, customerID, estimatedDuration, estimatedStart, actualStart, actualEnd, accumulatedCost,
                    size, currentComposition, natureReserve);
        }

        return project;
    }

    private Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private void addProject(Project project) {
        //if project is null don't add
        if (project == null){
            return;
        }
        //checking if project customerID is unique, and if so add to project manager. Because customer IDs are unique to each project.
        if (PM.isUnique(project)) {
            PM.addProject(project);
        } else{
            return;
        }
    }

    private void editProject(Project project) {
        //if project is null don't add
        if (project == null){
            return;
        }
        //checking that project customerID is NOT unique, and if so edit project in manager. Because customer IDs are unique to each project.
        if (!(PM.isUnique(project))) {
            PM.editProject(project);
        } else{
            return;
        }
    }

    private void pre_fill(ProjectManager pm) {
        //pre-fill and do stuff for when program first starts up
        //pre-fill add/edit JPanel project type JComboBox
        projectTypeJComboBox.removeAllItems();
        projectTypeJComboBox.addItem("House");
        projectTypeJComboBox.addItem("Bridge");
        projectTypeJComboBox.addItem("Tunnel");
        projectTypeJComboBox.addItem("Land");
        enableOrDisableProjectSpecificFields();
        //pre-fill search JPanel project type search JComboBox
        projectTypeSearchJComboBox.addItem("House");
        projectTypeSearchJComboBox.addItem("Bridge");
        projectTypeSearchJComboBox.addItem("Tunnel");
        projectTypeSearchJComboBox.addItem("Land");
    }

    private void enableOrDisableProjectSpecificFields() {
        disableAllProjectSpecificFields();

        if (projectTypeJComboBox.getSelectedItem().equals("House")){
            //set up GUI for house projects
            typeTextField.setEnabled(true);
            floodingRiskSpinner.setEnabled(true);
            buildingMaterialTextField.setEnabled(true);
            sizeSpinner.setEnabled(true);
            unitOfSizeLabel.setEnabled(true);
            unitOfSizeLabel.setText("m**2");
            bedroomsSpinner.setEnabled(true);
            landSizeAcresSpinner.setEnabled(true);
            bathroomsSpinner.setEnabled(true);
            garageCheckBox.setEnabled(true);
            roofTextField.setEnabled(true);
        }
        if (projectTypeJComboBox.getSelectedItem().equals("Bridge")){
            //set GUI up for bridge projects
            typeTextField.setEnabled(true);
            floodingRiskSpinner.setEnabled(true);
            buildingMaterialTextField.setEnabled(true);
            widthSpinner.setEnabled(true);
            overlayTextField.setEnabled(true);
            spanSpinner.setEnabled(true);
        }
        if (projectTypeJComboBox.getSelectedItem().equals("Tunnel")){
            //set GUI up for tunnel projects
            widthSpinner.setEnabled(true);
            overlayTextField.setEnabled(true);
            excavatingTextField.setEnabled(true);
            safetyTunnelCheckBox.setEnabled(true);
            lengthSpinner.setEnabled(true);
            groundStabilitySpinner.setEnabled(true);
        }
        if (projectTypeJComboBox.getSelectedItem().equals("Land")){
            //set GUI up for land projects
            sizeSpinner.setEnabled(true);
            unitOfSizeLabel.setEnabled(true);
            unitOfSizeLabel.setText("acres");
            compositionTextField.setEnabled(true);
            natureReserveCheckBox.setEnabled(true);
        }
    }

    private void clearAllProjectFields() {
        //clears input from all fields
        classificationTextField.setText("");
        budgetSpinner.setValue(0);
        managerTextField.setText("");
        locationTextField.setText("");
        newNotRenovationCheckBox.setSelected(false);
        customerIDTextField.setText("");
        estimatedDurationSpinner.setValue(0);
        estimatedStartSpinner.setValue(new Date());
        actualStartSpinner.setValue(new Date());
        actualEndSpinner.setValue(new Date());
        accumulatedCostSpinner.setValue(0);
        //project specific fields
        typeTextField.setText("");
        floodingRiskSpinner.setValue(0);
        buildingMaterialTextField.setText("");
        sizeSpinner.setValue(0);
        bedroomsSpinner.setValue(0);
        landSizeAcresSpinner.setValue(0.0);
        bathroomsSpinner.setValue(0);
        garageCheckBox.setSelected(false);
        roofTextField.setText("");
        widthSpinner.setValue(0);
        spanSpinner.setValue(0);
        overlayTextField.setText("");
        compositionTextField.setText("");
        natureReserveCheckBox.setSelected(false);
        excavatingTextField.setText("");
        safetyTunnelCheckBox.setSelected(false);
        lengthSpinner.setValue(0);
        groundStabilitySpinner.setValue(0);
    }

    private void disableAllProjectSpecificFields() {
        //disables all project specific fields
        typeTextField.setEnabled(false);
        floodingRiskSpinner.setEnabled(false);
        buildingMaterialTextField.setEnabled(false);
        sizeSpinner.setEnabled(false);
        unitOfSizeLabel.setText("unit");
        unitOfSizeLabel.setEnabled(false);
        bedroomsSpinner.setEnabled(false);
        landSizeAcresSpinner.setEnabled(false);
        bathroomsSpinner.setEnabled(false);
        garageCheckBox.setEnabled(false);
        roofTextField.setEnabled(false);
        widthSpinner.setEnabled(false);
        spanSpinner.setEnabled(false);
        overlayTextField.setEnabled(false);
        compositionTextField.setEnabled(false);
        natureReserveCheckBox.setEnabled(false);
        excavatingTextField.setEnabled(false);
        safetyTunnelCheckBox.setEnabled(false);
        lengthSpinner.setEnabled(false);
        groundStabilitySpinner.setEnabled(false);
    }

    private void setupGUI() {
        //spinner models
        SpinnerModel estimatedDurationSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        SpinnerModel accumulatedCostSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);

        //spinner date models
        SpinnerDateModel actualStartSpinnerModel = new SpinnerDateModel();
        SpinnerModel actualEndSpinnerModel = new SpinnerDateModel();
        SpinnerModel estimatedStartSpinnerModel = new SpinnerDateModel();

        SpinnerModel floodingRiskSpinnerModel = new SpinnerNumberModel(0, 0, 10, 1);
        SpinnerModel sizeSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        SpinnerModel bedroomsSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        SpinnerModel bathroomsSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        SpinnerModel landSizeAcresSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 0.1);
        SpinnerModel widthSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 5);
        SpinnerModel spanSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 5);
        SpinnerModel lengthSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        SpinnerModel groundStabilitySpinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
        SpinnerModel budgetSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        SpinnerModel minCostRangeSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        SpinnerModel maxCostRangeSpinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);

        //initialising GUI elements

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        tabbedPane = new JTabbedPane();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(tabbedPane, gbc);
        addOrEditJPanel = new JPanel();
        addOrEditJPanel.setLayout(new GridBagLayout());
        addOrEditJPanel.setInheritsPopupMenu(false);
        addOrEditJPanel.setName("estimated duration");
        tabbedPane.addTab("Add/Edit Project", addOrEditJPanel);
        projectTypeJComboBox = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(projectTypeJComboBox, gbc);
        projectTypeLabel = new JLabel();
        projectTypeLabel.setText("project type");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(projectTypeLabel, gbc);
        classificationLabel = new JLabel();
        classificationLabel.setText("classification");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(classificationLabel, gbc);
        classificationTextField = new JTextField();
        classificationTextField.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(classificationTextField, gbc);
        budgetLabel = new JLabel();
        budgetLabel.setText("budget");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(budgetLabel, gbc);
        managerLabel = new JLabel();
        managerLabel.setText("manager");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(managerLabel, gbc);
        locationLabel = new JLabel();
        locationLabel.setText("location");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(locationLabel, gbc);
        newNotRenovationLabel = new JLabel();
        newNotRenovationLabel.setText("new or renovation");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(newNotRenovationLabel, gbc);
        customerIDLabel = new JLabel();
        customerIDLabel.setText("customer ID");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(customerIDLabel, gbc);
        estimatedDurationLabel = new JLabel();
        estimatedDurationLabel.setText("estimated duration");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(estimatedDurationLabel, gbc);
        actualStartLabel = new JLabel();
        actualStartLabel.setText("actual start d/m/y");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(actualStartLabel, gbc);
        actualEndLabel = new JLabel();
        actualEndLabel.setText("actual end d/m/y");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(actualEndLabel, gbc);
        accumulatedCostLabel = new JLabel();
        accumulatedCostLabel.setText("accumulated cost");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(accumulatedCostLabel, gbc);
        managerTextField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(managerTextField, gbc);
        locationTextField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(locationTextField, gbc);
        customerIDTextField = new JTextField();
        customerIDTextField.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(customerIDTextField, gbc);
        estimatedDurationSpinner = new JSpinner(estimatedDurationSpinnerModel);
        estimatedDurationSpinner.setInheritsPopupMenu(true);
        estimatedDurationSpinner.setRequestFocusEnabled(true);
        estimatedDurationSpinner.setVisible(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(estimatedDurationSpinner, gbc);
        accumulatedCostSpinner = new JSpinner(accumulatedCostSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 12;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(accumulatedCostSpinner, gbc);
        typeLabel = new JLabel();
        typeLabel.setText("type");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(typeLabel, gbc);
        estimatedStartLabel = new JLabel();
        estimatedStartLabel.setText("estimated start d/m/y");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(estimatedStartLabel, gbc);
        floodingRiskLabel = new JLabel();
        floodingRiskLabel.setText("flooding risk");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(floodingRiskLabel, gbc);
        buildingMaterialLabel = new JLabel();
        buildingMaterialLabel.setText("building material");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(buildingMaterialLabel, gbc);
        sizeLabel = new JLabel();
        sizeLabel.setText("size");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(sizeLabel, gbc);
        bedroomsLabel = new JLabel();
        bedroomsLabel.setText("bedrooms");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(bedroomsLabel, gbc);
        landSizeAcresLabel = new JLabel();
        landSizeAcresLabel.setText("land size (acres)");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(landSizeAcresLabel, gbc);
        bathroomsLabel = new JLabel();
        bathroomsLabel.setText("bathrooms");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(bathroomsLabel, gbc);
        roofLabel = new JLabel();
        roofLabel.setText("roof");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 9;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(roofLabel, gbc);
        typeTextField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(typeTextField, gbc);
        floodingRiskSpinner = new JSpinner(floodingRiskSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(floodingRiskSpinner, gbc);
        buildingMaterialTextField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(buildingMaterialTextField, gbc);
        sizeSpinner = new JSpinner(sizeSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(sizeSpinner, gbc);
        bedroomsSpinner = new JSpinner(bedroomsSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(bedroomsSpinner, gbc);
        bathroomsSpinner = new JSpinner(bathroomsSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 7;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(bathroomsSpinner, gbc);
        landSizeAcresSpinner = new JSpinner(landSizeAcresSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(landSizeAcresSpinner, gbc);
        garageCheckBox = new JCheckBox();
        garageCheckBox.setText("garage");
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 8;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(garageCheckBox, gbc);
        roofTextField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 9;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(roofTextField, gbc);
        widthLabel = new JLabel();
        widthLabel.setText("width");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(widthLabel, gbc);
        spanLabel = new JLabel();
        spanLabel.setText("span");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(spanLabel, gbc);
        overlayLabel = new JLabel();
        overlayLabel.setText("overlay");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(overlayLabel, gbc);
        widthSpinner = new JSpinner(widthSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 10;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(widthSpinner, gbc);
        spanSpinner = new JSpinner(spanSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 11;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(spanSpinner, gbc);
        overlayTextField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 12;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(overlayTextField, gbc);
        unitOfSizeLabel = new JLabel();
        unitOfSizeLabel.setText("unit");
        gbc = new GridBagConstraints();
        gbc.gridx = 8;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(unitOfSizeLabel, gbc);
        compositionLabel = new JLabel();
        compositionLabel.setText("current composition");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(compositionLabel, gbc);
        natureReserveLabel = new JLabel();
        natureReserveLabel.setText("nature reserve");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 14;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(natureReserveLabel, gbc);
        natureReserveCheckBox = new JCheckBox();
        natureReserveCheckBox.setText("nature reserve");
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 14;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(natureReserveCheckBox, gbc);
        compositionTextField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 13;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(compositionTextField, gbc);
        excavatingLabel = new JLabel();
        excavatingLabel.setText("excavating");
        gbc = new GridBagConstraints();
        gbc.gridx = 11;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(excavatingLabel, gbc);
        excavatingTextField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 12;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(excavatingTextField, gbc);
        safetyTunnelCheckBox = new JCheckBox();
        safetyTunnelCheckBox.setText("safety tunnel");
        gbc = new GridBagConstraints();
        gbc.gridx = 12;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(safetyTunnelCheckBox, gbc);
        lengthSpinner = new JSpinner(lengthSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 12;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(lengthSpinner, gbc);
        groundStabilitySpinner = new JSpinner(groundStabilitySpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 12;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(groundStabilitySpinner, gbc);
        safetyTunnelLabel = new JLabel();
        safetyTunnelLabel.setText("safety tunnel");
        gbc = new GridBagConstraints();
        gbc.gridx = 11;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(safetyTunnelLabel, gbc);
        lengthLabel = new JLabel();
        lengthLabel.setText("length");
        gbc = new GridBagConstraints();
        gbc.gridx = 11;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(lengthLabel, gbc);
        groundStabilityLabel = new JLabel();
        groundStabilityLabel.setText("ground stability");
        gbc = new GridBagConstraints();
        gbc.gridx = 11;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(groundStabilityLabel, gbc);
        editProjectButton = new JButton();
        editProjectButton.setText("Edit project");
        gbc = new GridBagConstraints();
        gbc.gridx = 11;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.gridheight = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        addOrEditJPanel.add(editProjectButton, gbc);
        addProjectButton = new JButton();
        addProjectButton.setText("Record a new project");
        gbc = new GridBagConstraints();
        gbc.gridx = 11;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.gridheight = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        addOrEditJPanel.add(addProjectButton, gbc);
        newNotRenovationCheckBox = new JCheckBox();
        newNotRenovationCheckBox.setText("new");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(newNotRenovationCheckBox, gbc);
        garageLabel = new JLabel();
        garageLabel.setText("garage");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 8;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        addOrEditJPanel.add(garageLabel, gbc);
        budgetSpinner = new JSpinner(budgetSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(budgetSpinner, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 14;
        gbc.fill = GridBagConstraints.VERTICAL;
        addOrEditJPanel.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.gridheight = 14;
        gbc.fill = GridBagConstraints.VERTICAL;
        addOrEditJPanel.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 10;
        gbc.gridy = 1;
        gbc.gridheight = 14;
        gbc.fill = GridBagConstraints.VERTICAL;
        addOrEditJPanel.add(spacer3, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 13;
        gbc.gridy = 1;
        gbc.gridheight = 14;
        gbc.fill = GridBagConstraints.VERTICAL;
        addOrEditJPanel.add(spacer4, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 14;
        gbc.fill = GridBagConstraints.VERTICAL;
        addOrEditJPanel.add(spacer5, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 12;
        gbc.fill = GridBagConstraints.VERTICAL;
        addOrEditJPanel.add(spacer6, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 15;
        gbc.gridwidth = 12;
        gbc.fill = GridBagConstraints.VERTICAL;
        addOrEditJPanel.add(spacer7, gbc);
        estimatedStartSpinner = new JSpinner(estimatedStartSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 9;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(estimatedStartSpinner, gbc);
        actualStartSpinner = new JSpinner(actualStartSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 10;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(actualStartSpinner, gbc);
        actualEndSpinner = new JSpinner(actualEndSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 11;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        addOrEditJPanel.add(actualEndSpinner, gbc);
        searchJPanel = new JPanel();
        searchJPanel.setLayout(new GridBagLayout());
        tabbedPane.addTab("Search Projects", searchJPanel);
        projectTypeSearchButton = new JButton();
        projectTypeSearchButton.setText("search by project type");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        searchJPanel.add(projectTypeSearchButton, gbc);
        projectManagerSearchButton = new JButton();
        projectManagerSearchButton.setText("project manager search");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        searchJPanel.add(projectManagerSearchButton, gbc);
        costRangeSearchButton = new JButton();
        costRangeSearchButton.setBorderPainted(true);
        costRangeSearchButton.setContentAreaFilled(true);
        costRangeSearchButton.setText("cost range search");
        costRangeSearchButton.setVerticalAlignment(0);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        searchJPanel.add(costRangeSearchButton, gbc);
        projectTypeSearchJComboBox = new JComboBox();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        searchJPanel.add(projectTypeSearchJComboBox, gbc);
        minCostRangeSearchSpinner = new JSpinner(minCostRangeSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 10;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        searchJPanel.add(minCostRangeSearchSpinner, gbc);
        minCostRangeSearchLabel = new JLabel();
        minCostRangeSearchLabel.setText("min");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        searchJPanel.add(minCostRangeSearchLabel, gbc);
        maxCostRangeSearchLabel = new JLabel();
        maxCostRangeSearchLabel.setText("max");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 11;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        searchJPanel.add(maxCostRangeSearchLabel, gbc);
        projectManagerSearchTextField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        searchJPanel.add(projectManagerSearchTextField, gbc);

        //add a scroll pane
        final JScrollPane scroll = new JScrollPane (searchResultsTextArea);
        scroll.setPreferredSize(new Dimension(650, 350));
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridheight = 11;
        gbc.fill = GridBagConstraints.BOTH;
        searchJPanel.add(scroll, gbc);

        //add search results text area to the scroll pane
        searchResultsTextArea = new JTextArea();
        searchResultsTextArea.setLineWrap(false);
        searchResultsTextArea.setEditable(false);
        searchResultsTextArea.setVisible(true);
        //searchResultsTextArea.setPreferredSize(new Dimension(800, 800)); //changing dimensions of search results text area //must not use this, or it messes with JScrollPane
        scroll.setViewportView(searchResultsTextArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //original text area without scroll pane
        /*
        searchResultsTextArea = new JTextArea();
        searchResultsTextArea.setPreferredSize(new Dimension(1200, 350)); //changing dimensions of search results text area
        searchResultsTextArea.setEditable(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.gridheight = 11;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        searchJPanel.add(searchResultsTextArea, gbc);
        */

        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        searchJPanel.add(spacer8, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.VERTICAL;
        searchJPanel.add(spacer9, gbc);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        searchJPanel.add(spacer10, gbc);
        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.VERTICAL;
        searchJPanel.add(spacer11, gbc);
        final JPanel spacer12 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        searchJPanel.add(spacer12, gbc);
        final JPanel spacer13 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.VERTICAL;
        searchJPanel.add(spacer13, gbc);
        final JPanel spacer14 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        searchJPanel.add(spacer14, gbc);
        final JPanel spacer15 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 12;
        gbc.fill = GridBagConstraints.VERTICAL;
        searchJPanel.add(spacer15, gbc);
        final JPanel spacer16 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchJPanel.add(spacer16, gbc);
        final JPanel spacer17 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchJPanel.add(spacer17, gbc);
        final JPanel spacer18 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        searchJPanel.add(spacer18, gbc);
        maxCostRangeSearchSpinner = new JSpinner(maxCostRangeSpinnerModel);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 11;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        searchJPanel.add(maxCostRangeSearchSpinner, gbc);

        //setting JSpinner date editors to show date only
        SimpleDateFormat model = new SimpleDateFormat("dd.MM.yyyy");
        actualStartSpinner.setEditor(new JSpinner.DateEditor(actualStartSpinner, model.toPattern()));
        actualEndSpinner.setEditor(new JSpinner.DateEditor(actualEndSpinner, model.toPattern()));
        estimatedStartSpinner.setEditor(new JSpinner.DateEditor(estimatedStartSpinner, model.toPattern()));
    }

    private void setLAndF() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            // handle exception
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            // handle exception
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            // handle exception
            e.printStackTrace();
        }
    }

}
