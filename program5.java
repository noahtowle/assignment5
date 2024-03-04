package accidentpack;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class program5 {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        // C:/Users/Noah/eclipse-workspace/assignment2/src/accidents.csv
    	String csvFilePath = args[0];
        
        //printFile(reports);
        
    }

	
	private static void printFile(ArrayList<Report> reports) {
        for (Report report : reports) {
    		System.out.println("Start Time: " + report.getStart_time());
    		System.out.println("Date: " + report.getDate());
    		System.out.println("County: " + report.getCounty());
    		System.out.println("State: " + report.getState());
    		System.out.println("--------------------------------");
        }
	}


		
	
}

	