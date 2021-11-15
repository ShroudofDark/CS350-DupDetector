package edu.odu.cs.cs350.dupedetector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CLITest {
    @Test void CLItest() {
        CLI fileTest = new CLI();
        assertNotNull(fileTest.parseArgs(), "CLI should check property path and path index and iterate through");
        assertNotNull(fileTest.suppliedPaths(), "CLI should create list of the path files");
        assertNotNull(fileTest.numSuggestions(), "CLI shoudl check for number of path values");
        assertNotNull(fileTest.printForInvalidInvocation(), "CLI should not check for Invalid Invocations");
        
    }
}
