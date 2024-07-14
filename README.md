# Project Title - Mancala Game

This is an object oriented mancala game that allows users to play a graphics based version of Mancala,
according to either Kalah or Ayo rules.

## Description

This project has full data functionality with accessors and mutators for all private variables, allowing other classes
to modify data. Methods are created for Pit, Player, Store, and MancalaGame classes, as well as exception classes
and a GUI that manages what the users see. The Board class from assignment 2 has been split into a GameRules and MancalaDataStructure
classes, where GamesRules has the main game logic represented in abstract Kalah and Ayo rule classes. A countable interface is
also implemented to take away the ambiguity of pits and stores. Lastly, the program has the ability to serialize all objects
in order for the user to save the game in between sessions.

## Getting Started

### Dependencies

You will need gradle and the pmd plugin for gradle to run this code
Version 17 of the JDK was used for this assignment

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


## Limitations

As of the final version of this project, there should be no errors or issues with this code,
however there a couple small rules this game does not implement:
- When a player's side is empty, all the stones on the other side go into the opponent's store
- Players can choose an empty pit on their own side as a valid move
- Serialization may not work depending on where the user saves the file

**If there is an issue with submission, please contact me at the info found below**

## Author Information

Name: Noah Frey
Student Number: 1229138
Email: nfrey01@uoguelph.ca

## Development History

Commit and pushing log:

Nov. 13th: Starting refactoring and working with PMD
Nov. 16th: Created responsibility matrices
Nov. 20th: Implemented abstract classes and began working with countable interface
Nov. 21st: Refactored all secondary classes
Nov. 22th: Completed and pushed serialization methods
Nov. 23rd: Finished a bugged version of Kalah, began working on Ayo
Nov. 24th: Completed Kalah, Ayo work continues, work on GUI begins
Nov. 25th: Kalah test cases pass, continuing with GUI
Nov. 26th: Ayo test cases pass completing Ayo, completed PMD refactoring, pushed assets folder, completed semi-functional GUI


## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)

