import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    public GUI() {
        projectTypeJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        addProjectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        ProjectManager PM = new ProjectManager();
        JFrame frame = new JFrame("Architect Portfolio");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel panel1;
    private JButton addProjectButton;
    private JTabbedPane tabbedPane1;
    private JComboBox projectTypeJComboBox;
    private JLabel classificationLabel;
    private JTextField classificationTextField;
    private JButton editProjectButton;
    private JTextArea textArea;
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
    private JSlider estimatedDurationSlider;
    private JSpinner accumulatedCostSpinner;
    private JSpinner budgerSpinner;
    private JSpinner actualStartDaySpinner;
    private JSpinner actualStartMonthSpinner;
    private JSpinner actualStartYearSpinner;
    private JSpinner actualEndDaySpinner;
    private JSpinner estimatedStartDaySpinner;
    private JSpinner estimatedStartMonthSpinner;
    private JSpinner actualEndMonthSpinner;
    private JSpinner estimatedStartYearSpinner;
    private JSpinner actualEndYearSpinner;
    private JTextField typeTextField;
    private JSlider floodingRiskSlider;
    private JTextField buildingMaterialTextField;
    private JSpinner sizeSpinner;
    private JSpinner bedroomsSpinner;
    private JSpinner bathroomsSpinner;
    private JSpinner landSizeAcresSpinner;
    private JCheckBox garageCheckBox;
    private JTextField roofTextField;
    private JSpinner spinner16;
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
    private JLabel untiOfSizeLabel;
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
