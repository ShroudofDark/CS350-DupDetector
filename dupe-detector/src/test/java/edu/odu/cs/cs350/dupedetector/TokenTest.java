package edu.odu.cs.cs350.dupedetector;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class TokenTest {

    @Test
    public void lexmeSetGetWithoutLexeme() {
        Token tokenWithoutLex = new Token(TokenKinds.AND, 2, 3);
        Token before = (Token)tokenWithoutLex.clone(); // capture
        assertThat(tokenWithoutLex.getLexeme(), is(""));
        assertThat(tokenWithoutLex.getLineNumber(), is (2));
        assertThat(tokenWithoutLex.getColumnNumber(), is (3));
        assertThat(tokenWithoutLex.getKind(), is(TokenKinds.AND));
        
        // capture and compare, mutation coverage
        assertThat(tokenWithoutLex.getLexeme(), is(before.getLexeme()));
        assertThat(tokenWithoutLex.getLineNumber(), is (before.getLineNumber()));
        assertThat(tokenWithoutLex.getColumnNumber(), is (before.getColumnNumber()));
        assertThat(tokenWithoutLex.getKind(), is(before.getKind()));
    }

    @Test
    public void lexmeSetGetWithLexeme() {
        Token token = new Token(TokenKinds.IDENTIFIER, 6, 3, "foobar");
        Token before = (Token)token.clone(); // capture
        assertThat(token.getLexeme(), is("foobar"));
        assertThat(token.getLineNumber(), is (6));
        assertThat(token.getColumnNumber(), is (3));
        assertThat(token.getKind(), is(TokenKinds.IDENTIFIER));

        // capture and compare, mutation coverage
        assertThat(token.getLexeme(), is(before.getLexeme()));
        assertThat(token.getLineNumber(), is (before.getLineNumber()));
        assertThat(token.getColumnNumber(), is (before.getColumnNumber()));
        assertThat(token.getKind(), is(before.getKind()));
    }

    @Test
    public void lexemeQuotedNoQuotes() {
        Token token = new Token(TokenKinds.IDENTIFIER, 6, 3, "foobar");
        Token before = (Token)token.clone(); // capture
        assertThat(token.getLexeme(), is("foobar"));
        assertThat(token.getLexemeQuoted(), is ("foobar"));

        // capture and compare, mutation coverage
        assertThat(token.getLexeme(), is(before.getLexeme()));
        assertThat(token.getLineNumber(), is (before.getLineNumber()));
        assertThat(token.getColumnNumber(), is (before.getColumnNumber()));
        assertThat(token.getKind(), is(before.getKind()));
    }

    @Test
    public void lexemeQuotedChar() {
        Token token = new Token(TokenKinds.CHARACTER_LITERAL, 12, 88, "t");
        Token before = (Token)token.clone(); // capture
        assertThat(token.getLexeme(), is("t"));
        assertThat(token.getLexemeQuoted(), is("'t'"));

        // capture and compare, mutation coverage
        assertThat(token.getLexeme(), is(before.getLexeme()));
        assertThat(token.getLineNumber(), is (before.getLineNumber()));
        assertThat(token.getColumnNumber(), is (before.getColumnNumber()));
        assertThat(token.getKind(), is(before.getKind()));
    }

    @Test
    public void lexemeQuotedCharNoContent() {
        Token token = new Token(TokenKinds.CHARACTER_LITERAL, 12, 88);
        Token before = (Token)token.clone(); // capture
        assertThat(token.getLexeme(), is(""));
        assertThat(token.getLexemeQuoted(), is("''"));

        // capture and compare, mutation coverage
        assertThat(token.getLexeme(), is(before.getLexeme()));
        assertThat(token.getLineNumber(), is (before.getLineNumber()));
        assertThat(token.getColumnNumber(), is (before.getColumnNumber()));
        assertThat(token.getKind(), is(before.getKind()));
    }

    @Test
    public void colNumCstrGet() {
        Token token = new Token(TokenKinds.LBRACE, 12, 88);
        Token before = (Token)token.clone(); // capture
        assertThat(token.getColumnNumber(), is(88));

        // capture and compare, mutation coverage
        assertThat(token.getLexeme(), is(before.getLexeme()));
        assertThat(token.getLineNumber(), is (before.getLineNumber()));
        assertThat(token.getColumnNumber(), is (before.getColumnNumber()));
        assertThat(token.getKind(), is(before.getKind()));
    }   

    @Test
    public void lineNumCstrGet() {
        Token token = new Token(TokenKinds.UNION, 12, 127);
        Token before = (Token)token.clone(); // capture
        assertThat(token.getColumnNumber(), is(127));

        // capture and compare, mutation coverage
        assertThat(token.getLexeme(), is(before.getLexeme()));
        assertThat(token.getLineNumber(), is (before.getLineNumber()));
        assertThat(token.getColumnNumber(), is (before.getColumnNumber()));
        assertThat(token.getKind(), is(before.getKind()));
    }   

    @Test
    public void typeCstrGet() {
        Token token = new Token(TokenKinds.UNION, 12, 127);
        Token before = (Token)token.clone(); // capture
        assertThat(token.getColumnNumber(), is(127));

        // capture and compare, mutation coverage
        assertThat(token.getLexeme(), is(before.getLexeme()));
        assertThat(token.getLineNumber(), is (before.getLineNumber()));
        assertThat(token.getColumnNumber(), is (before.getColumnNumber()));
        assertThat(token.getKind(), is(before.getKind()));
    }
    
    @Test
    public void kindEquality() {
        Token token1 = new Token(TokenKinds.UNION, 12, 127);
        Token token2 = new Token(TokenKinds.UNION, 1222, 153);
        
        assertThat( token1.getKind(), is(TokenKinds.UNION) );
        assertThat( token1.getKind(), is(token2.getKind()) );

        // repeated
        assertThat( token1.getKind(), is(TokenKinds.UNION) );
        assertThat( token1.getKind(), is(token2.getKind()) );


    }
}
