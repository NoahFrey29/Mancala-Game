# Project Title - Mancala Game

This is an object oriented mancala game that allows users to play a text based version of Mancala,
according to "capture" rules.

## Description

This project has full data functionality with accessors and mutators for all private variables, allowing other classes
to modify data. Methods are created for Pit, Player, Store, Board, and MancalaGame classes, as well as exception classes
and a TextUI that manages what the users see. Lastly, an overriden toString method is present in every class and managed by
TextUI. JUnit test cases that were given pass for all classes except Board, where the Board test cases written by me cover
the required methods.

## Getting Started

### Dependencies

In a folder similar to GP1, you will need gradle and checkstyle to run this code
Version 17 of the JDK was used for this assignment

**NOTE** No AI was used in my solution of the code. After completing my AI solution,
I opted to rely solely on myself to create a fully functional game

### Executing program

* Open terminal and move to cloned directory
* In terminal folder, complete:
```
gradle build
```
```
gradle echo
```
* copy the last line that is outputted from gradle echo. This will execute the program.
* Paste the line to run the program.

* Expected Output:


## Limitations

As of the final version of this project, there should be no errors or issues with this code,
however there a couple small rules this game does not implement:
- When a player's side is empty, all the stones on the other side go into the opponent's store
- Players can choose an empty pit on their own side as a valid move

**If there is an issue with submission, please contact me at the info found below**

## Author Information

Name: Noah Frey
Student Number: 1229138
Email: nfrey01@uoguelph.ca

## Development History

Commit and pushing log:

Oct. 17th: Started with creating function stubs and folder structure
Oct. 19th: Completed Pit, Player, and Store classes
Oct. 21st: Continued with code progress, changed file structure to be correct
Oct. 22nd: Created an AI solution
Oct. 23rd: Fixed AI solution folders, continued with coding
Oct. 24th: Completed most of Board
Oct. 25th: Pushed for bonus marks
Oct. 26th: Implemented exception classes and exception handling
Oct. 27th: Tested with given JUnit tests, began working on self-made JUnit cases too
Oct. 28th: Began TextUI, finished JUnit work
Oct. 29th: Finished TextUI, began code review
Oct. 30th: Finished code review, added last minute functionalities
Nov. 1st: Finished README and completed interview

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)

