# DupeDetector

CS350, Old Dominion Univ., Fall 2021

Team W7-4

* [Project Website on GH Pages](https://john-hix.github.io/CS350-dupe-detector/)
* [Stories](https://trello.com/b/XxTMxuGT/refactor-project-management)

# Website

## Install Jekyll
Since GitHub Pages uses Jekyll themes, it was fastest to get local dev set up
by using Jekyll.

Follow the installation instructions for Jekyll [here](https://jekyllrb.com/docs/installation/).

## Running Jekyll server locally

To view updated report stats and view the website locally, do the following:

- `git clone --branch gh-pages git@github.com:john-hix/CS350-dupe-detector.git dupe-detector/build/gh-pages/`
- `./gradlew build reports` 
- `cd dupe-detector/build/gh-pages`
- `bundle exec jekyll serve`
- Navigate in your browser to [127.0.0.1:4000/CS350-dupe-detector/](http://127.0.0.1:4000/CS350-dupe-detector/).

Updating markdown files will cause the site to be rebuilt automatically.

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

