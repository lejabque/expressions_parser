package evaluator;

import exceptions.ZeroDivisionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShortEvaluatorTest {
    private final Evaluator<Short> evaluator = new ShortEvaluator();

    @Test
    void add() {
        assertEquals((short) 3, evaluator.add((short) 1, (short) 2));
        assertEquals((short) 3, evaluator.add((short) 2, (short) 1));
        assertEquals((short) -1, evaluator.add((short) 1, (short) -2));
        assertEquals((short) 3, evaluator.add((short) 0, (short) 3));

        assertEquals(Short.MAX_VALUE, evaluator.add(Short.MAX_VALUE, (short) 0));
        assertEquals((short) (Short.MAX_VALUE - 1), evaluator.add(Short.MAX_VALUE, (short) -1));
        assertEquals(Short.MIN_VALUE, evaluator.add(Short.MAX_VALUE, (short) 1));
        assertEquals((short) -2, evaluator.add(Short.MAX_VALUE, Short.MAX_VALUE));
    }

    @Test
    void subtract() {
        assertEquals((short) -1, evaluator.subtract((short) 1, (short) 2));
        assertEquals((short) 1, evaluator.subtract((short) 2, (short) 1));
        assertEquals((short) 3, evaluator.subtract((short) 1, (short) -2));
        assertEquals((short) -3, evaluator.subtract((short) 0, (short) 3));
        assertEquals((short) 3, evaluator.subtract((short) 3, (short) 0));

        assertEquals(Short.MAX_VALUE, evaluator.subtract(Short.MAX_VALUE, (short) 0));
        assertEquals(Short.MIN_VALUE, evaluator.subtract(Short.MAX_VALUE, (short) -1));
        assertEquals((short) (Short.MAX_VALUE - 1), evaluator.subtract(Short.MAX_VALUE, (short) 1));
        assertEquals((short) 0, evaluator.subtract(Short.MAX_VALUE, Short.MAX_VALUE));
        assertEquals((short) 0, evaluator.subtract(Short.MIN_VALUE, Short.MIN_VALUE));
    }

    @Test
    void multiply() {
        assertEquals((short) 2, evaluator.multiply((short) 1, (short) 2));
        assertEquals((short) 6, evaluator.multiply((short) 2, (short) 3));
        assertEquals((short) -2, evaluator.multiply((short) 1, (short) -2));
        assertEquals((short) 2, evaluator.multiply((short) -1, (short) -2));
        assertEquals((short) 0, evaluator.multiply((short) 0, (short) 3));
        assertEquals((short) 0, evaluator.multiply((short) 3, (short) 0));

        assertEquals((short) 0, evaluator.multiply(Short.MAX_VALUE, (short) 0));
        assertEquals((short) -Short.MAX_VALUE, evaluator.multiply(Short.MAX_VALUE, (short) -1));
        assertEquals(Short.MAX_VALUE, evaluator.multiply(Short.MAX_VALUE, (short) 1));
        assertEquals(Short.MIN_VALUE, evaluator.multiply(Short.MIN_VALUE, (short) -1));
    }

    @Test
    void divide() {
        assertEquals((short) 2, evaluator.divide((short) 2, (short) 1));
        assertEquals((short) 2, evaluator.divide((short) 6, (short) 3));
        assertEquals((short) 0, evaluator.divide((short) 2, (short) 3));
        assertEquals((short) 0, evaluator.divide((short) 1, (short) -2));
        assertEquals((short) -2, evaluator.divide((short) -4, (short) 2));
        assertEquals((short) 0, evaluator.divide((short) 0, (short) 3));

        assertEquals((short) 0, evaluator.divide((short) 0, Short.MAX_VALUE));
        assertEquals((short) 0, evaluator.divide((short) 0, Short.MIN_VALUE));
        assertEquals((short) 1, evaluator.divide(Short.MIN_VALUE, Short.MIN_VALUE));
        assertEquals((short) 1, evaluator.divide(Short.MAX_VALUE, Short.MAX_VALUE));
        assertEquals(Short.MAX_VALUE, evaluator.divide(Short.MAX_VALUE, (short) 1));
        assertEquals((short) -Short.MAX_VALUE, evaluator.divide(Short.MAX_VALUE, (short) -1));
        assertEquals(Short.MIN_VALUE, evaluator.divide(Short.MIN_VALUE, (short) -1));
    }

    @Test
    void divisionByZero() {
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide((short) 10, (short) 0));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide((short) 0, (short) 0));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide(Short.MAX_VALUE, (short) 0));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide(Short.MIN_VALUE, (short) 0));
    }

    @Test
    void negate() {
        assertEquals((short) -2, evaluator.negate((short) 2));
        assertEquals((short) 3, evaluator.negate((short) -3));
        assertEquals((short) 0, evaluator.negate((short) 0));
        assertEquals((short) -Short.MAX_VALUE, evaluator.negate(Short.MAX_VALUE));
        assertEquals(Short.MIN_VALUE, evaluator.negate(Short.MIN_VALUE));
    }

    @Test
    void count() {
        assertEquals((short) 2, evaluator.count((short) 5));
        assertEquals((short) 0, evaluator.count((short) 0));
        assertEquals((short) 15, evaluator.count(Short.MAX_VALUE));
        assertEquals((short) 1, evaluator.count(Short.MIN_VALUE));
    }

    @Test
    void max() {
        assertEquals((short) 2, evaluator.max((short) 2, (short) 1));
        assertEquals((short) 6, evaluator.max((short) 6, (short) 3));
        assertEquals((short) 3, evaluator.max((short) 2, (short) 3));
        assertEquals((short) 1, evaluator.max((short) 1, (short) -2));
        assertEquals((short) 2, evaluator.max((short) -4, (short) 2));
        assertEquals((short) 3, evaluator.max((short) 0, (short) 3));

        assertEquals(Short.MAX_VALUE, evaluator.max(Short.MAX_VALUE, (short) 0));
        assertEquals(Short.MAX_VALUE, evaluator.max(Short.MAX_VALUE, Short.MIN_VALUE));
        assertEquals((short) -10, evaluator.max(Short.MIN_VALUE, (short) -10));
        assertEquals((short) 10, evaluator.max(Short.MIN_VALUE, (short) 10));
    }

    @Test
    void min() {
        assertEquals((short) 1, evaluator.min((short) 2, (short) 1));
        assertEquals((short) 3, evaluator.min((short) 6, (short) 3));
        assertEquals((short) 2, evaluator.min((short) 2, (short) 3));
        assertEquals((short) -2, evaluator.min((short) 1, (short) -2));
        assertEquals((short) -4, evaluator.min((short) -4, (short) 2));
        assertEquals((short) 0, evaluator.min((short) 0, (short) 3));

        assertEquals((short) 0, evaluator.min(Short.MAX_VALUE, (short) 0));
        assertEquals(Short.MIN_VALUE, evaluator.min(Short.MIN_VALUE, (short) 0));
        assertEquals(Short.MIN_VALUE, evaluator.min(Short.MAX_VALUE, Short.MIN_VALUE));
        assertEquals(Short.MIN_VALUE, evaluator.min(Short.MIN_VALUE, (short) -10));
        assertEquals(Short.MIN_VALUE, evaluator.min(Short.MIN_VALUE, (short) 10));
    }

    @Test
    void parse() {
        assertEquals((short) 10, evaluator.parse("10"));
        assertEquals((short) -10, evaluator.parse("-10"));
        assertEquals(Short.MAX_VALUE, evaluator.parse(Short.MAX_VALUE + ""));
        assertEquals(Short.MIN_VALUE, evaluator.parse(Short.MIN_VALUE + ""));
    }

    @Test
    void getValue() {
        assertEquals((short) 10, evaluator.getValue(10));
        assertEquals((short) -5, evaluator.getValue(-5));
        assertEquals(Short.MIN_VALUE, evaluator.getValue(Short.MIN_VALUE));
        assertEquals(Short.MAX_VALUE, evaluator.getValue(Short.MAX_VALUE));
        assertEquals(Short.MIN_VALUE, evaluator.getValue(Short.MAX_VALUE + 1));
    }
}