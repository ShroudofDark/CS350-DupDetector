package edu.odu.cs.cs350.dupedetector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ReportSystemTest {

	public ReportSystemTest() {
		
	}
	
	/**
     * Going to spit out a bunch of information testing the Report class.
	 * @deprecated - we should move this to a unit test after merging with the
	 * tokenizing branch.
     * 
     * Will use functions and information currently have.
	 * EDIT: we should have more once we merge in the tokenizing branch.
     */
    public void test() {
    	System.out.println("Now Doing Report System Test\n");
    	
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
    	
    	//Initialize the suggested refactorings (doesn't have to be perfect, can be nonsense info) - these are set ones
    	//Reminder format is totalTokens, opportunity, totalSubs, data (might be nonsense to mix some stuff up)
    	SuggestedRefactoring refactoring1 = new SuggestedRefactoring(12, 24, 3, sequence1);
    	SuggestedRefactoring refactoring2 = new SuggestedRefactoring(16, 16, 3, sequence2);
    	SuggestedRefactoring refactoring3 = new SuggestedRefactoring(6, 18, 2, sequence3);
    	SuggestedRefactoring refactoring4 = new SuggestedRefactoring(12, 36, 7, sequence4);
    	SuggestedRefactoring refactoring5 = new SuggestedRefactoring(9, 27, 3, sequence5);
    	SuggestedRefactoring refactoring6 = new SuggestedRefactoring(16, 16, 3, sequence6);
    	SuggestedRefactoring refactoring7 = new SuggestedRefactoring(18, 64, 10, sequence7);
    	
    	//Initialize a few list of refactoring suggestions
    	ArrayList<SuggestedRefactoring> refactoringList1
    		= new ArrayList<SuggestedRefactoring>(Arrays.asList(refactoring1, refactoring2, refactoring3, refactoring4));
    	ArrayList<SuggestedRefactoring> refactoringList2
    		= new ArrayList<SuggestedRefactoring>(Arrays.asList(refactoring1, refactoring2, refactoring3, refactoring4, 
    															refactoring5, refactoring6, refactoring7));
    	ArrayList<SuggestedRefactoring> refactoringList3 = new ArrayList<SuggestedRefactoring>(); //Init empty to be filled randomly later
    	
    	//Print list [1] with no changes (as many as it can print)
    	Report rep1 = new Report(refactoringList1);
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now Printing List1 of Suggested Refactors with nSuggestion: 4");
    	System.out.println("------------------------------------------------------");
    	rep1.printReport(4);
    	
    	//Print list [1] after sorted
    	rep1.sortRefactorings();
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now Printing Sorted List1 of Suggested Refactors with nSuggestion: 4");
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
    	
    	//Print list [2] with no changes
    	Report rep2 = new Report(refactoringList2);
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
    	
    	//Print list [2] after sorted
    	rep2.sortRefactorings();
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now Printing Sorted List2 of Suggested Refactors with nSuggestion: 7");
    	System.out.println("------------------------------------------------------");
    	rep2.printReport(7);
    	
    	/**
    	 * Print list [3-large] after trimmed, then sorted. Information here will be convuluted obviously (not accurate/fake output)
    	 * but is here to show it working for large sets of data
    	 */
    	System.out.println("======================================================");
    	System.out.println("Generating 200 Random Suggestions to Showcase Large Dataset. The random numbers range from 1-200");
    	System.out.println("======================================================");
    	
    	//Random generator https://www.educative.io/edpresso/how-to-generate-random-numbers-in-java
    	Random rand = new Random();
    	
    	//0-199 + 1
    	int upperbound = 200;
    	//0-6
    	int switchUpperbound = 7;
    	
    	for(int i=0; i < 200; i++) {
    		
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
    	System.out.println("------------------------------------------------------");
    	System.out.println("Adding List to Report now");
    	Report rep3 = new Report(refactoringList3);
    	System.out.println("------------------------------------------------------");
    	System.out.println("Trimming extremes from List3 based on maxSubstitutions of 175 and minimumSequence of 25");
    	rep3.trimRefactorings(175, 25);
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now sorting suggestions of List3");
    	rep3.sortRefactorings();
    	System.out.println("------------------------------------------------------");
    	System.out.println("Now Printing Sorted List3 of Suggested Refactors with nSuggestion: 100");
    	System.out.println("------------------------------------------------------");
    	rep3.printReport(100);
    	
    	//End of system test
    	System.out.println("------------------------------------------------------");
    	System.out.println("End of Report System Test\n");
    }
}