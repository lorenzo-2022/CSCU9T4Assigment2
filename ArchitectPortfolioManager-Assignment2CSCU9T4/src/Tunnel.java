import java.util.Calendar;

public class Tunnel extends Project{
    /**object variables*/
    private int widthInMetres;
    private String overlay;
    private String excavating;
    private boolean safetyTunnel;
    private int lengthInMetres;
    private int groundStructuralStability;

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
    public Tunnel(String projectType, String projectClassification, int budget, String projectManager, String location, boolean newNotRenovation, String customerID, int estimatedDuration, Calendar estimatedStart, Calendar actualStart, Calendar actualEnd, int accumulatedCost,
                  int widthInMetres, String overlay, String excavating, boolean safetyTunnel, int lengthInMetres, int groundStructuralStability) {
        super(projectType, projectClassification, budget, projectManager, location, newNotRenovation, customerID, estimatedDuration, estimatedStart, actualStart, actualEnd, accumulatedCost);
        this.widthInMetres = widthInMetres;
        this.overlay = overlay;
        this.excavating = excavating;
        this.safetyTunnel = safetyTunnel;
        this.lengthInMetres = lengthInMetres;
        this.groundStructuralStability = groundStructuralStability;
    }


    /**object methods*/
}
