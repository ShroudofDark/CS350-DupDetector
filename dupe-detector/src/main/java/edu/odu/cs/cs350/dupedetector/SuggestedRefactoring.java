package edu.odu.cs.cs350.dupedetector;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Represents a Suggestion within DupeDetector, and provides a means of calculating
 * the Opportunity for Improvement as described in Zeil's requirements doc.
 * 
 * This class aggregates the duplicate token sequences found in the `SuppliedCode`,
 * by the property that aggregated token sequences are duplicate (TODO: also "nearly duplicate").
 * 
 * The class provides a toString() method suitible for use in the DupeDetector
 * Suggested Refactorings report.
 * 
 * @see SuppliedCode
 * @see Report
 * 
 * @author Jacob McFadden
 * @author John Hicks
 */
public class SuggestedRefactoring {

	/**
	 * Give the total opportunity for improvement for a duplicate token sequence, given
	 * that a function definition and function calls will replace the duplicate
	 * sequence.
	 * 
	 * This is the value that a suggested refactoring uses to determine its rank
	 * among otherrefactorings.
	 * 
	 * The calculation ignores the "overhead" of function calls per Zeil's requirements
	 * document.
	 * 
	 * Precondition: both numberOfSequences and sequenceLength are both positive integers or zero.
	 * @param sequenceLength Pre: >= 0
	 * @param numberOfSequences Pre: >= 0
	 * @return the opportunity for improvement
	 */
	public static int calcOpportunityForImprovement(int numberOfSequences, int sequenceLength) {
		return (numberOfSequences - 1) * sequenceLength;
	}

	/**
	 * This is the  constructor, where opportunity for improvement = 0, token count = 0,
	 * etc.
	 */
	public SuggestedRefactoring() {
		totalTokenAmount = 0;
		sequences = new LinkedList<TokenSequence>();
	}

	/**
	 * Adds a TokenSequence to the Suggestion, which has an side-effects on the
	 * token statistics stored in this ADT (number of tokens, opportunity for improvement, etc.)
	 * @param s
	 */
	public void addTokenSequence(TokenSequence theSequence) throws TokenSequencesIncompatible {
		totalTokenAmount += theSequence.getNumTokens(); // avoid looping by having a member var
		if (
			sequences.size() > 0 &&
			theSequence.getNumSubstitutions() != sequences.getFirst().getNumSubstitutions()
		) {
			StringBuilder errMsg = new StringBuilder();
			errMsg.append("Attempted to add TokenSequence with numSubstitutions = ");
			errMsg.append(theSequence.getNumSubstitutions());
			errMsg.append(" to SuggestedRefactoring with numSubstitutions = ");
			errMsg.append(sequences.getFirst().getNumSubstitutions());
			errMsg.append('.');
			throw new TokenSequencesIncompatible(errMsg.toString());
		}
		sequences.addLast(theSequence);
	}
	
	/**
	 * Get the total opportunity for this Suggestion. This is the 
	 * value that a suggested refactoring uses to determine its rank among other refactorings
	 * and is also printed in the Suggested Refactorings report.
	 * @return the opportunity for improvement for this Suggestion
	 */
	public int getOpportunity() {
		return calcOpportunityForImprovement(sequences.size(), totalTokenAmount);
	}
	
	/**
	 * Returns the total amount of tokens associated with refactoring
	 * @return the total amount of tokens associated with refactoring
	 */
	public int getTotalTokens() {
		return totalTokenAmount;
	}
	
	/**
	 * Determines total number of lexemes that will be subbed out
	 *
	 * Maintained here for backwards
	 * compatibility with Report.trimRefactorings(), but will otherwise be unused
	 * by the report. Total substitutions might become a static method for a
	 * calculation, but this object-bound method will likely be removed.
	 *
	 * @return the total number of tokens that would be removed or replaced
	 * by replacing duplicate code sequences with function calls.
	 */
	public int getTotalSubs() {
		int sequenceCount = sequences.size();
		if (sequenceCount < 1) {
			return 0;
		}
		// We know each has the same number of substitutions because that is
		// the definition of a SuggestedRefactoring (we ensure this during addTokenSequence)
		return sequenceCount * sequences.getFirst().getNumSubstitutions();
	}

	
	/**
	 * Returns the string of the compiled data, as shown in the report
	 * @see Report
	 * @return string suitible for use in the Report
	 */
	public String toString() {
		StringBuilder out = new StringBuilder();
		Iterator<TokenSequence> it = sequences.iterator();

		out.append("Opportunity ");
		out.append(Integer.toString(getOpportunity()));
		out.append(", ");
		out.append(Integer.toString(totalTokenAmount));
		out.append(" tokens");
		while (it.hasNext()) {
			out.append(it.next().toString());
			out.append(' ');
		}
		out.append(System.lineSeparator());
		return out.toString();

	}
	
	/**
	 * Compares suggested refactorings to see if they are equal.
	 * Comapares totalTokenAmount, Opportunity value, and the sequences
	 * reference (not a deep comparision).
	 * 
	 * @param obj object to be compared to this suggested refactoring
	 * @return true if provided object is equal to this one
	 */
	public boolean equals(Object obj) {

		if (obj.getClass() != this.getClass()) {
			return false;
		}
		SuggestedRefactoring other = (SuggestedRefactoring)obj;
		return (
			totalTokenAmount == other.totalTokenAmount &&
			getOpportunity() == other.getOpportunity() &&
			sequences == other.sequences
		);
	}

	// Determines the total amount of tokens found in this suggestion.
	// kept as member var to avoid looping over the linked list to calculate
	private int totalTokenAmount;

	// List of all the files where this sequence lives, how to locate them, and what tokens are in
	// the sequence (to fulfull "nearly duplicate" requirements, we need to store all the tokens
	// identified as part of this Suggestion)
	private LinkedList<TokenSequence> sequences;

	/**
	 * Used to stub this class when building reports; obsolete now.
	 * @param a
	 * @param b
	 * @param c
	 * @param mock
	 */
	@Deprecated
	SuggestedRefactoring(int a, int b, int c, String mock) {
		
	}
}
