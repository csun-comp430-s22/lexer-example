package lexer;

public class VariableToken implements Token {
    public final String name;

    public VariableToken(final String name) {
        this.name = name;
    }

    public int hashCode() {
        return name.hashCode();
    }

    public String toString() {
        // name: foo
        // Output: Variable(foo)
        return "Variable(" + name + ")";
    }

    public boolean equals(final Object other) {
        if (other instanceof VariableToken) {
            final VariableToken asVar = (VariableToken)other;
            return name.equals(asVar.name);
        } else {
            return false;
        }
    }
}
