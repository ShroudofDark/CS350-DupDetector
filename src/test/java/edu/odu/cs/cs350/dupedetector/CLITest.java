package edu.odu.cs.cs350.dupedetector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CLITest {
    @Test void CLItest() {
        CLI fileTest = new CLI();
        assertNotNull(fileTest.parseArgs(), "CLI should check property path and path index and iterate through");
        assertThat(fileTest.suppliedPaths(), ArrayList<String> supplied);
        assertTrue(fileTest.numSuggestions(3));
        assertNotNull(fileTest.printForInvalidInvocation(), "\"usage: java -jar DupDetector.jar nSuggestions [ properties ] path1 [ path2 … ]\");\r\n");
        
        
    	
    	
    }
}
