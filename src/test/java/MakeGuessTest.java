import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MakeGuessTest {

    private Game game = new Game();

    @Test
    public void correctGuess() {
        game.initGame("horse", "Zach");
        double result = game.makeGuess("horse");
        assertEquals(0, result, 0.0001);
    }
    @BeforeEach
    public void setup() {
        game = new Game("horse", "Zach"); // Initializing the game with a fixed answer "horse"
    }

    @Test
    @DisplayName("Test: countCorrectLetters with no guesses")
    public void testCountCorrectLetters_NoGuesses() {
        // No guesses made yet
        int result = game.countCorrectLetters();
        assertEquals(0, result, "Should return 0 when no guesses are made");
    }

    @Test
    @DisplayName("Test: countCorrectLetters with correct guesses")
    public void testCountCorrectLetters_CorrectGuesses() {
        // Adding guesses that are correct (letters in the word)
        game.guesses.add("h");
        game.guesses.add("o");

        int result = game.countCorrectLetters();
        assertEquals(2, result, "Should return 2 correct guesses for letters 'h' and 'o'");
    }

    @Test
    @DisplayName("Test: countCorrectLetters with incorrect guesses")
    public void testCountCorrectLetters_IncorrectGuesses() {
        // Adding incorrect guesses (letters not in the word)
        game.guesses.add("x");
        game.guesses.add("y");

        int result = game.countCorrectLetters();
        assertEquals(0, result, "Should return 0 correct guesses when no guessed letters are correct");
    }

    @Test
    @DisplayName("Test: countCorrectLetters with partial guesses")
    public void testCountCorrectLetters_PartialGuesses() {
        // Adding partial guesses (some letters in the word)
        game.guesses.add("h");
        game.guesses.add("r");

        int result = game.countCorrectLetters();
        assertEquals(2, result, "Should return 2 correct guesses for 'h' and 'r' in the word 'horse'");
    }

    @Test
    @DisplayName("Test: countCorrectLetters with mixed guesses")
    public void testCountCorrectLetters_MixedGuesses() {
        // Adding mixed guesses (correct and incorrect)
        game.guesses.add("h");
        game.guesses.add("x");
        game.guesses.add("e");

        int result = game.countCorrectLetters();
        assertEquals(2, result, "Should return 2 correct guesses for 'h' and 'e' in the word 'horse'");
    }

}

