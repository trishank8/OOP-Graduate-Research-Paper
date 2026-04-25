package guice;

import shared.Die;

/**
 * A Die implementation that always returns a fixed value.
 * Used in tests via a custom Guice binding to produce
 * deterministic results without modifying the Game class.
 */
public class FixedDie implements Die {
    private final int value;

    public FixedDie(int value) {
        this.value = value;
    }

    @Override
    public int roll() {
        return value;
    }
}