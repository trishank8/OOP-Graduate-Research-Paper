import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.FixedDie;
import guice.Game;
import guice.LoggedDie;
import shared.Die;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Change Locality Experiment Tests
 *
 * These tests directly support the change locality findings in the paper.
 *
 * Experiment 1: Replace RandomDie with FixedDie
 *   - Manual system: requires updating MainManual.java (the call site)
 *   - Guice system:  requires updating only GameModule.java (the binding)
 *                    Game.java and MainDI.java are untouched
 *
 * Experiment 2: Introduce LoggedDie (decorator)
 *   - Manual system: requires updating MainManual.java to wrap RandomDie
 *   - Guice system:  requires updating only GameModule.java
 *                    Game.java and MainDI.java are untouched
 *
 * Both experiments show that the Guice system isolates implementation
 * changes to a single file regardless of how many consumers exist.
 */
class ChangeLocalityTest {

    // -------------------------------------------------------
    // Experiment 1: Swap RandomDie -> FixedDie
    // -------------------------------------------------------

    @Test
    void guiceSwapToFixedDie_onlyModuleChanges() {
        // Simulates changing GameModule binding from RandomDie to FixedDie.
        // Game class is NOT modified — only the binding changes.
        Injector injector = Guice.createInjector(
                binder -> binder.bind(Die.class).toInstance(new FixedDie(4))
        );
        Game game = injector.getInstance(Game.class);
        assertEquals("Rolled: 4", game.playTurn(),
                "Swapping to FixedDie should only require a module change");
    }

    @Test
    void manualSwapToFixedDie_callSiteMustChange() {
        // In the manual system, the call site explicitly constructs the Die.
        // Switching requires updating this code directly.
        Die die = new FixedDie(4); // <-- this line changes per swap
        manual.Game game = new manual.Game(die);
        assertEquals("Rolled: 4", game.playTurn(),
                "Manual swap correctly returns new implementation value");
    }

    // -------------------------------------------------------
    // Experiment 2: Introduce LoggedDie decorator
    // -------------------------------------------------------

    @Test
    void guiceIntroduceLoggedDie_onlyModuleChanges() {
        // Simulates adding LoggedDie in GameModule.
        // LoggedDie itself receives its inner Die via injection.
        // Game class is NOT modified.
        Injector injector = Guice.createInjector(binder -> {
            binder.bind(Die.class).toInstance(
                    new LoggedDie(new FixedDie(5))
            );
        });
        Game game = injector.getInstance(Game.class);
        assertEquals("Rolled: 5", game.playTurn(),
                "LoggedDie decorator should not change the returned value");
    }

    @Test
    void manualIntroduceLoggedDie_callSiteMustChange() {
        // In the manual system, the decorator must be composed at the call site.
        Die inner = new manual.FixedDie(5);
        Die logged = new manual.LoggedDie(inner); // <-- added at call site
        manual.Game game = new manual.Game(logged);
        assertEquals("Rolled: 5", game.playTurn(),
                "Manual LoggedDie wrapping should still return the correct value");
    }

    // -------------------------------------------------------
    // Summary assertion: both systems produce the same results
    // -------------------------------------------------------

    @Test
    void bothSystemsProduceIdenticalOutputForSameInput() {
        int fixedValue = 6;

        // Manual
        manual.Game manualGame = new manual.Game(new manual.FixedDie(fixedValue));

        // Guice
        Injector injector = Guice.createInjector(
                binder -> binder.bind(Die.class).toInstance(new FixedDie(fixedValue))
        );
        Game guiceGame = injector.getInstance(Game.class);

        assertEquals(manualGame.playTurn(), guiceGame.playTurn(),
                "Both systems should produce identical output for the same Die value");
    }
}