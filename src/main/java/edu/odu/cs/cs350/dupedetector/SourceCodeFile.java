package edu.odu.cs.cs350.dupedetector;

/**
 * Represents the source code file
 * 
 * Currently stubbed
 * 
 * @author John Hicks
 */
public class SourceCodeFile {
    private TokenStream tokens = null;
    private String path;
    private int totalTokens = 128;
    
    SourceCodeFile(String thePath) {
        this.path = thePath;
    }

    public String getPath() {
        return path;
    }

    public int getTotalTokens() {
        /** A get function shouldn't initialize, can cause a lot of problems
        if (tokens == null) {
            tokenize();
        }
        */
        return totalTokens;
    }

    public TokenStream getTokens() {
        /** A get function shouldn't initialize, can cause a lot of problems
        if (tokens == null) {
            tokenize();
        }
        */
        return tokens; // TODO: deep copy
    }

    private void tokenize() {
        // set tokens member
        totalTokens = 128;
    }
    
	/**
	 * Compares source code files to see if they are equal. They are considered
	 * equal if all functions on them return equal
	 * 
	 * @param obj object to be compared to this suggested refactoring
	 * @return true if provided object is equal to this one
	 */
	public boolean equals(Object obj) {
		
		SourceCodeFile other = (SourceCodeFile)obj;
		
		if(path != other.path)
			return false;
		if(totalTokens != other.totalTokens)
			return false;
		if(tokens != other.tokens)
			return false;
		
		return true;
	}
}
