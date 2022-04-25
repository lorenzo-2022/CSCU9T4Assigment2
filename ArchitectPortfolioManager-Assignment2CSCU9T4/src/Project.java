import java.util.Calendar;

public class Project {
    /**object variables*/
    private String projectType;
    private String projectClassification;
    private int budget;
    private String projectManager;
    private String location;
    private boolean newNotRenovation;
    private String customerID;
    private int estimatedDuration;
    private Calendar estimatedStart;
    private Calendar actualStart;
    private Calendar actualEnd;
    private int accumulatedCost;

    /**object constructor*/
    public Project (String projectType, String projectClassification, int budget, String projectManager, String location, boolean newNotRenovation, String customerID, int estimatedDuration, Calendar estimatedStart, Calendar actualStart, Calendar actualEnd, int accumulatedCost){
        this.projectType = projectType;
        this.projectClassification = projectClassification;
        this.budget = budget;
        this.projectManager = projectManager;
        this.location = location;
        this.newNotRenovation = newNotRenovation;
        this.customerID = customerID;
        this.estimatedDuration = estimatedDuration;
        this.estimatedStart = estimatedStart;
        this.actualStart = actualStart;
        this.actualEnd = actualEnd;
        this.accumulatedCost = accumulatedCost;
    }

    /**object methods*/
}
