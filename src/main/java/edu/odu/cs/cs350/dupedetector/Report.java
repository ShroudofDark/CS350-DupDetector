package edu.odu.cs.cs350.dupedetector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 *  The report is where information is compiled together and printed out. It is
 * divided into 3 primary sections of: 
 * 
 * Source files read
 * Suggested refactorings
 * Possible number of refactorings
 * 
 * @author Jacob McFadden
 */

public class Report {	
	
	private ArrayList<SuggestedRefactoring> refactoringList;
	private ArrayList<SourceCodeFile> sourceFileList;
	
	/**
	 * Creates a report object with a list of Refactorings
	 * 
	 * @param providedSourceFiles a list of source code files that are deep copied
	 * @param providedRefactorings a list of suggested refactorings that are deep copied
	 */
	public Report(ArrayList<SourceCodeFile> providedSourceFiles, ArrayList<SuggestedRefactoring> providedRefactorings) {
		
		/**
		 * Deep copy the refactorings
		 * 
		 * https://www.javaprogramto.com/2020/04/java-arraylist-clone-deep-copy.html
		 */
		sourceFileList = new ArrayList<SourceCodeFile>();
		if(providedSourceFiles != null) {
			Iterator<SourceCodeFile> sourceIt = providedSourceFiles.iterator();
			while(sourceIt.hasNext()) {
				SourceCodeFile curr = sourceIt.next();
				SourceCodeFile newSource = 
						new SourceCodeFile(curr.getPath());
				sourceFileList.add(newSource);
			}
		}
		
		refactoringList = new ArrayList<SuggestedRefactoring>();
		if(providedRefactorings != null) {
			//Get iterator from original list
			Iterator<SuggestedRefactoring> refIt = providedRefactorings.iterator();
			//Iterate through and start adding suggestions to new list
			while (refIt.hasNext()) {
				SuggestedRefactoring curr = refIt.next();
				SuggestedRefactoring newRef 
					= new SuggestedRefactoring(curr.getTotalTokens(),curr.getOpportunity(), curr.getTotalSubs(), curr.toString());
				refactoringList.add(newRef);
			}
		}
	}
	
	/**
	 * Prints the entire report for the program when called.
	 * 
	 * Wrapper for:
	 * printSourceFileReport
	 * printRefactoringReport
	 * 
	 * @param nSuggestions how many total suggestions should be printed in the report
	 */
	public void printReport(int nSuggestions) {
		printSourceFileReport();
		printRefactoringReport(nSuggestions);
	}
	
	/**
	 * Prints the source code files for the project.
	 */
	private void printSourceFileReport() {
		
		System.out.println("Files scanned:");
		
		Iterator<SourceCodeFile> it = sourceFileList.iterator();
		
		while(it.hasNext()) {
			SourceCodeFile curr = it.next();
			System.out.println("	" + curr.getPath() + ", " + curr.getTotalTokens());
		}
		
		System.out.println();
	}
	
	/**
	 * Prints the report the by printing the refactoring suggestions and 
	 * notes how many could have been printed if more could of been printed
	 * 
	 * @param nSuggestions how many total suggestions should be printed in the report
	 */
	private void printRefactoringReport(int nSuggestions) {
		
		//Get the iterator for the ArrayList
		Iterator<SuggestedRefactoring> it = refactoringList.iterator();
		//This acts a sentinel 
		int count = 0;
		while(it.hasNext() && count < nSuggestions) {
			SuggestedRefactoring curr = it.next();
			/**
			 * Count will iterate after check, so say count is 2 nSug is 3, count is now 3 and this is third loop
			 * thus it should no longer print. Which is why we have < instead of <=.
			 */
			count++;
			
			//Print out the information about the current suggestion
			System.out.println("Opportunity " + curr.getOpportunity() + ", " + curr.getTotalTokens() + " tokens");
			System.out.print(curr.toString());
			//new line to break the suggestions apart
			System.out.println("");
		}
		
		/**
		 * count is total suggestions printed, which is going to be either nSuggestions or the list size
		 * and the list size is always going to be the possible amount of suggestions that could of been printed
		 */
		System.out.println("Printed " + count + " of " + refactoringList.size() + " suggestions.");	
	}
	
	/**
	 * Sorts the source files of the report alphabetically
	 */
	public void sortSourceFiles() {
		
	}
	
	/**
	 * Removes suggestions that don't meet criteria to be printed as defined by the parameters.
	 * 
	 * @param maxSubs if the refactoring exceeds this number of lexeme substituions, will not be printed
	 * @param minSeqLength if the refactoring total sequence tokens is under this number, will not be printed
	 */
	public void trimRefactorings(int maxSubs, int minSeqLength) {
		
		//Get the iterator for the ArrayList
		Iterator<SuggestedRefactoring> it = refactoringList.iterator();
		
		while(it.hasNext()) {
			SuggestedRefactoring curr = it.next();
			
			if(curr.getTotalSubs() > maxSubs || curr.getTotalTokens() < minSeqLength) {
				/**
				 * Found this while researching array lists and figure it would be best to implement it:
				 * 
				 * https://www.java67.com/2018/12/how-to-remove-objects-or-elements-while-iterating-Arraylist-java.html
				 */
				it.remove();
			}
		}
	}
	
	/**
	 * Sorts the refactorings from greatest opportunity to least opportunity
	 */
	public void sortRefactorings() {	
		//Don't bother if the list size is 0 or 1, it is already technically sorted
		if(!(refactoringList.size() == 0 || refactoringList.size() == 1)) {
			/**
			 * Below is a example of a lambda way of making a comparator without making an entire class
			 * for the function. Also includes helpful link about lambdas in Java.
			 * 
			 * https://howtodoinjava.com/java-sorting-guide/
			 * https://howtodoinjava.com/java8/lambda-expressions/
			 * 
			 * Also had assistance from:
			 * https://www.baeldung.com/java-comparator-comparable
			 */
			Comparator<SuggestedRefactoring> refactorSorter 
				= (firstRef, secondRef) -> Integer.compare(firstRef.getOpportunity(), secondRef.getOpportunity());
			
			//Because the above comparator uses default Integer compare, it will sort lowest to go greatest, so we reverse
			Collections.sort(refactoringList, refactorSorter.reversed());
		}
	}
	
	/**
	 * Get a source code file from specified location in list.
	 * 
	 * @param loc specified part of list
	 * @return a SourceCodeFile
	 */
	public SourceCodeFile getSourceCodeFile(int loc) {
		return sourceFileList.get(loc);
	}
	/**
	 * Get number of source code files provided by the report class.
	 * 
	 * @return size of source code file list as int
	 */
	public int totalSourceCodeFiles( ) {
		return sourceFileList.size();
	}
	
	/**
	 * Get a refactored suggestion at specified location in the list.
	 * 
	 * @param loc specified part of list
	 * @return a SuggestedRefactoring
	 */
	public SuggestedRefactoring getRefactoring(int loc) {
		return refactoringList.get(loc);
	}
	
	/**
	 * Get number of refactorings provided to the report class.
	 * 
	 * @return size of refactoring list as int
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
		
		//!= does not work here as found out in tests, need to use .equals
		if(!(sourceFileList.equals(other.sourceFileList))) {
			return false;
		}
		if(!(refactoringList.equals(other.refactoringList))) {
			return false;
		}
		
		return true;
	}
}
