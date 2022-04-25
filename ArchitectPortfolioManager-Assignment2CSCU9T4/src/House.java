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
}
