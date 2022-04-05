# SOS

## Introduction

[SOS](https://en.wikipedia.org/wiki/SOS_(game)) is a game for two or more players.  Before play begins, a square grid of at least 3x3 squares in size is drawn.  Players take turns to add either an "S" or an "O" to any square, with no requirement to use the same letter each turn. The object of the game is for each player to attempt to create the straight sequence S-O-S among connected squares (either diagonally, horizontally, or vertically), and to create as many such sequences as they can. If a player succeeds in creating an SOS, that player immediately takes another turn, and continues to do so until no SOS can be created on their turn. Otherwise turns alternate between players after each move.

My Java Swing version of SOS was designed for two players.  The grid can be any size between 3x3 and 10x10.  Here's what the GUI looks like when the game starts.

![Initial GUI](readme-resources/sos1.png)

The GUI is a tiny 542x397 pixels.  It should fit on any modern monitor screen.

Here's the GUI when using the full 10x10 grid.

![Initial GUI](readme-resources/sos2.png)

To place an S or an O, the player left-clicks on the square.

Here's the GUI after player 1 gets an SOS.

![SOS](readme-resources/sos3.png)
