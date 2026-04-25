package shared;

/**
 * Die abstraction shared by both the manual and Guice systems.
 * Both systems depend on this interface rather than any concrete implementation,
 * satisfying the Dependency Inversion Principle (Martin 2003).
 */
public interface Die {
    int roll();
}