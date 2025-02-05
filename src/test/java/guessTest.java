import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class guessTest {

    @Test
    @DisplayName("Test correct guess")
    public void testCorrectGuess() {
        Game game = new Game("lion", "Dr. M");
        double response = game.makeGuess("lion");
        assertEquals(0.0, response, "Correct guess should return 0.0");
        assertEquals(4, game.getPoints(), "Points should be based on word length");
        assertEquals(1, game.getGameStatus(), "Game should be won after correct guess");
    }

    @Test
    @DisplayName("Test incorrect guess with correct length")
    public void testIncorrectGuessWithCorrectLength() {
        Game game = new Game("lion", "Dr. M");
        double response = game.makeGuess("tiger");
        assertEquals(2.0, response, "Incorrect guess with correct length should return 2.0");
        assertEquals(9, game.getPoints(), "Points should be reduced by 1 for incorrect guess");
        assertEquals(0, game.getGameStatus(), "Game should still be in progress");
    }

    @Test
    @DisplayName("Test repeated guess")
    public void testRepeatedGuess() {
        Game game = new Game("lion", "Dr. M");
        game.makeGuess("l");
        double response = game.makeGuess("l"); // Repeated guess
        assertEquals(4.0, response, "Repeated guess should return 4.0");
        assertEquals(8, game.getPoints(), "Points should be reduced by 2 for repeated guess");
    }

    @Test
    @DisplayName("Test guess with symbols or numbers")
    public void testGuessWithSymbols() {
        Game game = new Game("lion", "Dr. M");
        double response = game.makeGuess("li0n!");
        assertEquals(4.1, response, "Guess with symbols or numbers should return 4.1");
        assertEquals(7, game.getPoints(), "Points should be reduced by 3 for invalid guess with symbols or numbers");
    }

    @Test
    @DisplayName("Test guess too long")
    public void testGuessTooLong() {
        Game game = new Game("lion", "Dr. M");
        double response = game.makeGuess("lions");
        assertEquals(2.1, response, "Guess too long should return 2.1");
        assertEquals(6, game.getPoints(), "Points should be subtracted based on how many letters off");
    }

    @Test
    @DisplayName("Test guess after game over")
    public void testGuessAfterGameOver() {
        Game game = new Game("lion", "Dr. M");
        for (int i = 0; i < 10; i++) {
            game.makeGuess("tiger");
        }
        double response = game.makeGuess("lion");
        assertEquals(5.0, response, "Game should be over after 10 incorrect guesses");
    }
}
