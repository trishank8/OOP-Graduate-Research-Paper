package guice;

import com.google.inject.AbstractModule;
import shared.Die;

/**
 * Guice module that defines the binding between the Die interface
 * and its concrete implementation.
 *
 * This is the ONLY file that needs to change when switching implementations.
 * To use FixedDie or LoggedDie instead of RandomDie, update the bind() call
 * here — the Game class and entry point remain untouched.
 *
 * This centralized responsibility demonstrates improved change locality
 * compared to the manual system.
 */
public class GameModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Die.class).to(RandomDie.class);
    }
}