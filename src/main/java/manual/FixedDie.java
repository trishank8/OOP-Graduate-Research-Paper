package manual;

import shared.Die;

/**
 * A Die implementation that always returns a fixed value.
 * Used in tests to produce deterministic, predictable results
 * without relying on randomness.
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