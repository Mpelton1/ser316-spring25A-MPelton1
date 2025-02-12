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
    public void statusWin(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("lion");
        assertEquals(0.0, response, 0.0);
        assertEquals(14, game.getPoints());
        assertEquals(1, game.getGameStatus());
    }
}