# DupeDetector

CS350, Old Dominion Univ., Fall 2021

Team W7-4

* [Project Website on GH Pages](https://john-hix.github.io/CS350-dupe-detector/)
* [Stories](https://trello.com/b/XxTMxuGT/refactor-project-management)

# Website
To develop the project website locally, you must first clone the gh-pages branch
to the build dir. This should be temporary, or later handled by a Gradle task.
From the project root, run
`git clone --branch gh-pages git@github.com:john-hix/CS350-dupe-detector.git dupe-detector/build/gh-pages/`.

# Attributions

Libraries are included in Gradle and contain licensing information through standard
means.

Code for the `Token`, `TokenKinds`, `TokenStream` classes  was adapted from
examples provided in Dr. Zeil's
[Jflex example repository](https://git.cs.odu.edu/zeil/jflexdemo/-/tree/master).

The Jflex specification in `src/main/jflex/cpp.jflex` is adapted from the example
repository and Gerwin Klein's Java JFlex specification, used under a BSD license.

Further, [Dr. Zeil's Design notes](https://www.cs.odu.edu/~zeil/cs350/f21/Protected/refactoringDesign/index.html#lexical-analysis)
have guided the implementation.

Adapted code is marked as such by source code comments.

