package edu.odu.cs.cs350.dupedetector;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
     * @throws IOException
     */
    public void tokenize() throws IOException {
     // Courtesy https://www.baeldung.com/junit-src-test-resources-directory-path
           //  Read in the file from disk
        Path path = Paths.get(this.path);
        tokens = new TokenStream(
            Files.newBufferedReader(path, StandardCharsets.US_ASCII)
        );
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
        if (tokens == null) {
            return -1;
        }
        return tokens.size();
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
