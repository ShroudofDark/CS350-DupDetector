package edu.odu.cs.cs350.dupedetector;

/**
 * All possible token types.
 * 
 * @author zeil, original
 * @author Gerwin Klein, jflex tokens
 *
 */
public enum TokenKinds {
    BOOLEAN_LITERAL,
    INTEGER_LITERAL,
    FLOATING_POINT_LITERAL,
    IDENTIFIER,
    STRING_LITERAL,
    CHARACTER_LITERAL,
    // some division
    ABSTRACT,
    BOOLEAN,
    BREAK,
    BYTE,
    CASE,
    CATCH,
    CHAR,
    CLASS,
    CONST,
    CONTINUE,
    DO,
    DOUBLE,
    ELSE,
    EXTENDS,
    FINAL,
    FINALLY,
    FLOAT,
    FOR,
    DEFAULT,
    IMPLEMENTS,
    IMPORT,
    INSTANCEOF,
    INT,
    INTERFACE,
    KEYWORD,
    LONG,
    NATIVE,
    NEW,
    GOTO,
    IF,
    PUBLIC,
    SHORT,
    SUPER,
    SWITCH,
    SYNCHRONIZED,
    PACKAGE,
    PRIVATE,
    PROTECTED,
    TRANSIENT,
    RETURN,
    VOID,
    STATIC,
    WHILE,
    THIS,
    THROW,
    THROWS,
    TRY,
    VOLATILE,
    STRICTFP,
    NULL_LITERAL,
    LPAREN,
    RPAREN,
    LBRACE,
    RBRACE,
    LBRACK,
    RBRACK,
    SEMICOLON,
    COMMA,
    DOT,
    EQ,
    GT,
    LT,
    NOT,
    COMP,
    QUESTION,
    COLON,
    EQEQ,
    LTEQ,
    GTEQ,
    NOTEQ,
    ANDAND,
    OROR,
    PLUSPLUS,
    MINUSMINUS,
    PLUS,
    MINUS,
    MULT,
    DIV,
    AND,
    OR,
    XOR,
    MOD,
    LSHIFT,
    RSHIFT,
    URSHIFT,
    PLUSEQ,
    MINUSEQ,
    MULTEQ,
    DIVEQ,
    ANDEQ,
    OREQ,
    XOREQ,
    MODEQ,
    LSHIFTEQ,
    RSHIFTEQ,
    URSHIFTEQ,
    EOF,
    BITAND,
    BITOR,
    COMPL,
    MOD_EQ,
    AND_EQ,
    DEREF,
    SCOPE,
    XOR_EQ,
    ASM,
    AUTO,
    BOOL,
    ENUM,
    OR_EQ,
    UNION,
    USING,
    DELETE,
    EXPORT,
    EXTERN,
    FRIEND,
    INLINE,
    SIGNED,
    SIZEOF,
    STRUCT,
    TYPEID,
    ALIGNAS,
    ALIGNOF,
    CHAR8_T,
    CONCEPT,
    MUTABLE,
    NULLPTR,
    TYPEDEF,
    VIRTUAL,
    WCHAR_T,
    CHAR16_T,
    CHAR32_T,
    CO_AWAIT,
    DECLTYPE,
    EXPLICIT,
    NOEXCEPT,
    OPERATOR,
    REFLEXPR,
    REGISTER,
    REQUIRES,
    TEMPLATE,
    TYPENAME,
    UNSIGNED,
    CO_RETURN,
    CONSTEVAL,
    CONSTEXPR,
    CONSTINIT,
    NAMESPACE,
    CONST_CAST,
    STATIC_CAST,
    DYNAMIC_CAST,
    THREAD_LOCAL,
    ATOMIC_CANCEL,
    ATOMIC_COMMIT,
    STATIC_ASSERT,
    ATOMIC_NOEXCEPT,
    REINTERPRET_CAST,
    CO_YIELD,
    POUND
};

// end code copied and adapted from zeil and Klein.
