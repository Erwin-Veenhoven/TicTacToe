import java.util.ArrayList;
import java.util.List;

/**
 * Represents a TicTacToe board.
 */
public class Board {
    public final int size;
    private final Icon[][] board;

    /**
     * Creates a new instance of the {@link Board} class.
     *
     * @param size The size of the board (number of rows and columns).
     */
    public Board (int size) {
        // Create the board
        this.size = size;
        this.board = new Icon[size][size];

        // Fill with spaces
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = Icon.NONE;
            }
        }
    }

    /**
     * Creates a new instance of the {@link Board} class based off an existing 2D array.
     *
     * @param data The raw data off the board.
     */
    public Board(Icon[][] data) {
        this.size = data.length;

        // Check if data is valid
        if (size == 0) throw new IllegalArgumentException("Data is empty.");
        for (Icon[] row : data) if (row.length != size) throw new IllegalArgumentException("Board is not square.");

        // Copy data
        this.board = copyData(data);
    }

    /**
     * Sets a player icon on the board without checking if it would be a legal move.
     *
     * @param position The position to put the icon.
     * @param icon The icon to put on the board.
     */
    public void set(Position position, Icon icon) {
        board[position.row()][position.col()] = icon;
    }

    /**
     * Removes a player icon from the board.
     *
     * @param position The position of the cell to clear.
     */
    public void remove(Position position) {
        board[position.row()][position.col()] = Icon.NONE;
    }

    /**
     * Puts a move on the board.
     *
     * @param move The position of the move to put on the board.
     * @param icon The icon of the player.
     * @throws IllegalMoveException when the move is not allowed.
     */
    public void setMove(Position move, Icon icon) throws IllegalMoveException {
        // Check if move is legal
        if (!isAllowed(move))
            throw new IllegalMoveException("Move is not allowed");

        // Set the move
        set(move, icon);
    }

    /**
     * Checks if a specific move is allowed.
     *
     * @param move The position of the move.
     * @return true if the move is allowed otherwise false.
     */
    public boolean isAllowed(Position move) {
        // Extract data
        int row = move.row();
        int col = move.col();

        // Check for illegal move
        if (row < 0 || row > size - 1 || col < 0 || col > size - 1)
            return false;

        // Check if cell is occupied
        return board[row][col] == Icon.NONE;
    }

    /**
     * Creates a List with all the moves that are still available.
     *
     * @return A List with possible moves.
     */
    public List<Position> getPossibleMoves() {
        List<Position> moves = new ArrayList<>();

        // Put positions off all empty cells in List
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == Icon.NONE)
                    moves.add(new Position(i, j));
            }
        }

        return moves;
    }

    /**
     * Creates a 2D array of {@link Icon} objects representing the raw data of the game board.
     *
     * @return A 2D array containing the raw data of the board.
     */
    public Icon[][] getData() {
        return copyData(board);
    }

    /**
     * Creates a copy of the board.
     *
     * @return A copy of the board.
     */
    public Board copy() {
        return new Board(copyData(board));
    }

    /**
     * Checks if a player has won.
     *
     * @param icon The icon of the player to check.
     * @return true if the player has won otherwise false.
     */
    public boolean isWinner(Icon icon) {
        return checkRows(icon) || checkCols(icon) || checkNE(icon) || checkNW(icon);
    }

    /**
     * Checks if each cell of the board is occupied.
     *
     * @return true if the board is full otherwise false.
     */
    public boolean isFull() {
        return getPossibleMoves().isEmpty();
    }

    /**
     * Formats the board in a clear way.
     * @return A string representation of the board.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(' ').append(board[i][j]);
                if (j < size - 1) sb.append(" |");
            }

            if (i < size - 1) sb.append('\n');
        }

        return sb.toString();
    }


    /**
     * Returns a deep-copy of a 2D {@link Icon} array.
     * @param data The array to copy.
     * @return a deep-copy of a 2D {@link Icon} array.
     */
    private Icon[][] copyData(Icon[][] data) {
        Icon[][] copy = new Icon[data.length][data.length];
        for (int i = 0; i < data.length; i++)
            System.arraycopy(data[i], 0, copy[i], 0, data.length);
        return copy;
    }

    /**
     * Checks if a player won horizontally.
     * @param icon The icon of the player to check.
     * @return true if the player has won otherwise false.
     */
    private boolean checkRows(Icon icon) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Break if cell does not contain player
                if (board[i][j] != icon) break;
                // Return true if the player has filled a row
                if (j == size - 1) return true;
            }
        }

        return false;
    }

    /**
     * Checks if a player won vertically.
     * @param icon The icon of the player to check.
     * @return true if the player has won otherwise false.
     */
    private boolean checkCols(Icon icon) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Break if cell does not contain player
                if (board[j][i] != icon) break;
                // Return true if the player has filled a column
                if (j == size - 1) return true;
            }
        }

        return false;
    }

    /**
     * Checks if a player won diagonally in the northwest direction.
     * @param icon The icon of the player to check.
     * @return true if the player has won otherwise false.
     */
    private boolean checkNW(Icon icon) {
        // Return false if a cell in diagonal does not contain player
        for (int i = 0; i < size; i++) {
            if (board[i][i] != icon) return false;
        }

        // Return true if player has filled diagonal
        return true;
    }

    /**
     * Checks if a player won diagonally in the northeast direction.
     * @param icon The icon of the player to check.
     * @return true if the player has won otherwise false.
     */
    private boolean checkNE(Icon icon) {
        // Return false if a cell in diagonal does not contain player
        for (int i = 0; i < size; i++) {
            if (board[i][size - 1 - i] != icon) return false;
        }

        // Return true if player has filled diagonal
        return true;
    }
}
