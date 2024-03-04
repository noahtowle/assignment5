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

    public MyBinarySearchTree readReports() throws FileNotFoundException, ParseException {
    	MyBinarySearchTree reports = new MyBinarySearchTree();

        try (Scanner scanner = new Scanner(new File(csvFilePath))) {
            // Assuming the CSV file columns are in the order: Id, Severity, Start Time, End Time, Location, Temperature, Humidity, Visibility, Weather condition, Crossing, Sunrise/Sunset
            if (scanner.hasNextLine()) {
                scanner.nextLine(); // Skip the header line
            }
            System.out.println("Enter the state (e.g., IL): ");
            Scanner userInput = new Scanner(System.in);
            String targetState = userInput.nextLine();

            int index = 0;
            while (scanner.hasNextLine()) {
            	// Splits lines from the CSV apart into their components
                reports = reportSetup(reports, scanner, index, targetState);
            }
        } 

        return reports;
    }
    // Creates and sets up individual reports.
	private MyBinarySearchTree reportSetup(MyBinarySearchTree reports, Scanner scanner, int index, String targetState) {
		String line = scanner.nextLine();
		String[] values = line.split(",");
		String startTime = values[2];
		String county = values[6];
		String state = values[7];
		String[] timeValues = startTime.split(" ");
		String date = timeValues[0];
		
		Report report = new Report(startTime, county, state, date);
		//add report to ArrayList
		if (report.getState().equals(targetState)) {
			reports.insert(report);
		}
		
		//return ArrayList
		return reports;

	}
	
	// Converts report values to double
    private double toDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0; // Handle the case where the conversion fails
        }
    }
    
    // Converts report values to int
    private int toInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0; // Handle the case where the conversion fails
        }
    }
    

}
