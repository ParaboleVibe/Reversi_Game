
public class Game {
    /**
     * Board for the game
     */
    private Board board;
    /**
     * first player
     */
    private final Player player1;
    /**
     * second player
     */
    private final Player player2;

    /**
     * Game constructor
     * @param typeOfGame type of game:
     *                   if equals 1 then pvp,
     *                   else pve
     */
    public Game(int typeOfGame){
        board = new Board();
        player1 = new Player("Human", -1, board);
        if (typeOfGame == 1) {
            player2 = new Player("Human", 1, board);
        } else {
            player2 = new Player("AI", 1, board);
        }
    }

    /**
     * Method where gameplay happens
     */
    public void gameplay(){
        boolean playersMoved;
        do {
            playersMoved = oneMoveByPlayer(player1);
            if (board.isNotFull()) {
                playersMoved |= oneMoveByPlayer(player2);
            }
        } while (board.isNotFull() && playersMoved);
        board.printField();
        System.out.println("Счёт: \n" +
                ("Human".equals(player2.typeOfPlayer) ? "Игрок №1 " : "Игрок ") + "(чёрные) - " + getResult(-1) + "\n" +
                ("Human".equals(player2.typeOfPlayer) ? "Игрок №2 " : "Компьютер ") + "(белые) - " + getResult(1) + "\n" +
                "---------------------------------------------------");
    }

    /**
     * Method for one move (by Human or AI)
     * @param player human or AI
     * @return success of move
     */
    private boolean oneMoveByPlayer(Player player) {
        if (player.canMove()) {
            if ("Human".equals(player.typeOfPlayer))
                board.printField();
            System.out.println("Ход " + ("Human".equals(player.typeOfPlayer) ? "игрока " : "компьютера."));
            player.makeMove();
            board.printField();
            return true;
        } else {
            System.out.println("Доступных ходов нет. " +
                    ("Human".equals(player.typeOfPlayer) ? "Игрок " : "Компьютер ") +
                    "пропускает ход.");
            return false;
        }
    }

    /**
     * Method for getting result of pve game
     * @param color color of player
     * @return count of player's pieces
     */
    public int getResult(int color) {
        return board.countOfPieces(color);
    }
}
