public class Main {
    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.playGame(new HumanPlayer(), new AIPlayer(8));
    }
}