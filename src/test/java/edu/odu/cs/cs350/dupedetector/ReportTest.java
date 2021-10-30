package edu.odu.cs.cs350.dupedetector;

//Junit Imports
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Wrappers for JUnit that make it more readable
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

//Other important imports
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

class ReportTest {

	//Data for SuggestedRefactoring until class is implemented (tests will need to be reviewed afterwards)
	String data1 = "/home/zeil/projects/cppProject1/src/foo.cpp:100:0\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/foo.cpp:156:4\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:056:8\r\n"
			+ "a b 2\r\n";
	String data2 = "/home/zeil/projects/cppProject1/src/foo.cpp:100:4\r\n"
			+ "x y 1\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:056:4\r\n"
			+ "a b 2\r\n";
	String data3 = "/home/zeil/projects/cppProject1/src/foo.cpp:099:0\r\n"
			+ "m n\r\n"
			+ "/home/zeil/projects/cppProject1/src/foo.cpp:123:4\r\n"
			+ "x y\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:080:8\r\n"
			+ "a b\r\n";
	String data4 = "/home/zeil/projects/cppProject1/src/foo.cpp:0120:0\r\n"
			+ "m n o p q l s\r\n"
			+ "/home/zeil/projects/cppProject1/src/foo.cpp:125:4\r\n"
			+ "x y f g h n r\r\n"
			+ "/home/zeil/projects/cppProject1/src/headers/bar.h:086:8\r\n"
			+ "a b p q t l s\r\n";
	
	//Suggested refactorings go tokenAmount, Opportunity, totalSubs, data currently
	SuggestedRefactoring refactoring1 = new SuggestedRefactoring(12, 24, 3, data1);
	SuggestedRefactoring refactoring2 = new SuggestedRefactoring(16, 16, 3, data2);
	SuggestedRefactoring refactoring3 = new SuggestedRefactoring(6, 18, 2, data3);
	SuggestedRefactoring refactoring4 = new SuggestedRefactoring(12, 36, 7, data4);
	
	ArrayList<SuggestedRefactoring> refactoringList1;
	ArrayList<SuggestedRefactoring> refactoringList2;
	ArrayList<SuggestedRefactoring> refactoringList3;
	
	/**
	 * For println testing. Information intepreted from these results:
	 * 
	 * https://stackoverflow.com/questions/32241057/how-to-test-a-print-method-in-java-using-junit/32241300
	 * https://stackoverflow.com/questions/1119385/junit-test-for-system-out-println
	 */
	ByteArrayOutputStream outContent;
	PrintStream originalOut = System.out;
	
	/**
	 * Setup data to be used/reset after each function
	 */
	@BeforeEach
	public void setUp() {
		//https://howtodoinjava.com/java/collections/arraylist/initialize-arraylist/ -> way to initialize ArrayList
		refactoringList1 
			= new ArrayList<SuggestedRefactoring>(Arrays.asList(refactoring1, refactoring2, refactoring3)); 
		refactoringList2 
			= new ArrayList<SuggestedRefactoring>(Arrays.asList(refactoring1, refactoring3));
		refactoringList3 
			= new ArrayList<SuggestedRefactoring>(Arrays.asList(refactoring1, refactoring2, refactoring3, refactoring4));
		
		//Output streams
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}
	
	/**
	 * Deallocate information once a test is done
	 */
	@AfterEach
	public void cleanUp() {
		refactoringList1.clear();
		refactoringList2.clear();
		refactoringList3.clear();
		
		//Clean up streams
		outContent.reset();
		System.setOut(originalOut);
	}
	
	/**
	 * Test method for Report constructor
	 */
	@Test
	void testReport() {
		Report rep = new Report(refactoringList1);
		
		//Check that information is as expected
		assertThat(rep.totalRefactorings(), is(3));
		assertThat(rep.getRefactoring(0), equalTo(refactoring1));
		assertThat(rep.getRefactoring(1), equalTo(refactoring2));
		assertThat(rep.getRefactoring(2), equalTo(refactoring3));
		assertThat(rep.getRefactoring(1), not(equalTo(refactoring3)));
		
		//Checks to make sure equal works as intended
		Report repSame = new Report(refactoringList1);
		assertThat(rep, equalTo(repSame));
		
		//Check that Report isn't just initializing the same list despite different input
		Report rep2 = new Report(refactoringList2);
		
		assertThat(rep2.totalRefactorings(), is(2));
		assertThat(rep, not(equalTo(rep2)));
		
		//Check that deepy copy is actually made
		refactoringList1.add(refactoring4);
		assertThat(refactoringList1.size(), is(4));
		assertThat(rep.totalRefactorings(), is(3));
		/**
		 * Expected that since refactoringList1 is adding something new that trying to access something should
		 * throw an error.
		 * 
		 * https://stackoverflow.com/questions/60289884/junit-test-indexoutofboundsexception
		 */
		assertThrows(IndexOutOfBoundsException.class, ()-> rep.getRefactoring(3));
	}
	
	/**
	 * Test method printReport
	 */
	@Test
	void testPrintReport() {
		Report rep = new Report(refactoringList1);
		
		//Prints report, with suggestion count of 3
		rep.printReport(3);
		
		//Check that printReport didn't change things it shouldn't
		assertThat(rep.totalRefactorings(), is(3));
		assertThat(rep.getRefactoring(0), equalTo(refactoring1));
		assertThat(rep.getRefactoring(1), equalTo(refactoring2));
		assertThat(rep.getRefactoring(2), equalTo(refactoring3));
		
		//Catch the output, converting it to a string
		String output = new String(outContent.toByteArray());
		
		//Assert that the output contains the expected data
		assertThat(output, containsString("Opportunity"));
		assertThat(output, containsString(data1));
		assertThat(output, containsString(data2));
		assertThat(output, containsString(data3));
		assertThat(output, not(containsString(data4)));
		
		//Format was grabbed from specifications, so there should be a line like this
		assertThat(output, containsString("Printed 3 of 3 suggestions."));
		
		/**
		 * Test Divide 1
		 */
		
		//Reset the stream output to do another test
		outContent.reset();
		
		//Should print only 2 of the 3 total suggestions
		rep.printReport(2);
		
		//Sets output to new string
		output = new String(outContent.toByteArray());
		
		//Assert that the output contains the expected data
		assertThat(output, containsString("Opportunity"));
		assertThat(output, containsString(data1));
		assertThat(output, containsString(data2));
		assertThat(output, not(containsString(data3)));
		assertThat(output, not(containsString(data4)));
		
		//Format was grabbed from specifications, so there should be a line like this
		assertThat(output, containsString("Printed 2 of 3 suggestions."));
		
		
		/**
		 * Test Divide 2
		 */
		
		//Reset the stream output to do another test
		outContent.reset();
		
		//Should print only 3 of the 3 total suggestions, given that there is only 3 items
		rep.printReport(4);
		
		//Sets output to new string
		output = new String(outContent.toByteArray());
		
		//Assert that the output contains the expected data
		assertThat(output, containsString("Opportunity"));
		assertThat(output, containsString(data1));
		assertThat(output, containsString(data2));
		assertThat(output, containsString(data3));
		assertThat(output, not(containsString(data4)));
		
		//Format was grabbed from specifications, so there should be a line like this
		assertThat(output, containsString("Printed 3 of 3 suggestions."));
	}
	
	/**
	 * Test method trimRefactorings
	 */
	@Test 
	void testTrimRefactorings() {
		//Original list
		Report rep = new Report(refactoringList3);
		//Lists to compare to (need new list because the ()-> nots local variables must be final reinitializing)
		Report rep2 = new Report(refactoringList3);
		Report rep3 = new Report(refactoringList3);
		Report rep4 = new Report(refactoringList3);
		
		//Confirm they are equal
		assertThat(rep2, equalTo(rep));
		assertThat(rep3, equalTo(rep));
		assertThat(rep4, equalTo(rep));
		
		//Max Subs/Min Sequence Length
		
		//Should not remove anything
		rep2.trimRefactorings(999, 0);
		
		assertThat(rep2.totalRefactorings(), is(4));
		assertThat(rep2.getRefactoring(0), equalTo(refactoring1));
		assertThat(rep2.getRefactoring(1), equalTo(refactoring2));
		assertThat(rep2.getRefactoring(2), equalTo(refactoring3));
		assertThat(rep2.getRefactoring(3), equalTo(refactoring4));
		
		/**
		 * Test Divide 1
		 */
		
		//Should remove refactoring3 only
		rep2.trimRefactorings(999, 12);
		
		assertThat(rep2.totalRefactorings(), is(3));
		assertThat(rep2.getRefactoring(0), equalTo(refactoring1));
		assertThat(rep2.getRefactoring(1), equalTo(refactoring2));
		assertThat(rep2.getRefactoring(2), equalTo(refactoring4));
		//This should no longer exist, thus throw out of bounds
		assertThrows(IndexOutOfBoundsException.class, ()-> rep2.getRefactoring(3));
		
		//These two should no longer be equal
		assertThat(rep2, not(equalTo(rep)));
		
		/**
		 * Test Divide 2
		 */
		
		//Should remove refactoring4 only
		rep3.trimRefactorings(3, 0);
		
		assertThat(rep3.totalRefactorings(), is(3));
		assertThat(rep3.getRefactoring(0), equalTo(refactoring1));
		assertThat(rep3.getRefactoring(1), equalTo(refactoring2));
		assertThat(rep3.getRefactoring(2), equalTo(refactoring3));
		//This should no longer exist, thus throw out of bounds
		assertThrows(IndexOutOfBoundsException.class, ()-> rep3.getRefactoring(3));
		
		//These two should no longer be equal
		assertThat(rep3, not(equalTo(rep)));
		
		/**
		 * Test Divide 3
		 */
		
		//Should remove everything except refactoring2
		rep4.trimRefactorings(3, 14);
		
		assertThat(rep4.totalRefactorings(), is(1));
		assertThat(rep4.getRefactoring(0), equalTo(refactoring2));
		//This should no longer exist, thus throw out of bounds
		assertThrows(IndexOutOfBoundsException.class, ()-> rep4.getRefactoring(1));
		
		//These two should no longer be equal
		assertThat(rep4, not(equalTo(rep)));
		
	}
	
	/**
	 * Test method sortRefactorings
	 */
	@Test 
	void testSortRefactorings() {
		//Original list, unsorted
		Report rep = new Report(refactoringList3);
		//Second list to compare against
		Report rep2 = new Report(refactoringList3);
		
		//Confirm equal
		assertThat(rep2, equalTo(rep));
		
		//Do the sort, which sorts from greatest opportunity to lowest
		rep2.sortRefactorings();
		
		//Expected order should now be refactoring 4, 1, 3, 2
		assertThat(rep2.totalRefactorings(), is(4));
		assertThat(rep2.getRefactoring(0), equalTo(refactoring4));
		assertThat(rep2.getRefactoring(1), equalTo(refactoring1));
		assertThat(rep2.getRefactoring(2), equalTo(refactoring3));
		assertThat(rep2.getRefactoring(3), equalTo(refactoring2));
		
		//Shouldn't be equal because ordering
		assertThat(rep2, not(equalTo(rep)));
		
		//add duplicate opportunity to refactoring list3
		refactoringList3.add(refactoring3);
		assertThat(refactoringList3.size(), is(5));
		
		//Check how it handles same Opportunity
		Report rep3 = new Report(refactoringList3);
		rep3.sortRefactorings();
		
		//Expected order should be refactoring 4, 1, 3, 3, 2
		assertThat(rep3.totalRefactorings(), is(5));
		assertThat(rep3.getRefactoring(0), equalTo(refactoring4));
		assertThat(rep3.getRefactoring(1), equalTo(refactoring1));
		assertThat(rep3.getRefactoring(2), equalTo(refactoring3));
		assertThat(rep3.getRefactoring(3), equalTo(refactoring3));
		assertThat(rep3.getRefactoring(4), equalTo(refactoring2));
	}
	
	
}
