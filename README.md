# Robo rally
![Github Repo size](https://img.shields.io/github/repo-size/inf112-v19/Bender.svg?style=flat-square&logo=GitHub)
[![Java Version](https://img.shields.io/badge/Java_Version-1.7-blue.svg?style=flat-square&logo=Java)](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/9551cfb1be2c4646a922e5e798830533)](https://www.codacy.com/app/inf112-Bender/Bender?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=inf112-v19/Bender&amp;utm_campaign=Badge_Grade)
[![Known Vulnerabilities](https://snyk.io/test/github/inf112-v19/Bender/badge.svg?targetFile=pom.xml)](https://snyk.io/test/github/inf112-v19/Bender?targetFile=pom.xml)

[![Open Issues](https://img.shields.io/github/issues-raw/inf112-v19/Bender.svg?maxAge=2592000&colorB=critical)]() 
[![GClosed Issues](https://img.shields.io/github/issues-closed-raw/inf112-v19/Bender.svg?maxAge=2592000)]()

# Documentation
## The board loader
* The board loader can be used to parse text-files into two-dimensional arrays of tiles.
* The file format:
    * The file should start with two integers, *width* and *height*.
    * The next *height* lines should contain *width* integers seperated by spaces.
    * Example
```
10 10
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 2 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 0 0 0 2 0 0 0 0
0 0 0 0 0 0 0 0 0 0
0 0 1 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0
```
