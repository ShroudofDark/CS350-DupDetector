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
	
	private SuggestedRefactoring[] refactoringList;
	
	/**
	 * Creates a report object
	 * 
	 * @param providedRefactorings a list of suggested refactorings that are deep copied
	 */
	public Report(SuggestedRefactoring[] providedRefactorings) {
		
	}
	
	/**
	 * Prints the entire report for the program when called.
	 * 
	 * Wrapper for more specific print functions.
	 * 
	 * @param nSuggestions how many total suggestions should be printed in the report
	 */
	public void printReport(int nSuggestions) {
		printRefactoringReport(nSuggestions);
	}
	
	/**
	 * Organizes and prints a the refactoring suggestions part of the
	 * report.
	 * 
	 * @param nSuggestions how many total suggestions should be printed in the report
	 */
	private void printRefactoringReport(int nSuggestions) {
		
	}
	
	/**
	 * Sorts the refactorings from greatest opportunity to least opportunity
	 */
	private void sortRefactorings() {
		
	}	
}
