package edu.odu.cs.cs350.dupedetector;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author John Hicks
 * To hold a sequence of tokens, as part of a SuggestedRefactoring.
 * Distinct from the `TokenStream`, which reads tokens from source code.
 * @see SuggestedRefactoring
 * @see TokenStream
 */
public class TokenSequence {
    // Implementation: this LinkedList will hold only printable Tokens, ie those
    // that are either constants or identifiers.
    private LinkedList<Token> tokensToSubstitute;
    private int numTokens; // The actual number of tokens in the detected sequence
    private String filePath;
    private int startingLine;
    private int startingColumn;

    public TokenSequence() {
        numTokens = 0;
        startingLine = -1;
        startingColumn = -1;
        filePath = "No File path specified."; // For humans during debugging; shouldn't ever see this.
        tokensToSubstitute = new LinkedList<Token>();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String theFilePath) {
        filePath = theFilePath;
    }

    public int getNumTokens() {
        return numTokens;
    }

    public int getNumSubstitutions() {
        return tokensToSubstitute.size();
    }

    /**
     * Logically adds a token to the TokenSequence
     * @param t 
     * .
     */
    public void add(Token t) {
        if (tokensToSubstitute.isEmpty()) {
            startingLine = t.getLineNumber();
            startingColumn = t.getColumnNumber();
        }
        if (shouldAddToList(t)) {
            tokensToSubstitute.add(t);
        }
        numTokens++;
    }

    private boolean shouldAddToList(Token t) {
        TokenKinds k = t.getKind();
        if (
            k == TokenKinds.IDENTIFIER ||
            k == TokenKinds.CHARACTER_LITERAL ||
            k == TokenKinds.STRING_LITERAL ||
            k == TokenKinds.INTEGER_LITERAL ||
            k == TokenKinds.FLOATING_POINT_LITERAL ||
            k == TokenKinds.BOOLEAN_LITERAL ||
            k == TokenKinds.NULL_LITERAL
        )
        {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * @return -1 if no tokens are in the sequence
     */
    public int getStartingLine() {
        return startingLine;
    }

    /**
     * 
     * @return -1 if no tokens are in the sequence
     */
    public int getStartingColumn() {
        return startingColumn;
    }

    /**
     * Will only print characters eligible for substitution in a refactor
     */
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(filePath);
        out.append(':');
        out.append(Integer.toString(getStartingLine()));
        out.append(':');
        out.append(Integer.toString(getStartingLine()));
        out.append("\r\n");
        // print tokens separated by spaces
        Iterator<Token> it = tokensToSubstitute.iterator();
        while(it.hasNext()) {
            Token t = it.next();
            out.append(t.getLexemeQuoted());
            out.append(' ');
        }
        out.append("\r\n");
        return out.toString();
    }
}
