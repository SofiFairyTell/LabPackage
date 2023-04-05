package LabFunctions.lab4;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Класс для проверки правильности введенной даты. Содержит соответствующие функции
 */
public class DataChecker {
    private static final String[] DATE_FORMATS = {"dd.MM.yyyy", "dd.MM.yy", "yy.MM.dd", "yyyy.MM.dd", "dd-MM-yyyy", "dd-MM-yy", "yy-MM-dd", "yyyy-MM-dd", "dd:MM:yyyy", "dd:MM:yy", "yy:MM:dd", "yyyy:MM:dd"};
    private static final Pattern DATE_PATTERN = Pattern.compile("^(\\d{2}|\\d{4})[\\-\\:\\.]?(\\d{2})[\\-\\:\\.]?(\\d{2}|\\d{4})$");
    /**
     * Проверяет, является ли строка допустимой датой
     * @param dateStr строка для проверки
     * @return true, если строка соответствует шаблону
     */
    public static boolean isDateValid(String dateStr) {
        Matcher matcher = DATE_PATTERN.matcher(dateStr);
        if (matcher.find()) {
            for (String format : DATE_FORMATS) {
                try {
                    new java.text.SimpleDateFormat(format).parse(dateStr);
                    return true;
                } catch (java.text.ParseException e) {
                }
            }
        }
        return false;
    }
}
