import java.util.Calendar;

public class Land extends Project{
    /**object variables*/
    private int size_acres;
    private String currentComposition;
    private boolean natureReserve;

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
    public Land(String projectType, String projectClassification, int budget, String projectManager, String location, boolean newNotRenovation, String customerID, int estimatedDuration, Calendar estimatedStart, Calendar actualStart, Calendar actualEnd, int accumulatedCost,
                int size_acres, String currentComposition, boolean natureReserve) {
        super(projectType, projectClassification, budget, projectManager, location, newNotRenovation, customerID, estimatedDuration, estimatedStart, actualStart, actualEnd, accumulatedCost);
        this.size_acres = size_acres;
        this.currentComposition = currentComposition;
        this.natureReserve = natureReserve;
    }

    /**object methods*/
}
