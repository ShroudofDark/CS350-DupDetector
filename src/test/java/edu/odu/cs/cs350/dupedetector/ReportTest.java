package edu.odu.cs.cs350.dupedetector;

//Junit Imports
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

//Wrappers for JUnit that make it more readable
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

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
	
	//Suggested refactorings go tokenAmount, Opportunity, Data currently
	SuggestedRefactoring refactoring1 = new SuggestedRefactoring(12, 24, 3, data1);
	SuggestedRefactoring refactoring2 = new SuggestedRefactoring(16, 16, 3, data2);
	SuggestedRefactoring refactoring3 = new SuggestedRefactoring(6, 18, 2, data3);
	SuggestedRefactoring refactoring4 = new SuggestedRefactoring(12, 36, 7, data4);
	
	SuggestedRefactoring[] refactoringList1 = {refactoring1, refactoring2, refactoring3}; 
	SuggestedRefactoring[] refactoringList2 = {refactoring1, refactoring3};
	SuggestedRefactoring[] refactoringList3 = {refactoring1, refactoring2, refactoring3, refactoring4};
	
	/**
	 * Test method for Report constructor
	 */
	@Test
	void testReport() {
		Report rep = new Report(refactoringList1);
		
		assertThat(rep.totalRefactorings(), is(3));
		assertThat(rep.getRefactoring(0), equalTo(refactoring1));
		assertThat(rep.getRefactoring(1), not(equalTo(refactoring3)));
		
		Report rep2 = new Report(refactoringList2);
		
		assertThat(rep2.totalRefactorings(), is(2));
		assertThat(rep, not(equalTo(rep2)));
	}
	
	/**
	 * Test method printReport
	 */
	void testPrintReport() {
		
	}
}
