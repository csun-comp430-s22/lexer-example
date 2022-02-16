import java.util.List;
import java.util.ArrayList;

public class Tokenizer {
    // for the moment, the language:
    // exp ::= true | false
    //
    // valid program: truetruefalsefalsetruefalse
    // invalid: true true
    //
    // int x y = 17; // should fail to parse
    // intxy=17;
    public static List<Token> tokenize(final String input)
        throws TokenizerException {
        final List<Token> tokens = new ArrayList<Token>();
        int offset = 0;

        while (offset < input.length()) {
            // skip whitespace - breaks for trailing whitespace
            while (Character.isWhitespace(input.charAt(offset))) {
                offset++;
            }
            
            if (input.startsWith("true", offset)) {
                tokens.add(new TrueToken());
                offset += 4;
            } else if (input.startsWith("false", offset)) {
                tokens.add(new FalseToken());
                offset += 5;
            } else {
                throw new TokenizerException();
            }
        }

        return tokens;
    }
}
