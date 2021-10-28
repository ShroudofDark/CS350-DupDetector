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

/**
 * Jacob -> I'm leaving a comment here to track my thoughts about a subject to be addressed for how to handle
 * some of the report functions. Should functions like trimRefactorings and sortRefactorings be public methods
 * that are called within the main class while the printReport functions only focus on printing the output. Also
 * considered making the PrintReport like toString methods, though there is concern that it would make a singular
 * string that is too large instead of printing out multiple strings.
 */
import java.util.*;

public class Report implements Comparator<SuggestedRefactoring> {	
	
	private ArrayList<SuggestedRefactoring> refactoringList;
	
	/**
	 * Creates a report object with a list of Refactorings
	 * 
	 * @param providedRefactorings a list of suggested refactorings that are deep copied
	 */
	public Report(ArrayList<SuggestedRefactoring> providedRefactorings) {
		
		/**
		 * Deep copy the refactorings
		 * 
		 * https://www.javaprogramto.com/2020/04/java-arraylist-clone-deep-copy.html
		 */
		refactoringList = new ArrayList<>();
		//Get iterator from original list
		Iterator<SuggestedRefactoring> it = providedRefactorings.iterator();
		//Iterate through and start adding suggestions to new list
		while (it.hasNext()) {
			SuggestedRefactoring curr = it.next();
			SuggestedRefactoring newRef 
				= new SuggestedRefactoring(curr.getTotalTokens(),curr.getOpportunity(), curr.getTotalSubs(), curr.toString());
			refactoringList.add(newRef);
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
	 * Prepares the suggested refactorings by removing suggestions that don't meet criteria to be printed 
	 * defined by the parameters and sorting the list in descending order by opportunity. Once done it outputs
	 * the report.
	 * 
	 * Uses:
	 * trimReport
	 * sortRefactorings
	 * 
	 * @param nSuggestions how many total suggestions should be printed in the report
	 * @param maxSubs if the refactoring exceeds this number of lexeme substituions, will not be printed
	 * @param minSeqLength if the refactoring total sequence tokens is under this number, will not be printed
	 */
	private void printRefactoringReport(int nSuggestions, int maxSubs, int minSeqLength) {
		
	}
	
	/**
	 * Removes suggestions that don't meet criteria to be printed as defined by the parameters.
	 * 
	 * @param maxSubs if the refactoring exceeds this number of lexeme substituions, will not be printed
	 * @param minSeqLength if the refactoring total sequence tokens is under this number, will not be printed
	 */
	private void trimRefactorings(int maxSubs, int minSeqLength) {
		
	}
	
	/**
	 * Sorts the refactorings from greatest opportunity to least opportunity
	 */
	private void sortRefactorings() {	
		//Don't bother if the list size is 0 or 1, it is already technically sorted
		if(refactoringList.size() == 0 || refactoringList.size() == 1) {
			
		}
	}
	
	/**
	 * Get a refactored suggestion at specified location in the list.
	 * Helpful for tests.
	 */
	public SuggestedRefactoring getRefactoring(int loc) {
		return refactoringList.get(loc);
	}
	
	/**
	 * Get number of refactorings provided to the report class.
	 * Helpful for tests.
	 */
	public int totalRefactorings() {
		return refactoringList.size();
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
	
	/**
	 * Compares suggested refactorings by opportunity for sorting
	 * 
	 * https://www.baeldung.com/java-comparator-comparable code assisted by this article
	 */
	@Override
	public int compare(SuggestedRefactoring firstRef, SuggestedRefactoring secondRef) {
		return Integer.compare(firstRef.getOpportunity(), secondRef.getOpportunity());
	}
}
