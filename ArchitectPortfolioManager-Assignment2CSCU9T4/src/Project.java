import javax.swing.*;
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
    /**getters*/
    public String getProjectType() {
        return projectType;
    }

    public String getProjectClassification() {
        return projectClassification;
    }

    public int getBudget() {
        return budget;
    }

    public String getProjectManager() {
        return projectManager;
    }

    public String getLocation() {
        return location;
    }

    public boolean isNewNotRenovation() {
        return newNotRenovation;
    }

    public String getCustomerID() {
        return customerID;
    }

    public int getEstimatedDuration() {
        return estimatedDuration;
    }

    public Calendar getEstimatedStart() {
        return estimatedStart;
    }

    public Calendar getActualStart() {
        return actualStart;
    }

    public Calendar getActualEnd() {
        return actualEnd;
    }

    public int getAccumulatedCost() {
        return accumulatedCost;
    }
    /**setters*/
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public void setProjectClassification(String projectClassification) {
        this.projectClassification = projectClassification;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNewNotRenovation(boolean newNotRenovation) {
        this.newNotRenovation = newNotRenovation;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setEstimatedDuration(int estimatedDuration) {
        this.estimatedDuration = estimatedDuration;
    }

    public void setEstimatedStart(Calendar estimatedStart) {
        this.estimatedStart = estimatedStart;
    }

    public void setActualStart(Calendar actualStart) {
        this.actualStart = actualStart;
    }

    public void setActualEnd(Calendar actualEnd) {
        this.actualEnd = actualEnd;
    }

    public void setAccumulatedCost(int accumulatedCost) {
        this.accumulatedCost = accumulatedCost;
    }

    public boolean isCorrect() {
        boolean isCorrect = true;
        JOptionPane.showMessageDialog(null, "warning", "warning title", JOptionPane.WARNING_MESSAGE);
        return isCorrect;
    }
}
