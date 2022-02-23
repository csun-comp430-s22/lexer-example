package lexer;

// Token t1 = new TrueToken();
// Token t2 = new TrueToken();
// t1.equals(t2)?  // currently false
public class TrueToken implements Token {
    public boolean equals(final Object other) {
        return other instanceof TrueToken;
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return "true";
    }
}
