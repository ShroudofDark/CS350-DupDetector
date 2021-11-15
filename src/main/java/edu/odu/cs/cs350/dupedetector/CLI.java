package edu.odu.cs.cs350.dupedetector;

import java.util.*;

public class CLI {
	
	public static void main(String[] args) {
		App a = new App();
		
		try {
			a.parseArgs(args);
		}
		
		catch(Exception e)
		{
			a.printForInvalidInvocation();
			return;
		}
		
			SuppliedCode theCode = new SuppliedCode();
			theCode.setNumSuggestions(a.numSuggestions);
			
			// Get list of files from supplied files/directories
			theCode.setSuppliedFilesAndDirs(new ArrayList<String>());
			// Of the dirs supplied, if any, search recursively for eligible SourceCodeFiles
			theCode.findSourceCodeFiles();
			ArrayList<SuggestedRefactoring> refactorings = theCode.produceSuggestions();
	}
	
			public int numSuggestions;
			private ArrayList<String> suppliedPaths;
			private String propertiesFile;
			
			private void parseArgs(String[] args) throws Exception {
				
				numSuggestions = Integer.parseInt(args[0]);

				if (args.length < 2) { // holds with or without properties file
					throw new Exception("Too few arguments.");
				}

				int startPathindex = 3;
				if (args[1].endsWith(".ini"))
				{
					propertiesFile = args[1];
					++startPathindex;
					if (args.length < 3) { // with properties file
						throw new Exception("Too few arguments.");
					}
				}
				
				for (int i = startPathindex; i < args.length; ++i) {
					// push suppliedPaths
					suppliedPaths.add(args[i]);
				}
				
			
			}

			public void printForInvalidInvocation() {
				System.out.println("usage: java -jar DupDetector.jar nSuggestions [ properties ] path1 [ path2 … ]");
			}
}