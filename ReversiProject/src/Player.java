public class Player {
    /**
     * Type of player: Human or AI
     */
    public final String typeOfPlayer;
    /**
     * Color of this player's pieces
     */
    private final int colorOfPlayer;
    /**
     * Board
     */
    private Board board;

    /**
     * Constructor of Player objects
     * @param typeOfPlayer Type of player: Human or AI
     * @param colorOfPlayer Color of this player's pieces
     * @param board Board
     */
    public Player(String typeOfPlayer, int colorOfPlayer, Board board) {
        this.typeOfPlayer = typeOfPlayer;
        this.colorOfPlayer = colorOfPlayer;
        this.board = board;
    }

    /**
     * Checks all player's pieces, trying to find one with available moves
     * @return can player move or can't
     */
    public boolean canMove() {
        boolean moveIsPossible = false;
        for (int i = 0; i < board.size; i++) {
            for (int j = 0; j < board.size; j++) {
                // если фишка нашего цвета и из неё можно сделать ход
                if (board.squares[i][j] == colorOfPlayer && board.isAvailableToMoveFromPiece(i, j)) {
                    moveIsPossible = true;
                }
            }
        }
        return moveIsPossible;
    }

    /**
     * Makes move on (x, y) position
     */
    public void makeMove() {
        int x = 0;
        int y = 0;
        if ("Human".equals(typeOfPlayer)) {
            System.out.println((colorOfPlayer == 1 ? "(белые)." : "(чёрные).") + " Доступные ходы:");
            board.printAvailablePieces();
            do {
                System.out.println("Сделать ход на клетку (<x> <y>):");
                x = Main.readIntNumber();
                y = Main.readIntNumber();
            } while (x < 1 || x > 8 || y < 1 || y > 8 || board.squares[x - 1][y - 1] != 2);
            board.addNewPiece(x - 1, y - 1, colorOfPlayer);
        } else if ("AI".equals(typeOfPlayer)) {
            double bestImpact = 0;
            for (int i = 0; i < board.size; i++) {
                for (int j = 0; j < board.size; j++) {
                    if (board.squares[i][j] == 2 && (board.impactFromMove(i, j, colorOfPlayer) > bestImpact)) {
                        x = i;
                        y = j;
                        bestImpact = board.impactFromMove(i, j, colorOfPlayer);
                    }
                }
            }
            board.addNewPiece(x, y, colorOfPlayer);
        }
    }
}
