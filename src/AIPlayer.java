import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents an AI player that uses the minimax algorithm to make game moves.
 */
public class AIPlayer extends Player {
    private final int ply;
    private final Tiebreaker tiebreaker;
    private final Random random = new Random();

    /**
     * Creates a new instance of the {@link AIPlayer} class.
     *
     * @param ply The depth of the search tree (number of ply) the minimax algorithm explores.
     */
    public AIPlayer(int ply) {
        this(ply, Tiebreaker.RANDOM);
    }

    /**
     * Creates a new instance of the {@link AIPlayer} class.
     *
     * @param ply The depth of the search tree (number of ply) the minimax algorithm explores.
     * @param tiebreaker The tiebreaker the minimax algorithm will use when multiple moves have the same score.
     */
    public AIPlayer(int ply, Tiebreaker tiebreaker) {
        this.ply = ply;
        this.tiebreaker = tiebreaker;
    }

    /**
     * A collection of tiebreaker moves {@link AIPlayer} can use.
     */
    public enum Tiebreaker {
        RIGHT,
        LEFT,
        RANDOM
    }

    /**
     * Computes and returns the best move for the current player using the minimax algorithm.
     *
     * @param board The current game board on which the player can make a move.
     * @return The move chosen by the minimax algorithm represented as a {@link Position} on the board.
     */
    @Override
    public Position getMove(Board board) {
        return minimax(ply, board, getIcon()).move();
    }

    /**
     * Computes and returns the best move and score for a player using the minimax algorithm.
     *
     * @param ply The depth of the search tree (number of ply) to explore.
     * @param board The current game board.
     * @param player The player for whom the best move and score are being computed.
     * @return A `MoveScore` object representing the best move and its associated score.
     */
    private MoveScore minimax(int ply, Board board, Icon player) {
        // Get all possible moves
        List<Position> possibleMoves = board.getPossibleMoves();
        int[] scores = new int[possibleMoves.size()];

        for (int i = 0; i < possibleMoves.size(); i++) {
            // Temporarily put move on the board
            Position move = possibleMoves.get(i);
            board.set(move, player);

            // Give the current move a score.
            // Ply is added for wins and losses to give a higher score for
            // the quickest win and a lower score for a quicker loss.
            // +10 if the player wins
            if (board.isWinner(player)) scores[i] = 10 + ply;
            // -10 if the player loses
            else if (board.isWinner(player.opponent())) scores[i] = -10 - ply;
            // 0 if it's a draw
            else if (board.isFull()) scores[i] = 0;
            // 0 if the ply is 0
            else if (ply == 0) scores[i] = 0;
            // Else calculate the opponent score and invert it
            else scores[i] = -1 * minimax(ply - 1, board, player.opponent()).score;

            // Remove the move
            board.remove(move);
        }

        // Return the best move and score
        int index = tiebreakerMove(scores);
        return new MoveScore(possibleMoves.get(index), scores[index]);
    }

    /**
     * Decides the best move in an array. If multiple scores are the best it will decide based on the set tiebreaker.
     *
     * @param scores An array of scores to choose from.
     * @return The index of the best score in the array.
     */
    private int tiebreakerMove(int[] scores) {
        // Return 0 if array has only one item
        if (scores.length == 1) return 0;

        // Add all the indices of the biggest numbers to a list
        int biggest = max(scores);
        List<Integer> biggest_indices = new ArrayList<>();
        for (int i = 0; i < scores.length; i++)
            if (scores[i] == biggest) biggest_indices.add(i);

        // Pick the number from the list based on the set tiebreaker move
        switch (tiebreaker) {
            case LEFT -> { return biggest_indices.get(0); }
            case RIGHT -> { return biggest_indices.get(biggest_indices.size() - 1); }
            default -> {
                int index = random.nextInt(biggest_indices.size());
                return biggest_indices.get(index);
            }
        }
    }

    /**
     * Finds the biggest number in an array.
     *
     * @param numbers An array of numbers.
     * @return The biggest number in the array.
     */
    private int max(int[] numbers) {
        int highest = Integer.MIN_VALUE;
        for (int num : numbers)
            if (num > highest) highest = num;
        return highest;
    }

    /**
     * A data structure that stores a move and an associated score.
     *
     * @param move The move associated with this record.
     * @param score The score associated with the move.
     */
    private record MoveScore(Position move, int score) { }
}
