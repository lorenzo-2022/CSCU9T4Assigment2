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
}
