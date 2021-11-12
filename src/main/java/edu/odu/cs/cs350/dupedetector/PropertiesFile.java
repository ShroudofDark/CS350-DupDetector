package edu.odu.cs.cs350.dupedetector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Holds and process's data that are used for the programs properties. These are
 * either provided by an .ini file on the CLI or resorts to defaults if not provided. 
 * They follow a format of propertyName = propertyValue
 * There exist three primary properties in: 
 * 
 * CppExtensions
 * MinSequenceLength
 * MaxSubstitutions
 * 
 * @author Jacob
 */
public class PropertiesFile {

	//properties is an optional path to a properties file, with file extension .ini, containing properties identified below in the format: 
	
	private ArrayList<String> cppExtensions;
	private int minSequenceLength;
	private int maxSubstitutions;
	
	/**
	 * Creates a property file with no .ini file.
	 * Provides default values.
	 */
	public PropertiesFile() {
		cppExtensions = new ArrayList<String>(Arrays.asList("cpp","h"));
		minSequenceLength = 10;
		maxSubstitutions = 8;
	}
	
	/**
	 * Creates a property file with values provided by .ini file.
	 * Will provide default values if a property is not found.
	 * 
	 * @param propFilePath path of file with defined property values.
	 * @throws FileNotFoundException
	 */
	public PropertiesFile(String propFilePath) throws FileNotFoundException {
		File testFile1 = new File(propFilePath);
		
		if(testFile1.exists()) {
			//Continue as normal
		}
		else {
			throw new FileNotFoundException("File " + testFile1.getAbsolutePath() + " does not exist.");
		}

	}
	
	/**
	 * Pulls the properties from the file and sets them
	 */
	private void extractProperties() {
		//Personal note on return the reading should be able to handle a line that doesn't necessarily have a newline character
	}
	
	public ArrayList<String> getCppExtensions() {
		//We want to return a deep copy of the List provided by this object as to not modify it
		ArrayList<String> returnList = new ArrayList<String>();
		Iterator<String> it = cppExtensions.iterator();
		while (it.hasNext()) {
			String curr = it.next();
			returnList.add(curr);
		}
		return returnList;
	}
	
	public int getMinSequenceLength() {
		return minSequenceLength;
	}
	
	public int getMaxSubstitutions() {
		return maxSubstitutions;
	}
}

/**
 * Set of custom exceptions that can be thrown by the class and be used.
 * 
 * https://www.javatpoint.com/custom-exception
 */
class InvalidFileTypeException extends Exception {
	private static final long serialVersionUID = 1L; //Removes warning / default generated

	public InvalidFileTypeException(String str) {
		super(str);
	}
}

class DuplicatePropertiesException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicatePropertiesException(String str) {
		super(str);
	}
}

class InvalidPropertyFormatException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidPropertyFormatException(String str) {
		super(str);
	}
}