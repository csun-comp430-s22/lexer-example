# lexer-example
Example lexer code for a small language

# The Language

md - markdown, markup language, like HTML

```
x is a variable
i is an integer
exp ::= x | i | true | false | if (exp) { exp } else { exp }
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
- variable
- integers

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

Problems:

- Should have a helper method to cut down on what we need to do to tokenize things
- Need whitespace before/after
- Multiple tokens
- Test all the tokens
- Reducing redundancy between tests

Want:
- Unit testing framework (show output saying which tests pass / fail, won't bring down all tests on a failure)
- Library: JUnit
- Build tool - maven
