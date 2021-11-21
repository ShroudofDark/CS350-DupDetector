package edu.odu.cs.cs350.dupedetector;

//Junit Imports
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

//Wrappers for JUnit that make it more readable
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

class SuggestedRefactoringTest {

	/**
	 * Test method for SuggestedRefactoring constructor
	 */
	@Test
	void testSuggestedRefactoring() {
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
		
		SuggestedRefactoring sugRef = new SuggestedRefactoring(12, 24, 3, data1);
		SuggestedRefactoring sugRefSame = new SuggestedRefactoring(12, 24, 3, data1);
		SuggestedRefactoring sugRefDif = new SuggestedRefactoring(16, 16, 3, data2);
		
		assertThat(sugRef.getTotalTokens(), is(12));
		assertThat(sugRef.getOpportunity(), is(24));
		assertThat(sugRef.getTotalSubs(), is(3));
		assertThat(sugRef.toString(), equalTo(data1));
		
		assertThat(sugRef.toString(), not(equalTo(data2)));
		
		assertThat(sugRef, equalTo(sugRefSame));
		assertThat(sugRef, not(equalTo(sugRefDif)));
	}
}
