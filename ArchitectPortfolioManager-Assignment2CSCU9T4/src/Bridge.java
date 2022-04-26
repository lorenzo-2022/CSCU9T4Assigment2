import javax.swing.*;
import java.util.Calendar;

public class Bridge extends Project{
    /**object variables*/
    private String type;
    private int floodingRisk;
    private String buildingMaterial;
    private int widthInMetres;
    private String overlay;
    private int spanInMetres;

    /**
     * object constructor
     *
     * @param projectType
     * @param projectClassification
     * @param budget
     * @param projectManager
     * @param location
     * @param newNotRenovation
     * @param customerID
     * @param estimatedDuration
     * @param estimatedStart
     * @param actualStart
     * @param actualEnd
     * @param accumulatedCost
     */
    public Bridge(String projectType, String projectClassification, int budget, String projectManager, String location, boolean newNotRenovation, String customerID, int estimatedDuration, Calendar estimatedStart, Calendar actualStart, Calendar actualEnd, int accumulatedCost,
                  String type, int floodingRisk, String buildingMaterial, int widthInMetres, String overlay, int spanInMetres) {
        super(projectType, projectClassification, budget, projectManager, location, newNotRenovation, customerID, estimatedDuration, estimatedStart, actualStart, actualEnd, accumulatedCost);
        this.type = type;
        this.floodingRisk = floodingRisk;
        this.buildingMaterial = buildingMaterial;
        this.widthInMetres = widthInMetres;
        this.overlay = overlay;
        this.spanInMetres = spanInMetres;
    }

    /**object methods*/

    @Override
    public boolean isCorrect() {
        boolean isCorrect = super.isCorrect();
        if (this.type.equals("")){
            JOptionPane.showMessageDialog(null, "Bridge type cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            isCorrect = false;
        }
        if (this.buildingMaterial.equals("")){
            JOptionPane.showMessageDialog(null, "Building Materials cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            isCorrect = false;
        }
        if (this.overlay.equals("")){
            JOptionPane.showMessageDialog(null, "Overlay cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            isCorrect = false;
        }
        return isCorrect;
    }

    /**getters and setters*/
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFloodingRisk() {
        return floodingRisk;
    }

    public void setFloodingRisk(int floodingRisk) {
        this.floodingRisk = floodingRisk;
    }

    public String getBuildingMaterial() {
        return buildingMaterial;
    }

    public void setBuildingMaterial(String buildingMaterial) {
        this.buildingMaterial = buildingMaterial;
    }

    public int getWidthInMetres() {
        return widthInMetres;
    }

    public void setWidthInMetres(int widthInMetres) {
        this.widthInMetres = widthInMetres;
    }

    public String getOverlay() {
        return overlay;
    }

    public void setOverlay(String overlay) {
        this.overlay = overlay;
    }

    public int getSpanInMetres() {
        return spanInMetres;
    }

    public void setSpanInMetres(int spanInMetres) {
        this.spanInMetres = spanInMetres;
    }
}
