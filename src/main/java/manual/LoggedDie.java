package manual;

import shared.Die;

/**
 * A Die decorator that wraps another Die and logs each roll result.
 * Demonstrates the change locality experiment: in the manual system,
 * introducing this decorator requires updating the call site explicitly.
 */
public class LoggedDie implements Die {
    private final Die inner;

    public LoggedDie(Die inner) {
        this.inner = inner;
    }

    @Override
    public int roll() {
        int result = inner.roll();
        System.out.println("[LOG] Die rolled: " + result);
        return result;
    }
}