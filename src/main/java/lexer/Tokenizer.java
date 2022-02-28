package lexer;

import java.util.List;
import java.util.ArrayList;

public class Tokenizer {
    private final String input;
    private int offset;

    // true
    // TrueToken
    //
    // true true
    // TrueToken, TrueToken
    //
    // truetrue
    // VariableToken("truetrue")
    
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
        while (offset < input.length() &&
               Character.isWhitespace(input.charAt(offset))) {
            offset++;
        }
    }

    public Token tryTokenizeVariable() {
        String name = "";

        // idea: read one character at a time
        // when we are out of characters, check what the name is
        // if the name is special (e.g., "true"), emit the special token for it (e.g., TrueToken)
        // if the name isn't special (e.g., "foo"), emit a variable token for it (e.g., VariableToken("foo"))
        
        
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
