/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 1998-2018  Gerwin Klein <lsf@jflex.de>                    *
 * All rights reserved.                                                    *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 *                                                                         *
 * Modified by John Hicks for ODU course work, adaptation for C++ lexing   *
 *                                                                         *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/* Java 1.2 language lexer specification */

/* Use together with unicode.flex for Unicode preprocesssing */
/* and java12.cup for a Java 1.2 parser                      */

/* Note that this lexer specification is not tuned for speed.
   It is in fact quite slow on integer and floating point literals, 
   because the input is read twice and the methods used to parse
   the numbers are not very fast. 
   For a production quality application (e.g. a Java compiler) 
   this could be optimized */


package edu.odu.cs.cs350.dupedetector;
import edu.odu.cs.cs350.dupedetector.TokenKinds.*;

%%

%public
%class Scanner

%unicode

%line
%column

%type Token

%{
  StringBuilder string = new StringBuilder();
  
  private Token symbol(TokenKinds type) {
    return new Token(type, yyline+1, yycolumn+1);
  }

  private Token symbol(TokenKinds type, Object value) {
    return new Token(type, yyline+1, yycolumn+1, value.toString());
  }

  /** 
   * assumes correct representation of a long value for 
   * specified radix in scanner buffer from <code>start</code> 
   * to <code>end</code> 
   */
  private long parseLong(int start, int end, int radix) {
    long result = 0;
    long digit;

    for (int i = start; i < end; i++) {
      digit  = Character.digit(yycharat(i),radix);
      result*= radix;
      result+= digit;
    }

    return result;
  }
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | 
          {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*
DecLongLiteral    = {DecIntegerLiteral} [lL]

HexIntegerLiteral = 0 [xX] 0* {HexDigit} {1,8}
HexLongLiteral    = 0 [xX] 0* {HexDigit} {1,16} [lL]
HexDigit          = [0-9a-fA-F]

OctIntegerLiteral = 0+ [1-3]? {OctDigit} {1,15}
OctLongLiteral    = 0+ 1? {OctDigit} {1,21} [lL]
OctDigit          = [0-7]
    
/* floating point literals */        
FloatLiteral  = ({FLit1}|{FLit2}|{FLit3}) {Exponent}? [fF]
DoubleLiteral = ({FLit1}|{FLit2}|{FLit3}) {Exponent}?

FLit1    = [0-9]+ \. [0-9]* 
FLit2    = \. [0-9]+ 
FLit3    = [0-9]+ 
Exponent = [eE] [+-]? [0-9]+

/* string and character literals */
StringCharacter = [^\r\n\"\\]
SingleCharacter = [^\r\n\'\\]

%state STRING, CHARLITERAL

%%

<YYINITIAL> {

  /* keywords */
  "abstract"                     { return symbol(TokenKinds.ABSTRACT); }
  "boolean"                      { return symbol(TokenKinds.BOOLEAN); }
  "break"                        { return symbol(TokenKinds.BREAK); }
  "byte"                         { return symbol(TokenKinds.BYTE); }
  "case"                         { return symbol(TokenKinds.CASE); }
  "catch"                        { return symbol(TokenKinds.CATCH); }
  "char"                         { return symbol(TokenKinds.CHAR); }
  "class"                        { return symbol(TokenKinds.CLASS); }
  "const"                        { return symbol(TokenKinds.CONST); }
  "continue"                     { return symbol(TokenKinds.CONTINUE); }
  "do"                           { return symbol(TokenKinds.DO); }
  "double"                       { return symbol(TokenKinds.DOUBLE); }
  "else"                         { return symbol(TokenKinds.ELSE); }
  "extends"                      { return symbol(TokenKinds.EXTENDS); }
  "final"                        { return symbol(TokenKinds.FINAL); }
  "finally"                      { return symbol(TokenKinds.FINALLY); }
  "float"                        { return symbol(TokenKinds.FLOAT); }
  "for"                          { return symbol(TokenKinds.FOR); }
  "default"                      { return symbol(TokenKinds.DEFAULT); }
  "implements"                   { return symbol(TokenKinds.IMPLEMENTS); }
  "import"                       { return symbol(TokenKinds.IMPORT); }
  "instanceof"                   { return symbol(TokenKinds.INSTANCEOF); }
  "int"                          { return symbol(TokenKinds.INT); }
  "interface"                    { return symbol(TokenKinds.INTERFACE); }
  "long"                         { return symbol(TokenKinds.LONG); }
  "native"                       { return symbol(TokenKinds.NATIVE); }
  "new"                          { return symbol(TokenKinds.NEW); }
  "goto"                         { return symbol(TokenKinds.GOTO); }
  "if"                           { return symbol(TokenKinds.IF); }
  "public"                       { return symbol(TokenKinds.PUBLIC); }
  "short"                        { return symbol(TokenKinds.SHORT); }
  "super"                        { return symbol(TokenKinds.SUPER); }
  "switch"                       { return symbol(TokenKinds.SWITCH); }
  "synchronized"                 { return symbol(TokenKinds.SYNCHRONIZED); }
  "package"                      { return symbol(TokenKinds.PACKAGE); }
  "private"                      { return symbol(TokenKinds.PRIVATE); }
  "protected"                    { return symbol(TokenKinds.PROTECTED); }
  "transient"                    { return symbol(TokenKinds.TRANSIENT); }
  "return"                       { return symbol(TokenKinds.RETURN); }
  "void"                         { return symbol(TokenKinds.VOID); }
  "static"                       { return symbol(TokenKinds.STATIC); }
  "while"                        { return symbol(TokenKinds.WHILE); }
  "this"                         { return symbol(TokenKinds.THIS); }
  "throw"                        { return symbol(TokenKinds.THROW); }
  "throws"                       { return symbol(TokenKinds.THROWS); }
  "try"                          { return symbol(TokenKinds.TRY); }
  "volatile"                     { return symbol(TokenKinds.VOLATILE); }
  "strictfp"                     { return symbol(TokenKinds.STRICTFP); }
  
  /* boolean literals */
  "true"                         { return symbol(TokenKinds.BOOLEAN_LITERAL, true); }
  "false"                        { return symbol(TokenKinds.BOOLEAN_LITERAL, false); }
  
  /* null literal */
  "null"                         { return symbol(TokenKinds.NULL_LITERAL); }
  
  
  /* separators */
  "("                            { return symbol(TokenKinds.LPAREN); }
  ")"                            { return symbol(TokenKinds.RPAREN); }
  "{"                            { return symbol(TokenKinds.LBRACE); }
  "}"                            { return symbol(TokenKinds.RBRACE); }
  "["                            { return symbol(TokenKinds.LBRACK); }
  "]"                            { return symbol(TokenKinds.RBRACK); }
  ";"                            { return symbol(TokenKinds.SEMICOLON); }
  ","                            { return symbol(TokenKinds.COMMA); }
  "."                            { return symbol(TokenKinds.DOT); }
  
  /* operators */
  "="                            { return symbol(TokenKinds.EQ); }
  ">"                            { return symbol(TokenKinds.GT); }
  "<"                            { return symbol(TokenKinds.LT); }
  "!"                            { return symbol(TokenKinds.NOT); }
  "~"                            { return symbol(TokenKinds.COMP); }
  "?"                            { return symbol(TokenKinds.QUESTION); }
  ":"                            { return symbol(TokenKinds.COLON); }
  "=="                           { return symbol(TokenKinds.EQEQ); }
  "<="                           { return symbol(TokenKinds.LTEQ); }
  ">="                           { return symbol(TokenKinds.GTEQ); }
  "!="                           { return symbol(TokenKinds.NOTEQ); }
  "&&"                           { return symbol(TokenKinds.ANDAND); }
  "||"                           { return symbol(TokenKinds.OROR); }
  "++"                           { return symbol(TokenKinds.PLUSPLUS); }
  "--"                           { return symbol(TokenKinds.MINUSMINUS); }
  "+"                            { return symbol(TokenKinds.PLUS); }
  "-"                            { return symbol(TokenKinds.MINUS); }
  "*"                            { return symbol(TokenKinds.MULT); }
  "/"                            { return symbol(TokenKinds.DIV); }
  "&"                            { return symbol(TokenKinds.AND); }
  "|"                            { return symbol(TokenKinds.OR); }
  "^"                            { return symbol(TokenKinds.XOR); }
  "%"                            { return symbol(TokenKinds.MOD); }
  "<<"                           { return symbol(TokenKinds.LSHIFT); }
  ">>"                           { return symbol(TokenKinds.RSHIFT); }
  ">>>"                          { return symbol(TokenKinds.URSHIFT); }
  "+="                           { return symbol(TokenKinds.PLUSEQ); }
  "-="                           { return symbol(TokenKinds.MINUSEQ); }
  "*="                           { return symbol(TokenKinds.MULTEQ); }
  "/="                           { return symbol(TokenKinds.DIVEQ); }
  "&="                           { return symbol(TokenKinds.ANDEQ); }
  "|="                           { return symbol(TokenKinds.OREQ); }
  "^="                           { return symbol(TokenKinds.XOREQ); }
  "%="                           { return symbol(TokenKinds.MODEQ); }
  "<<="                          { return symbol(TokenKinds.LSHIFTEQ); }
  ">>="                          { return symbol(TokenKinds.RSHIFTEQ); }
  ">>>="                         { return symbol(TokenKinds.URSHIFTEQ); }
  
  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }

  /* character literal */
  \'                             { yybegin(CHARLITERAL); }

  /* numeric literals */

  /* This is matched together with the minus, because the number is too big to 
     be represented by a positive integer. */
  "-2147483648"                  { return symbol(TokenKinds.INTEGER_LITERAL, Integer.valueOf(Integer.MIN_VALUE)); }
  
  {DecIntegerLiteral}            { return symbol(TokenKinds.INTEGER_LITERAL, Integer.valueOf(yytext())); }
  {DecLongLiteral}               { return symbol(TokenKinds.INTEGER_LITERAL, new Long(yytext().substring(0,yylength()-1))); }
  
  {HexIntegerLiteral}            { return symbol(TokenKinds.INTEGER_LITERAL, Integer.valueOf((int) parseLong(2, yylength(), 16))); }
  {HexLongLiteral}               { return symbol(TokenKinds.INTEGER_LITERAL, new Long(parseLong(2, yylength()-1, 16))); }
 
  {OctIntegerLiteral}            { return symbol(TokenKinds.INTEGER_LITERAL, Integer.valueOf((int) parseLong(0, yylength(), 8))); }
  {OctLongLiteral}               { return symbol(TokenKinds.INTEGER_LITERAL, new Long(parseLong(0, yylength()-1, 8))); }
  
  {FloatLiteral}                 { return symbol(TokenKinds.FLOATING_POINT_LITERAL, new Float(yytext().substring(0,yylength()-1))); }
  {DoubleLiteral}                { return symbol(TokenKinds.FLOATING_POINT_LITERAL, new Double(yytext())); }
  {DoubleLiteral}[dD]            { return symbol(TokenKinds.FLOATING_POINT_LITERAL, new Double(yytext().substring(0,yylength()-1))); }
  
  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return symbol(TokenKinds.IDENTIFIER, yytext()); }  
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return symbol(TokenKinds.STRING_LITERAL, string.toString()); }
  
  {StringCharacter}+             { string.append( yytext() ); }
  
  /* escape sequences */
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }
  \\[0-3]?{OctDigit}?{OctDigit}  { char val = (char) Integer.parseInt(yytext().substring(1),8);
                        				   string.append( val ); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}

<CHARLITERAL> {
  {SingleCharacter}\'            { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, yytext().charAt(0)); }
  
  /* escape sequences */
  "\\b"\'                        { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\b');}
  "\\t"\'                        { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\t');}
  "\\n"\'                        { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\n');}
  "\\f"\'                        { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\f');}
  "\\r"\'                        { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\r');}
  "\\\""\'                       { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\"');}
  "\\'"\'                        { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\'');}
  "\\\\"\'                       { yybegin(YYINITIAL); return symbol(TokenKinds.CHARACTER_LITERAL, '\\'); }
  \\[0-3]?{OctDigit}?{OctDigit}\' { yybegin(YYINITIAL); 
			                              int val = Integer.parseInt(yytext().substring(1,yylength()-1),8);
			                            return symbol(TokenKinds.CHARACTER_LITERAL, (char)val); }
  
  /* error cases */
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated character literal at end of line"); }
}

/* error fallback */
[^]                              { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return symbol(TokenKinds.EOF); }