package LabFunctions.lab1;

import java.util.Scanner;

/**
 * Класс для преобразования длины отрезка из заданной единицы измерения в метры.
 */
public class LengthConverter {

    /**
     * Преобразует длину отрезка из заданной единицы измерения в метры.
     *
     * @param unit номер единицы измерения (1 — дециметр, 2 — километр, 3 — метр, 4 — миллиметр, 5 — сантиметр)
     * @param length длина отрезка
     * @return длина отрезка в метрах
     * @throws IllegalArgumentException если номер единицы измерения не соответствует ни одной из допустимых значений
     */
    public static double convertToMeters(int unit, double length) {
        switch (unit) {
            case 1: // дециметр
                return length / 10;
            case 2: // километр
                return length * 1000;
            case 3: // метр
                return length;
            case 4: // миллиметр
                return length / 1000;
            case 5: // сантиметр
                return length / 100;
            default:
                throw new IllegalArgumentException("Недопустимый номер единицы измерения: " + unit);
        }
    }

    /**
     * Выводит меню выбора единицы измерения и запрашивает у пользователя необходимые данные.
     */
    public static void MenuLengthConverter() {
        System.out.println("Выберите единицу измерения:");
        System.out.println("1. Дециметр");
        System.out.println("2. Километр");
        System.out.println("3. Метр");
        System.out.println("4. Миллиметр");
        System.out.println("5. Сантиметр");
        System.out.println("0. Выход");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Номер: ");
        int unit = scanner.nextInt();
        switch(unit)
        {
            case 0:
                System.out.println("Выход.");
                return;
            default:
                break;
        }
        System.out.print("Длина отрезка: ");
        double length = scanner.nextDouble();

        try {
            double result = convertToMeters(unit, length);
            System.out.println("Длина отрезка в метрах: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
