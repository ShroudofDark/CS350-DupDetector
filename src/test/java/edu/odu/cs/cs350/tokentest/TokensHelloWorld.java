package edu.odu.cs.cs350.tokentest;
import java.util.List;

import edu.odu.cs.cs350.dupedetector.Token;
import edu.odu.cs.cs350.dupedetector.TokenKinds;

public class TokensHelloWorld extends TokensProvider {
    public TokensHelloWorld() {
        tokens = List.of(
            new Token(TokenKinds.PUBLIC, 2, 1),
            new Token(TokenKinds.CLASS, 2, 8),
            new Token(TokenKinds.IDENTIFIER, 2, 14, "HelloWorld"),
            new Token(TokenKinds.LBRACE, 2, 25),
            new Token(TokenKinds.PUBLIC, 3, 5),
            new Token(TokenKinds.STATIC, 3, 12),
            new Token(TokenKinds.VOID, 3, 19),
            new Token(TokenKinds.IDENTIFIER, 3, 24, "main"),
            new Token(TokenKinds.LPAREN, 3, 28),
            new Token(TokenKinds.IDENTIFIER, 3, 29, "String"),
            new Token(TokenKinds.LBRACK, 3, 35),
            new Token(TokenKinds.RBRACK, 3, 36),
            new Token(TokenKinds.IDENTIFIER,  3, 38, "args"),
            new Token(TokenKinds.RPAREN, 3, 42),
            new Token(TokenKinds.LBRACE, 3, 44),
            new Token(TokenKinds.IDENTIFIER, 4, 9, "System"),
            new Token(TokenKinds.DOT, 4, 15),
            new Token(TokenKinds.IDENTIFIER, 4, 16, "out"),
            new Token(TokenKinds.DOT, 4, 19),
            new Token(TokenKinds.IDENTIFIER, 4, 20, "println"),
            new Token(TokenKinds.LPAREN, 4, 27),
            new Token(TokenKinds.STRING_LITERAL, 4, 42, "Hello, world!"),
            new Token(TokenKinds.RPAREN, 4, 43),
            new Token(TokenKinds.SEMICOLON, 4, 44),
            new Token(TokenKinds.RBRACE, 5, 5),
            new Token(TokenKinds.RBRACE, 6, 1)
        );
    }
    

}
