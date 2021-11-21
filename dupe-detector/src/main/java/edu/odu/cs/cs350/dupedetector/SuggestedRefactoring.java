package edu.odu.cs.cs350.dupedetector;

/**
 * Stub class to generate information for the Report class
 * 
 * Information revolves around what is expected that the Report class
 * will be able to use until actual functionality is made.
 * 
 * @author Jacob McFadden
 */
public class SuggestedRefactoring {

	//Value that a suggested refactoring uses to determine its rank among other refactorings
	private int opportunityAmount;
	//Determines the total amount of tokens found in this suggestion
	private int totalTokenAmount;
	//Determines total number of lexemes that will be subbed out
	private int totalSubstitutions;
	//Stand in variable that represents compiled data
	private String refactorSuggestion;
	
	/**
	 * Constructor for a stub SuggestedRefactoring
	 * 
	 * @param totalTokens to set token amount
	 * @param opportunity to set opportunity
	 * @param totalSubs to set fake data
	 * @param data a string that is returned by toString(). This is a stub.
	 */
	public SuggestedRefactoring(int totalTokens, int opportunity, int totalSubs, String data) {
		totalTokenAmount = totalTokens;
		opportunityAmount = opportunity;
		totalSubstitutions = totalSubs;
		refactorSuggestion = data;
	}
	
	//Returns the opportunity
	public int getOpportunity() {
		return opportunityAmount;
	}
	
	//Returns the total amount of tokens associated with refactoring
	public int getTotalTokens() {
		return totalTokenAmount;
	}
	
	//Returns total amount of substitutions associated with refactoring
	public int getTotalSubs() {
		return totalSubstitutions;
	}
	
	//Returns the string of the compiled data
	public String toString() {
		return refactorSuggestion;
	}
	
	/**
	 * Compares suggested refactorings to see if they are equal. They are considered
	 * equal if all functions on them return equal
	 * 
	 * @param obj object to be compared to this suggested refactoring
	 * @return true if provided object is equal to this one
	 */
	public boolean equals(Object obj) {
		
		SuggestedRefactoring other = (SuggestedRefactoring)obj;
		
		if(totalTokenAmount != other.totalTokenAmount)
			return false;
		if(opportunityAmount != other.opportunityAmount)
			return false;
		if(refactorSuggestion != other.refactorSuggestion)
			return false;
		
		return true;
	}
}
