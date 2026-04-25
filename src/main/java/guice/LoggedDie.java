package guice;

import com.google.inject.Inject;
import shared.Die;

/**
 * A Die decorator that wraps another Die and logs each roll.
 * In the Guice system, introducing this decorator only requires
 * updating GameModule — the Game class is never touched.
 * This demonstrates superior change locality compared to the manual system.
 */
public class LoggedDie implements Die {
    private final Die inner;

    @Inject
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