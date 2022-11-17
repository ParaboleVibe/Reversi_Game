public class Task2 {
    public static void main(String[] args) {
        boolean isSimple;
        System.out.println("Простые числа в диапазоне от 2 до 100:");
        for (int i = 2; i <= 100; ++i) {
            isSimple = true;
            for (int j = 2; j < i; ++j) {
                if (i % j == 0) {
                    isSimple = false;
                }
            }
            if (isSimple)
                System.out.println(i);
        }
    }
}
