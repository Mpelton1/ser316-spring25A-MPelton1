import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class guessTest {

    @Test
    public void testCorrectLetterGuess() {
        Game game = new Game("lion");
        double result = game.makeGuess("l");
        assertEquals(1.0 + 1, result, "The letter 'l' should occur once in 'lion'");
        assertEquals(6, game.getPoints(), "Points should increase by the number of occurrences");
    }

    @Test
    public void testIncorrectLetterGuess() {
        Game game = new Game("lion");
        double result = game.makeGuess("x");
        assertEquals(1.0, result, "The letter 'x' does not exist in 'lion'");
        assertEquals(5, game.getPoints(), "Points should not change when the guess is wrong");
    }

    @Test
    public void testCorrectWordGuess() {
        Game game = new Game("lion");
        double result = game.makeGuess("lion");
        assertEquals(0.0, result, "The word 'lion' is correct");
        assertEquals(9, game.getPoints(), "Points should be based on the length of the word (4 points)");
        assertEquals(1, game.getGameStatus(), "The game should be marked as won");
    }

    @Test
    public void testIncorrectWordGuess() {
        Game game = new Game("lion");
        double result = game.makeGuess("tiger");
        assertEquals(2.0, result, "The word 'tiger' has the correct length but is incorrect");
        assertEquals(4, game.getPoints(), "Points should decrease after a wrong word guess");
    }

    @Test
    public void testPartialWordGuess() {
        Game game = new Game("lion");
        double result = game.makeGuess("lone");
        assertEquals(3.0, result, "The word 'lone' is partially correct, has some correct letters");
        assertEquals(7, game.getPoints(), "Points should increase for a partial match");
    }

    @Test
    public void testRepeatedGuess() {
        Game game = new Game("lion");
        game.makeGuess("l");
        double result = game.makeGuess("l");
        assertEquals(4.0, result, "Guess 'l' was already used");
        assertEquals(3, game.getPoints(), "Points should decrease by 2 for repeated guess");
    }

    @Test
    public void testGuessWithInvalidCharacters() {
        Game game = new Game("lion");
        double result = game.makeGuess("li@#");
        assertEquals(4.1, result, "The guess 'li@#' contains invalid characters");
        assertEquals(2, game.getPoints(), "Points should decrease by 3 for invalid characters");
    }

    @Test
    public void testGameOverAfter10IncorrectGuesses() {
        Game game = new Game("lion");
        for (int i = 0; i < 9; i++) {
            game.makeGuess("a");
        }
        double result = game.makeGuess("b");
        assertEquals(5.0, result, "After 10 guesses, the game should be over");
        assertEquals(2, game.getGameStatus(), "The game should be set to 'Game Over'");
    }

    @Test
    public void testGuessAfterGameOver() {
        Game game = new Game("lion");
        for (int i = 0; i < 10; i++) {
            game.makeGuess("a");
        }
        double result = game.makeGuess("z");
        assertEquals(5.1, result, "Guesses after the game is over should return 5.1");
        assertEquals(2, game.getGameStatus(), "The game status should remain 'Game Over'");
    }

    @Test
    public void testGuessAfterWinning() {
        Game game = new Game("lion");
        game.makeGuess("lion");
        double result = game.makeGuess("tiger");
        assertEquals(5.1, result, "Guesses after the game is won should return 5.1");
        assertEquals(1, game.getGameStatus(), "The game status should remain 'Won'");
    }
}
