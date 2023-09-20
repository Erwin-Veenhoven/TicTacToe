import java.util.Scanner;

/**
 * Represents a human player that uses the console to do a move.
 */
public class HumanPlayer extends Player {
    /**
     * Lets a human put moves on the game board using the console.
     *
     * @param board The current game board on which the player can make a move.
     * @return The chosen move represented as a {@link Position} on the board.
     */
    @Override
    public Position getMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        Position movePos;

        // Loop while move from user is invalid
        while (true) {
            int move;

            // Get move from user
            System.out.print("Enter move: ");
            String userInput = scanner.nextLine();

            // Check if move is an integer
            try {
                move = Integer.parseInt(userInput) - 1;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid move");
                continue;
            }

            // Check if move is possible
            movePos = new Position(move / board.size, move % board.size);
            if (board.isAllowed(movePos)) break;
            System.out.println("Invalid move");
        }

        return movePos; // Return the move
    }
}
