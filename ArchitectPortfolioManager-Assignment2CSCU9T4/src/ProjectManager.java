import java.util.ArrayList;
import java.util.List;

public class ProjectManager {

    //object variables
    private List<Project> projects;

    //object constructor
    public ProjectManager(){
        //creating an array list to store instances of each project (row) in the CSV file
        projects = new ArrayList<Project>();

        //reading the CSV file
        CSVReader reader = new CSVReader();
    }

    //object methods
    public void addProject(Project project){

    }
}