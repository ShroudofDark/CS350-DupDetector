Information Found at: https://www.cs.odu.edu/~zeil/cs350/f21/Protected/refactoring/index.html

# 1 Overview

This document describes DupDetector, a program for examining C++ code and making recommendations for near-duplicate code blocks that can be refactored into a separate function to reduce the overall size and complexity of the C++ program.

# 2 Background

Refactoring is the process of introducing changes to code that are intended to improve the code quality without affecting its behavior. For example, renaming a variable to give it a more descriptive name is one of the most common, and easily performed, types of refactoring.

A more challenging form of refactoring is extracting blocks of code that have been repeated in multiple different locations and forming it into a separate function that can then be called from each of those locations.

This form for refactoring has a big potential payoff in improving the code quality.

* If the function is given an appropriately descriptive name, then all of places from which it is called should become easier to read and understand.
* Isolating that repeated code into a function makes it available for unit testing.
* If a bug should be found in that code, it only needs to be fixed once, rather than needing to fix it separately in multiple different locations.

DupDetector is envisioned as a system to aid programmers in performing this refactoring by identifying candidate blocks of nearly duplicate code that should be considered for extraction into a function.

When identifying candidate blocks of code, DupDetector must ignore

* differences in comments,
* difference in indentation, blank lines, and other whitespace, and
* to a limited degree, differences in identifiers and constants.

## 2.1 Tokens and Lexemes

The first two items above suggest that we will not be focusing attention upon characters or strings or lines directly from the source code text, but upon the lexical elements that make up the source code being analyzed.

A token is a basic, indivisible unit of programming syntax, as defined by the rules of the programming language. Examples of tokens are operators, reserved words, numeric literals, and identifiers. For example, the code

```java
if (x < y0) {  // look here
```

would be considered to be a sequence of seven tokens:

   1. a reserved word “if”,
   2. a left parenthesis operator,
   3. an identifier,
   4. a less-than operator,
   5. another identifier,
   6. a right parenthesis operator,
   7. and a left bracket operator.

The blank spaces and comment are not considered to contribute tokens.

Closely related to the idea of a “token” is that of a “lexeme”. A lexeme is the sequence of characters that represents a specific token. For example, the same block of code has seven lexemes:

   1. “if”
   2. “(”
   3. “x”
   4. “<”
   5. “y0”
   6. “)”
   7. “{”

Some kinds of tokens can have different lexemes for each instance of that token kind. Identifiers, for example, are a single type of token, but we need to examine the lexeme to see which identifier is being named. For all of the other token kinds in our example, once we know the kind of token, we implicitly know the lexeme. That is, each kind of operator and each reserved word is generally considered a distinct kind of token.

This process of reducing a character stream to a stream of tokens is called lexical analysis or, less formally, scanning, and is a fundamental pre-processing step in compilers and other tools that parse source code.

## 2.2 Detecting Repeated Sequences

The problem of detecting long duplicated sequences of tokens in one or more strings is a hard one, but one that has already received a great deal of attention. It is, for example, at the heart of much of the work in computational genetics, where the genome sequences being analyzed are far longer than what would be encountered here (but have fewer distinct token types).

For this project, we will require some mechanism to identify which sequences of tokens start at multiple locations in the code being analyzed.
 
***
foo.cpp
```java
  ⋮
100: if (x < y)
101:     y = x + 1;
102: cout << y;
  ⋮
156: if (x < y)
157:    y = x + 1;
158: if (y > 0)
  ⋮
```
bar.h
```java
  ⋮
042:    if (a < b) b = a + 2;
043: }
  ⋮
056: if (a < b) a = b + 2;
057: cout << a;
  ⋮
```
Figure 1: Sample C++ Code
***

Figure 1 shows some sample C++ code that we might encounter during analysis.

Regardless of what occurs in the ⋮ portions, DupDetector should be able to determine that the twelve-token sequence

{if, left-parenthesis, identifier, less-than, identifier, right-parenthesis, identifier, assignment-op, identifier, plus-op, numeric-literal, semi-colon}

occurs starting at foo.cpp:100, foo.cpp:156, bar.h:042, and bar.h:056, and that the sixteen-token sequence

{if, left-parenthesis, identifier, less-than, identifier, right-parenthesis, identifier, assignment-op, identifier, plus-op, numeric-literal, semi-colon, identifier, left-shift-op, identifier, semi-colon}

occurs starting at foo.cpp:100 and bar.h:056.

## 2.3 Nearly Duplicate Sequences

When we turn code into a function, some of the symbols and constants in that code may be turned into function parameters, enhancing the reusability of that function. Therefore, code blocks can be candidates for refactoring even if their lexemes, specifically the exact identifier names and constants used, do not match exactly. On the other hand, we can’t ignore the lexemes entirely. The code “a = b / a;” and “a = a / b;” cannot be made to match by any simple substitution of lexemes.

It will also be necessary to determine if it is possible to find a mapping of lexemes from one string of tokens to the other that would bring the two into conformance. For example, the code blocks

```java
if (x < y)
    y = x + 1;
```

and

```java
if (a < b) b = a + 2;
```

would have the same sequence of tokens, and the lexemes of the first can be transformed into the second by the mapping

x→a,y→b,1→2

We will call blocks of code for which such a mapping exists “nearly duplicate”.

By way of contrast, the code blocks

```java
if (x < y)
    y = x + 1;
```

and

```java
if (a < b)
    a = b + 2;
```

cannot be matched by any mapping of lexemes.

Thus, in figure 1, we would be left with these candidates for refactoring:

* The token sequences of length 12 at foo.cpp:100, foo.cpp:156, and bar.h:056.
* The token sequences of length 16 at foo.cpp:100 and bar.h:056.

## 2.4 Opportunity for Improvement

Based upon those candidates, we might consider writing a function

```java
void refactored1 (? x, ?& y, int k)
{
  if (x < y)
     y = x + k;
}
```
(The programmer would need to determine the correct data type to insert in place if the ‘?’.)

Then the twelve-token sequences could be replaced by:

refactored1 (x, y, 1);
  ⋮
refactored1 (x, y, 1);
  ⋮
refactored1 (a, b, 2);

or we might suggest a different function based on the sixteen-token sequences:

```java
void refactored2 (? x, ?& y, int k)
{
  if (x < y)
     y = x + k;
  cout << y;
}
```
allowing a similar replacement for the two occurrences of that longer sequence.

Now, if we set aside the overhead of adding the function header and the function calls, refactored1 would permit a savings of 2∗12=24
tokens, while refactored2 would save only 1∗16=16

tokens, suggesting that refactored1 might be the better choice.

We would say that the repeated sequences of 12 tokens offer a higher opportunity for improvement than do the repeated sequences of 16 tokens in this example.

# 3 The Program

## 3.1 Invocation & Input

The program will be packaged in a Java jar file named DupDetector.jar. It will be invoked from the command line as:

java -jar DupDetector.jar nSuggestions [ properties ] path1 [ path2 … ]

where the [ ... ] denote optional parameters, and the parameters are interpreted as follows:

* nSuggestions is the maximum number of suggested refactorings to be printed
* properties is an optional path to a properties file, with file extension .ini, containing properties identified below in the format: propertyName = propertyValue
* path1, path2, … are paths to files containing C++ source code or directories containing C++ source code.
  * If a directory is named, then it is searched, recursively, for source code files, identified by names ending with an appropriate extension. The extensions that will be accepted is determined by the property CppExtensions, which has as its value a comma-separated list of file extensions, e.g.,    CppExtensions = C,cpp,h,hpp,H   The default value of CppExtensions is: cpp,h

All source files should contain ASCII text.

## 3.2 Output

Output from the program will occur in two sections.

The first section will be a list of all source files examined. These will be printed one per line, giving their absolute paths., a comma and space, then the number of tokens in that file.

E.g.,

Files scanned:
    /home/zeil/projects/cppProject1/src/foo.cpp, 485
    /home/zeil/projects/cppProject1/src/headers/bar.h, 222

Files should be listed in alphabetical order of the absolute path.

After a blank line, the second section begins. This section will contain zero to nSuggestions suggested refactorings.

Each suggested refactoring is separated from any following by a blank line. A suggested refactoring begins with a line indicating the opportunity for improvement and the number of tokens in the sequences involved. After that line will be the nearly duplicate sequences involved.

Each such sequence is listed by printing, on one line 1) the absolute path of the file containing the sequence, and 2) the line number and column number where that sequence begins. Then on the next line, print the lexemes that would be candidates for replacement by parameters of the common function representing all the sequences. These should be separated by blanks in the listing.

E.g.,

Opportunity 24, 12 tokens
/home/zeil/projects/cppProject1/src/foo.cpp:100:0
x y 1
/home/zeil/projects/cppProject1/src/foo.cpp:156:4
x y 1
/home/zeil/projects/cppProject1/src/headers/bar.h:056:8
a b 2

Opportunity 16, 16 tokens
/home/zeil/projects/cppProject1/src/foo.cpp:100:4
x y 1
/home/zeil/projects/cppProject1/src/headers/bar.h:056:4
a b 2

The suggested refactorings should be printed in descending order by opportunity. Within each suggestion, the sequences should be listed in alphabetical order by the file’s absolute path. Within each file, sequences should be listed in ascending numerical order by the line number.

The lexemes may be listed in any order, so long as the order is consistent within the entire suggestion. I.e., the kth

lexeme in each sequence must be ones that would be substituted for one another during the refactoring.

Not all near-duplicate sequences will be listed as suggestions.

* Suggestions involving token sequences of length less thant the property MinSequenceLength will not be printed. The default value of this property is 10, but can be changed in the optional properties file.
* Suggestions requiring substitutions of more than the property MaxSubstitutions lexemes will not be printed. The default value of this property is 8, but can be changed in the optional properties file.

The second section of output is terminated by a blank line, followed by a line stating

Printed k of n suggestions.

where k is the number of suggestions printed and n is the number of refactorings that would have qualified for printing if nSuggestions had been high enough.

# 4 Additional Requirements

As noted earlier, the program will be invoked as a CLI program and will not be interactive.

It will be implemented in Java 1.11, to provide compatibility with useful 3rd-partly libraries.

It will be delivered as a DupDetector.jar file that can be executed via a “java -jar” invocation. This Jar will include:

* Compiled code for the CodeComp system.
* Any necessary data files
* Compiled code from any third-party libraries required for execution of the program.

It shall not, however, include compiled versions of unit or integration test drivers and the libraries for their support.

The system should run on Windows, Linux, and MacOS systems equipped with an appropriate Java JRE.

It should be capable of handling typical source code file sizes and typical numbers of files.
