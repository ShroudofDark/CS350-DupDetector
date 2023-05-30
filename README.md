# DupeDetector

Some information below may not properly display as it links off to the private version of the repo, such as the github pages that were created during the project life time.

The overall goal of this project was to first introduce the team to software development on a larger scale and within groups. We worked through a small scale development cycle and integrated a variety of ideas such as version control, continous integration, testing, and so forth in one semester.

The actual code that was being developed was a program that would read in a file, one that had code for example, and determine common tokens in order to provide suggestions on how to refactor the code in order to make it more efficient. Ultimately the project was not finished, but that was not the goal of the class.

A full description of the project can be found here: https://github.com/ShroudofDark/CS350-DupDetector/blob/main/project-description.md

My overall contribution was primarily focused on creating the factoring report output, correlating them to the correct files, creating the properties files and code that reads in the files, and create the suggested refactoring class (although we did not get far enough to ultimately define duplicate tokens).

This can be further seen within the story board and commits. 

Original Information
***

CS350, Old Dominion Univ., Fall 2021

Team W7-4

* [Project Website on GH Pages](https://shroudofdark.github.io/CS350-DupDetector/)
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

