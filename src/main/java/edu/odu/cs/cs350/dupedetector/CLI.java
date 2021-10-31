//CLI Parameters Class
//Will be used to find the initial directory/filepath for refactoring files
//Also will give rating to files to see whether or not they should be refactored
package edu.odu.cs.cs350.dupedetector;
import java.util.Scanner;
/**
 * @author cs_rpami001
 *
 */
public class CLI {
	public void initialPath(){
	//Getting Initial directory/filepath from end user
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Please enter the directory/filepath you want to be refactored: ");
		
		String initFilePath;
		
		initFilePath = scan.nextLine();
		
		System.out.println("Please confirm, you entered:  " + initFilePath);
			
	}
	
	public void value() {
		//Assign value to all files within the file paths
		int suggestedValues;
		
		//Go through system take the directories/filepath given and assign values
		System.out.println("After searching system we determine that this file needs " +suggestedValues);
	}
}
