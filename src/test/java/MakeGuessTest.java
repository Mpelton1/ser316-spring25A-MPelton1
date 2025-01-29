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
    
}