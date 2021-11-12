package edu.odu.cs.cs350.dupedetector;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

//Wrappers for JUnit that make it more readable
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

//Important libraries
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

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
		//fail("Not yet implemented");
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
	 */
	@Test
	void testPropertiesFileString() {
		fail("Not yet implemented");
		//Need to figure out how to set up input files for this part here
	}

}
