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
    private int totalTokens = 0;

    SourceCodeFile(String thePath) {
        this.path = thePath;
    }

    public String getPath() {
        return path;
    }

    public int getTotalTokens() {
        if (tokens == null) {
            tokenize();
        }
        return totalTokens;
    }

    public TokenStream getTokens() {
        if (tokens == null) {
            tokenize();
        }
        return tokens; // TODO: deep copy
    }

    private void tokenize() {
        // set tokens member
        totalTokens = 128;
    }
}
