import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectManager {

    /**object variables*/
    //for reading the CSV file
    private String inputFileName;

    //creating an array list to store instances of each project (row) in the CSV file
    private List<Project> projects;

    /**object constructor*/
    public ProjectManager(){
        readCSV();
        projects = new ArrayList<Project>();
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
                String newNotRenovation = projectInformation[13];
                String customerID = projectInformation[14];
                String estimatedDuration = projectInformation[15];
                String estimatedStart = projectInformation[16];
                String actualStart = projectInformation[17];
                String actualEnd = projectInformation[18];
                String accumulatedCost = projectInformation[19];

                //type is for house and bridge projects
                String type = projectInformation[1];
                String floodingRisk = projectInformation[6];
                String buildingMaterial = projectInformation[8];

                //size is for house and land projects but with different units of measurement
                String size = projectInformation[2];

                //these fields are for house projects only
                String bedrooms = projectInformation[3];
                String landSize = projectInformation[4];
                String bathrooms = projectInformation[5];
                String garage = projectInformation[7];
                String roof = projectInformation[9];

                //width and overlay is for bridge and tunnel
                String width = projectInformation[21];
                String overlay = projectInformation[22];

                //excavating is for tunnel
                String excavating = projectInformation[20];
                String safetyTunnel = projectInformation[23];
                String length = projectInformation[26];
                String groundStructuralStability = projectInformation[28];

                //span is for bridge
                String span = projectInformation[24];

                //current composition is for land
                String currentComposition = projectInformation[25];
                String natureReserve = projectInformation[27];

                //constructing the object and adding it to the projects array list
                if (projectType.equals("House")){
                    //create and add house object to Project Manager array list
                    Project project = new House(, , , , , , , , , , , , , , , , , , , , );
                } else if (projectType.equals("Bridge")){
                    //create and add bridge object to Project Manager array list
                } else if (projectType.equals("Tunnel")){
                    //create and add tunnel object to Project Manager array list
                } else if (projectType.equals("Land")){
                    //create and add land object to Project Manager arrayList
                }

            }

            inputStreamReader.close();                               // File finished reading, so close file
        }
        catch (IOException ex) {
            System.out.println("File reading error");    // File handling error: read no further
        }
    }

    private void addProject(Project project) {
        projects.add(project);
    }
}