package edu.odu.cs.cs350.dupedetector;

//Junit Imports
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Wrappers for JUnit that make it more readable
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import java.util.*;

class ReportTest {

	//Data for SuggestedRefactoring until class is implemented (tests will need to be reviewed afterwords)
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
	
	//Suggested refactorings go tokenAmount, Opportunity, totalSug, data currently
	SuggestedRefactoring refactoring1 = new SuggestedRefactoring(12, 24, 3, data1);
	SuggestedRefactoring refactoring2 = new SuggestedRefactoring(16, 16, 3, data2);
	SuggestedRefactoring refactoring3 = new SuggestedRefactoring(6, 18, 2, data3);
	SuggestedRefactoring refactoring4 = new SuggestedRefactoring(12, 36, 7, data4);
	
	ArrayList<SuggestedRefactoring> refactoringList1;
	ArrayList<SuggestedRefactoring> refactoringList2;
	ArrayList<SuggestedRefactoring> refactoringList3;
	
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
	}
	
	/**
	 * Deallocate information once a test is done
	 */
	@AfterEach
	public void cleanUp() {
		refactoringList1.clear();
		refactoringList2.clear();
		refactoringList3.clear();
	}
	
	/**
	 * Test method for Report constructor
	 */
	@Test
	void testReport() {
		//Note when I come back to this, make sure to check deep copy by changing the original and seeing if still equal
		Report rep = new Report(refactoringList1);
		
		//Check that information is as expected
		assertThat(rep.totalRefactorings(), is(3));
		assertThat(rep.getRefactoring(0), equalTo(refactoring1));
		assertThat(rep.getRefactoring(1), not(equalTo(refactoring3)));
		
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
	void testPrintReport() {
		
	}
}
