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
	
	SuggestedRefactoring refactoring1 = new SuggestedRefactoring(12, 24, data1);
	SuggestedRefactoring refactoring2 = new SuggestedRefactoring(16, 16, data2);
	SuggestedRefactoring refactoring3 = new SuggestedRefactoring(6, 18, data3);
	
	SuggestedRefactoring[] refactoringList = {refactoring1, refactoring2, refactoring3}; 
	
	/**
	 * Test method for Report constructor
	 */
	@Test
	void testReport() {
		
	}
	
	/**
	 * Test method for Report's sort method
	 */
	@Test
	void testSortRefactorings() {
		
	}
}
