import java.util.List;
import java.util.ArrayList;

public class Tokenizer {
    private final String input;
    private int offset;

    public Tokenizer(final String input) {
        this.input = input;
        offset = 0;
    }
    
    // for the moment, the language:
    // exp ::= true | false
    //
    // valid program: truetruefalsefalsetruefalse
    // invalid: true true
    //
    // int x y = 17; // should fail to parse
    // Token: int
    // Token: variable(x)
    // Token: variable(y)
    // Token: =
    // Token: integer(17)
    // Token: ;
    //
    // intxy=17;
    // Token: variable(intxy)
    //
    public void skipWhitespace() {
        while (Character.isWhitespace(input.charAt(offset))) {
            offset++;
        }
    }

    // returns null if there are no more tokens left
    public Token tokenizeSingle() throws TokenizerException {
        skipWhitespace();
        if (offset < input.length()) {
            if (input.startsWith("true", offset)) {
                offset += 4;
                return new TrueToken();
            } else if (input.startsWith("false", offset)) {
                offset += 5;
                return new FalseToken();
            } else if (input.startsWith("if", offset)) {
                offset += 2;
                return new IfToken();
            } else if (input.startsWith("(", offset)) {
                offset += 1;
                return new LeftParenToken();
            } else if (input.startsWith(")", offset)) {
                offset += 1;
                return new RightParenToken();
            }  else if (input.startsWith("{", offset)) {
                offset += 1;
                return new LeftCurlyToken();
            } else if (input.startsWith("}", offset)) {
                offset += 1;
                return new RightCurlyToken();
            } else if (input.startsWith("else", offset)) {
                offset += 4;
                return new ElseToken();
            } else {
                throw new TokenizerException();
            }
        } else {
            return null;
        }
    }
    
    public List<Token> tokenize() throws TokenizerException {
        final List<Token> tokens = new ArrayList<Token>();
        Token token = tokenizeSingle();

        while (token != null) {
            tokens.add(token);
            token = tokenizeSingle();
        }

        return tokens;
    }
}
