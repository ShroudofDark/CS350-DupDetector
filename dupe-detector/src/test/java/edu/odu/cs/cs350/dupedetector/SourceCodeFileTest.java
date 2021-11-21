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
    void testEquals() {
        SourceCodeFile a = new SourceCodeFile("foo.txt");
        SourceCodeFile a2 = new SourceCodeFile("foo.txt");
        SourceCodeFile b = new SourceCodeFile("bar.txt");

        assertThat(a.equals(b), is(false));
        assertThat(a.equals(a2), is(true));

        Object c = new Token(TokenKinds.AND, 0, 0);
        assertThat(a.equals(c), is(false));
    }

}
