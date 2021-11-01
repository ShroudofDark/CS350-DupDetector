/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.odu.cs.cs350.dupedetector;

import java.util.*;

public class App {
	
	public static void main(String[] args) {
        App app = new App(); // for config, etc., per design discussions
		app.parseArgs(args);

		SuppliedCode theCode = new SuppliedCode();
		theCode.setNumSuggestions(app.numSuggestions);

		// Get list of files from supplied files/directories
		theCode.setSuppliedFilesAndDirs(new ArrayList<String>());
		// Of the dirs supplied, if any, search recursively for eligible SourceCodeFiles
		theCode.findSourceCodeFiles();
		ArrayList<SuggestedRefactoring> refactorings = theCode.produceSuggestions();

		// TODO: print files read report here.
		
		// Report
		Report report = new Report(refactorings);
		report.sortRefactorings(); // TODO: make this a private concern of the class
		report.printReport(app.numSuggestions);
  
    }
	
	private int numSuggestions;
	public ArrayList<String> suppliedPaths;
	private String propertiesFile;

	private App()
	{
		suppliedPaths = new ArrayList<String>();
	}
	
    public String getGreeting() {
        return "Hello World!";
    }

	private void parseArgs(String[] args) {

		// TODO: call printForInvalidInvocation when needed
		
		try {
			numSuggestions = Integer.parseInt(args[2]);
		}
		
		catch(NumberFormatException e)
		{
			printForInvalidInvocation();
		}

		int startPathindex = 3;
		if(args[2] == ".ini")
		{
			propertiesFile = args[3];
			++startPathindex;
		}
		
		
		for (int i = startPathindex; i < args.length; ++i) {
			// push suppliedPaths
			suppliedPaths.add(args[i]);
					}
		
	
	}

	private void printForInvalidInvocation() {
		System.out.println("usage: java -jar DupDetector.jar nSuggestions [ properties ] path1 [ path2 … ]");
	}


    

		/*
		// System test thing maybe
		ReportSystemTest reportTest = new ReportSystemTest();
		reportTest.test();
		*/

}
