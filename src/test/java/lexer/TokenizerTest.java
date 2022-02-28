package lexer;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TokenizerTest {
    // annotation
    @Test
    public void testEmptyString() throws TokenizerException {
        // check that tokenizing empty string works
        Tokenizer tokenizer = new Tokenizer("");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(0, tokens.size());
        //assertTrue(tokens.size() == 0);
    }

    @Test
    public void testOnlyWhitespace() throws TokenizerException {
        Tokenizer tokenizer = new Tokenizer("    ");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(0, tokens.size());
        //assertTrue(tokens.size() == 0);
    }

    // "true"
    // " true"
    // "true "
    // " true "
    // "true false"
    // "truefalse"
    // "(true"
    @Test
    public void testTrueByItself() throws TokenizerException {
        Tokenizer tokenizer = new Tokenizer("true");
        List<Token> tokens = tokenizer.tokenize();
        assertEquals(1, tokens.size());
        Token trueToken = tokens.get(0);
        assertTrue(trueToken instanceof TrueToken);
    }

    // Test-driven development: write tests first
    // 1. TokenizerTest.  Compile and run.
    // 2. Tokens/Tokenizer
    // public static void main(String[] args) throws TokenizerException {
    //     testOnlyWhitespace();
    //     testEmptyString();
    //     testTrueByItself();
    // }
}
