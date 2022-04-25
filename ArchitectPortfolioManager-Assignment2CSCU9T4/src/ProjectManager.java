import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectManager {

    /**object variables*/
    //for reading the CSV file
    private String inputFileName;
    private String contents = "";

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
        parse();
    }

    //reads the CSV file into a String called contents
    private void read() {
        try {
            BufferedReader inputStreamReader = new BufferedReader(new FileReader(inputFileName));
            String dataLine;  // To receive each line from the file

            lineCount = 0;
            while ((dataLine = inputStreamReader.readLine()) != null) {  // if the next line in the file is not empty, get next line from file
                contents += dataLine+"\n";
            }

            inputStreamReader.close();                               // File finished reading, so close file
        }
        catch (IOException ex) {
            System.out.println("File reading error");    // File handling error: read no further
        }
    }

    //parses the CSV file contents into its files and constructs appropriate java objects from each row, i.e. project
    private void parse() {
        String[] projectStrings = contents.split("\n"); //each project is one line so this array's elements are each one line, i.e. project string
        int numOfProjects = projectStrings.length;

        //ArrayList<String> parsedProjects = new ArrayList();

        //cycling through each projectString
        for (int cycle = 0; cycle < numOfProjects; cycle++){
            if (cycle == 0){continue;} //skip first project string line because it contains headings rather than a project.
            String projectString = projectStrings[cycle];
            //going through each comma seperated value on each line
            String [] projectInformation = projectString.split(",");
            System.out.println(projectInformation.length); //it should be length 29 but it says it's length is 20 for some reason?!
            String projectTypeAndClassificationString = projectInformation[0];
            String[] projectTypeAndClassification = projectTypeAndClassificationString.split(";");
            String projectType = projectTypeAndClassification[0];
            String classification = projectTypeAndClassification[1];
            System.out.println(classification); //i need to remove first char which is a space for the classification field
            String type = projectInformation[1];
            String size = projectInformation[2];
            String bedrooms = projectInformation[3];
            String landSize = projectInformation[4];
            String bathrooms = projectInformation[5];
            String floodingRisk = projectInformation[6];
            String garage = projectInformation[7];
            String buildingMaterial = projectInformation[8];
            String roof = projectInformation[9];
            String budget = projectInformation[10];
            String projectManager = projectInformation[11];
            String location = projectInformation[12];
            String newNotRenovation = projectInformation[13];
            String customerID = projectInformation[14];
            String estimatedDuration = projectInformation[15];
            String estimatedStart = projectInformation[16];
            String actualStart = projectInformation[17];
            String actualEnd = projectInformation[18];
            String accumulatedCost = projectInformation[19];
            String excavating = projectInformation[20];
            String width = projectInformation[21];
            String overlay = projectInformation[22];
            String safetyTunnel = projectInformation[23];
            String span = projectInformation[24];
            String currentComposition = projectInformation[25];
            String length = projectInformation[26];
            String natureReserve = projectInformation[27];
            String groundStructuralStability = projectInformation[28];

            //constructing the object and adding it to the projects array list
            Project project = new Project();
            addProject(project);
        }

    }

    private void addProject(Project project) {
    }
}