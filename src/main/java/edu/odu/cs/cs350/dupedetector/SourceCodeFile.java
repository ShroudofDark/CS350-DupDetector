package edu.odu.cs.cs350.dupedetector;

import java.nio.file.Path;

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
    private int totalTokens = -1;
    
    /**
     * Sets the file's path and does no more -- @see SourceCodeFile.tokenize()
     * for initializing an instance of this class.
     * @param thePath
     */
    SourceCodeFile(String thePath) {
        this.path = thePath;
    }

    SourceCodeFile(Path thePath) {
        this.path = thePath.toString();
    }

    /**
     * Reads the file from disk and tokenizes its contents as C++ source code.
     * An instance of this class has been properly initialized after this method
     * has been called.
     */
    public void tokenize() { // TODO: the whole thing :)
        // Courtesy https://www.baeldung.com/junit-src-test-resources-directory-path
        //  Read in the file from disk
        // Path path = Paths.get("src","test","resources", "cpp", fileName);
        // return Files.newBufferedReader(path, StandardCharsets.US_ASCII);

        // scan

        // set values

        // set tokens member
        totalTokens = 128;
    }

    /**
     * @return the path to this SourceCodeFile on the file system
     */
    public String getPath() {
        return path;
    }

    public Path getFilePath() {
        return Path.of(this.path);
    }

    /**
     * Gets the number of tokens in the file
     * @return the number of tokens in the file. -1 if tokens not read in yet.
     */
    public int getTotalTokens() {
        return totalTokens;
    }

    /**
     * Get a TokenStream representing the file's C++ token contents. Will return
     * null until the `TokenStream.tokenize()` method has been called on the instance.
     * @return the tokens retrieved from the file, or null
     */
    public TokenStream getTokens() {
        return tokens; // TODO: deep copy
    }


    
	/**
	 * Compares source code files to see if they are equal. They are considered
	 * equal if all functions on them return equal
	 * 
	 * @param obj object to be compared to this suggested refactoring
	 * @return true if provided object is equal to this one
	 */
	public boolean equals(Object obj) {
		if (obj.getClass() != this.getClass()) {
            return false;
        }
		SourceCodeFile other = (SourceCodeFile)obj;
		if(other == this)
            return true;
		if(path != other.path)
			return false;
		if(totalTokens != other.totalTokens)
			return false;
		if(tokens != other.tokens)
			return false;
		
		return true;
	}
}
