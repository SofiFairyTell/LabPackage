package LabFunctions.lab4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator {
    // Регулярное выражение для проверки Фамилии
    private static final String SURNAME_PATTERN = "^([А-ЯЁа-яё]+-?[А-ЯЁа-яё]*)|([A-Za-z]+-?[A-Za-z]*)$";

    // Регулярное выражение для проверки Имени
    private static final String NAME_PATTERN = "^(([A-Za-z]+)|([А-Яа-я]+))$";

    /**
     * Проверяет, является ли строка допустимой Фамилией
     * @param surname строка для проверки
     * @return true, если строка соответствует шаблону
     */
    public static boolean isValidSurname(String surname) {
        Pattern pattern = Pattern.compile(SURNAME_PATTERN);
        Matcher matcher = pattern.matcher(surname);
        return matcher.matches();
    }

    /**
     * Проверяет, является ли строка допустимым Именем
     * @param name строка для проверки
     * @return true, если строка соответствует шаблону
     */
    public static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
}
