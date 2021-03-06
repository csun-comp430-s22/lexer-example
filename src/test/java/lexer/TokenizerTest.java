package lexer;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TokenizerTest {
    public void assertTokenizes(final String input,
                                final Token[] expected) throws TokenizerException {
        final Tokenizer tokenizer = new Tokenizer(input);
        final List<Token> received = tokenizer.tokenize();
        assertArrayEquals(expected,
                          received.toArray(new Token[received.size()]));
    }
    
    // annotation
    @Test
    public void testEmptyString() throws TokenizerException {
        // check that tokenizing empty string works
        assertTokenizes("", new Token[0]);
    }

    @Test
    public void testOnlyWhitespace() throws TokenizerException {
        assertTokenizes("    ", new Token[0]);
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
        assertTokenizes("true",
                        new Token[] { new TrueToken() });
    }
    
    // foo
    @Test
    public void testVariable() throws TokenizerException {
        assertTokenizes("foo",
                        new Token[] { new VariableToken("foo") });
    }

    // truetrue
    @Test
    public void testTrueTrueIsVariable() throws TokenizerException {
        assertTokenizes("truetrue",
                        new Token[]{ new VariableToken("truetrue") });
    }

    // true true
    @Test
    public void testTrueSpaceTrueAreTrueTokens() throws TokenizerException {
        assertTokenizes("true true",
                        new Token[]{ new TrueToken(), new TrueToken() });
    }

    @Test
    public void testSingleDigitInteger() throws TokenizerException {
        assertTokenizes("1",
                        new Token[]{ new IntegerToken(1) });
    }

    @Test
    public void testMultiDigitInteger() throws TokenizerException {
        assertTokenizes("123",
                        new Token[]{ new IntegerToken(123) });
    }
    
    @Test
    public void testAllRemaining() throws TokenizerException {
        assertTokenizes("(){}else if false",
                        new Token[]{
                            new LeftParenToken(),
                            new RightParenToken(),
                            new LeftCurlyToken(),
                            new RightCurlyToken(),
                            new ElseToken(),
                            new IfToken(),
                            new FalseToken()
                        });
    }

    @Test(expected = TokenizerException.class)
    public void testInvalid() throws TokenizerException {
        assertTokenizes("$", null);
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
