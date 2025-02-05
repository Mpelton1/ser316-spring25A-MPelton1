# Code Review Form

**Reviewer:** Malakai Pelton
**GH Repo:** https://github.com/Mpelton1/ser316-spring25A-MPelton1

| ID # | Location                                    | Problem Description | Problem File and Line Number | Category | Severity |
|------|---------------------------------------------|---------------------|------------------------------|----------|----------|
| 1    | Game.java, in the makeGuess() method | The makeGuess method is declared but not implemented.| Game.java Line 112| FD| BR|
| 2    | Game.java, in the Game(String fixedWord, String name) constructor | The name parameter is not used and the name is hardcoded to "Anna".| Game.java, Line 69| FD| MJ|
| 3    | Game.java, in the getName() method | The getName() method incorrectly returns this.answer instead of the player’s name.| Game.java, Line 38 | FD | BR |
| 4    | Game.java| The gameStatus is not properly updated in the game when the player guesses. It should show win, loss, or in progress. | Game.java, Lines 120-140| FD| MJ|
| 5    | Game.java, countLetters() method | The countLetters() method uses indexOf repeatedly, which leads to performance issues for longer words.| Game.java, Line 50| CS | LOW |
| 6    | Game.java, makeGuess() method | The makeGuess() method should return a double value, thats not 0.0.|Game.java, Line 112| FD| BR|
