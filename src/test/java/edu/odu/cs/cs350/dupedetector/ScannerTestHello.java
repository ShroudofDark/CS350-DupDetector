package edu.odu.cs.cs350.dupedetector;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import edu.odu.cs.cs350.dupedetector.tokentest.TokensHelloWorld;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat; 


public class ScannerTestHello {

    private Path getTestFilePath(String fileName) {
        // Courtesy https://www.baeldung.com/junit-src-test-resources-directory-path
        return Paths.get("src","test","resources", fileName);
    }

    @Test public void testHelloWorld() throws IOException {
        TokensHelloWorld helloExpected = new TokensHelloWorld();
        
        // Read in the test file and get a TokenStream iterator to the tokens
        Path path = getTestFilePath("HelloWorld.java");
        BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.US_ASCII);
        TokenStream stream = new TokenStream(reader);
        Iterator<Token> si = stream.iterator();


        ArrayList<Token> tokens = Lists.newArrayList(si);
        ArrayList<Integer> lineNumbers = new ArrayList<Integer>();
        ArrayList<Integer> colNumbers = new ArrayList<Integer>();
        for (Token t : tokens) {
            lineNumbers.add(t.getLineNumber());
        }
        for (Token t : tokens) {
            colNumbers.add(t.getColumnNumber());
        }

        assertThat(helloExpected.getLineNumbers(), is(lineNumbers));
        assertThat(helloExpected.getColumnNumbers(), is(colNumbers));
        assertThat(helloExpected.getTokens(), is(tokens));

    }

}
