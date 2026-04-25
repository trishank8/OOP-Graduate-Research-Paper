package manual;

import shared.Die;

/**
 * Entry point for the manual dependency injection system.
 * All object creation and wiring is done explicitly here.
 *
 * To switch from RandomDie to FixedDie or LoggedDie,
 * this file must be updated directly.
 */
public class MainManual {
    public static void main(String[] args) {
        Die die = new RandomDie();
        Game game = new Game(die);
        System.out.println(game.playTurn());
    }
}