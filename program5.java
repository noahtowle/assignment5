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
        
    	MyBinarySearchTree reports = new ReportReader(csvFilePath).readReports();
        reports.inorder();
        
    }

}

	