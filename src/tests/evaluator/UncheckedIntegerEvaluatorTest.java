package evaluator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UncheckedIntegerEvaluatorTest {
    private final Evaluator<Integer> evaluator = new IntegerEvaluator();
    private final BaseIntegerEvaluatorTest baseTester = new BaseIntegerEvaluatorTest(evaluator);

    @Test
    void add() {
        baseTester.add();
        assertEquals(Integer.MIN_VALUE, evaluator.add(Integer.MAX_VALUE, 1));
        assertEquals(0, evaluator.add(Integer.MIN_VALUE, Integer.MIN_VALUE));
        assertEquals(-2, evaluator.add(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test
    void subtract() {
        baseTester.subtract();
        assertEquals(Integer.MIN_VALUE, evaluator.subtract(Integer.MAX_VALUE, -1));
        assertEquals(-1, evaluator.subtract(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    @Test
    void multiply() {
        baseTester.multiply();
        assertEquals(Integer.MIN_VALUE, evaluator.multiply(Integer.MIN_VALUE, -1));
        assertEquals(0, evaluator.multiply(Integer.MIN_VALUE, 2));
    }

    @Test
    void divide() {
        baseTester.divide();
        assertEquals(Integer.MIN_VALUE, evaluator.divide(Integer.MIN_VALUE, -1));
    }

    @Test
    void divisionByZero() {
        baseTester.divisionByZero();
    }

    @Test
    void negate() {
        baseTester.negate();
        assertEquals(Integer.MIN_VALUE, evaluator.negate(Integer.MIN_VALUE));
    }

    @Test
    void countWithOverflow() {
        baseTester.count();
    }

    @Test
    void max() {
        baseTester.max();
    }

    @Test
    void min() {
        baseTester.min();
    }

    @Test
    void parse() {
        baseTester.parse();
    }

    @Test
    void getValue() {
        baseTester.getValue();
    }
}