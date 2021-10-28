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
		
		//Deep copy of refactorings
		refactoringList = new SuggestedRefactoring[providedRefactorings.length];
		for(int i = 0; i < providedRefactorings.length; i++) {
			refactoringList[i] = providedRefactorings[i];
		}
	}
	
	/**
	 * Prints the entire report for the program when called.
	 * 
	 * Wrapper for:
	 * printRefactoringReport
	 * 
	 * @param nSuggestions how many total suggestions should be printed in the report
	 * @param maxSubs if the refactoring exceeds this number of lexeme substituions, will not be printed
	 * @param minSeqLength if the refactoring total sequence tokens is under this number, will not be printed
	 */
	public void printReport(int nSuggestions, int maxSubs, int minSeqLength) {
		printRefactoringReport(nSuggestions, maxSubs, minSeqLength);
	}
	
	/**
	 * Organizes and prints a the refactoring suggestions part of the
	 * report.
	 * 
	 * Uses:
	 * sortRefactorings
	 * 
	 * @param nSuggestions how many total suggestions should be printed in the report
	 * @param maxSubs if the refactoring exceeds this number of lexeme substituions, will not be printed
	 * @param minSeqLength if the refactoring total sequence tokens is under this number, will not be printed
	 */
	private void printRefactoringReport(int nSuggestions, int maxSubs, int minSeqLength) {
		
	}
	
	/**
	 * Sorts the refactorings from greatest opportunity to least opportunity
	 */
	private void sortRefactorings() {
		
	}
	
	/**
	 * Get a refactored suggestion at specified location in the list.
	 * Helpful for tests.
	 */
	public SuggestedRefactoring getRefactoring(int loc) {
		return refactoringList[loc];
	}
	
	/**
	 * Get number of refactorings provided to the report class.
	 * Helpful for tests.
	 */
	public int totalRefactorings() {
		return refactoringList.length;
	}
	
	/**
	 * Compares reports to see if they are equal. They are considered
	 * equal if all functions on them return equal
	 * 
	 * @param obj object to be compared to this suggested refactoring
	 * @return true if provided object is equal to this one
	 */
	public boolean equals(Object obj) {
		Report other = (Report)obj;
		
		if(refactoringList != other.refactoringList)
			return false;
		
		return true;
	}
}
