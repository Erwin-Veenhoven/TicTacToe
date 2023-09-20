/**
 * Represents a Tic-Tac-Toe game manager. This class facilitates the playing of a Tic-Tac-Toe game
 * between two players, X and O, on a game board.
 */
public class TicTacToe {
    private final Board board;

    /**
     * Initializes a Tic-Tac-Toe game with a default board size of 3x3.
     */
    public TicTacToe() {
        this(3);
    }

    /**
     * Initializes a Tic-Tac-Toe game with a custom board size.
     *
     * @param size The size of the game board (number of rows and columns).
     */
    public TicTacToe(int size) {
        board = new Board(size);
    }

    /**
     * Starts the Tic-Tac-Toe game between the given X and O players.
     *
     * @param xPlayer The X player.
     * @param oPlayer The O player.
     */
    public void playGame(Player xPlayer, Player oPlayer) {
        // Set player icons
        xPlayer.setIcon(Icon.X);
        oPlayer.setIcon(Icon.O);

        // Game loop
        boolean loopGame = true;
        while (loopGame) {
            // Loop trough players
            Player[] players = new Player[]{xPlayer, oPlayer};
            for (Player player : players) {
                // Start player turn
                printBoardMsg("\n" + player.getIcon() + "'s turn");

                // Set move, if move is illegal opponent wins
                try {
                    board.setMove(player.getMove(board.copy()), player.getIcon());
                }
                catch (IllegalMoveException e) {
                    // End game
                    printBoardMsg("\n" + player.getIcon().opponent() + " won!");
                    loopGame = false;
                    break;
                }

                // Check for winner
                if (board.isWinner(player.getIcon())) {
                    // End game
                    printBoardMsg("\n" + player.getIcon() + " won!");
                    loopGame = false;
                    break;
                }

                // Check for draw
                if (board.isFull()) {
                    // End game
                    printBoardMsg("\nIts a draw");
                    loopGame = false;
                    break;
                }
            }
        }
    }

    /**
     * Prints a message along with the current game board.
     *
     * @param msg The message to be displayed.
     */
    private void printBoardMsg(String msg) {
        System.out.println();
        System.out.println(board);
        System.out.println(msg);
    }
}
