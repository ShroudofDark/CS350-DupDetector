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
