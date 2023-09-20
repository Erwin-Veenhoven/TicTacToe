/**
 * Represents a player in a game.
 * Each player has an associated game piece (icon) and can make moves on a game board.
 */
public abstract class Player {
    private Icon icon = Icon.NONE;

    /**
     * Retrieves the game piece (icon) associated with this player.
     *
     * @return The game piece (icon) of this player.
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * Sets the game piece (icon) for this player.
     *
     * @param icon The game piece (icon) to be associated with this player.
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * Abstract method representing the player's strategy to choose a move on the game board.
     * Subclasses must implement this method to provide their specific move-selection logic.
     *
     * @param board The current game board on which the player can make a move.
     * @return The chosen move represented as a {@link Position} on the board.
     */
    abstract public Position getMove(Board board);
}
