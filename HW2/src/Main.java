import java.util.Objects;
import java.util.Scanner;

public class Main {
    private static void RandomCase(Scanner in, Group group) {
        Student currentStudent;
        String currentState;
        try {
            currentStudent = group.ChooseLuckyStudent();
            System.out.println("Отвечает студент " + currentStudent.getName()
                    + ". Присутствует ли на паре?");
            do {
                System.out.print("(y/n): ");
                currentState = in.nextLine();
                if (Objects.equals(currentState, "y")) {
                    currentStudent.setState(true);
                    System.out.print("Оценка за ответ: ");
                    currentStudent.setGrade(in.nextInt());
                } else if (Objects.equals(currentState, "n")) {
                    currentStudent.setState(false);
                }
            } while (!Objects.equals(currentState, "y")
                    && !Objects.equals(currentState, "n"));
            System.out.println();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        // В группу я по умолчанию загружаю первых шестерых (по списку) замечательных одногруппников
        Group bpi219_ShortVersion = new Group();
        String command;
        Scanner in = new Scanner(System.in);

        System.out.println("bpi219_ShortVersion\n"
                + bpi219_ShortVersion.getCountOfStudents() + " students:\n"
                + bpi219_ShortVersion);
        System.out.println("Type \"/h\" to get some help");

        do {
            command = in.nextLine();
            switch (command) {
                case ("/h") -> {
                    System.out.println("1. /r - choose random student");
                    System.out.println("2. /l - list of students with grades");
                    System.out.println("3. /s - stop\n");
                }
                case ("/r") -> RandomCase(in, bpi219_ShortVersion);
                case ("/l") -> System.out.println(bpi219_ShortVersion);
                case ("/s") -> System.out.println("Занятие завершено.");
            }
        } while (!command.equals("/s"));
        in.close();
    }
}