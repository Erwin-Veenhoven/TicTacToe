/**
 * This enum represents the different states on the game board, including 'X', 'O', and 'NONE'.
 */
public enum Icon {
    X, O, NONE;

    /**
     * Retrieves the opponent's game piece (icon).
     *
     * @return The game piece (icon) of the opponent.
     */
    public Icon opponent() {
        if (this == X) return O;
        if (this == O) return X;
        return NONE;
    }

    /**
     * Returns a string representation of the game piece (icon).
     * For 'NONE', it returns a space character.
     *
     * @return A string representation of the game piece (icon).
     */
    @Override
    public String toString() {
        if (this == NONE) {
            return " ";
        }
        return super.toString();
    }
}
