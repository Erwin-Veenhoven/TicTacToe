/**
 * An exception class that represents an illegal move in a game.
 * This exception is typically thrown when a player attempts to make an invalid move.
 */
public class IllegalMoveException extends Exception {

    /**
     * Constructs a new IllegalMoveException with the specified detail message.
     *
     * @param message A descriptive message explaining the illegal move.
     */
    public IllegalMoveException(String message) {
        super(message);
    }

    /**
     * Constructs a new IllegalMoveException with the specified cause.
     *
     * @param cause The cause of the illegal move, such as another exception that triggered it.
     */
    public IllegalMoveException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new IllegalMoveException with the specified detail message and cause.
     *
     * @param message A descriptive message explaining the illegal move.
     * @param cause The cause of the illegal move, such as another exception that triggered it.
     */
    public IllegalMoveException(String message, Throwable cause) {
        super(message, cause);
    }
}