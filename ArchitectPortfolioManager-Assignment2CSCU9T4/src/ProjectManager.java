import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;

public class ProjectManager {

    /**object variables*/
    //for reading the CSV file
    private String inputFileName;

    //creating an array list to store instances of each project (row) in the CSV file
    private List<Project> projects;

    /**object constructor*/
    public ProjectManager(){
        projects = new ArrayList<Project>(); //must initialise array list before the read() method
        readCSV();
    }

    /**object methods*/

    private void readCSV() {
        inputFileName = ("jenga_projects.csv");
        read();
    }

    private void read() {
        try {
            BufferedReader inputStreamReader = new BufferedReader(new FileReader(inputFileName));
            String dataLine;  // To receive each line from the file

            for (int lineCount = 0; ((dataLine = inputStreamReader.readLine()) != null); lineCount++) {
                //skipping the first line because it contains column headings
                if (lineCount == 0) {
                    continue;
                }

                //each line is a project
                String projectString = dataLine;

                //splitting each line around commas to allow us to find each comma seperated value
                String[] projectInformation = projectString.split(",", Integer.MAX_VALUE); //providing a limit value as large as possible because otherwiseThe one-argument split method is specified to ignore trailing empty string splits but the version that takes a "limit" argument preserves them

                //going through each comma seperated value

                //these fields exist for all project types, keep in mind that actual start and actual end fields can be blank
                String projectTypeAndClassificationString = projectInformation[0];
                String[] projectTypeAndClassification = projectTypeAndClassificationString.split(";");
                String projectType = projectTypeAndClassification[0];
                String typeClassification = projectTypeAndClassification[1].trim(); //.trim() method removes empty spaces surrounding string
                String budgetString = projectInformation[10];
                int budget = Integer.parseInt(budgetString);
                String projectManager = projectInformation[11];
                String location = projectInformation[12];
                String newNotRenovationString = projectInformation[13];
                boolean newNotRenovation = Boolean.parseBoolean(newNotRenovationString);
                String customerID = projectInformation[14];
                String estimatedDurationString = projectInformation[15];
                int estimatedDuration = Integer.parseInt(estimatedDurationString);
                String estimatedStartString = projectInformation[16];//convert to Calendar object
                Calendar estimatedStart = stringToCalendarDate(estimatedStartString);
                String actualStartString = projectInformation[17];//convert to Calendar object
                Calendar actualStart = stringToCalendarDate(actualStartString);
                String actualEndString = projectInformation[18];//convert to Calendar object
                Calendar actualEnd = stringToCalendarDate(actualEndString);
                String accumulatedCostString = projectInformation[19];
                int accumulatedCost = Integer.parseInt(accumulatedCostString);

                //type is for house and bridge projects
                String type = projectInformation[1];
                String floodingRiskString = projectInformation[6];
                int floodingRisk = stringToInt(floodingRiskString);
                String buildingMaterial = projectInformation[8];

                //size is for house and land projects but with different units of measurement, square metres for house, and acres for land
                String sizeString = projectInformation[2];
                int size = stringToInt(sizeString);

                //these fields are for house projects only
                String bedroomsString = projectInformation[3];
                int bedrooms = stringToInt(bedroomsString);
                String landSizeString = projectInformation[4];
                float landSize = stringToFloat(landSizeString);
                String bathroomsString = projectInformation[5];
                int bathrooms = stringToInt(bathroomsString);
                String garageString = projectInformation[7];
                boolean garage = Boolean.parseBoolean(garageString);
                String roof = projectInformation[9];

                //width and overlay is for bridge and tunnel
                String widthString = projectInformation[21];
                int width = stringToInt(widthString);
                String overlay = projectInformation[22];

                //excavating is for tunnel
                String excavating = projectInformation[20];
                String safetyTunnelString = projectInformation[23];
                boolean safetyTunnel = Boolean.parseBoolean(safetyTunnelString);
                String lengthString = projectInformation[26];
                int length = stringToInt(lengthString);
                String groundStructuralStabilityString = projectInformation[28];
                int groundStructuralStability = stringToInt(groundStructuralStabilityString);

                //span is for bridge
                String spanString = projectInformation[24];
                int span = stringToInt(spanString);

                //current composition is for land
                String currentComposition = projectInformation[25];
                String natureReserveString = projectInformation[27];
                boolean natureReserve = Boolean.parseBoolean(natureReserveString);

                //constructing the object and adding it to the projects array list
                if (projectType.equals("House")){
                    //create and add house object to Project Manager array list
                    Project project = new House(projectType, typeClassification, budget, projectManager, location, newNotRenovation,
                            customerID, estimatedDuration, estimatedStart, actualStart, actualEnd, accumulatedCost,
                            type, floodingRisk, buildingMaterial, size, bedrooms, landSize, bathrooms, garage, roof);
                    addProject(project);
                } else if (projectType.equals("Bridge")){
                    //create and add bridge object to Project Manager array list
                    Project project = new Bridge(projectType, typeClassification, budget, projectManager, location, newNotRenovation, customerID, estimatedDuration, estimatedStart, actualStart, actualEnd, accumulatedCost,
                            type, floodingRisk, buildingMaterial, width, overlay, span);
                    addProject(project);
                } else if (projectType.equals("Tunnel")){
                    //create and add tunnel object to Project Manager array list
                    Project project = new Tunnel(projectType, typeClassification, budget, projectManager, location, newNotRenovation, customerID, estimatedDuration, estimatedStart, actualStart, actualEnd, accumulatedCost,
                            width, overlay, excavating, safetyTunnel, length, groundStructuralStability);
                    addProject(project);
                } else if (projectType.equals("Land")){
                    //create and add land object to Project Manager arrayList
                    Project project = new Land(projectType, typeClassification, budget, projectManager, location, newNotRenovation, customerID, estimatedDuration, estimatedStart, actualStart, actualEnd, accumulatedCost,
                            size, currentComposition, natureReserve);
                    addProject(project);
                }
            }

            inputStreamReader.close();                               // File finished reading, so close file
        }
        catch (IOException ex) {
            System.out.println("File reading error");    // File handling error: read no further
        }
    }

    private int stringToInt(String string) {
        if (string.equals("")){
            return 0;
        }
        else {
            String numberOnlyString = string.replaceAll("[^0-9]", ""); //replacing all the parts of the sizeString that aren't numbers with nothing, i.e. removing those parts!
            int numberOnly = Integer.parseInt(numberOnlyString);
            return numberOnly;
        }
    }

    private float stringToFloat(String string) {
        if (string.equals("")){
            return 0;
        } else{
            String numberOnlyString = string.replaceAll("[^\\d.]", ""); //replacing all the parts of the sizeString that aren't numbers or a dot (decimal dot) with nothing, i.e. removing those parts!
            float floatOnly = Float.parseFloat(numberOnlyString);
            return floatOnly;
        }
    }

    //converts a string containing a date in format d/m/y to a Calendar object
    private Calendar stringToCalendarDate(String dmy){
        if (dmy.equals("")){
            return null;
        }
        String[] calArray = dmy.split("-"); //forward slashes / are read as dashed -
        int day = Integer.parseInt(calArray[0]);
        int month = Integer.parseInt(calArray[1]);
        int year = Integer.parseInt(calArray[2]);
        Calendar calendar = (Calendar.getInstance());
        calendar.set(year, month, day);
        return calendar;
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    /**getters and setters*/
    public String getInputFileName() {
        return inputFileName;
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public boolean isUnique(Project project) {
        //checks if a project customer ID property value is unique or not
        boolean isUnique = true;

        ListIterator<Project> projectListIterator = projects.listIterator();

        while (projectListIterator.hasNext()){
            Project currentProject = projectListIterator.next();

            if (currentProject.getCustomerID().equals(project.getCustomerID())) {
                isUnique = false;
            }
        }
        return isUnique;
    }
}