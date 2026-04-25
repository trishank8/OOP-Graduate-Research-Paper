package guice;

import shared.Die;
import java.util.Random;

/**
 * A Die implementation that returns a random value between 1 and 6.
 * In the Guice system, this is bound in GameModule — never referenced
 * directly in the Game class or entry point.
 */
public class RandomDie implements Die {
    private final Random random = new Random();

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}