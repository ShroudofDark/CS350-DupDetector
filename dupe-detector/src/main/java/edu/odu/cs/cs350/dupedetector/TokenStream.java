package edu.odu.cs.cs350.dupedetector;


/**
 * 
 * @author zeil, original
 */
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * @author zeil
 * start copied and adapted code
 */
public class TokenStream implements Iterable<Token> {
    
    /**
     * The list of tokens obtained from the actual scanner.
     */
    private List<Token> tokens;
    
   /**
    * Create a TokenStream from an input source.
    * @param input input source for this stream
    */
    public TokenStream(final Reader input) {
        // NOTE: using the %standalone setting in jflex will render starter code
        // similar to but not as complete as this.
        tokens = new LinkedList<Token>();
        Scanner scanner = new Scanner (input);
        try {
            Token token = scanner.yylex();
            while (token != null && token.getKind() != TokenKinds.EOF) {
                tokens.add (token);
                token = scanner.yylex();
            }
        } catch (IOException ex) {
            // Not necessarily a problem, depending on the input source
        }
    }

    /**
     * Return an iterator over the list of tokens.
     * @see java.lang.Iterable#iterator()
     * @return iterator
     */
    @Override
    public final Iterator<Token> iterator() {
        return tokens.iterator();
    }

    public int size() {
        return tokens.size();
    }
    
}
// end code copied and adapted from zeil.
