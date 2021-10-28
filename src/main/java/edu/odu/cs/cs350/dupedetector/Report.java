package edu.odu.cs.cs350.dupedetector;

/**
 *  The report is where information is compiled together and printed out. It is
 * divided into 3 primary sections of: 
 * 
 * Source files read
 * Suggested refactorings
 * Possible number of refactorings
 * 
 * @author Jacob McFadden: created the suggested refactorings output
 */

public class Report {

	/**
	 * Creates a report object
	 * 
	 * @param exampleNumber
	 */
	public Report() {
		
	}
	
	/**
	 * Prints the entire report for the program when called.
	 * 
	 * Wrapper for more specific print functions.
	 */
	public void printReport() {
		printRefactoringReport();
	}
	
	/**
	 * Organizes and prints a the refactoring suggestions part of the
	 * report.
	 */
	private void printRefactoringReport() {
		
	}
	
	/**
	 * Sorts the refactorings from greatest opportunity to least opportunity
	 */
	private void sortRefactorings() {
		
	}	
}