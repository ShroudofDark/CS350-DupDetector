package edu.odu.cs.cs350.dupedetector;

import java.util.ArrayList;


/**
 * @author Jacob McFadden
 * @author John Hicks
 */
public class SuppliedCode {

    private int numSuggestions;
    // private ArrayList<SourceCodeFile> files; // TODO: implement class

    public int getNumSuggestions() {
        return numSuggestions;
    }

    public void setNumSuggestions(int nSuggestions) {
        numSuggestions = nSuggestions;
    }

    public ArrayList<SuggestedRefactoring> produceSuggestions(ArrayList<SourceCodeFile> projectFiles) {
        ArrayList<SuggestedRefactoring> refactoringList3 = new ArrayList<SuggestedRefactoring>();
        return refactoringList3;
    }
}
