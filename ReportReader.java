package accidentpack;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

//Creates all of the reports by looping through all available lines in the csv
public class ReportReader {
    private String csvFilePath;

    public ReportReader(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public ArrayList<MyBinarySearchTree> readReports() throws FileNotFoundException, ParseException {
    	ArrayList<MyBinarySearchTree> trees = new ArrayList<MyBinarySearchTree>();

        try (Scanner scanner = new Scanner(new File(csvFilePath))) {
            // Assuming the CSV file columns are in the order: Id, Severity, Start Time, End Time, Location, Temperature, Humidity, Visibility, Weather condition, Crossing, Sunrise/Sunset
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip the header line
            }
            int index = 0;
            while (scanner.hasNextLine()) {
            	// Splits lines from the CSV apart into their components
                trees = reportSetup(scanner, index, trees) ;
            }
        } 

        return trees;
    }
    // Creates and sets up individual reports.
	private ArrayList<MyBinarySearchTree> reportSetup(Scanner scanner, int index, ArrayList<MyBinarySearchTree> trees) {
		String line = scanner.nextLine();
		String[] values = line.split(",");
		String startTime = values[2];
		String county = values[6];
		String state = values[7];
		String[] timeValues = startTime.split(" ");
		String date = timeValues[0];
		
		Report report = new Report(startTime, county, state, date);
		
		boolean insertStatus = true;
		MyBinarySearchTree reports = new MyBinarySearchTree();
		// Establishes a base binary tree
		if (trees.size() == 0) {
			reports.insert(report);
			trees.add(reports);
		}
		
		//add report to Binary Tree
		for (MyBinarySearchTree stateReports : trees) {
			if (stateReports.searchState(report.getState())) {
				stateReports.insert(report);
				insertStatus = false;
				break;
			}
		}
		if(insertStatus) {
			reports.insert(report);
			trees.add(reports);
		}
		//return List of binary trees
		return trees;

	}
    

}
