import javax.swing.*;
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

    @Override
    public String toString() {
        String safetyTunnelString;
        if (safetyTunnel){
            safetyTunnelString = "a safety tunnel";
        } else {
            safetyTunnelString = "no safety tunnel";
        }

        String output;
        output = super.toString();
        output += ".\n" + widthInMetres + "m wide and " +
                lengthInMetres + "m long " +
                "tunnel excavating " + excavating +
                " with: \nground structural stability " + groundStructuralStability +
                ", a " + overlay + " overlay" +
                ", and " + safetyTunnelString;
        return output;
    }

    @Override
    public boolean isCorrect() {
        boolean isCorrect = super.isCorrect();
        if (this.overlay.equals("")){
            JOptionPane.showMessageDialog(null, "Overlay cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            isCorrect = false;
        }
        if (this.excavating.equals("")){
            JOptionPane.showMessageDialog(null, "Excavating cannot be empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            isCorrect = false;
        }
        return isCorrect;
    }

    /**getters and setters*/
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

    public String getExcavating() {
        return excavating;
    }

    public void setExcavating(String excavating) {
        this.excavating = excavating;
    }

    public boolean isSafetyTunnel() {
        return safetyTunnel;
    }

    public void setSafetyTunnel(boolean safetyTunnel) {
        this.safetyTunnel = safetyTunnel;
    }

    public int getLengthInMetres() {
        return lengthInMetres;
    }

    public void setLengthInMetres(int lengthInMetres) {
        this.lengthInMetres = lengthInMetres;
    }

    public int getGroundStructuralStability() {
        return groundStructuralStability;
    }

    public void setGroundStructuralStability(int groundStructuralStability) {
        this.groundStructuralStability = groundStructuralStability;
    }
}
