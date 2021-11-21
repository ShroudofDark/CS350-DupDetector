package edu.odu.cs.cs350.dupedetector;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

//Wrappers for JUnit that make it more readable
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

//Important libraries
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileNotFoundException;

class PropertiesFileTest {

	//Expected default values
	ArrayList<String> defaultCppExten = new ArrayList<String>(Arrays.asList("cpp","h"));
	int defaultMinSeq = 10;
	int defaultMaxSub = 8;
	
	/**
	 * Test method for PropertiesFile constructor
	 */
	@Test
	void testPropertiesFile() {
		PropertiesFile propFile = new PropertiesFile();
		
		assertThat(propFile.getMinSequenceLength(), is(10));
		assertThat(propFile.getMaxSubstitutions(), is(8));
		
		/**
		 * Order doesn't matter for this list, so should be equal no matter.
		 * 
		 * https://www.baeldung.com/java-assert-lists-equality-ignore-order
		 */
		ArrayList<String> diffOrderCppExten = new ArrayList<String>(Arrays.asList("h","cpp"));
		
		//assertThat(propFile.getCppExtensions(), equalTo(defaultCppExten));
		assertThat(propFile.getCppExtensions(), containsInAnyOrder(defaultCppExten.toArray()));
		assertThat(propFile.getCppExtensions(), containsInAnyOrder(diffOrderCppExten.toArray()));
		
		//Check that cpp extens provided is deep copy
		ArrayList<String> propExten = propFile.getCppExtensions();
		propExten.add("notExten");
		ArrayList<String> expectedPropExten = new ArrayList<String>(Arrays.asList("cpp","h", "notExten"));
		
		assertThat(propExten, equalTo(expectedPropExten));
		assertThat(propFile.getCppExtensions(), containsInAnyOrder(defaultCppExten.toArray()));
		assertThat(propFile.getCppExtensions(), not(containsInAnyOrder(propExten.toArray())));	
	}

	/**
	 * Test method for PropertiesFile constructor with String param
	 * @throws FileNotFoundException 
	 * @throws InvalidFileTypeException 
	 * @throws InvalidPropertyFormatException 
	 * @throws DuplicatePropertiesException 
	 */
	@Test
	void testPropertiesFileString() 
			throws FileNotFoundException, InvalidFileTypeException, InvalidPropertyFormatException, DuplicatePropertiesException {
		
		//Empty file should act like default constructor
		String testFilePath1 = "src/test/data/ini/empty.ini";
		PropertiesFile propFile = new PropertiesFile(testFilePath1);
		
		assertThat(propFile.getMinSequenceLength(), is(10));
		assertThat(propFile.getMaxSubstitutions(), is(8));
		assertThat(propFile.getCppExtensions(), containsInAnyOrder(defaultCppExten.toArray()));
		assertThat(propFile.getCppExtensions(), not(contains("falseCpp")));
		
		//Single property files should read as expected
		String testFilePath2 = "src/test/data/ini/cppExten1.ini";
		propFile = new PropertiesFile(testFilePath2);
		ArrayList<String> expectedExten = new ArrayList<String>(Arrays.asList("C","cpp","h","hpp","H"));
		
		assertThat(propFile.getMinSequenceLength(), is(10));
		assertThat(propFile.getMaxSubstitutions(), is(8));
		assertThat(propFile.getCppExtensions(), containsInAnyOrder(expectedExten.toArray()));
		assertThat(propFile.getCppExtensions(), not(contains("falseCpp")));
		
		//=========
		String testFilePath3 = "src/test/data/ini/cppExten2.ini";
		propFile = new PropertiesFile(testFilePath3);
		expectedExten = new ArrayList<String>(Arrays.asList("C"));
		
		assertThat(propFile.getMinSequenceLength(), is(10));
		assertThat(propFile.getMaxSubstitutions(), is(8));
		assertThat(propFile.getCppExtensions(), containsInAnyOrder(expectedExten.toArray()));
		assertThat(propFile.getCppExtensions(), not(contains("falseCpp")));
		
		//=========
		String testFilePath4 = "src/test/data/ini/minSeq1.ini";
		propFile = new PropertiesFile(testFilePath4);
		
		assertThat(propFile.getMinSequenceLength(), is(20));
		assertThat(propFile.getMaxSubstitutions(), is(8));
		assertThat(propFile.getCppExtensions(), containsInAnyOrder(defaultCppExten.toArray()));
		assertThat(propFile.getCppExtensions(), not(contains("falseCpp")));
		
		//=========
		String testFilePath5 = "src/test/data/ini/maxSub1.ini";
		propFile = new PropertiesFile(testFilePath5);
		
		assertThat(propFile.getMinSequenceLength(), is(10));
		assertThat(propFile.getMaxSubstitutions(), is(3));
		assertThat(propFile.getCppExtensions(), containsInAnyOrder(defaultCppExten.toArray()));
		assertThat(propFile.getCppExtensions(), not(contains("falseCpp")));
		
		//Mixed properties should be read fine ignoring order/empty lines so long as they are formatted correctly
		String testFilePath6 = "src/test/data/ini/mixProp1.ini";
		propFile = new PropertiesFile(testFilePath6);
		expectedExten = new ArrayList<String>(Arrays.asList("C","cpp","h","hpp","H"));
		
		assertThat(propFile.getMinSequenceLength(), is(22));
		assertThat(propFile.getMaxSubstitutions(), is(8));
		assertThat(propFile.getCppExtensions(), containsInAnyOrder(expectedExten.toArray()));
		assertThat(propFile.getCppExtensions(), not(contains("falseCpp")));
		
		//========
		String testFilePath7 = "src/test/data/ini/mixProp2.ini";
		propFile = new PropertiesFile(testFilePath7);
		expectedExten = new ArrayList<String>(Arrays.asList("C","cpp","h","hpp","H"));
		
		assertThat(propFile.getMinSequenceLength(), is(15));
		assertThat(propFile.getMaxSubstitutions(), is(6));
		assertThat(propFile.getCppExtensions(), containsInAnyOrder(expectedExten.toArray()));
		assertThat(propFile.getCppExtensions(), not(contains("falseCpp")));
		
		/**
		 * If a file extension being added is as a repeat it is expected to ignore adding it again (letter case matters)
		 */
		String testFilePath8 = "src/test/data/ini/dupeCppExten.ini";
		propFile = new PropertiesFile(testFilePath8);
		expectedExten = new ArrayList<String>(Arrays.asList("C","c"));
		
		assertThat(propFile.getMinSequenceLength(), is(10));
		assertThat(propFile.getMaxSubstitutions(), is(8));
		assertThat(propFile.getCppExtensions(), containsInAnyOrder(expectedExten.toArray()));
		assertThat(propFile.getCppExtensions(), not(contains("falseCpp")));
	}
	
	/**
	 * Test method for PropertiesFile constructor with String param and
	 * when a property shows up multiple times in a file
	 */
	@Test
	void testPropertiesFileStringThrowsDuplicatePropertiesException() {
		String dupeProps = "src/test/data/ini/dupeProps.ini";
		assertThrows(DuplicatePropertiesException.class, ()-> {new PropertiesFile(dupeProps);});
	}
	
	/**
	 * Test method for PropertiesFile constructor with String param and
	 * when a property is formatted in a way to cause problems
	 */
	@Test
	void testPropertiesFileStringThrowsInvalidPropertyFormatException() {
		String invalidProps1 = "src/test/data/ini/invalidProps1.ini";
		assertThrows(InvalidPropertyFormatException.class, ()-> {new PropertiesFile(invalidProps1);});
		
		String invalidProps2 = "src/test/data/ini/invalidProps2.ini";
		assertThrows(InvalidPropertyFormatException.class, ()-> {new PropertiesFile(invalidProps2);});
		
		String invalidProps3 = "src/test/data/ini/invalidProps3.ini";
		assertThrows(InvalidPropertyFormatException.class, ()-> {new PropertiesFile(invalidProps3);});
	}
	
	/**
	 * Test method for PropertiesFile constructor with String param and
	 * file provided is not an .ini
	 */
	@Test
	void testPropertiesFileStringThrowsInvalidFileTypeException() {
		String invalidFileType = "src/test/data/ini/invalidFileType.txt";
		assertThrows(InvalidFileTypeException.class, ()-> {new PropertiesFile(invalidFileType);});
	}

	/**
	 * Test method for PropertiesFile constructor with String param and
	 * this string results in a file not found.
	 */
	@Test
	void testPropertiesFileStringThrowsFileNotFoundException() {
		String nonExistantFile = "src/test/data/ini/dpePops.ini";
		assertThrows(FileNotFoundException.class, ()-> {new PropertiesFile(nonExistantFile);});
	}

}
