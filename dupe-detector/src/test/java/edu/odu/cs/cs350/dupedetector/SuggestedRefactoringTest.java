package edu.odu.cs.cs350.dupedetector;

//Junit Imports
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//Wrappers for JUnit that make it more readable
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import static org.mockito.Mockito.*;

class SuggestedRefactoringTest {

	@Test
	void testOpportunityScoreNormal() {
		fail();
	}

	@Test
	void testDefaultConstructor() {
		fail();
	}

	@Test
	void testAddSequenceNormal() {
		// test getter/setter
		fail();
	}

	@Test
	void testAddSequenceSubsequentAdds() {
		// test getter/setter
		fail();
	}

	@Test
	void testAddSequenceIncompatible() {
		// should throw the exception
		fail();
	}

	/**
	 * Test method for SuggestedRefactoring constructor
	 * @throws TokenSequencesIncompatible
	 */
	@Test
	void testOutPutSameForIdenticalSuggestions() throws TokenSequencesIncompatible {

		// Mocks
		TokenSequence mockedTokenSequence1a = mock(TokenSequence.class);
		TokenSequence mockedTokenSequence1b = mock(TokenSequence.class);
		TokenSequence mockedTokenSequence1c = mock(TokenSequence.class);
		
		// mock getNumTokens because it is called in SuggestedRefactoring.addTokenSequence
		when(mockedTokenSequence1a.getNumTokens()).thenReturn(3);
		when(mockedTokenSequence1a.toString()).thenReturn(
			"/home/zeil/projects/cppProject1/src/foo.cpp:100:0\r\n"
			+ "x y 1\r\n"
		);
		when(mockedTokenSequence1b.getNumTokens()).thenReturn(3);
		when(mockedTokenSequence1b.toString()).thenReturn(
			"/home/zeil/projects/cppProject1/src/foo.cpp:156:4\r\n"
			+ "x y 1\r\n"
		);
		// mock getNumTokens because it is called in SuggestedRefactoring.addTokenSequence
		when(mockedTokenSequence1c.getNumTokens()).thenReturn(3);
		when(mockedTokenSequence1c.toString()).thenReturn(
			"/home/zeil/projects/cppProject1/src/headers/bar.h:056:8\r\n"
			+ "a b 2\r\n"
		);
		
		TokenSequence mockedTokenSequence2a = mock(TokenSequence.class);
		TokenSequence mockedTokenSequence2b = mock(TokenSequence.class);
		
		when(mockedTokenSequence2a.getNumTokens()).thenReturn(3);
		when(mockedTokenSequence2a.toString()).thenReturn(
			"/home/zeil/projects/cppProject1/src/headers/bar.h:056:8\r\n"
			+ "a b 2\r\n"
		);
		when(mockedTokenSequence2b.getNumTokens()).thenReturn(3);
		when(mockedTokenSequence2b.toString()).thenReturn(
			"/home/zeil/projects/cppProject1/src/headers/bar.h:056:8\r\n"
			+ "a b 2\r\n"
		);

		// System under test
		SuggestedRefactoring sugRef = new SuggestedRefactoring();
		SuggestedRefactoring sugRefSame = new SuggestedRefactoring();
		SuggestedRefactoring sugRefDiff = new SuggestedRefactoring();

		sugRef.addTokenSequence(mockedTokenSequence1a);
		sugRef.addTokenSequence(mockedTokenSequence1b);
		sugRef.addTokenSequence(mockedTokenSequence1c);

		sugRefSame.addTokenSequence(mockedTokenSequence1a);
		sugRefSame.addTokenSequence(mockedTokenSequence1b);
		sugRefSame.addTokenSequence(mockedTokenSequence1c);

		sugRefDiff.addTokenSequence(mockedTokenSequence2a);
		sugRefDiff.addTokenSequence(mockedTokenSequence2b);

		
		assertThat(sugRef.getTotalTokens(), is(9));
		assertThat(sugRef.getOpportunity(), is(24));
		assertThat(sugRef.getTotalSubs(), is(3));
		assertThat(sugRef.toString(), equalTo(sugRefSame.toString()));
		
		assertThat(sugRef.toString(), not(equalTo(sugRefDiff.toString())));
		
		assertThat(sugRef, equalTo(sugRefSame));
		assertThat(sugRef, not(equalTo(sugRefDiff)));
	}
}
