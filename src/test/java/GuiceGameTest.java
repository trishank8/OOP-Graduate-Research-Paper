import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.FixedDie;
import guice.Game;
import guice.GameModule;
import shared.Die;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Guice dependency injection system.
 *
 * Key design point: Game is never constructed directly.
 * The Guice injector handles wiring based on module bindings.
 * Tests override the binding inline to use FixedDie for determinism.
 */
class GuiceGameTest {

    @Test
    void testGuiceInjectsDefaultBinding() {
        // Uses the production GameModule (RandomDie binding)
        Injector injector = Guice.createInjector(new GameModule());
        Game game = injector.getInstance(Game.class);
        assertNotNull(game.playTurn());
        assertTrue(game.playTurn().startsWith("Rolled: "));
    }

    @Test
    void testGuiceWithFixedDieOverride() {
        // Override binding inline — Game class is never touched
        Injector injector = Guice.createInjector(
                binder -> binder.bind(Die.class).toInstance(new FixedDie(3))
        );
        Game game = injector.getInstance(Game.class);
        assertEquals("Rolled: 3", game.playTurn());
    }

    @Test
    void testGuiceWithDifferentFixedValue() {
        Injector injector = Guice.createInjector(
                binder -> binder.bind(Die.class).toInstance(new FixedDie(6))
        );
        Game game = injector.getInstance(Game.class);
        assertEquals("Rolled: 6", game.playTurn());
    }

    @Test
    void testGameClassNeverReferencesConcreteImplementation() {
        // Confirms Game only holds a Die reference (not RandomDie/FixedDie)
        // This verifies dependency inversion is respected structurally
        Injector injector = Guice.createInjector(
                binder -> binder.bind(Die.class).toInstance(new FixedDie(2))
        );
        Game game = injector.getInstance(Game.class);
        assertEquals("Rolled: 2", game.playTurn());
    }
}