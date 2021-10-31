package edu.odu.cs.cs350.dupedetector.tokentest;
import java.util.List;

import edu.odu.cs.cs350.dupedetector.Token;
import edu.odu.cs.cs350.dupedetector.TokenKinds;

public class TokensInvalid extends TokensProvider {
    public TokensInvalid() {
        tokens = List.of(
            new Token(TokenKinds.POUND, 1, 1),
            new Token(TokenKinds.IDENTIFIER, 1, 2, "include"),
            new Token(TokenKinds.LT, 1, 10),
            new Token(TokenKinds.IDENTIFIER, 1, 11, "iostream"),
            new Token(TokenKinds.GT, 1, 19),
            new Token(TokenKinds.USING, 3, 1),
            new Token(TokenKinds.NAMESPACE, 3, 7),
            new Token(TokenKinds.IDENTIFIER, 3, 17, "std"),
            new Token(TokenKinds.SEMICOLON, 3, 20),
            new Token(TokenKinds.INT, 5, 1),
            new Token(TokenKinds.IDENTIFIER, 5, 5, "main"),
            new Token(TokenKinds.LPAREN, 5, 9),
            new Token(TokenKinds.INT, 5, 10),
            new Token(TokenKinds.IDENTIFIER, 5, 14, "argc"),
            new Token(TokenKinds.COMMA, 5, 18),
            new Token(TokenKinds.CHAR, 5, 20),
            new Token(TokenKinds.CONST, 5, 25),
            new Token(TokenKinds.MULT, 5, 31),
            new Token(TokenKinds.IDENTIFIER, 5, 32, "argv"),
            new Token(TokenKinds.LBRACK, 5, 36),
            new Token(TokenKinds.RBRACK, 5, 37),
            new Token(TokenKinds.RPAREN, 5, 38),
            new Token(TokenKinds.LBRACE, 6, 1),
            new Token(TokenKinds.IDENTIFIER, 7, 5, "cout"),
            new Token(TokenKinds.LSHIFT, 7, 10),
            new Token(TokenKinds.STRING_LITERAL, 7, 27, "Hello, world!"),
            new Token(TokenKinds.LSHIFT, 7, 29),
            new Token(TokenKinds.IDENTIFIER, 7, 32, "endl"),
            new Token(TokenKinds.SEMICOLON, 7, 36),
            new Token(TokenKinds.IDENTIFIER, 9, 5, "Couldnt"),
            new Token(TokenKinds.THIS, 9, 13),
            new Token(TokenKinds.IDENTIFIER, 9, 18, "be"),
            new Token(TokenKinds.IDENTIFIER, 9, 21, "done"),
            new Token(TokenKinds.IDENTIFIER, 9, 26, "better"),
            new Token(TokenKinds.QUESTION, 9, 32),
            new Token(TokenKinds.RETURN, 11, 5),
            new Token(TokenKinds.INTEGER_LITERAL, 11, 12, "0"),
            new Token(TokenKinds.SEMICOLON, 11, 13),
            new Token(TokenKinds.RBRACE, 12, 1)
        );
    }
}
