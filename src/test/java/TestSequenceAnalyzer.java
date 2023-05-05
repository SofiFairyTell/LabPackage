import org.junit.jupiter.api.Test;
import LabFunctions.lab1.SequenceAnalyzer;
import org.junit.jupiter.api.Assertions;

public class TestSequenceAnalyzer {
    /**
     * Тест на корректность подсчета количества чисел меньших, равных и больших заданного числа:
     */
    @Test
    public void testAnalyzeSequence() {
        double[] inputSequence = {1.2, 2.3, 3.4, 4.5, 5.6, 6.7};
        double targetNumber = 3.4;
        int[] expectedResult = {2, 1, 3};
        Assertions.assertArrayEquals(expectedResult, SequenceAnalyzer.analyzeSequence(inputSequence, targetNumber));
    }
    /**
     * Тест на обработку пустой последовательности:
     */
    @Test
    public void testAnalyzeSequenceEmptySequence() {
        double[] inputSequence = {};
        double targetNumber = 1.0;
        int[] expectedResult = {0, 0, 0};
        Assertions.assertArrayEquals(expectedResult, SequenceAnalyzer.analyzeSequence(inputSequence, targetNumber));
    }
}
