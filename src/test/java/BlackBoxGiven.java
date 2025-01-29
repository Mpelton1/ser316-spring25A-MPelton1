import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
class BlackBoxGiven {
   // Method that supplies instances of different classes to the parameterized test
   static Stream<Arguments> provideGuessingGameInstances() {
       return Stream.of(
               Arguments.of(new Game0()),
               Arguments.of(new Game1()),
               Arguments.of(new Game2()),
               Arguments.of(new Game3()),
               Arguments.of(new Game4())
       );
   }
   // Parameterized test that tests the same method on different classes
   @ParameterizedTest
   @MethodSource("provideGuessingGameInstances")
   @DisplayName("Test correct guess")
   public void statusWin(Game game) {
       game.initGame("lion", "Dr. M");
       double response = game.makeGuess("lion");
       assertEquals(0.0, response, 0.0);
       assertEquals(4, game.getPoints());
       assertEquals(1, game.getGameStatus());
   }
// Test incorrect guess with correct length, expecting response 2.0, points reduced by 1, and game in progress
   @ParameterizedTest
   @MethodSource("provideGuessingGameInstances")
   @DisplayName("Test incorrect guess with correct length")
   public void incorrectGuessCorrectLength(Game game) {
       game.initGame("lion", "Dr. M");
       double response = game.makeGuess("tiger");
       assertEquals(2.0, response, "Incorrect guess of correct length should return 2.0");
       assertEquals(9, game.getPoints(), "Points should be reduced by 1 for incorrect guess of the correct length");
       assertEquals(0, game.getGameStatus(), "Game status should be 0 (in progress) after incorrect guess");
   }
   // Test incorrect guess that partially matches the word, expecting response 3.0, points increased
   @ParameterizedTest
   @MethodSource("provideGuessingGameInstances")
   @DisplayName("Test partial match guess")
   public void partialMatchGuess(Game game) {
       game.initGame("lion", "Dr. M");
       double response = game.makeGuess("lone");
       assertEquals(3.0, response, "Partially correct guess should return 3.0");
       assertEquals(12, game.getPoints(), "Points should increase based on correct letters in partial match");
   }
   // Test repeated guess, expecting response 4.0, points reduced by 2, and game still in progress
   @ParameterizedTest
   @MethodSource("provideGuessingGameInstances")
   @DisplayName("Test repeated guess")
   public void repeatedGuess(Game game) {
       game.initGame("lion", "Dr. M");
       game.makeGuess("l");
       double response = game.makeGuess("l"); // Repeated guess
       assertEquals(4.0, response, "Repeated guess should return 4.0");
       assertEquals(8, game.getPoints(), "Points should be reduced by 2 for repeated guess");
       assertEquals(0, game.getGameStatus(), "Game status should be 0 (in progress) after repeated guess");
   }
   // Test guess with symbols/numbers, expecting response 4.1, points reduced by 3
   @ParameterizedTest
   @MethodSource("provideGuessingGameInstances")
   @DisplayName("Test guess with symbols or numbers")
   public void guessWithSymbolsOrNumbers(Game game) {
       game.initGame("lion", "Dr. M");
       double response = game.makeGuess("li0n!");
       assertEquals(4.1, response, "Guess with symbols or numbers should return 4.1");
       assertEquals(7, game.getPoints(), "Points should be reduced by 3 for invalid guess with symbols or numbers");
       assertEquals(0, game.getGameStatus(), "Game status should be 0 (in progress) after invalid guess");
   }
   // Test guess that's too long, expecting response 2.1, points reduced based on the difference
   @ParameterizedTest
   @MethodSource("provideGuessingGameInstances")
   @DisplayName("Test guess that's too long")
   public void guessTooLong(Game game) {
       game.initGame("lion", "Dr. M");
       double response = game.makeGuess("lions");
       assertEquals(2.1, response, "Guess that's too long should return 2.1");
       assertEquals(6, game.getPoints(), "Points should be reduced by 1 for too long guess");
       assertEquals(0, game.getGameStatus(), "Game status should be 0 (in progress) after too long guess");
   }
   // Test guess that's too short, expecting response 2.2, points reduced based on the difference
   @ParameterizedTest
   @MethodSource("provideGuessingGameInstances")
   @DisplayName("Test guess that's too short")
   public void guessTooShort(Game game) {
       game.initGame("lion", "Dr. M");
       double response = game.makeGuess("lo");
       assertEquals(2.2, response, "Guess that's too short should return 2.2");
       assertEquals(8, game.getPoints(), "Points should be reduced by 2 for too short guess");
       assertEquals(0, game.getGameStatus(), "Game status should be 0 (in progress) after too short guess");
   }
   // Test game over after 10 incorrect guesses, expecting response 5.0
   @ParameterizedTest
   @MethodSource("provideGuessingGameInstances")
   @DisplayName("Test game over after 10 incorrect guesses")
   public void gameOverAfter10IncorrectGuesses(Game game) {
       game.initGame("lion", "Dr. M");
       for (int i = 0; i < 10; i++) {
           game.makeGuess("tiger"); // Incorrect guess
       }
       double response = game.makeGuess("lion"); // Guess after 10 incorrect guesses
       assertEquals(5.0, response, "Game should be over after 10 incorrect guesses");
       assertEquals(0, game.getGameStatus(), "Game status should be 0 (game over) after 10 incorrect guesses");
   }
   // Test guess after game has been won, expecting response 5.1
   @ParameterizedTest
   @MethodSource("provideGuessingGameInstances")
   @DisplayName("Test guess after game won")
   public void guessAfterGameWon(Game game) {
       game.initGame("lion", "Dr. M");
       game.makeGuess("lion"); // Game won
       double response = game.makeGuess("tiger"); // Invalid guess after game is won
       assertEquals(5.1, response, "Game should not accept guesses after being won");
       assertEquals(1, game.getGameStatus(), "Game status should be 1 (won) after making a correct guess");
   }
   // Test guess after game is over, expecting response 5.1
   @ParameterizedTest
   @MethodSource("provideGuessingGameInstances")
   @DisplayName("Test guess after game over")
   public void guessAfterGameOver(Game game) {
       game.initGame("lion", "Dr. M");
       for (int i = 0; i < 10; i++) {
           game.makeGuess("tiger"); // Incorrect guesses
       }
       double response = game.makeGuess("lion"); // Invalid guess after game is over
       assertEquals(5.1, response, "Game should not accept guesses after being over");
       assertEquals(0, game.getGameStatus(), "Game status should be 0 (game over) after 10 incorrect guesses");
   }
}
  

