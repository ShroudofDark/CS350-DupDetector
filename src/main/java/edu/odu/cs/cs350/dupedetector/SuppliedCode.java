package edu.odu.cs.cs350.dupedetector;

import java.util.ArrayList;
import java.util.Random;


/**
 * @author Jacob McFadden
 * @author John Hicks
 */
public class SuppliedCode {

    private int numSuggestions;
    // private ArrayList<SourceCodeFile> files; // TODO: implement class
    // private ArrayList<Directory> dirs; // TODO: implement class
    private ArrayList<String> eligibleExtensions;

    public int getNumSuggestions() {
        return numSuggestions;
    }

    public void setNumSuggestions(int nSuggestions) {
        numSuggestions = nSuggestions;
    }

    public void setEligibleExtensions(ArrayList<String> exts) {
        eligibleExtensions = exts;
    }

    public ArrayList<String> getEligibleExtensions() {
        return (ArrayList<String>)eligibleExtensions.clone();
    }

    public void setSuppliedFilesAndDirs(ArrayList<String> userInput) {
        // TODO: parse out whether input is a file or is a dir; add to internal list
        // of files and directories
        return;
    }

    public void findSourceCodeFiles() {
        // TODO: implement recursive file search
        return;
    }

    public ArrayList<SuggestedRefactoring> produceSuggestions() {
        ArrayList<SuggestedRefactoring> refactoringList3 = new ArrayList<SuggestedRefactoring>(); //Init empty to be filled randomly later

        // TODO: Temporary, until we merge with ftr-eligible-cpp-tokens
        // https://github.com/john-hix/CS350-dupe-detector/pull/4
        //Initialize a bunch of strings to be used for Refactoring Suggestions (can repeat use if needed)
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
           	
    	//Random generator https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
    	Random rand = new Random();
    	
    	//0-199 + 1
    	int upperbound = 200;
    	//0-6
    	int switchUpperbound = 7;
    	
    	for(int i=0; i < numSuggestions; i++) { // use suggested refactorings limit
    		
            
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
    		refactoringList3.add(randRefactor);
    	}

        return refactoringList3;
    }
}
