package manual;

import shared.Die;
import java.util.Random;

/**
 * A Die implementation that returns a random value between 1 and 6.
 * In the manual system, this is instantiated directly at the call site.
 */
public class RandomDie implements Die {
    private final Random random = new Random();

    @Override
    public int roll() {
        return random.nextInt(6) + 1;
    }
}