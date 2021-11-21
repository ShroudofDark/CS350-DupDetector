package edu.odu.cs.cs350.dupedetector;


/**
 * Token class, stores the token type, the lexeme (the actual character string)
 * ant the location (column and line numbers).
 * @author zeil, original
 *
 */
public class Token implements Cloneable{

    /**
     * What kind of token is this?
     */
    private TokenKinds kind;

    /**
     * For variables & literals, the original lexical string.
     */
    private String lexeme;
    
    /**
     * Where did we find this within the file?
     */
    private int lineNumber;
    
    /**
     * Where did we find this within the file?
     */
    private int columnNumber;
    

    /**
     * Create a basic token with no explicit lexeme.
     * @param theKind the kind of token
     * @param line line number where token was found
     * @param column column number where token begins
     */
    public Token (final TokenKinds theKind, final int line, final int column) {
        kind = theKind;
        lexeme = "";
        lineNumber = line;
        columnNumber = column;
    }

    /**
     * Create a token.
     * @param theKind what kind of token
     * @param line line number where token was found
     * @param column column number where token begins
     * @param theLexeme the original lexeme
     */
    public Token (final TokenKinds theKind, final int line, final int column,
            final String theLexeme) {
        kind = theKind;
        lexeme = theLexeme;
        lineNumber = line;
        columnNumber = column;
    }

    /**
     * Representation of the token for debugging purposes.
     */
    @Override
    public final String toString() {
        if (getLexeme().length() > 0) {
            return getKind() + ":" + getLexeme();
        } else {
            return getKind().toString();
        }
    }

    /**
     * What kind of token is this?
     * @return the kind
     */
    public final TokenKinds getKind() {
        return kind;
    }


    /**
     * What is the character string (lexeme) associated with this token?
     * @return the lexeme
     */
    public final String getLexeme() {
        return lexeme;
    }

    /**
     * Gets lexeme with C++ quotation marks for char and string constants if applicable
     * @return the lexeme, with quotes if it is a char or string literal.
     */
    public String getLexemeQuoted() {
        if (kind == TokenKinds.CHARACTER_LITERAL) {
            return new String("'" + lexeme + "'");
        } else if (kind == TokenKinds.STRING_LITERAL) {
            return new String("\"" + lexeme + "\"");
        }
        return lexeme;
    }

    /**
     * @return the lineNumber
     */
    public int getLineNumber() {
        return lineNumber;
    }


    /**
     * @return the columnNumber
     */
    public int getColumnNumber() {
        return columnNumber;
    }

    /**
     * Does not account for lexeme
     */
    public boolean equals(Object rhs) {
        if (rhs.getClass() != this.getClass()) {
            return false;
        }
        Token other = (Token) rhs;
        return (
            kind == other.kind &&
            // lexeme == other.lexeme &&
            lineNumber == other.lineNumber &&
            columnNumber == other.columnNumber
        );
    }

    public final Token clone() {
        return new Token(kind, lineNumber, columnNumber, new String(lexeme));
    }

}
// end code copied and adapted from zeil.

