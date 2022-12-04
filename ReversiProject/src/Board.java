public class Board {
    /**
     * Squares with values:  Black = -1,
     * Empty = 0,
     * White = 1,
     * Available = 2
     */
    protected int[][] squares;
    /**
     * size of board side
     */
    protected final int size = 8;

    /**
     * Board constructor
     */
    public Board() {
        squares = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                squares[i][j] = 0;
            }
        }
        squares[4][4] = -1;
        squares[3][3] = -1;
        squares[4][3] = 1;
        squares[3][4] = 1;
    }

    /**
     * Try to find any empty positions
     * @return true if board isn't full
     */
    public boolean isNotFull() {
        return (countOfPieces(0) != 0);
    }

    /**
     * Method that goes through squares and counts pieces of a certain color
     * @param color color of required pieces
     * @return count of pieces of a certain color
     */
    public int countOfPieces(int color) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (squares[i][j] == color)
                    ++count;
            }
        }
        return count;
    }

    /**
     * Prints board
     */
    public void printField() {
        for (int i = 0; i <= size; ++i) {
            for (int j = 0; j <= size; ++j) {
                if (i == 0 || j == 0) {
                    if (i == 0 && j == 0)
                        System.out.print(" \t");
                    if (i == 0 && j != 0)
                        System.out.print(j + "\t");
                    if (j == 0 && i != 0)
                        System.out.print(i + "\t");
                } else {
                    if (squares[i - 1][j - 1] == -1) {
                        System.out.print("○\t");
                    } else if (squares[i - 1][j - 1] == 0) {
                        System.out.print("·\t");
                    } else if (squares[i - 1][j - 1] == 1) {
                        System.out.print("⬤\t");
                    } else if (squares[i - 1][j - 1] == 2) {
                        System.out.print("✲\t");
                    }
                }
            }
            System.out.println();
        }
    }

    /**
     * Prints available positions for human
     */
    public void printAvailablePieces() {
        int countOfAvailableMoves = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (squares[i][j] == 2) {
                    ++countOfAvailableMoves;
                    System.out.println(countOfAvailableMoves + ") Строка " + (i + 1) + ", столбец " + (j + 1));
                }
            }
        }
    }

    /**
     * Checks availability of mover from (x, y)
     * @param x row
     * @param y column
     * @return true if it is available to move from (x, y)
     */
    boolean isAvailableToMoveFromPiece(int x, int y) {
        boolean isAvailable = false;
        boolean moveIsOver;
        int currentX;
        int currentY;
        // проверяем всех соседей фишки
        for (int i = Math.max(0, x - 1); i <= Math.min(size - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(size - 1, y + 1); j++) {
                // если нашёлся сосед противоположного цвета
                if (squares[i][j] == -squares[x][y]) {
                    moveIsOver = false;
                    // встаём на позицию противника и смотрим дальше
                    currentX = i;
                    currentY = j;
                    // продолжаем двигаться в ту же сторону до пустой или своей клетки
                    do {
                        currentX += i - x;
                        currentY += j - y;
                        if ((currentX >= 0) && (currentX <= size - 1) &&
                                (currentY >= 0) && (currentY <= size - 1) &&
                                (squares[currentX][currentY] != 2) &&
                                (squares[currentX][currentY] != squares[x][y])) {
                            if (squares[currentX][currentY] == 0) {
                                isAvailable = true;
                                squares[currentX][currentY] = 2;
                                moveIsOver = true;
                            }
                        } else {
                            moveIsOver = true;
                        }
                    } while (!moveIsOver);
                }
            }
        }
        return isAvailable;
    }

    /**
     * Adds new piece to (x, y)
     * @param x row
     * @param y column
     * @param color color of the new piece
     */
    void addNewPiece(int x, int y, int color) {
        boolean directionIsOver;
        int currentX;
        int currentY;
        for (int i = Math.max(0, x - 1); i <= Math.min(size - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(size - 1, y + 1); j++) {
                // если нашёлся сосед противоположного цвета
                if (squares[i][j] == -color) {
                    directionIsOver = false;
                    // встаём на позицию соседа и смотрим дальше
                    currentX = i;
                    currentY = j;
                    // продолжаем двигаться в его сторону до пустой или своей клетки
                    do {
                        currentX += i - x;
                        currentY += j - y;
                        if ((currentX >= 0) && (currentX <= size - 1) &&
                                (currentY >= 0) && (currentY <= size - 1) &&
                                (squares[currentX][currentY] != 0) &&
                                (squares[currentX][currentY] != 2)) {
                            if (squares[currentX][currentY] == color) {
                                recolorLine(currentX, currentY, x, y);
                                directionIsOver = true;
                            }
                        } else {
                            directionIsOver = true;
                        }
                    } while (!directionIsOver);
                }
            }
        }
        recolorOptionsToNull();
    }



    /**
     * Recolors row from (x1, y1) to (x2, y2)
     * @param x1 row of first piece
     * @param y1 column of first piece
     * @param x2 row of second piece
     * @param y2 column of second piece
     */
    private void recolorLine(int x1, int y1, int x2, int y2) {
        int color = squares[x1][y1];
        if (x1 == x2)
            for (int i = y2; i != y1; i += (y2 > y1 ? -1 : 1)) {
                squares[x1][i] = color;
            }
        else if (y1 == y2) {
            for (int i = x2; i != x1; i += (x2 > x1 ? -1 : 1)) {
                squares[i][y1] = color;
            }
        } else {
            for (int i = 0; i < Math.abs(x1 - x2); i++) {
                squares[(x2 > x1) ? (x2 - i) : (x2 + i)][(y2 > y1) ? (y2 - i) : (y2 + i)] = color;
            }
        }
    }

    /**
     * Recolors all available squares back to empty
     */
    void recolorOptionsToNull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (squares[i][j] == 2)
                    squares[i][j] = 0;
            }
        }
    }

    /**
     * Counts summed impact from move to (x, y)
     * @param x row
     * @param y column
     * @param color color of recoloring
     * @return P(x, y)
     */
    public double impactFromMove(int x, int y, int color) {
        double impact = 0;
        if (x == 0 || x == 7)
            impact += 0.4;
        if (y == 0 || y == 7)
            impact += 0.4;
        boolean directionIsOver;
        int currentX;
        int currentY;
        for (int i = Math.max(0, x - 1); i <= Math.min(size - 1, x + 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(size - 1, y + 1); j++) {
                // если нашёлся сосед противоположного цвета
                if (squares[i][j] == -color) {
                    directionIsOver = false;
                    // встаём на позицию соседа и смотрим дальше
                    currentX = i;
                    currentY = j;
                    // продолжаем двигаться в его сторону до пустой или своей клетки
                    do {
                        currentX += i - x;
                        currentY += j - y;
                        if ((currentX >= 0) && (currentX <= size - 1) &&
                                (currentY >= 0) && (currentY <= size - 1) &&
                                (squares[currentX][currentY] != 0)) {
                            if (squares[currentX][currentY] == color) {
                                impact += countImpactFromLine(currentX, currentY, x, y);
                                directionIsOver = true;
                            }
                        } else {
                            directionIsOver = true;
                        }
                    } while (!directionIsOver);
                }
            }
        }
        return impact;
    }

    /**
     * Counts impact from line of pieces that could be recolored
     * @param x1 row of first piece
     * @param y1 column of first piece
     * @param x2 row of second piece
     * @param y2 column of second piece
     * @return impact of recoloring line
     */
    private int countImpactFromLine(int x1, int y1, int x2, int y2) {
        int impact = 0;
        int color = squares[x1][y1];
        if (x1 == x2)
            impact += countImpactFromRowOrColumn(x1, y1, y2, color);
        else if (y1 == y2)
            impact += countImpactFromRowOrColumn(y1, x1, x2, color);
        else
            for (int i = 0; i < Math.abs(x1 - x2); i++) {
                if (i != 0 && squares[(x2 > x1) ? (x2 - i) : (x2 + i)][(y2 > y1) ? (y2 - i) : (y2 + i)] != color) {
                    impact += 1;
                }
            }
        return impact;
    }

    /**
     * Counts impact from row/column recoloring
     * @param similarParam parameter that is similar for both coordinates
     * @param diffParam1 first param that differs
     * @param diffParam2 second param that differs
     * @param color color of recoloring
     * @return impact of recoloring row/column
     */
    private int countImpactFromRowOrColumn(int similarParam, int diffParam1, int diffParam2, int color) {
        int impact = 0;
        for (int i = diffParam2; i != diffParam1; i += (diffParam2 > diffParam1 ? -1 : 1)) {
            if (i != diffParam2 && squares[i][similarParam] != color) {
                if (similarParam == 0 || similarParam == 7 || i == 0 || i == 7)
                    impact += 2;
                else
                    impact += 1;
            }
        }
        return impact;
    }
}
