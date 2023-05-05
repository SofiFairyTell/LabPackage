import LabFunctions.lab1.ThreeNumbers;
import LabFunctions.lab2.LoggerClass;
import org.junit.jupiter.api.Test;

public class TestThreeNumbers {
    @Test
    public void testCheckNumbers() {
        int a = 1;
        int b = 2;
        int c = -3;

        // Создание объекта LoggerClass с заменой вывода на консоль
        //LoggerClass logger = new LoggerClass(System.out::println);

        // Вызов метода checkNumbers
        ThreeNumbers.checkNumbers(a, b, c);

        // Проверка вывода сообщения в зависимости от результата проверки
        //assertEquals("Сумма каких-либо двух чисел не является положительной.", logger.getLastMessage());
    }
}
