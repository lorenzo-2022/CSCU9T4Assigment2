import javax.swing.*;
import java.util.Calendar;

public class House extends Project{
    /**object variables*/
    private String type;
    private int floodingRisk;
    private String buildingMaterial;
    private int size_m2;
    private int bedrooms;
    private float landSizeAcres;
    private int bathrooms;
    private boolean garage;
    private String roof;

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
    public House(String projectType, String projectClassification, int budget, String projectManager, String location, boolean newNotRenovation, String customerID, int estimatedDuration, Calendar estimatedStart, Calendar actualStart, Calendar actualEnd, int accumulatedCost, String type,
                 int floodingRisk, String buildingMaterial, int size_m2, int bedrooms, float landSizeAcres, int bathrooms, boolean garage, String roof) {
        super(projectType, projectClassification, budget, projectManager, location, newNotRenovation, customerID, estimatedDuration, estimatedStart, actualStart, actualEnd, accumulatedCost);
        this.type = type;
        this.floodingRisk = floodingRisk;
        this.buildingMaterial = buildingMaterial;
        this.size_m2 = size_m2;
        this.bedrooms = bedrooms;
        this.landSizeAcres = landSizeAcres;
        this.bathrooms = bathrooms;
        this.garage = garage;
        this.roof = roof;
    }

    /**object methods*/

    @Override
    public boolean isCorrect() {
        boolean isCorrect = super.isCorrect();
        if (this.type.equals("")){
            JOptionPane.showMessageDialog(null, "House type cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            isCorrect = false;
        }
        if (this.buildingMaterial.equals("")){
            JOptionPane.showMessageDialog(null, "Building Materials cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            isCorrect = false;
        }
        //allow building size to be zero to allow flexibility to the architecture firm
        if (this.roof.equals("")){
            JOptionPane.showMessageDialog(null, "Roof cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
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

    public int getSize_m2() {
        return size_m2;
    }

    public void setSize_m2(int size_m2) {
        this.size_m2 = size_m2;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public float getLandSizeAcres() {
        return landSizeAcres;
    }

    public void setLandSizeAcres(float landSizeAcres) {
        this.landSizeAcres = landSizeAcres;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public boolean isGarage() {
        return garage;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    public String getRoof() {
        return roof;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }
}
