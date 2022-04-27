import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        if (this.projectType.equals(null)){
            JOptionPane.showMessageDialog(null, "Project type cannot be null.", "Warning", JOptionPane.WARNING_MESSAGE);
            isCorrect=false;
        }
        if (this.projectClassification.equals("")){
            JOptionPane.showMessageDialog(null, "Project classification cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            isCorrect=false;
        }
        if (this.projectManager.equals("")){
            JOptionPane.showMessageDialog(null, "Project manager cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            isCorrect=false;
        }
        if (this.location.equals("")){
            JOptionPane.showMessageDialog(null, "Location cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            isCorrect=false;
        }
        if (this.customerID.equals("")){
            JOptionPane.showMessageDialog(null, "Customer ID cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            isCorrect=false;
        }
        if (this.estimatedStart.after(this.actualStart)){ //Assuming estimated start cannot be after actual start seeing as all projects follow this rule.
            JOptionPane.showMessageDialog(null, "Actual start cannot be before estimated start.", "Warning", JOptionPane.WARNING_MESSAGE);
            isCorrect=false;
        }
        if (this.actualEnd.before(this.actualStart)){
            JOptionPane.showMessageDialog(null, "Actual end cannot be before actual start.", "Warning", JOptionPane.WARNING_MESSAGE);
            isCorrect=false;
        }
        return isCorrect;
    }

    @Override
    public String toString() {
        //because actual start and end dates can also not have a value it must be determined whether they are null in order to print those date values for the projects.
        String actualStartString = "";
        if (actualStart == null){
            actualStartString = "unknown";
        } else{
            actualStartString = (new SimpleDateFormat("dd.MM.yyyy").format(actualStart.getTime()));
        }
        String actualEndString = "";
        if (actualEnd == null){
            actualEndString = "unknown";
        } else {
            actualEndString = (new SimpleDateFormat("dd.MM.yyyy").format(actualEnd.getTime()));
        }

        String newOrRen;
        if (newNotRenovation){
            newOrRen = "New";
        } else {
            newOrRen = "Renovation";
        }
        return "\n" + customerID +":\n"+
                newOrRen +
                " " + projectClassification+
                " " + projectType +
                " located in " + location +
                " managed by " + projectManager + "."+
                "\nBudget of $" + budget +
                ". Accumulated cost of $" + accumulatedCost +
                ".\nEstimated start " + (new SimpleDateFormat("dd.MM.yyyy").format(estimatedStart.getTime())) +
                " and estimated duration " + estimatedDuration + " days" +
                ".\nWorks start " + actualStartString +
                " and end " + actualEndString;
    }
}
