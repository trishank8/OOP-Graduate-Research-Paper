import manual.FixedDie;
import manual.Game;
import manual.LoggedDie;
import manual.RandomDie;
import shared.Die;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the manual dependency injection system.
 * Dependencies are wired by hand using constructor injection.
 * FixedDie is used to make test outcomes deterministic.
 */
class ManualGameTest {

    @Test
    void testGameWithFixedDieReturnsCorrectOutput() {
        Die die = new FixedDie(3);
        Game game = new Game(die);
        assertEquals("Rolled: 3", game.playTurn());
    }

    @Test
    void testGameWithDifferentFixedValue() {
        Die die = new FixedDie(5);
        Game game = new Game(die);
        assertEquals("Rolled: 5", game.playTurn());
    }

    @Test
    void testGameWithRandomDieReturnsNonNullOutput() {
        Die die = new RandomDie();
        Game game = new Game(die);
        assertNotNull(game.playTurn());
        assertTrue(game.playTurn().startsWith("Rolled: "));
    }

    @Test
    void testGameWithLoggedDieStillReturnsCorrectValue() {
        // LoggedDie wraps FixedDie — output value should still be correct
        Die inner = new FixedDie(4);
        Die logged = new LoggedDie(inner);
        Game game = new Game(logged);
        assertEquals("Rolled: 4", game.playTurn());
    }
}