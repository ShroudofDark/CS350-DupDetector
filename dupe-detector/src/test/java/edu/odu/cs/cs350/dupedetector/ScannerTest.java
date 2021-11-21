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
import edu.odu.cs.cs350.dupedetector.tokentest.TokensInvalid;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat; 


public class ScannerTest {

    @Test
    public void testHelloWorld() throws IOException {
        TokensHelloWorld helloExpected = new TokensHelloWorld();        
        TokenStream stream = new TokenStream(getTestTokens("hello-world.cpp"));
        Iterator<Token> si = stream.iterator();

        ArrayList<Token> tokens = Lists.newArrayList(si);
        ArrayList<Integer> colNumbers = getAllColNums(tokens);
        ArrayList<Integer> lineNumbers = getAllLineNums(tokens);

        assertThat(helloExpected.getLineNumbers(), is(lineNumbers));
        assertThat(helloExpected.getColumnNumbers(), is(colNumbers));
        assertThat(helloExpected.getTokens(), is(tokens));

    }

    @Test
    public void testTokenizeInvalidCppStream() throws IOException {
        TokensInvalid helloExpected = new TokensInvalid();        
        TokenStream stream = new TokenStream(getTestTokens("invalid.cpp"));
        Iterator<Token> si = stream.iterator();

        ArrayList<Token> tokens = Lists.newArrayList(si);
        ArrayList<Integer> colNumbers = getAllColNums(tokens);
        ArrayList<Integer> lineNumbers = getAllLineNums(tokens);
        
        assertThat(helloExpected.getLineNumbers(), is(lineNumbers));
        assertThat(helloExpected.getColumnNumbers(), is(colNumbers));
        assertThat(helloExpected.getTokens(), is(tokens));
    }

    private BufferedReader getTestTokens(String fileName) throws IOException {
        // Courtesy https://www.baeldung.com/junit-src-test-resources-directory-path
        //  Read in the test file
        Path path = Paths.get("src","test","data", "cpp", fileName);
        return Files.newBufferedReader(path, StandardCharsets.US_ASCII);
    }

    private ArrayList<Integer> getAllLineNums(ArrayList<Token> tokens) {
        ArrayList<Integer> lineNumbers = new ArrayList<Integer>();
        for (Token t : tokens) {
            lineNumbers.add(t.getLineNumber());
        }
        return lineNumbers;
    }

    private ArrayList<Integer> getAllColNums(ArrayList<Token> tokens) {
        ArrayList<Integer> columnNumbers = new ArrayList<Integer>();
        for (Token t : tokens) {
            columnNumbers.add(t.getColumnNumber());
        }
        return columnNumbers;
    }

}
