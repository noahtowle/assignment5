package accidentpack;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class program5 {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        // C:/Users/Noah/eclipse-workspace/assignment2/src/accidents.csv
    	String csvFilePath = args[0];
        // Creates all of the binary trees and reports time
    	ArrayList<MyBinarySearchTree> trees = createBinaryTrees(csvFilePath);
        
    	//Obtains user input
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter the state(e.g. IL): ");
        String targetState = userInput.nextLine();
        System.out.println("Enter the date(e.g. 2022-09-08): ");
        String targetDate = userInput.nextLine();
        userInput.close();
         
        countMethod(trees, targetState, targetDate);
        
        recursiveMethod(trees, targetState, targetDate);
         
    }

	private static void recursiveMethod(ArrayList<MyBinarySearchTree> trees, String targetState, String targetDate) {
		long startTimer = System.currentTimeMillis();;
          for (MyBinarySearchTree stateReports : trees) {
         	 if (stateReports.searchState(targetState)) {
         		int numRecords = stateReports.numberOfRecords(targetDate);
 				long endTimer = System.currentTimeMillis();
 				long elapsedTime = (endTimer - startTimer);
 				System.out.println(numRecords + " reports are available for " + targetState + " on and after the date " + targetDate);
 				System.out.println(elapsedTime + " milliseconds to calculate this using recursive method");
 				break;
 			}
         }
	}

	private static ArrayList<MyBinarySearchTree> createBinaryTrees(String csvFilePath)
	        throws FileNotFoundException, ParseException {
		ReportReader reportReader = new ReportReader(csvFilePath);
    	 long startTimer = System.currentTimeMillis();
    	 ArrayList<MyBinarySearchTree> trees = reportReader.readReports();
    	 long endTimer = System.currentTimeMillis();
         long elapsedTime = (endTimer - startTimer) / 1000;
         System.out.println(elapsedTime + " seconds to build the binary search trees");
		return trees;
	}

	private static void countMethod(ArrayList<MyBinarySearchTree> trees, String targetState, String targetDate) {
		long startTimer;
		long endTimer;
		long elapsedTime;
		startTimer = System.currentTimeMillis();
         for (MyBinarySearchTree stateReports : trees) {
        	 if (stateReports.searchState(targetState)) {
				System.out.println(stateReports.search(targetDate) + " reports are available for " + targetState + " on and after the date " + targetDate);
				endTimer = System.currentTimeMillis();
				elapsedTime = (endTimer - startTimer);
				System.out.println(elapsedTime + " milliseconds to calculate this using children count fields");
				break;
			}
        }
	}

}

	