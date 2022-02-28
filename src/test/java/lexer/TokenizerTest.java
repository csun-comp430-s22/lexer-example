package lexer;

import java.util.List;

public class TokenizerTest {
    public static void testEmptyString() throws TokenizerException {
        // check that tokenizing empty string works
        Tokenizer tokenizer = new Tokenizer("");
        List<Token> tokens = tokenizer.tokenize();
        assert(tokens.size() == 0);
    }

    public static void testOnlyWhitespace() throws TokenizerException {
        Tokenizer tokenizer = new Tokenizer("    ");
        List<Token> tokens = tokenizer.tokenize();
        assert(tokens.size() == 0);
    }

    // "true"
    // " true"
    // "true "
    // " true "
    // "true false"
    // "truefalse"
    // "(true"
    public static void testTrueByItself() throws TokenizerException {
        Tokenizer tokenizer = new Tokenizer("true");
        List<Token> tokens = tokenizer.tokenize();
        assert(tokens.size() == 1);
        Token trueToken = tokens.get(0);
        assert(trueToken instanceof TrueToken);
    }

    // Test-driven development: write tests first
    // 1. TokenizerTest.  Compile and run.
    // 2. Tokens/Tokenizer
    public static void main(String[] args) throws TokenizerException {
        testOnlyWhitespace();
        testEmptyString();
        testTrueByItself();
    }
}
