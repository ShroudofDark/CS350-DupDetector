package edu.odu.cs.cs350.dupedetector.tokentest;

import java.util.ArrayList;
import java.util.List;

import edu.odu.cs.cs350.dupedetector.Token;

public abstract class TokensProvider {
    protected List<Token> tokens;

    public List<Token> getTokens() {
        
        return tokens;
    }

    public ArrayList<Integer> getLineNumbers() {
        ArrayList<Integer> lineNums = new ArrayList<Integer>();
        for (Token t : tokens) {
            lineNums.add(t.getLineNumber());
        }
        return lineNums;
    }

    public ArrayList<Integer> getColumnNumbers() {
        ArrayList<Integer> colNums = new ArrayList<Integer>();
        for (Token t : tokens) {
            colNums.add(t.getColumnNumber());
        }
        return colNums;
    }

}
