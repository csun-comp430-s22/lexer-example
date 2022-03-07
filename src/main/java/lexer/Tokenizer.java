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

    // returns null if it wasn't an integer token
    public IntegerToken tryTokenizeInteger() {
        skipWhitespace();

        // 12345
        String number = "";

        while (offset < input.length() &&
               Character.isDigit(input.charAt(offset))) {
            number += input.charAt(offset);
            offset++;
        }

        if (number.length() > 0) {
            // convert string to an integer
            return new IntegerToken(Integer.parseInt(number));
        } else {
            return null;
        }
    }
    
    // returns null if it fails to read in any variable or keyword
    public Token tryTokenizeVariableOrKeyword() {
        skipWhitespace();
        
        String name = "";

        // idea: read one character at a time
        // when we are out of characters, check what the name is
        // if the name is special (e.g., "true"), emit the special token for it (e.g., TrueToken)
        // if the name isn't special (e.g., "foo"), emit a variable token for it (e.g., VariableToken("foo"))
        //
        // First character of the variable: letter
        // Every subsequent character: letter or a digit
        //

        if (offset < input.length() &&
            Character.isLetter(input.charAt(offset))) {
            name += input.charAt(offset);
            offset++;

            while (offset < input.length() &&
                   Character.isLetterOrDigit(input.charAt(offset))) {
                name += input.charAt(offset);
                offset++;
            }

            // by this point, `name` holds a potential variable
            // `name` could be "true"
            if (name.equals("true")) {
                return new TrueToken();
            } else if (name.equals("false")) {
                return new FalseToken();
            } else if (name.equals("if")) {
                return new IfToken();
            } else if (name.equals("else")) {
                return new ElseToken();
            } else {
                return new VariableToken(name);
            }
        } else {
            return null;
        }
    }

    // returns null if it couldn't read in a symbol
    public Token tryTokenizeSymbol() {
        skipWhitespace();
        Token retval = null;
        
        if (input.startsWith("(", offset)) {
            offset += 1;
            retval = new LeftParenToken();
        } else if (input.startsWith(")", offset)) {
            offset += 1;
            retval = new RightParenToken();
        }  else if (input.startsWith("{", offset)) {
            offset += 1;
            retval = new LeftCurlyToken();
        } else if (input.startsWith("}", offset)) {
            offset += 1;
            retval = new RightCurlyToken();
        }

        return retval;
    }
    
    // returns null if there are no more tokens left
    public Token tokenizeSingle() throws TokenizerException {
        Token retval = null;
        skipWhitespace();
        if (offset < input.length() &&
            (retval = tryTokenizeVariableOrKeyword()) == null &&
            (retval = tryTokenizeInteger()) == null &&
            (retval = tryTokenizeSymbol()) == null) {
            throw new TokenizerException();
        }

        return retval;
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
