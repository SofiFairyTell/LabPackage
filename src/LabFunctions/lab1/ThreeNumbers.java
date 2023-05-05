package LabFunctions.lab1;

import LabFunctions.lab2.LoggerClass;

import java.util.Scanner;

/**
 * Класс для определения что сумма двух чисел из трех является положительной.
 */
public class ThreeNumbers {
    private int a, b, c;


    /**
     * Создает экземпляр класса ThreeNumbers.
     *
     * @param a первое число
     * @param b второе число
     * @param c третье число
     */

    public ThreeNumbers(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Проверяет, является ли сумма каких-либо двух чисел положительной.
     *
     * @return true, если сумма каких-либо двух чисел положительна, false - в противном случае
     */
    public boolean hasPositiveSum() {
        return (a + b > 0) || (a + c > 0) || (b + c > 0);
    }

    public static void checkNumbers(int a, int b, int c) {
        ThreeNumbers tn = new ThreeNumbers(a, b, c);

        LoggerClass logger = new LoggerClass();
        if (tn.hasPositiveSum()) {
            logger.logInfo("Сумма каких-либо двух чисел является положительной.");
        } else {
            logger.logWarning("Сумма каких-либо двух чисел не является положительной.");
        }
    }

    public static void MenuCheckNumbers() {
        Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.println("Выберите действие:");
        System.out.println("1. Проверить числа");
        System.out.println("2. Выход");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Введите первое число: ");
                int a = scanner.nextInt();

                System.out.print("Введите второе число: ");
                int b = scanner.nextInt();

                System.out.print("Введите третье число: ");
                int c = scanner.nextInt();

                checkNumbers(a, b, c);

                break;

            case 2:
                System.out.println("Выход.");
                return;

            default:
                System.out.println("Неверный выбор.");
        }
    }

}
}

