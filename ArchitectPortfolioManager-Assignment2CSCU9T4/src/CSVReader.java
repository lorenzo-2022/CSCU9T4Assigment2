import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
    //object variables
    private String inputFileName;
    private String contents = "";
    private String parsedContents;
    private String output;

    //constructor
    public CSVReader() {
        inputFileName = ("jenga_projects.csv");
        read();
        System.out.println(contents+"\n");
        parse();
        System.out.println(parsedContents);
    }

    /** object methods */

    //reads the CSV file into a String
    public void read() {
        try {
            BufferedReader inputStreamReader = new BufferedReader(new FileReader(inputFileName));
            String dataLine;  // To receive each line from the file

            int lineCount = 0;
            while ((dataLine = inputStreamReader.readLine()) != null) {  // if the next line in the file is not empty, get next line from file

                contents += dataLine;
/*
                // Find the position of the *first* tab for splitting the line
                int tabPos1 = dataLine.indexOf(',');

                int tabPos2 = dataLine.indexOf(',',tabPos1+1);

                int tabPos3 = dataLine.indexOf(',',tabPos2+1);

                int tabPos4 = dataLine.indexOf(',', tabPos3+1);

                // Check, in case no tab was found (indexOf returns -1)
                if (tabPos1 < 0) {
                    System.out.println("No tab in data line");
                    break;                                  // Bad data: read no more!
                }

                // Split the line: extract the parts of the string up to the comma,
                // and from after the tab to the end of the string
                String artifactIDPart = dataLine.substring(0, tabPos1);
                String artifactNamePart = dataLine.substring(tabPos1+1, tabPos2);
                //adding the image part
                String imagePart = dataLine.substring(tabPos2+1, tabPos3);
                //adding the floor part
                String floorPart = dataLine.substring(tabPos3+1, tabPos4);
                //adding the room part
                String roomPart = dataLine.substring(tabPos4+1);

                // Convert artifactIDPart to a proper int for storing
                int artifactID = 0;   // To hold the converted number
                try {
                    artifactID = Integer.parseInt(artifactIDPart);      // Convert
                }
                catch (NumberFormatException ex) {
                    System.out.println("Bad data in number");
                    break;                                 // Bad data: read no further!
                }

                //convert floorPart to a proper int for storing
                int floor = 0;
                try
                {
                    floor = Integer.parseInt(floorPart);
                }
                catch (NumberFormatException ex)
                {
                    System.out.println("Bad data in number");
                    break;                                 // Bad data: read no further!
                }

                // We now have the text and number parts,
                // so store the data obtained as next entry in the arrays
                addEntry(artifactID, artifactNamePart, imagePart, floor, roomPart);

                //we also have to add the image file names to their array, and the
                //artifactImageFileNames[counter] = imagePart;
                //counter++;
                */

            }

            inputStreamReader.close();                               // File finished, arrays full or bad data, so close file
        }
        catch (IOException ex) {
            System.out.println("File reading error");    // File handling error: read no further
        }
    }

    //parses the CSV file String
    private void parse() {
        String[] projectStrings = contents.split("\n"); //each project is one line so this array's elements are each one line, i.e. project string
        int numOfProjects = projectStrings.length;

        ArrayList<String> parsedProjects = new ArrayList();

        //cycling through each projectString
        for (int cycle = 0; cycle < numOfProjects; cycle++){
            if (cycle == 0){continue;} //skip first project because it contains headings.
            String projectString = projectStrings[cycle];
            //going through each comma seperated value on each line
            String [] projectInformation = projectString.split(",");
            String projectType = projectInformation[0];
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
        }


    }
}
