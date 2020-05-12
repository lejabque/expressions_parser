package evaluator;

import exceptions.ZeroDivisionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ByteEvaluatorTest {
    private final Evaluator<Byte> evaluator = new ByteEvaluator();

    @Test
    void add() {
        assertEquals((byte) 3, evaluator.add((byte) 1, (byte) 2));
        assertEquals((byte) 3, evaluator.add((byte) 2, (byte) 1));
        assertEquals((byte) -1, evaluator.add((byte) 1, (byte) -2));
        assertEquals((byte) 3, evaluator.add((byte) 0, (byte) 3));

        assertEquals(Byte.MAX_VALUE, evaluator.add(Byte.MAX_VALUE, (byte) 0));
        assertEquals((byte) (Byte.MAX_VALUE - 1), evaluator.add(Byte.MAX_VALUE, (byte) -1));
        assertEquals(Byte.MIN_VALUE, evaluator.add(Byte.MAX_VALUE, (byte) 1));
        assertEquals((byte) -2, evaluator.add(Byte.MAX_VALUE, Byte.MAX_VALUE));
    }

    @Test
    void subtract() {
        assertEquals((byte) -1, evaluator.subtract((byte) 1, (byte) 2));
        assertEquals((byte) 1, evaluator.subtract((byte) 2, (byte) 1));
        assertEquals((byte) 3, evaluator.subtract((byte) 1, (byte) -2));
        assertEquals((byte) -3, evaluator.subtract((byte) 0, (byte) 3));
        assertEquals((byte) 3, evaluator.subtract((byte) 3, (byte) 0));

        assertEquals(Byte.MAX_VALUE, evaluator.subtract(Byte.MAX_VALUE, (byte) 0));
        assertEquals(Byte.MIN_VALUE, evaluator.subtract(Byte.MAX_VALUE, (byte) -1));
        assertEquals((byte) (Byte.MAX_VALUE - 1), evaluator.subtract(Byte.MAX_VALUE, (byte) 1));
        assertEquals((byte) 0, evaluator.subtract(Byte.MAX_VALUE, Byte.MAX_VALUE));
        assertEquals((byte) 0, evaluator.subtract(Byte.MIN_VALUE, Byte.MIN_VALUE));
    }

    @Test
    void multiply() {
        assertEquals((byte) 2, evaluator.multiply((byte) 1, (byte) 2));
        assertEquals((byte) 6, evaluator.multiply((byte) 2, (byte) 3));
        assertEquals((byte) -2, evaluator.multiply((byte) 1, (byte) -2));
        assertEquals((byte) 2, evaluator.multiply((byte) -1, (byte) -2));
        assertEquals((byte) 0, evaluator.multiply((byte) 0, (byte) 3));
        assertEquals((byte) 0, evaluator.multiply((byte) 3, (byte) 0));

        assertEquals((byte) 0, evaluator.multiply(Byte.MAX_VALUE, (byte) 0));
        assertEquals((byte) -Byte.MAX_VALUE, evaluator.multiply(Byte.MAX_VALUE, (byte) -1));
        assertEquals(Byte.MAX_VALUE, evaluator.multiply(Byte.MAX_VALUE, (byte) 1));
        assertEquals(Byte.MIN_VALUE, evaluator.multiply(Byte.MIN_VALUE, (byte) -1));
    }

    @Test
    void divide() {
        assertEquals((byte) 2, evaluator.divide((byte) 2, (byte) 1));
        assertEquals((byte) 2, evaluator.divide((byte) 6, (byte) 3));
        assertEquals((byte) 0, evaluator.divide((byte) 2, (byte) 3));
        assertEquals((byte) 0, evaluator.divide((byte) 1, (byte) -2));
        assertEquals((byte) -2, evaluator.divide((byte) -4, (byte) 2));
        assertEquals((byte) 0, evaluator.divide((byte) 0, (byte) 3));

        assertEquals((byte) 0, evaluator.divide((byte) 0, Byte.MAX_VALUE));
        assertEquals((byte) 0, evaluator.divide((byte) 0, Byte.MIN_VALUE));
        assertEquals((byte) 1, evaluator.divide(Byte.MIN_VALUE, Byte.MIN_VALUE));
        assertEquals((byte) 1, evaluator.divide(Byte.MAX_VALUE, Byte.MAX_VALUE));
        assertEquals(Byte.MAX_VALUE, evaluator.divide(Byte.MAX_VALUE, (byte) 1));
        assertEquals((byte) -Byte.MAX_VALUE, evaluator.divide(Byte.MAX_VALUE, (byte) -1));
        assertEquals(Byte.MIN_VALUE, evaluator.divide(Byte.MIN_VALUE, (byte) -1));
    }

    @Test
    void divisionByZero() {
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide((byte) 10, (byte) 0));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide((byte) 0, (byte) 0));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide(Byte.MAX_VALUE, (byte) 0));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide(Byte.MIN_VALUE, (byte) 0));
    }

    @Test
    void negate() {
        assertEquals((byte) -2, evaluator.negate((byte) 2));
        assertEquals((byte) 3, evaluator.negate((byte) -3));
        assertEquals((byte) 0, evaluator.negate((byte) 0));
        assertEquals((byte) -Byte.MAX_VALUE, evaluator.negate(Byte.MAX_VALUE));
        assertEquals(Byte.MIN_VALUE, evaluator.negate(Byte.MIN_VALUE));
    }

    @Test
    void count() {
        assertEquals((byte) 2, evaluator.count((byte) 5));
        assertEquals((byte) 0, evaluator.count((byte) 0));
        assertEquals((byte) 7, evaluator.count(Byte.MAX_VALUE));
    }

    @Test
    void max() {
        assertEquals((byte) 2, evaluator.max((byte) 2, (byte) 1));
        assertEquals((byte) 6, evaluator.max((byte) 6, (byte) 3));
        assertEquals((byte) 3, evaluator.max((byte) 2, (byte) 3));
        assertEquals((byte) 1, evaluator.max((byte) 1, (byte) -2));
        assertEquals((byte) 2, evaluator.max((byte) -4, (byte) 2));
        assertEquals((byte) 3, evaluator.max((byte) 0, (byte) 3));

        assertEquals(Byte.MAX_VALUE, evaluator.max(Byte.MAX_VALUE, (byte) 0));
        assertEquals(Byte.MAX_VALUE, evaluator.max(Byte.MAX_VALUE, Byte.MIN_VALUE));
        assertEquals((byte) -10, evaluator.max(Byte.MIN_VALUE, (byte) -10));
        assertEquals((byte) 10, evaluator.max(Byte.MIN_VALUE, (byte) 10));
    }

    @Test
    void min() {
        assertEquals((byte) 1, evaluator.min((byte) 2, (byte) 1));
        assertEquals((byte) 3, evaluator.min((byte) 6, (byte) 3));
        assertEquals((byte) 2, evaluator.min((byte) 2, (byte) 3));
        assertEquals((byte) -2, evaluator.min((byte) 1, (byte) -2));
        assertEquals((byte) -4, evaluator.min((byte) -4, (byte) 2));
        assertEquals((byte) 0, evaluator.min((byte) 0, (byte) 3));

        assertEquals((byte) 0, evaluator.min(Byte.MAX_VALUE, (byte) 0));
        assertEquals(Byte.MIN_VALUE, evaluator.min(Byte.MIN_VALUE, (byte) 0));
        assertEquals(Byte.MIN_VALUE, evaluator.min(Byte.MAX_VALUE, Byte.MIN_VALUE));
        assertEquals(Byte.MIN_VALUE, evaluator.min(Byte.MIN_VALUE, (byte) -10));
        assertEquals(Byte.MIN_VALUE, evaluator.min(Byte.MIN_VALUE, (byte) 10));
    }

    @Test
    void parse() {
        assertEquals((byte) 10, evaluator.parse("10"));
        assertEquals((byte) -10, evaluator.parse("-10"));
        assertEquals(Byte.MAX_VALUE, evaluator.parse(Byte.MAX_VALUE + ""));
        assertEquals(Byte.MIN_VALUE, evaluator.parse(Byte.MIN_VALUE + ""));
    }

    @Test
    void getValue() {
        assertEquals((byte) 10, evaluator.getValue(10));
        assertEquals((byte) -5, evaluator.getValue(-5));
        assertEquals(Byte.MIN_VALUE, evaluator.getValue(Byte.MIN_VALUE));
        assertEquals(Byte.MAX_VALUE, evaluator.getValue(Byte.MAX_VALUE));
        assertEquals(Byte.MIN_VALUE, evaluator.getValue(Byte.MAX_VALUE + 1));
    }
}