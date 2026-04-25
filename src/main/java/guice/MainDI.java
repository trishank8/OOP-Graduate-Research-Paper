package guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Entry point for the Guice dependency injection system.
 * The injector reads GameModule bindings and wires all dependencies automatically.
 *
 * Notice: RandomDie is never referenced here. Switching implementations
 * requires only changing GameModule, not this file.
 */
public class MainDI {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new GameModule());
        Game game = injector.getInstance(Game.class);
        System.out.println(game.playTurn());
    }
}