package evaluator;

import exceptions.ZeroDivisionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LongEvaluatorTest {
    private final Evaluator<Long> evaluator = new LongEvaluator();

    @Test
    void add() {
        assertEquals(3, evaluator.add((long) 1, (long) 2));
        assertEquals(3, evaluator.add((long) 2, (long) 1));
        assertEquals(-1, evaluator.add((long) 1, (long) -2));
        assertEquals(3, evaluator.add((long) 0, (long) 3));

        assertEquals(Long.MAX_VALUE, evaluator.add(Long.MAX_VALUE, (long) 0));
        assertEquals(Long.MAX_VALUE - 1, evaluator.add(Long.MAX_VALUE, (long) -1));
        assertEquals(Long.MIN_VALUE, evaluator.add(Long.MAX_VALUE, (long) 1));
        assertEquals(-2, evaluator.add(Long.MAX_VALUE, Long.MAX_VALUE));
    }

    @Test
    void subtract() {
        assertEquals(-1, evaluator.subtract((long) 1, (long) 2));
        assertEquals(1, evaluator.subtract((long) 2, (long) 1));
        assertEquals(3, evaluator.subtract((long) 1, (long) -2));
        assertEquals(-3, evaluator.subtract((long) 0, (long) 3));
        assertEquals(3, evaluator.subtract((long) 3, (long) 0));

        assertEquals(Long.MAX_VALUE, evaluator.subtract(Long.MAX_VALUE, (long) 0));
        assertEquals(Long.MIN_VALUE, evaluator.subtract(Long.MAX_VALUE, (long) -1));
        assertEquals(Long.MAX_VALUE - 1, evaluator.subtract(Long.MAX_VALUE, (long) 1));
        assertEquals(0, evaluator.subtract(Long.MAX_VALUE, Long.MAX_VALUE));
        assertEquals(0, evaluator.subtract(Long.MIN_VALUE, Long.MIN_VALUE));
    }

    @Test
    void multiply() {
        assertEquals(2, evaluator.multiply((long) 1, (long) 2));
        assertEquals(6, evaluator.multiply((long) 2, (long) 3));
        assertEquals(-2, evaluator.multiply((long) 1, (long) -2));
        assertEquals(2, evaluator.multiply((long) -1, (long) -2));
        assertEquals(0, evaluator.multiply((long) 0, (long) 3));
        assertEquals(0, evaluator.multiply((long) 3, (long) 0));

        assertEquals(0, evaluator.multiply(Long.MAX_VALUE, (long) 0));
        assertEquals(-Long.MAX_VALUE, evaluator.multiply(Long.MAX_VALUE, (long) -1));
        assertEquals(Long.MAX_VALUE, evaluator.multiply(Long.MAX_VALUE, (long) 1));
        assertEquals(Long.MIN_VALUE, evaluator.multiply(Long.MIN_VALUE, (long) -1));
    }

    @Test
    void divide() {
        assertEquals(2, evaluator.divide((long) 2, (long) 1));
        assertEquals(2, evaluator.divide((long) 6, (long) 3));
        assertEquals(0, evaluator.divide((long) 2, (long) 3));
        assertEquals(0, evaluator.divide((long) 1, (long) -2));
        assertEquals(-2, evaluator.divide((long) -4, (long) 2));
        assertEquals(0, evaluator.divide((long) 0, (long) 3));

        assertEquals(0, evaluator.divide((long) 0, Long.MAX_VALUE));
        assertEquals(0, evaluator.divide((long) 0, Long.MIN_VALUE));
        assertEquals(1, evaluator.divide(Long.MIN_VALUE, Long.MIN_VALUE));
        assertEquals(1, evaluator.divide(Long.MAX_VALUE, Long.MAX_VALUE));
        assertEquals(Long.MAX_VALUE, evaluator.divide(Long.MAX_VALUE, (long) 1));
        assertEquals(-Long.MAX_VALUE, evaluator.divide(Long.MAX_VALUE, (long) -1));
        assertEquals(Long.MIN_VALUE, evaluator.divide(Long.MIN_VALUE, (long) -1));
    }

    @Test
    void divisionByZero() {
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide((long) 10, (long) 0));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide((long) 0, (long) 0));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide(Long.MAX_VALUE, (long) 0));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide(Long.MIN_VALUE, (long) 0));
    }

    @Test
    void negate() {
        assertEquals(-2, evaluator.negate((long) 2));
        assertEquals(3, evaluator.negate((long) -3));
        assertEquals(0, evaluator.negate((long) 0));
        assertEquals(-Long.MAX_VALUE, evaluator.negate(Long.MAX_VALUE));
        assertEquals(Long.MIN_VALUE, evaluator.negate(Long.MIN_VALUE));
    }

    @Test
    void count() {
        assertEquals(2, evaluator.count((long) 5));
        assertEquals(0, evaluator.count((long) 0));
        assertEquals(63, evaluator.count(Long.MAX_VALUE));
        assertEquals(1, evaluator.count(Long.MIN_VALUE));
    }

    @Test
    void max() {
        assertEquals(2, evaluator.max((long) 2, (long) 1));
        assertEquals(6, evaluator.max((long) 6, (long) 3));
        assertEquals(3, evaluator.max((long) 2, (long) 3));
        assertEquals(1, evaluator.max((long) 1, (long) -2));
        assertEquals(2, evaluator.max((long) -4, (long) 2));
        assertEquals(3, evaluator.max((long) 0, (long) 3));

        assertEquals(Long.MAX_VALUE, evaluator.max(Long.MAX_VALUE, (long) 0));
        assertEquals(Long.MAX_VALUE, evaluator.max(Long.MAX_VALUE, Long.MIN_VALUE));
        assertEquals(-10, evaluator.max(Long.MIN_VALUE, (long) -10));
        assertEquals(10, evaluator.max(Long.MIN_VALUE, (long) 10));
    }

    @Test
    void min() {
        assertEquals(1, evaluator.min((long) 2, (long) 1));
        assertEquals(3, evaluator.min((long) 6, (long) 3));
        assertEquals(2, evaluator.min((long) 2, (long) 3));
        assertEquals(-2, evaluator.min((long) 1, (long) -2));
        assertEquals(-4, evaluator.min((long) -4, (long) 2));
        assertEquals(0, evaluator.min((long) 0, (long) 3));

        assertEquals(0, evaluator.min(Long.MAX_VALUE, (long) 0));
        assertEquals(Long.MIN_VALUE, evaluator.min(Long.MIN_VALUE, (long) 0));
        assertEquals(Long.MIN_VALUE, evaluator.min(Long.MAX_VALUE, Long.MIN_VALUE));
        assertEquals(Long.MIN_VALUE, evaluator.min(Long.MIN_VALUE, (long) -10));
        assertEquals(Long.MIN_VALUE, evaluator.min(Long.MIN_VALUE, (long) 10));
    }

    @Test
    void parse() {
        assertEquals(10, evaluator.parse("10"));
        assertEquals(-10, evaluator.parse("-10"));
        assertEquals(Long.MAX_VALUE, evaluator.parse(Long.MAX_VALUE + ""));
        assertEquals(Long.MIN_VALUE, evaluator.parse(Long.MIN_VALUE + ""));
    }

    @Test
    void getValue() {
        assertEquals(10, evaluator.getValue(10));
        assertEquals(-5, evaluator.getValue(-5));
        assertEquals(Long.MIN_VALUE, evaluator.getValue(Long.MIN_VALUE));
        assertEquals(Long.MAX_VALUE, evaluator.getValue(Long.MAX_VALUE));
    }
}