public class Task1 {
    public static void main(String[] args) {
        int countOfElems = 10;
        double[] arr = new double[countOfElems];
        double max, min, sum = 0;
        System.out.println("Массив:");
        for (int i = 0; i < countOfElems; ++i) {
            arr[i] = Math.random() * 10;
            System.out.printf("arr[" + i + "] = %.3f;\n", arr[i]);
        }
        min = arr[0];
        max = arr[0];
        for (int i = 1; i < countOfElems; ++i) {
            if (arr[i] < min)
                min = arr[i];
            if (arr[i] > max)
                max = arr[i];
            sum += arr[i];
        }
        System.out.printf("Максимальное значение: %.3f;\n", max);
        System.out.printf("Минимальное значение: %.3f;\n", min);
        System.out.printf("Среднее арифметическое значение: %.3f.", (sum / countOfElems));
    }
}