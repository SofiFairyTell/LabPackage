package LabFunctions.lab1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Класс для определения количества чисел меньших, равных и больших заданного числа в последовательности.
 */
public class SequenceAnalyzer {

    private static final Logger logger = LogManager.getLogger(SequenceAnalyzer.class);

    private static double[] sequence; // последовательность чисел

    public SequenceAnalyzer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите длину последовательности: ");
        int n = scanner.nextInt();
        sequence = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Введите " + (i+1) + "-й элемент: ");
            sequence[i] = scanner.nextDouble();
        }
    }
    /**
     * Конструктор класса, инициализирующий последовательность заданным массивом чисел.
     *
     * @param sequence массив вещественных чисел
     */
    public SequenceAnalyzer(double[] sequence) {
        this.sequence = sequence;
    }
    /**
     * Метод, который запускает анализ последовательности чисел.
     * @param inputSequence Последовательность чисел.
     * @param targetNumber Число, для которого нужно определить количество чисел меньших, равных и больших.
     * @return Массив с количеством чисел меньших, равных и больших заданного числа соответственно.
     */
    public static int[] analyzeSequence(double[] inputSequence, double targetNumber) {
        int[] result = new int[3]; // Массив с количеством чисел меньших, равных и больших заданного числа.
        for (double number : inputSequence) {
            if (number < targetNumber) {
                result[0]++;
            } else if (number == targetNumber) {
                result[1]++;
            } else {
                result[2]++;
            }
        }
        return result;
    }

    public static  void MenuSequenceAnalyzer()
    {
        Scanner scanner = new Scanner(System.in);
            System.out.println("Какую последовательность будем использовать?:");
            System.out.println("1. По умолчанию");
            System.out.println("2. Сформируем новую");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    double[] sequence = { 1.2, 3.5, 0.8, 5.1, 2.7, 1.2, 1.2 };//Пример входной последовательности чисел.
                    SequenceAnalyzer analyzer = new SequenceAnalyzer(sequence);
                    break;
                case 2:
                    System.out.println("Формируем новую последовательность");
                    System.out.print("Введите количество элементов в массиве: ");
                    int length = scanner.nextInt();
                    if (length <= 0) {
                        logger.error("Ошибка: длина последовательности должна быть больше 0.");
                        throw new IllegalArgumentException("Длина последовательности должна быть больше 0.");
                    }
                    sequence = new double[length];
                    System.out.println("Введите элементы массива:");
                    for (int i = 0; i < length; i++) {
                        sequence[i] = scanner.nextDouble();
                    }
                    analyzer = new SequenceAnalyzer(sequence);
                    logger.debug("Создана последовательность длины {} со значением по умолчанию {}.", length, sequence);
                    break;
                default:
                    System.out.println("Ошибка: некорректный номер команды!");
        }
        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Анализ последовательности чисел");
            System.out.println("0. Выход");
            System.out.print("Введите номер команды: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Введите число для анализа: ");
                    double targetNumber = scanner.nextDouble();
                    int[] result = SequenceAnalyzer.analyzeSequence(sequence, targetNumber);
                    System.out.printf("Количество чисел меньше %f: %d\n", targetNumber, result[0]);
                    System.out.printf("Количество чисел равных %f: %d\n", targetNumber, result[1]);
                    System.out.printf("Количество чисел больших %f: %d\n", targetNumber, result[2]);
                    logger.debug("Посчитано чисел меньше {}, равных {} и больших {} в последовательности длины {}.", result[0], result[1], result[2], sequence.length);
                    break;
                case 0:
                    System.out.println("Выход из программы...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ошибка: некорректный номер команды!");
            }
        }

    }
}
