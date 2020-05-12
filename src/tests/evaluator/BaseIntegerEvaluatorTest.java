package evaluator;

import exceptions.ZeroDivisionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BaseIntegerEvaluatorTest {
    protected final Evaluator<Integer> evaluator;

    BaseIntegerEvaluatorTest(Evaluator<Integer> evaluator) {
        this.evaluator = evaluator;
    }

    void add() {
        assertEquals(3, evaluator.add(1, 2));
        assertEquals(3, evaluator.add(2, 1));
        assertEquals(-1, evaluator.add(1, -2));
        assertEquals(3, evaluator.add(0, 3));

        assertEquals(Integer.MAX_VALUE, evaluator.add(Integer.MAX_VALUE, 0));
        assertEquals(Integer.MAX_VALUE - 1, evaluator.add(Integer.MAX_VALUE, -1));
    }

    void subtract() {
        assertEquals(-1, evaluator.subtract(1, 2));
        assertEquals(1, evaluator.subtract(2, 1));
        assertEquals(3, evaluator.subtract(1, -2));
        assertEquals(-3, evaluator.subtract(0, 3));
        assertEquals(3, evaluator.subtract(3, 0));

        assertEquals(Integer.MAX_VALUE, evaluator.subtract(Integer.MAX_VALUE, 0));
        assertEquals(Integer.MAX_VALUE - 1, evaluator.subtract(Integer.MAX_VALUE, 1));
        assertEquals(0, evaluator.subtract(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(0, evaluator.subtract(Integer.MIN_VALUE, Integer.MIN_VALUE));
    }

    void multiply() {
        assertEquals(2, evaluator.multiply(1, 2));
        assertEquals(6, evaluator.multiply(2, 3));
        assertEquals(-2, evaluator.multiply(1, -2));
        assertEquals(2, evaluator.multiply(-1, -2));
        assertEquals(0, evaluator.multiply(0, 3));
        assertEquals(0, evaluator.multiply(3, 0));

        assertEquals(0, evaluator.multiply(Integer.MAX_VALUE, 0));
        assertEquals(-Integer.MAX_VALUE, evaluator.multiply(Integer.MAX_VALUE, -1));
        assertEquals(Integer.MAX_VALUE, evaluator.multiply(Integer.MAX_VALUE, 1));
    }

    void divide() {
        assertEquals(2, evaluator.divide(2, 1));
        assertEquals(2, evaluator.divide(6, 3));
        assertEquals(0, evaluator.divide(2, 3));
        assertEquals(0, evaluator.divide(1, -2));
        assertEquals(-2, evaluator.divide(-4, 2));
        assertEquals(0, evaluator.divide(0, 3));

        assertEquals(0, evaluator.divide(0, Integer.MAX_VALUE));
        assertEquals(0, evaluator.divide(0, Integer.MIN_VALUE));
        assertEquals(1, evaluator.divide(Integer.MIN_VALUE, Integer.MIN_VALUE));
        assertEquals(1, evaluator.divide(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertEquals(Integer.MAX_VALUE, evaluator.divide(Integer.MAX_VALUE, 1));
        assertEquals(-Integer.MAX_VALUE, evaluator.divide(Integer.MAX_VALUE, -1));
    }

    void divisionByZero() {
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide(10, 0));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide(0, 0));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide(Integer.MAX_VALUE, 0));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide(Integer.MIN_VALUE, 0));
    }

    void negate() {
        assertEquals(-2, evaluator.negate(2));
        assertEquals(3, evaluator.negate(-3));
        assertEquals(0, evaluator.negate(0));
        assertEquals(-Integer.MAX_VALUE, evaluator.negate(Integer.MAX_VALUE));
    }

    void count() {
        assertEquals(2, evaluator.count(5));
        assertEquals(0, evaluator.count(0));
        assertEquals(31, evaluator.count(Integer.MAX_VALUE));
        assertEquals(1, evaluator.count(Integer.MIN_VALUE));
    }

    void max() {
        assertEquals(2, evaluator.max(2, 1));
        assertEquals(6, evaluator.max(6, 3));
        assertEquals(3, evaluator.max(2, 3));
        assertEquals(1, evaluator.max(1, -2));
        assertEquals(2, evaluator.max(-4, 2));
        assertEquals(3, evaluator.max(0, 3));

        assertEquals(Integer.MAX_VALUE, evaluator.max(Integer.MAX_VALUE, 0));
        assertEquals(Integer.MAX_VALUE, evaluator.max(Integer.MAX_VALUE, Integer.MIN_VALUE));
        assertEquals(-10, evaluator.max(Integer.MIN_VALUE, -10));
        assertEquals(10, evaluator.max(Integer.MIN_VALUE, 10));
    }

    void min() {
        assertEquals(1, evaluator.min(2, 1));
        assertEquals(3, evaluator.min(6, 3));
        assertEquals(2, evaluator.min(2, 3));
        assertEquals(-2, evaluator.min(1, -2));
        assertEquals(-4, evaluator.min(-4, 2));
        assertEquals(0, evaluator.min(0, 3));

        assertEquals(0, evaluator.min(Integer.MAX_VALUE, 0));
        assertEquals(Integer.MIN_VALUE, evaluator.min(Integer.MIN_VALUE, 0));
        assertEquals(Integer.MIN_VALUE, evaluator.min(Integer.MAX_VALUE, Integer.MIN_VALUE));
        assertEquals(Integer.MIN_VALUE, evaluator.min(Integer.MIN_VALUE, -10));
        assertEquals(Integer.MIN_VALUE, evaluator.min(Integer.MIN_VALUE, 10));
    }

    void parse() {
        assertEquals(10, evaluator.parse("10"));
        assertEquals(-10, evaluator.parse("-10"));
        assertEquals(Integer.MAX_VALUE, evaluator.parse(Integer.MAX_VALUE + ""));
        assertEquals(Integer.MIN_VALUE, evaluator.parse(Integer.MIN_VALUE + ""));
    }

    void getValue() {
        assertEquals(10, evaluator.getValue(10));
        assertEquals(-5, evaluator.getValue(-5));
        assertEquals(Integer.MIN_VALUE, evaluator.getValue(Integer.MIN_VALUE));
        assertEquals(Integer.MAX_VALUE, evaluator.getValue(Integer.MAX_VALUE));
    }
}