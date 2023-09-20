import java.util.List;
import java.util.Random;

/**
 * Represents a player that does random moves.
 */
public class RandomPlayer extends Player {
    /**
     * Chooses a random move.
     *
     * @param board The current game board on which the player can make a move.
     * @return The chosen move represented as a {@link Position} on the board.
     */
    @Override
    public Position getMove(Board board) {
        // Get all possible moves
        List<Position> possibleMoves = board.getPossibleMoves();

        // Pick a random move
        Random r = new Random();
        int rand = r.nextInt(possibleMoves.size());
        return  possibleMoves.get(rand);
    }
}
