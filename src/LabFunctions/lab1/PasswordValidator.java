package LabFunctions.lab1;

import java.util.Scanner;

/**
 * Класс для проверки пароля
 */
public class PasswordValidator {
    private final String password;
    private final int maxAttempts;
    private int attempts;

    /**
     * Конструктор класса
     *
     * @param password    правильный пароль
     * @param maxAttempts максимальное количество попыток
     */
    public PasswordValidator(String password, int maxAttempts) {
        this.password = password;
        this.maxAttempts = maxAttempts;
        attempts = 0;
    }

    /**
     * Метод для проверки пароля
     *
     * @param inputPassword введенный пользователем пароль
     * @return true, если пароль верный, и false, если пароль неверный
     */
    public boolean validate(String inputPassword) {
        if (inputPassword.equals(password)) {
            return true;
        }
        attempts++;
        if (attempts >= maxAttempts) {
            return false;
        }
        return validate(inputPassword);
    }

    /**
     * Возвращает максимальное количество попыток ввода пароля.
     *
     * @return максимальное количество попыток
     */
    public int getMaxAttempts() {
        return maxAttempts;
    }

    /**
     * Функция для проверки пароля с помощью класса PasswordValidator
     * @param scanner объект класса Scanner для ввода пароля
     */
    public static void validatePassword(Scanner scanner) {
        PasswordValidator validator = new PasswordValidator("1234", 3);
        boolean success = false;
        int attempts = 0;
        while (!success && attempts < validator.getMaxAttempts()) {
            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();
            success = validator.validate(password);
            attempts++;
        }
        System.out.println(success ? "Пароль введен верно" : "Превышено максимальное количество попыток");
    }

    /**
     * Функция для проверки пароля с помощью класса PasswordValidator
     * @param password входной пароль для проверки
     * @return результат ввода пароля
     */
    public static String validatePassword(String password) {
        PasswordValidator validator = new PasswordValidator("1234", 3);
        boolean success = validator.validate(password);
        return (success ? "Пароль введен верно" : "Превышено максимальное количество попыток");
    }

    public static  void MenuValidatePassword()
    {
        Scanner scanner = new Scanner(System.in);
        validatePassword(scanner);
    }
}

