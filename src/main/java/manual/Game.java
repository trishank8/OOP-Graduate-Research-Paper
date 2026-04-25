package manual;

import shared.Die;

/**
 * Game class for the manual system.
 * Dependencies are passed explicitly through the constructor —
 * no framework annotations required.
 *
 * This makes the Die dependency visible in the constructor signature,
 * but the caller is responsible for creating and wiring the Die.
 */
public class Game {
    private final Die die;

    public Game(Die die) {
        this.die = die;
    }

    public String playTurn() {
        return "Rolled: " + die.roll();
    }
}