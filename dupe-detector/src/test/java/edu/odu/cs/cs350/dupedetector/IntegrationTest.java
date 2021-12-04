package edu.odu.cs.cs350.dupedetector;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * TODO:
 * 
 * Make a integration test with report and playing around with what files are getting pulled from directory (kind of like with the 
 * prop file testing, but provide set extensions instead of extracting)
 */

/**
 * Location where we can compile the tests that interact with multiple systems.
 * Some of these tests may perform to the output, others using standard assertions.
 * 
 * Refer to comment above method for which.
 * 
 * @author Jacob
 */

class IntegrationTest {
	
	//Data usage / values may need to be changed or shifted as systems are updated.
	ArrayList<String> userFileInput = new ArrayList<String>(
			Arrays.asList(getPathStringForTest("test-recursion"), getPathStringForTest("test-filter-files")));
	
	String sequence1 = 
			  "/home/zeil/projects/cppProject1/src/foo.cpp:100:0\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/foo.cpp:156:4\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:056:8\r\n"
			+ "a b 2\r\n";
	String sequence2 = 
			  "/home/zeil/projects/cppProject1/src/foo.cpp:100:4\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:056:4\r\n"
			+ "a b 2\r\n";
	String sequence3 = 
			  "/home/zeil/projects/cppProject1/src/foo.cpp:099:0\r\n"
			+ "m n\r\n"
			+ "/home/zeil/projects/cppProject1/src/foo.cpp:123:4\r\n"
			+ "x y\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:080:8\r\n"
			+ "a b\r\n";
	String sequence4 = 
			  "/home/zeil/projects/cppProject1/src/foo.cpp:0120:0\r\n"
			+ "m n o p q l s\r\n"
			+ "/home/zeil/projects/cppProject1/src/foo.cpp:125:4\r\n"
			+ "x y f g h n r\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:086:8\r\n"
			+ "a b p q t l s\r\n";
	String sequence5 = 
			  "/home/zeil/projects/cppProject1/src/alphabet.cpp:200:0\r\n"
			+ "a b c d e f g h i j\r\n"
			+ "/home/zeil/projects/cppProject1/src/grung.cpp:122:2\r\n"
			+ "\"blah\" z 3 4 5 6 7 8 9 10\r\n";
	String sequence6 = 
			  "/home/zeil/projects/cppProject1/src/foo.cpp:100:0\r\n"
			+ "l \"number\"\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:056:8\r\n"
			+ "x y\r\n"
			+ "/home/zeil/projects/cppProject1/src/oops.cpp:156:4\r\n"
			+ "x y\r\n"
			+ "/home/zeil/projects/cppProject1/src/oops.cpp:252:1\r\n"
			+ "x t\r\n";
	String sequence7 = 
			  "/home/zeil/projects/cppProject1/src/alphabet.cpp:200:0\r\n"
			+ "a b c\r\n"
			+ "/home/zeil/projects/cppProject1/src/foo.cpp:100:0\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/foo.cpp:156:4\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/grung.cpp:122:2\r\n"
			+ "\"blah\" z r\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:056:8\r\n"
			+ "a b 2\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/zoo.h:015:2\r\n"
			+ "v \"elaphant\" t\r\n"
			+ "/home/zeil/projects/cppProject1/src/JacobWasHere.cpp:156:4\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/JacobWasHere.cpp:211:2\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/JacobWasHere.cpp:317:3\r\n"
			+ "y a y\r\n";
	
	//Initialize the suggested refactorings (doesn't have to be perfect, can be nonsense info) - these are set ones
	//Reminder format is totalTokens, opportunity, totalSubs, data (might be nonsense to mix some stuff up)
	SuggestedRefactoring refactoring1 = new SuggestedRefactoring(12, 24, 3, sequence1);
	SuggestedRefactoring refactoring2 = new SuggestedRefactoring(16, 16, 3, sequence2);
	SuggestedRefactoring refactoring3 = new SuggestedRefactoring(6, 18, 2, sequence3);
	SuggestedRefactoring refactoring4 = new SuggestedRefactoring(12, 36, 7, sequence4);
	SuggestedRefactoring refactoring5 = new SuggestedRefactoring(9, 27, 3, sequence5);
	SuggestedRefactoring refactoring6 = new SuggestedRefactoring(16, 16, 3, sequence6);
	SuggestedRefactoring refactoring7 = new SuggestedRefactoring(18, 64, 10, sequence7);
	
	//Set Data for File Path Inputs to do integration without reliance on actual directories being read
	String filePath1 = "/home/zeil/projects/cppProject1/src/foo.cpp";
	String filePath2 = "/home/zeil/projects/cppProject1/src/headers/bar.h";
	String filePath3 = "H:\\cygwin\\home\\Jacob\\someCpp.cpp";
	String filePath4 = "/home/fakeZeil/projects/cppProject1/src/foo.cpp";
	String filePath5 = "/coolhome/fakeZeil/cppProject1/src/tool.cpp";
	
	SourceCodeFile file1 = new SourceCodeFile(filePath1);
	SourceCodeFile file2 = new SourceCodeFile(filePath2);
	SourceCodeFile file3 = new SourceCodeFile(filePath3);
	SourceCodeFile file4 = new SourceCodeFile(filePath4);
	SourceCodeFile file5 = new SourceCodeFile(filePath5);
	
	/**
	 * Outputs to console, shows effect of property values with correct input, if any.
	 */
	@Test 
	void testCorrectPropertyFileDeclarations() {
		System.out.println("Performing Property File Integration Test: Accepted Files");
		System.out.println("=========================================================");
		
		System.out.println("Default, no property file provided.");
		System.out.println("---------------------------------------------------------");
		
		PropertiesFile defaultProps = new PropertiesFile();
		
		System.out.println("Extracted CppExtensions: " + defaultProps.getCppExtensions().toString());
		System.out.println("Extracted MaxSubstitutions: " + defaultProps.getMaxSubstitutions());
		System.out.println("Extracted MinSequenceLengths: " + defaultProps.getMinSequenceLength());
		System.out.println();
		
		System.out.println("Demo on Controlled Refactorings");
		System.out.println("---------------------------------------------------------\n");
		
		//Simulate Console Input with set information
		int numSuggestions = 3;
		
		//Apply extensions from Property File  
		SuppliedFilePaths project = new SuppliedFilePaths();
		project.setEligibleExtensions(defaultProps.getCppExtensions());
		ArrayList<SourceCodeFile> projectFiles = project.findEligibleSourceCode(userFileInput);
    	
		//When show integration of prop file, what the suggested refactorings are exactly does not matter.
		ArrayList<SuggestedRefactoring> refListControlled
			= new ArrayList<SuggestedRefactoring>(Arrays.asList(refactoring1, refactoring2, refactoring3, refactoring4, 
					refactoring5, refactoring6, refactoring7));	
    	
		
    	//Removes refactoring3, refactoring5, and refactoring7 (then one is not printed)
    	Report unpreppedRep = new Report(projectFiles, refListControlled);
		Report preppedRep = prepReport(unpreppedRep,
				defaultProps.getMaxSubstitutions(), 
				defaultProps.getMinSequenceLength());
		preppedRep.printReport(numSuggestions);
		
		System.out.println("\n=========================================================");
		System.out.println("Empty File, no property values provided. Uses default.");
		System.out.println("---------------------------------------------------------");
		
		try {
			PropertiesFile blankProps = new PropertiesFile("src/test/data/ini/empty.ini");
			System.out.println("Extracted CppExtensions: " + blankProps.getCppExtensions().toString());
			System.out.println("Extracted MaxSubstitutions: " + blankProps.getMaxSubstitutions());
			System.out.println("Extracted MinSequenceLengths: " + blankProps.getMinSequenceLength());
			System.out.println();
			
			System.out.println("Demo on Controlled Refactorings");
			System.out.println("---------------------------------------------------------\n");
			
			//Apply extensions from Property File  
			project = new SuppliedFilePaths();
			project.setEligibleExtensions(blankProps.getCppExtensions());
			projectFiles.clear();
			projectFiles = project.findEligibleSourceCode(userFileInput);
			
	    	//Removes refactoring3, refactoring5, and refactoring7 (then one is not printed)
	    	unpreppedRep = new Report(projectFiles, refListControlled);
			preppedRep = prepReport(unpreppedRep,
				blankProps.getMaxSubstitutions(), 
				blankProps.getMinSequenceLength());
			preppedRep.printReport(numSuggestions);	
		}
		catch(Exception e) {
			System.out.println("Not fishing for specific exceptions, an issue happened when not expected.");
			System.out.println(e);
		}	
		
		System.out.println("\n=========================================================");
		System.out.println("Only CppExtensions provided. Uses default for not provided.");
		System.out.println("---------------------------------------------------------");
		
		try {
			PropertiesFile cppProps = new PropertiesFile("src/test/data/ini/cppExten1.ini");
			System.out.println("Extracted CppExtensions: " + cppProps.getCppExtensions().toString());
			System.out.println("Extracted MaxSubstitutions: " + cppProps.getMaxSubstitutions());
			System.out.println("Extracted MinSequenceLengths: " + cppProps.getMinSequenceLength());
			System.out.println();
			
			System.out.println("Demo on Controlled Refactorings");
			System.out.println("---------------------------------------------------------\n");
			
			//Apply extensions from Property File  
			project = new SuppliedFilePaths();
			project.setEligibleExtensions(cppProps.getCppExtensions());
			projectFiles.clear();
			projectFiles = project.findEligibleSourceCode(userFileInput);
			
	    	//Removes refactoring3, refactoring5, and refactoring7 (then one is not printed)
	    	unpreppedRep = new Report(projectFiles, refListControlled);
			preppedRep = prepReport(unpreppedRep,
				cppProps.getMaxSubstitutions(), 
				cppProps.getMinSequenceLength());
			preppedRep.printReport(numSuggestions);	
		}
		catch(Exception e) {
			System.out.println("Not fishing for specific exceptions, an issue happened when not expected.");
			System.out.println(e);
		}	
		
		System.out.println("\n=========================================================");
		System.out.println("All properties provided.");
		System.out.println("---------------------------------------------------------");
		
		try {
			PropertiesFile allProps = new PropertiesFile("src/test/data/ini/mixProp2.ini");
			System.out.println("Extracted CppExtensions: " +  allProps.getCppExtensions().toString());
			System.out.println("Extracted MaxSubstitutions: " + allProps.getMaxSubstitutions());
			System.out.println("Extracted MinSequenceLengths: " + allProps.getMinSequenceLength());
			System.out.println();
			
			System.out.println("Demo on Controlled Refactorings");
			System.out.println("---------------------------------------------------------\n");
			
			//Apply extensions from Property File  
			project = new SuppliedFilePaths();
			project.setEligibleExtensions(allProps.getCppExtensions());
			projectFiles.clear();
			projectFiles = project.findEligibleSourceCode(userFileInput);
			
	    	//Removes refactoring3, refactoring5, and refactoring7 (then one is not printed)
	    	unpreppedRep = new Report(projectFiles, refListControlled);
			preppedRep = prepReport(unpreppedRep,
				allProps.getMaxSubstitutions(), 
				allProps.getMinSequenceLength());
			preppedRep.printReport(numSuggestions);	
			
	    	System.out.println("\nDemo on Random Refactorings");
			System.out.println("---------------------------------------------------------\n");
			
			//Apply extensions from Property File  
			project = new SuppliedFilePaths();
			project.setEligibleExtensions(allProps.getCppExtensions());
			projectFiles.clear();
			projectFiles = project.findEligibleSourceCode(userFileInput);
			
			ArrayList<SuggestedRefactoring> refListRand = randRefactoringList(200); //Simulate refactor list
			numSuggestions = 3;
			unpreppedRep = new Report(projectFiles, refListRand);
			preppedRep = prepReport(unpreppedRep,
					allProps.getMaxSubstitutions(), 
					allProps.getMinSequenceLength());
			preppedRep.printReport(numSuggestions);	
			System.out.println();
		}
		catch(Exception e) {
			System.out.println("Not fishing for specific exceptions, an issue happened when not expected.");
			System.out.println(e);
		}
		
		System.out.println();
		System.out.println("End of Property File Integration Test: Accepted Files");
		System.out.println();
		System.out.println();
	}
	
	/**
	 * Outputs to console, shows error messages when an issue occurs.
	 */
	@Test 
	void testIncorrectPropertyFileDeclarations() {
		System.out.println("Performing Property File Integration Test: Incorrect Files");
		System.out.println("=========================================================");
		System.out.println("When a file that is not an .ini is provided:\n");
		try {
			PropertiesFile wrongFile = new PropertiesFile("src/test/data/ini/invalidFileType.txt");
		}
		catch(Exception e) {
			System.out.println(e.toString());
			System.out.println();
		}
		
		System.out.println("---------------------------------------------------------");
		System.out.println("When a file that is not found:\n");
		try {
			PropertiesFile wrongFile = new PropertiesFile("src/test/data/ini/noExist.ini");
		}
		catch(Exception e) {
			System.out.println(e.toString());
			System.out.println();
		}
		
		System.out.println("---------------------------------------------------------");
		System.out.println("Duplicate Declared Properties in the property file:\n");
		try {
			PropertiesFile wrongFile = new PropertiesFile("src/test/data/ini/dupeProps.ini");
		}
		catch(Exception e) {
			System.out.println(e.toString());
			System.out.println();
		}
		
		System.out.println("---------------------------------------------------------");
		System.out.println("Variety of incorrect formatting in the file:\n");
		try {
			PropertiesFile wrongFile = new PropertiesFile("src/test/data/ini/invalidProps1.ini");
		}
		catch(Exception e) {
			System.out.println(e.toString());
			System.out.println();
		}
		
		try {
			PropertiesFile wrongFile = new PropertiesFile("src/test/data/ini/invalidProps2.ini");
		}
		catch(Exception e) {
			System.out.println(e.toString());
			System.out.println();
		}
		
		try {
			PropertiesFile wrongFile = new PropertiesFile("src/test/data/ini/invalidProps3.ini");
		}
		catch(Exception e) {
			System.out.println(e.toString());
			System.out.println();
		}
		
		try {
			PropertiesFile wrongFile = new PropertiesFile("src/test/data/ini/invalidProps4.ini");
		}
		catch(Exception e) {
			System.out.println(e.toString());
			System.out.println();
		}
		
		System.out.println();
		System.out.println("End of Property File Integration Test: Incorrect Files");
		System.out.println();
		System.out.println();
	}
	
	/**
	 * Outputs to console to show a report being printed with provided set information
	 */
	@Test
	void testReportPrintOuts() {
		
		System.out.println("Performing Report Output Test");
		System.out.println("=========================================================");
		
		//Provides set list of files to print
		ArrayList<SourceCodeFile> projectFiles = new ArrayList<SourceCodeFile>(
				Arrays.asList(file1, file2, file3, file4, file5));
		
		//Initialize a few list of refactoring suggestions
    	ArrayList<SuggestedRefactoring> refactoringList1
			= new ArrayList<SuggestedRefactoring>(Arrays.asList(refactoring1, refactoring2, refactoring3, refactoring4));
    	ArrayList<SuggestedRefactoring> refactoringList2
    		= new ArrayList<SuggestedRefactoring>(Arrays.asList(refactoring1, refactoring2, refactoring3, refactoring4, 
    															refactoring5, refactoring6, refactoring7));
    	ArrayList<SuggestedRefactoring> refactoringList3 
    		= new ArrayList<SuggestedRefactoring>(); //Init empty to be filled randomly later
    	
    	//Print list [1] with no changes (as many as it can print)
    	Report rep1 = new Report(projectFiles, refactoringList1);
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now Printing List1 of Suggested Refactors with nSuggestion: 4");
    	System.out.println("Source Files Not Sorted");
    	System.out.println("------------------------------------------------------");
    	rep1.printReport(4);
    	
    	//Print list [1] after sorted
       	rep1.sortSourceFiles();
    	rep1.sortRefactorings();
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now Printing Sorted List1 of Suggested Refactors with nSuggestion: 4");
    	System.out.println("Source Files Sorted [and onwards in this test]");
    	System.out.println("------------------------------------------------------");
    	rep1.printReport(4);
    	
    	//Print list [1] after trimmed
    	System.out.println("------------------------------------------------------");
    	System.out.println("Trimming List1 based on maxSubstitutions of 3 and minimumSequence of 14");
    	rep1.trimRefactorings(3, 14);
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now Printing Sorted List1 of Suggested Refactors with nSuggestion: 4");
    	System.out.println("------------------------------------------------------");
    	rep1.printReport(4);
    	
    	//Print list [2] with no changes (sorts source files)
    	Report rep2 = new Report(projectFiles, refactoringList2);
    	rep2.sortSourceFiles();
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now Printing List2 of Suggested Refactors with nSuggestion: 7");
    	System.out.println("------------------------------------------------------");
    	rep2.printReport(7);
    	
    	//Print list [2] with no changes (cut off the amount it prints with nSuggestions)
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now Printing List2 of Suggested Refactors with nSuggestion: 3");
    	System.out.println("------------------------------------------------------");
    	rep2.printReport(3);
    	
    	//Print list [2] after trimmed
    	System.out.println("------------------------------------------------------");
    	System.out.println("Trimming List2 based on maxSubstitutions of 8 and minimumSequence of 2");
    	rep2.trimRefactorings(8, 2);
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now Printing List2 of Suggested Refactors with nSuggestion: 4");
    	System.out.println("------------------------------------------------------");
    	rep2.printReport(4);
    	
    	//Print list [2] after refactors sorted
    	rep2.sortRefactorings();
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now Printing Sorted List2 of Suggested Refactors with nSuggestion: 7");
    	System.out.println("------------------------------------------------------");
    	rep2.printReport(7);
    	
    	//
    	// Print list [3-large] after trimmed, then sorted. Information here will be convuluted obviously (not accurate/fake output)
    	// but is here to show it working for large sets of data
    	//
    	System.out.println("======================================================");
    	System.out.println("Generating 200 Random Suggestions to Showcase Large Dataset. The random numbers range from 1-200");
    	System.out.println("======================================================");
    	
    	refactoringList3 = randRefactoringList(200);
    	
    	System.out.println("------------------------------------------------------");
    	System.out.println("Adding List to Report now");
    	Report rep3 = new Report(projectFiles, refactoringList3);
    	System.out.println("------------------------------------------------------");
    	System.out.println("Trimming extremes from List3 based on maxSubstitutions of 175 and minimumSequence of 25");
    	rep3.trimRefactorings(175, 25);
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now sorting suggestions of List3");
    	rep3.sortRefactorings();
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now sorting file paths of List3");
    	rep3.sortSourceFiles();
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now Printing Sorted List3 of Suggested Refactors with nSuggestion: 100");
    	System.out.println("------------------------------------------------------");
    	rep3.printReport(100);
    	
    	//End of system test
    	System.out.println("------------------------------------------------------");
    	
		System.out.println();
    	System.out.println("End of Report Output Test\n");
		System.out.println();
		System.out.println();
	}
	
	//Helper Methods Below
	
	/**
	 * Used by testing functions to generate random nonsense Suggestions to test large data sizes.
	 * Will uses any of the 1-7 provided by class to provide fake data. As for integer values it
	 * will generate any number from 1-200 to fill those spots.
	 * 
	 * @param genNumSuggestions how many suggestions should it generate.
	 * 
	 * @return ArrayList<SuggestedRefactoring> that is randomly filled by function.
	 */
	private ArrayList<SuggestedRefactoring> randRefactoringList(int genNumSuggestions) {
		ArrayList<SuggestedRefactoring> randRefactorList = new ArrayList<SuggestedRefactoring>();
		//Random generator https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
    	Random rand = new Random();
    	//0-199 + 1
    	int upperbound = 200;
    	//0-6
    	int switchUpperbound = 7;
    	
    	for(int i=0; i < genNumSuggestions; i++) {
    		
    		int randTotalTokens = 1 + rand.nextInt(upperbound);
    		int randOpportunity = 1 + rand.nextInt(upperbound);
    		int randTotalSubs = 1 + rand.nextInt(upperbound);
    		
    		//Randomly generated refactor suggestion
    		SuggestedRefactoring randRefactor;
    		
    		int randData = rand.nextInt(switchUpperbound);
    		switch(randData) {
    			case 0: randRefactor = new SuggestedRefactoring(randTotalTokens, randOpportunity, randTotalSubs, sequence1);
    					break;
    			case 1: randRefactor = new SuggestedRefactoring(randTotalTokens, randOpportunity, randTotalSubs, sequence2);
						break;
    			case 2: randRefactor = new SuggestedRefactoring(randTotalTokens, randOpportunity, randTotalSubs, sequence3);
						break;
    			case 3: randRefactor = new SuggestedRefactoring(randTotalTokens, randOpportunity, randTotalSubs, sequence4);
						break;
    			case 4: randRefactor = new SuggestedRefactoring(randTotalTokens, randOpportunity, randTotalSubs, sequence5);
						break;
    			case 5: randRefactor = new SuggestedRefactoring(randTotalTokens, randOpportunity, randTotalSubs, sequence6);
						break;
    			case 6: randRefactor = new SuggestedRefactoring(randTotalTokens, randOpportunity, randTotalSubs, sequence7);
						break;
				default: System.out.println("Error Generating Random Suggestion. Setting default.");
						randRefactor = new SuggestedRefactoring(0, 0, 0, "Default on loop: " + i);
						break;
    		}
    		//Add the randomly generated suggestion to the refactoring list
    		randRefactorList.add(randRefactor);
    	}
    	return randRefactorList;
	}
	
	//Follows set pattern if not testing inidivdual qualities.
	private Report prepReport(Report unpreppedReport, int maxSubs, int minSeqLength) {
		Report retReport = unpreppedReport;
		
		retReport.sortSourceFiles();
		retReport.trimRefactorings(maxSubs, minSeqLength);
		retReport.sortRefactorings();
		
		return retReport;
	}
	
	/**
	 * Takes a fileName and appends the path information for the data folder the files should reside in
	 * at a top level. In this case the test files will reside in src/test/data/cpp/[filename]
	 * 
	 * @param fileName name of file to be converted to string
	 * @return string of the file name
	 */
    String getPathStringForTest(String fileName) {
        Path path = Paths.get("src","test","data", "cpp", fileName);
        return path.toString();
    }
}
