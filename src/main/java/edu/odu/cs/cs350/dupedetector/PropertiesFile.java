package edu.odu.cs.cs350.dupedetector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

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
	 * 
	 * @throws FileNotFoundException
	 * @throws InvalidFileTypeException 
	 * @throws InvalidPropertyFormatException 
	 * @throws DuplicatePropertiesException 
	 */
	public PropertiesFile(String propFilePath) 
			throws FileNotFoundException, InvalidFileTypeException, InvalidPropertyFormatException, DuplicatePropertiesException {
		//https://stackoverflow.com/questions/30913799/is-there-a-new-java-8-way-of-retrieving-the-file-extension
		String extCheck = propFilePath.substring(propFilePath.lastIndexOf('.') + 1);
		if(extCheck.equals("ini")) {
			File testFile1 = new File(propFilePath);
			if(testFile1.exists()) {
				extractProperties(testFile1);
			}
			else {
				throw new FileNotFoundException("File " + testFile1.getAbsolutePath() + " does not exist.");
			}
		}
		else {
			throw new InvalidFileTypeException("File path " + propFilePath + " does not lead to an .ini file.");
		}
	}
	
	/**
	 * Pulls the properties from the file and sets them
	 * 
	 * @param propFile file to extract data from
	 * 
	 * @throws FileNotFoundException 
	 * @throws InvalidPropertyFormatException 
	 * @throws DuplicatePropertiesException 
	 */
	private void extractProperties(File propFile) throws FileNotFoundException, InvalidPropertyFormatException, DuplicatePropertiesException {
		//Sets defaults so at minimum they are set if something isn't found in the file.
		cppExtensions = new ArrayList<String>(Arrays.asList("cpp","h"));
		minSequenceLength = 10;
		maxSubstitutions = 8;
		
		//Used for multiple property error checking
		boolean readCppProp = false;
		boolean readMinSeqProp = false;
		boolean readMaxSubProp = false;
		
		//Forces to use the java.util.Scanenr
		java.util.Scanner fileReader = new java.util.Scanner(propFile);
		
		while (fileReader.hasNextLine()) {
			String originalLine = fileReader.nextLine();
			String currLine = originalLine.trim();
			if(!currLine.isEmpty()) {
				/**
				 * If we accept different types of properties or other lines of text, can add another else if.
				 * Currently case sensitive.
				 */
				if(currLine.startsWith("CppExtensions")) {
					if(!readCppProp) {
						currLine = extractPropertyValue(currLine, "CppExtensions");
						cppExtensions = extractExtensions(currLine);
						readCppProp = true;
					}
					else {
						fileReader.close();
						throw new DuplicatePropertiesException("Duplicate CppExtensions detected. Please define a property only once.");
					}
				}
				
				else if (currLine.startsWith("MinSequenceLength")) {
					if(!readMinSeqProp) {
						currLine = extractPropertyValue(currLine, "MinSequenceLength");
						minSequenceLength = extractInteger(currLine);
						readMinSeqProp = true;
					}
					else {
						fileReader.close();
						throw new DuplicatePropertiesException("Duplicate MinSubstitutions detected. Please define a property only once.");
					}
				}
				
				else if (currLine.startsWith("MaxSubstitutions")) {
					if (!readMaxSubProp) {
						currLine = extractPropertyValue(currLine, "MaxSubstitutions");
						maxSubstitutions = extractInteger(currLine);
						readMaxSubProp = true;
					}
					else {
						fileReader.close();
						throw new DuplicatePropertiesException("Duplicate MaxSubstitutions detected. Please define a property only once.");
					}
				}
				else {
					fileReader.close();
					throw new InvalidPropertyFormatException("The line \"" + originalLine + "\" is not an accepted Property.");
				}
			}
		}
		fileReader.close();
	}
	
	/**
	 * Will take a correctly declared property, check for equals sign, then extracts if there is one.
	 * 
	 * @param propertyStatement valid property string to extract values from
	 * @param propertyDecLength the property declaration that is to be removed
	 * 
	 * @return String values that occur after an equal sign
	 * 
	 * @throws InvalidPropertyFormatException 
	 */
	private String extractPropertyValue(String propertyStatement, String declaredProperty) throws InvalidPropertyFormatException {
		//Remove the property name and leading spaces if any
		String retValue = propertyStatement.substring(declaredProperty.length()).trim();
		if(retValue.startsWith("=")) {
			//Remove the equal sign and leading spaces
			retValue = retValue.substring(1).trim();
			
			//If only white spaces or empty, no property listed
			if(retValue.isBlank() || retValue.isEmpty()) {
				throw new InvalidPropertyFormatException("Expected value after = sign in property declaration.");
			}
		}
		else {
			throw new InvalidPropertyFormatException("Expected = sign after property name.");
		}
		return retValue;
	}
	
	/**
	 * Takes a string that is assumed to be an integer and converts it to an actual int.
	 * 
	 * @param intAsString string that we want to convert into integer
	 * 
	 * @return int where int interpreted from the string
	 * 
	 * @throws InvalidPropertyFormatException when the value cannot be interpreted as integer.
	 */
	private int extractInteger(String intAsString) throws InvalidPropertyFormatException {
		int retValue;
		try {
			retValue = Integer.parseInt(intAsString);
		}
		catch(NumberFormatException e){
			throw new InvalidPropertyFormatException(intAsString + " is not an integer.");
		}
		return retValue;
	}
	
	/**
	 * Extracts the extensions and ignores duplicates. Extensions are separated by a comma.
	 * Ignores multiple empty commas and trailing commas.
	 * 
	 * @param extenValuesAsString The string of values that needs to be broken into individual components
	 * 
	 * @return ArrayList<String> Individual extension components
	 * 
	 * @throws InvalidPropertyFormatException 
	 */
	private ArrayList<String> extractExtensions(String extenValuesAsString) throws InvalidPropertyFormatException {
		ArrayList<String> retValueList = new ArrayList<String>();
		//https://www.geeksforgeeks.org/split-string-java-examples/
		String[] splitValuesArray = extenValuesAsString.split(",", 0);
		/**
		 * This is so we can maintain an ArrayList usage, but check for duplicates at an o(n)
		 * instead of o(n^2) complexity.
		 * 
		 * https://www.geeksforgeeks.org/how-to-prevent-the-addition-of-duplicate-elements-to-the-java-arraylist/
		 */
		HashSet<String> dupeChecker = new HashSet<>();
		
		if(splitValuesArray.length == 0) {
			throw new InvalidPropertyFormatException("Invalid property type after CppExtensions. "
					+ "Indicates a string of commas, but no listed extensions.");
		}
		
		for(String extenValue : splitValuesArray) {
			//Remove leading / trailing white spaces from extension for conistency checking
			extenValue = extenValue.trim();
			if(!dupeChecker.contains(extenValue)) {
				dupeChecker.add(extenValue);
				retValueList.add(extenValue);
			}
		}
		return retValueList;
	}
	
	//=============== Accessors ============
	
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