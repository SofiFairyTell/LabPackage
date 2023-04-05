package LabFunctions.lab4;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Класс для проверки правильности введенного номера телефона. Содержит соответствующие функции
 */
public class PhoneChecker {
    private static final String PHONE_PATTERN = "^(\\+7|8|7)?[\\(\\- ]*(\\d{3})[\\)\\- ]*(\\d{3})[\\- ]*(\\d{2})[\\- ]*(\\d{2})$";

    /**
     * Проверяет, является ли строка допустимым телефоном
     * @param phone строка для проверки
     * @return true, если строка соответствует шаблону
     */
    public static boolean checkPhone(String phone) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
