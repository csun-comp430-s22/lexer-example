# lexer-example
Example lexer code for a small language

# The Language

md - markdown, markup language, like HTML

```
exp ::= true | false | if (exp) { exp } else { exp }
```

data Token = TrueToken | FalseToken | IfToken | LeftParenToken ...

Possible tokens:
- `true`
- `false`
- `if`
- `(`
- `)`
- `{`
- `}`
- `else`

Algebraic data types
- One type, but different ways to make that type
- Boolean, true and false
- Equivalent to `if`: pattern matching
- Swift, Rust: `enum`, `switch`

         Token
         /   \
  TrueToken  FalseToken

```java
              List<Token>
public static Token[] tokenize(String input) { ... }
```
