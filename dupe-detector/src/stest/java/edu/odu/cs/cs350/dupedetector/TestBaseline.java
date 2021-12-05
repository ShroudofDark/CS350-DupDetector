package edu.odu.cs.cs350.dupedetector;
import java.util.LinkedList;
import java.util.List;

import edu.odu.cs.cs350.dupedetector.SysTestHelper;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Performs automated system tests by comparing output to expected out. 
 * 
 * Current note, can only test certain parts of the output report as suggestions
 * are randomly defined and thus there is no consistency in what might occur.
 * 
 * The either from hamcrest matchers allows us to check the two possible variations of a path
 * depending on what system the file is found. Checking for either / or \ possible path strings.
 * 
 * @author Jacob
 * @author John
 */

public final class TestBaseline {
    @Test
    void testNoArgsErrorMessage() {
        LinkedList<String> args = new LinkedList<String>();
        String output = SysTestHelper.runJarRaw(args);
        assertThat(output, is("usage: java -jar DupDetector.jar nSuggestions [ properties ] path1 [ path2 … ]"));
    }

    @Test
    void testSystemCppPlaceholder() {
        LinkedList<String> paths = new LinkedList<String>();
        paths.add("src/test/data/cpp");
        String output = SysTestHelper.runJar("52", null, paths);
        assertThat(output, not(is("usage: java -jar DupDetector.jar nSuggestions [ properties ] path1 [ path2 … ]")));
    }
    
    /**
     * Checks to make sure if a duplicate files added in by args doesn't get read multiple times
     */
    @Test
    void testDuplicateFiles() {
    	LinkedList<String> paths = new LinkedList<String>();
    	paths.add("src/stest/data/test-project1/cellIndex.cpp");
    	paths.add("src/stest/data/test-project1/cellIndex.cpp");
    	
    	String output = SysTestHelper.runJar("50", "src/stest/data/propertyFiles/expectedProperty.ini", paths);
    	
    	/**
    	 * It is important to check for the comma after given the expected output of a file report should have a comma
    	 * after the file path is listed then the number of tokens. The file could appear again in the suggested refactoring
    	 * report, however it won't have a comma following it. We don't care for it reappearing there.
    	 */
    	assertThat(output, either(containsString("src/stest/data/test-project1/cellIndex.cpp,"))
    			.or(containsString("src\\stest\\data\\test-project1\\cellIndex.cpp,")));
    	
    	//Removes for / version
    	String removedFileOutput1 = output.substring(output.indexOf("src/stest/data/test-project1/cellIndex.cpp,")+1);
    	//Removes for \ version
    	String removedFileOutput2 = output.substring(output.indexOf("src\\stest\\data\\test-project1\\cellIndex.cpp,")+1);
    	
    	//Since output1 may contain \ we have to pass this test it does
    	assertThat(removedFileOutput1, either(not(containsString("src/stest/data/test-project1/cellIndex.cpp,")))
    			.or(containsString("src\\stest\\data\\test-project1\\cellIndex.cpp,")));
    	
    	//Since output2 may contain / we have to pass this test it does
    	assertThat(removedFileOutput2, either(containsString("src/stest/data/test-project1/cellIndex.cpp,"))
    			.or(not(containsString("src\\stest\\data\\test-project1\\cellIndex.cpp,"))));
    	
    	//Providing Directory Instead
    	paths.clear();    	
    	paths.add("src/stest/data/test-project1/");
    	paths.add("src/stest/data/test-project1/");
    	
    	output = SysTestHelper.runJar("50", "src/stest/data/propertyFiles/expectedProperty.ini", paths);
    	
    	assertThat(output, either(containsString("src/stest/data/test-project1/dir1/Cylinder.cpp,"))
    			.or(containsString("src\\stest\\data\\test-project1\\dir1\\Cylinder.cpp,")));
    	
    	//Removes for / version
    	removedFileOutput1 = output.substring(output.indexOf("src/stest/data/test-project1/dir1/Cylinder.cpp,")+1);
    	//Removes for \ version
    	removedFileOutput2 = output.substring(output.indexOf("src\\stest\\data\\test-project1\\dir1\\Cylinder.cpp,")+1);
    	
    	//Since output1 may contain \ we have to pass this test it does
    	assertThat(removedFileOutput1, either(not(containsString("src/stest/data/test-project1/dir1/Cylinder.cpp,")))
    			.or(containsString("src\\stest\\data\\test-project1\\dir1\\Cylinder.cpp,")));
    	
    	//Since output2 may contain / we have to pass this test it does
    	assertThat(removedFileOutput2, either(containsString("src/stest/data/test-project1/dir1/Cylinder.cpp,"))
    			.or(not(containsString("src\\stest\\data\\test-project1\\dir1\\Cylinder.cpp,"))));
    }

    /**
     * Tests basic input with everything expected to be there
     */
    @Test
    void testExpectedInput() {
    	fail("Not Implemented");
    }
    
    /**
     * Tests when a provided property file is empty, using default values
     */
    @Test
    void testEmptyPropertyFile() {
    	fail("Not Implemented");
    }
    
    /**
     * Tests output with high amount of allowed numSuggestions (all eligible suggestions should print)
     */
    @Test
    void testHighNumSuggestions() {
    	/**
    	 * Can't properly test as output currently provides randomly generated suggestions and the amount removed is random
    	 */
    	fail("Not Implemented");
    }
    
    /**
     * Tests output with low amount of allowed numSuggestions (not all eligible suggestions should print)
     */
    @Test
    void testLowNumSuggestions() {
    	/**
    	 * Can't properly test as output currently provides randomly generated suggestions and the amount removed is random
    	 */
    	fail("Not Implemented");
    }
    
    /**
     * Tests output with single source file provided
     */
    @Test
    void testSingleSourceFile() {
    	LinkedList<String> paths = new LinkedList<String>();
    	paths.add("src/stest/data/test-project1/faculty.cpp");
    	
    	String output = SysTestHelper.runJar("50", "src/stest/data/propertyFiles/expectedProperty.ini", paths);
    
    	assertThat(output, either(containsString("src/stest/data/test-project1/faculty.cpp,"))
    			.or(containsString("src\\stest\\data\\test-project1\\faculty.cpp,")));
    	
    	assertThat(output, not(either(containsString("src/stest/data/test-project1/cellIndex.cpp,"))
    			.or(containsString("src\\stest\\data\\test-project1\\cellIndex.cpp,"))));
    }
    
    /**
     * Tests ouput with multiple directories provided
     */
    @Test
    void testMultipleDirectories() {
    	fail("Not Implemented");
    }
    
    /**
     * Tests multiple invalid property files
     */
    @Test
    void testInvalidPropertyFiles() {
    	fail("Not Implemented");
    }
    
    /**
     * Tests when multiple property files are provided, 2nd one should be read in as source file
     */
    @Test
    void testMultiplePropertyFiles() {
    	fail("Not Implemented");
    }
    
    /**
     * Tests that there is no property file provided, uses defaults
     */
    @Test
    void testNoPropertyFile() {
    	fail("Not Implemented");
    }
    
    /**
     * Tests bizzare extension choices, should still allow for them to be read in as source files
     */
    @Test
    void testBizzareExtensions() {
    	fail("Not Implemented");
    }
    
    /**
     * Tests when user only provides numSuggestions for arguments
     */
    @Test
    void testNumSuggestionsOnly() {
    	fail("Not Implemented");
    }
    
    /**
     * Tests when provided property file doesn't exist
     */
    @Test
    void testPropertyFileNotFound() {
    	fail("Not Implemented");
    }
    
    /**
     * Tests that specified files are still added even if extensions don't match directory searchers
     */
    @Test
    void testIndividualFilesAddedWhenExtensionDoesNotMatchPropertyFile() {
    	fail("Not Implemented");
    }
    
    /**
     * Tests when empty directory is provided
     */
    @Test
    void testEmptyDirectory() {
    	fail("Not Implemented");
    }
    
    /**
     * Tests when directory or file doesn't exist
     */
    @Test
    void testSourceFileOrDirectoryDoesNotExist() {
    	fail("Not Implemented");
    }
    
}
