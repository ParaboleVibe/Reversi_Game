import java.util.Scanner;

public class Main {
    private static int bestRes = 0;
    private static Scanner in;

    /**
     * Prints Rules
     */
    private static void printRules() {
        System.out.println("Правила игры можно найти по ссылке:\n " +
                "https://drive.google.com/file/d/18WzMWH6D0GnBnBvc7DpxP15lWPaary1U/view?usp=sharing");
    }

    /**
     * Prints my rules
     */
    private static void printMyRules() {
        System.out.println("""
                А мои правила такие:
                Выбирайте тип игры - между двумя людьми либо с компьютером, введя номер режима
                Возможные ходы будут отображаться и списком, и на поле
                Чтобы выбрать клетку, вводите номер строки и номер столбца через пробел""");
    }

    /**
     * Prints options (main menu)
     */
    private static void printGameOptions() {
        System.out.print("""
                ---------------------------------------------------
                                    
                Запустить игру?
                \t1) Игрок против игрока
                \t2) Игрок против компьютера (нормальная сложность)
                Или же
                \t3) Вывести лучший результат против компьютера
                \t0) Завершить сессию
                Ваш выбор: №""");
    }

    public static int readIntNumber() {
        return in.nextInt();
    }

    /**
     * Main method
     * @param args args
     */
    public static void main(String[] args) {
        printRules();
        printMyRules();
        in = new Scanner(System.in);
        int command = 3;
        while (command != 0) {
            // choose pve / pvp
            printGameOptions();
            command = in.nextInt();
            if (command == 1 || command == 2) {                         // game initialisation
                Game game = new Game(command);
                game.gameplay();
                if (command == 2)
                    bestRes = Math.max(game.getResult(-1), bestRes);
                System.out.println("Игра окончена!");
            } else if (command == 3) {                                  // print results
                System.out.println("Лучший счёт против компьютера: " + bestRes);
            }
        }
        in.close();
    }
}

