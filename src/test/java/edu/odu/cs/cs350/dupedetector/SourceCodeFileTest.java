package edu.odu.cs.cs350.dupedetector;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class SourceCodeFileTest {
    
    @Test
    void getSetPath() {
        SourceCodeFile file = new SourceCodeFile("/tmp/foo/bar.txt");
        assertThat(file.getPath(), is("/tmp/foo/bar.txt"));
    }

    @Test
    void lazyLoadTokens() {
        SourceCodeFile file = new SourceCodeFile("/tmp/foo/bar.txt");
        assertThat(file.getTotalTokens(), is(128)); // hard-coded value for stub.
        fail("Fail because this isn't actually implemented yet."); // fail because this isn't actually implemented yet.

        // Accessor-mutator coverage
        assertThat(file.getPath(), is("/tmp/foo/bar.txt")); 
    }
}
