package edu.odu.cs.cs350.dupedetector;

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
	 */
	public PropertiesFile(String propFilePath) {
		//File testFile1 = new File("src/test/ini/empty.ini");
	}
	
	/**
	 * Pulls the properties from the file and sets them
	 */
	private void extractProperties() {
		
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
