package guice;

import com.google.inject.Inject;
import shared.Die;

/**
 * Game class for the Guice system.
 * The @Inject annotation signals to the Guice container that
 * the Die dependency should be provided at runtime.
 *
 * Note: The Game class only knows about the Die interface —
 * it never references RandomDie, FixedDie, or LoggedDie directly.
 * Which implementation is used is determined entirely by GameModule.
 */
public class Game {
    private final Die die;

    @Inject
    public Game(Die die) {
        this.die = die;
    }

    public String playTurn() {
        return "Rolled: " + die.roll();
    }
}