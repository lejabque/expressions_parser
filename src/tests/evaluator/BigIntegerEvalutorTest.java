package evaluator;

import exceptions.ZeroDivisionException;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BigIntegerEvalutorTest {
    private final Evaluator<BigInteger> evaluator = new BigIntegerEvaluator();

    @Test
    void add() {
        assertEquals(BigInteger.valueOf(3), evaluator.add(BigInteger.ONE, BigInteger.TWO));
        assertEquals(BigInteger.valueOf(3), evaluator.add(BigInteger.TWO, BigInteger.ONE));
        assertEquals(BigInteger.valueOf(-1), evaluator.add(BigInteger.ONE, BigInteger.valueOf(-2)));
        assertEquals(BigInteger.valueOf(3), evaluator.add(BigInteger.ZERO, BigInteger.valueOf(3)));

        assertEquals(BigInteger.valueOf(Long.MAX_VALUE),
                evaluator.add(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.ZERO));
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE - 1),
                evaluator.add(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.valueOf(-1)));
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE),
                evaluator.add(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.ONE));
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.TWO),
                evaluator.add(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.valueOf(Long.MAX_VALUE)));
    }

    @Test
    void subtract() {
        assertEquals(BigInteger.valueOf(-1),
                evaluator.subtract(BigInteger.ONE, BigInteger.TWO));
        assertEquals(BigInteger.ONE,
                evaluator.subtract(BigInteger.TWO, BigInteger.ONE));
        assertEquals(BigInteger.valueOf(3),
                evaluator.subtract(BigInteger.ONE, BigInteger.valueOf(-2)));
        assertEquals(BigInteger.valueOf(-3),
                evaluator.subtract(BigInteger.ZERO, BigInteger.valueOf(3)));
        assertEquals(BigInteger.valueOf(3),
                evaluator.subtract(BigInteger.valueOf(3), BigInteger.ZERO));

        assertEquals(BigInteger.valueOf(Long.MAX_VALUE),
                evaluator.subtract(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.ZERO));
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE),
                evaluator.subtract(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.valueOf(-1)));
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE - 1),
                evaluator.subtract(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.ONE));
        assertEquals(BigInteger.ZERO,
                evaluator.subtract(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.valueOf(Long.MAX_VALUE)));
        assertEquals(BigInteger.ZERO,
                evaluator.subtract(BigInteger.valueOf(Long.MIN_VALUE), BigInteger.valueOf(Long.MIN_VALUE)));
    }

    @Test
    void multiply() {
        assertEquals(BigInteger.TWO,
                evaluator.multiply(BigInteger.ONE, BigInteger.TWO));
        assertEquals(BigInteger.valueOf(6),
                evaluator.multiply(BigInteger.TWO, BigInteger.valueOf(3)));
        assertEquals(BigInteger.valueOf(-2),
                evaluator.multiply(BigInteger.ONE, BigInteger.valueOf(-2)));
        assertEquals(BigInteger.TWO,
                evaluator.multiply(BigInteger.valueOf(-1), BigInteger.valueOf(-2)));
        assertEquals(BigInteger.ZERO,
                evaluator.multiply(BigInteger.ZERO, BigInteger.valueOf(3)));
        assertEquals(BigInteger.ZERO,
                evaluator.multiply(BigInteger.valueOf(3), BigInteger.ZERO));

        assertEquals(BigInteger.ZERO,
                evaluator.multiply(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.ZERO));
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE).negate(),
                evaluator.multiply(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.valueOf(-1)));
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE),
                evaluator.multiply(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.ONE));
        assertEquals(BigInteger.valueOf(Long.MIN_VALUE).negate(),
                evaluator.multiply(BigInteger.valueOf(Long.MIN_VALUE), BigInteger.valueOf(-1)));
    }

    @Test
    void divide() {
        assertEquals(BigInteger.TWO,
                evaluator.divide(BigInteger.TWO, BigInteger.ONE));
        assertEquals(BigInteger.TWO,
                evaluator.divide(BigInteger.valueOf(6), BigInteger.valueOf(3)));
        assertEquals(BigInteger.ZERO,
                evaluator.divide(BigInteger.TWO, BigInteger.valueOf(3)));
        assertEquals(BigInteger.ZERO,
                evaluator.divide(BigInteger.ONE, BigInteger.valueOf(-2)));
        assertEquals(BigInteger.valueOf(-2),
                evaluator.divide(BigInteger.valueOf(-4), BigInteger.TWO));
        assertEquals(BigInteger.ZERO,
                evaluator.divide(BigInteger.ZERO, BigInteger.valueOf(3)));

        assertEquals(BigInteger.ZERO,
                evaluator.divide(BigInteger.ZERO, BigInteger.valueOf(Long.MAX_VALUE)));
        assertEquals(BigInteger.ZERO,
                evaluator.divide(BigInteger.ZERO, BigInteger.valueOf(Long.MIN_VALUE)));
        assertEquals(BigInteger.ONE,
                evaluator.divide(BigInteger.valueOf(Long.MIN_VALUE), BigInteger.valueOf(Long.MIN_VALUE)));
        assertEquals(BigInteger.ONE,
                evaluator.divide(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.valueOf(Long.MAX_VALUE)));
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE),
                evaluator.divide(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.ONE));
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE).negate(),
                evaluator.divide(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.valueOf(-1)));
        assertEquals(BigInteger.valueOf(Long.MIN_VALUE).negate(),
                evaluator.divide(BigInteger.valueOf(Long.MIN_VALUE), BigInteger.valueOf(-1)));
    }

    @Test
    void divisionByZero() {
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide(BigInteger.TEN, BigInteger.ZERO));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide(BigInteger.ZERO, BigInteger.ZERO));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.ZERO));
        assertThrows(ZeroDivisionException.class, () -> evaluator.divide(BigInteger.valueOf(Long.MIN_VALUE), BigInteger.ZERO));
    }

    @Test
    void negate() {
        assertEquals(BigInteger.valueOf(-2), evaluator.negate(BigInteger.TWO));
        assertEquals(BigInteger.valueOf(3), evaluator.negate(BigInteger.valueOf(-3)));
        assertEquals(BigInteger.ZERO, evaluator.negate(BigInteger.ZERO));
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE).negate(), evaluator.negate(BigInteger.valueOf(Long.MAX_VALUE)));
        assertEquals(BigInteger.valueOf(Long.MIN_VALUE).negate(), evaluator.negate(BigInteger.valueOf(Long.MIN_VALUE)));
    }

    @Test
    void count() {
        assertEquals(BigInteger.TWO, evaluator.count(BigInteger.valueOf(5)));
        assertEquals(BigInteger.ZERO, evaluator.count(BigInteger.ZERO));
        assertEquals(BigInteger.valueOf(63),
                evaluator.count(BigInteger.valueOf(Long.MAX_VALUE)));
        assertEquals(BigInteger.valueOf(63), evaluator.count(BigInteger.valueOf(Long.MIN_VALUE)));
    }

    @Test
    void max() {
        assertEquals(BigInteger.TWO, evaluator.max(BigInteger.TWO, BigInteger.ONE));
        assertEquals(BigInteger.valueOf(6), evaluator.max(BigInteger.valueOf(6), BigInteger.valueOf(3)));
        assertEquals(BigInteger.valueOf(3), evaluator.max(BigInteger.TWO, BigInteger.valueOf(3)));
        assertEquals(BigInteger.ONE, evaluator.max(BigInteger.ONE, BigInteger.valueOf(-2)));
        assertEquals(BigInteger.TWO, evaluator.max(BigInteger.valueOf(-4), BigInteger.TWO));
        assertEquals(BigInteger.valueOf(3), evaluator.max(BigInteger.ZERO, BigInteger.valueOf(3)));

        assertEquals(BigInteger.valueOf(Long.MAX_VALUE),
                evaluator.max(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.ZERO));
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE),
                evaluator.max(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.valueOf(Long.MIN_VALUE)));
        assertEquals(BigInteger.valueOf(-10),
                evaluator.max(BigInteger.valueOf(Long.MIN_VALUE), BigInteger.valueOf(-10)));
        assertEquals(BigInteger.TEN,
                evaluator.max(BigInteger.valueOf(Long.MIN_VALUE), BigInteger.TEN));
    }

    @Test
    void min() {
        assertEquals(BigInteger.ONE, evaluator.min(BigInteger.TWO, BigInteger.ONE));
        assertEquals(BigInteger.valueOf(3), evaluator.min(BigInteger.valueOf(6), BigInteger.valueOf(3)));
        assertEquals(BigInteger.TWO, evaluator.min(BigInteger.TWO, BigInteger.valueOf(3)));
        assertEquals(BigInteger.valueOf(-2), evaluator.min(BigInteger.ONE, BigInteger.valueOf(-2)));
        assertEquals(BigInteger.valueOf(-4), evaluator.min(BigInteger.valueOf(-4), BigInteger.TWO));
        assertEquals(BigInteger.ZERO, evaluator.min(BigInteger.ZERO, BigInteger.valueOf(3)));

        assertEquals(BigInteger.ZERO, evaluator.min(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.ZERO));
        assertEquals(BigInteger.valueOf(Long.MIN_VALUE), evaluator.min(BigInteger.valueOf(Long.MIN_VALUE), BigInteger.ZERO));
        assertEquals(BigInteger.valueOf(Long.MIN_VALUE), evaluator.min(BigInteger.valueOf(Long.MAX_VALUE), BigInteger.valueOf(Long.MIN_VALUE)));
        assertEquals(BigInteger.valueOf(Long.MIN_VALUE), evaluator.min(BigInteger.valueOf(Long.MIN_VALUE), BigInteger.valueOf(-10)));
        assertEquals(BigInteger.valueOf(Long.MIN_VALUE), evaluator.min(BigInteger.valueOf(Long.MIN_VALUE), BigInteger.TEN));
    }

    @Test
    void parse() {
        assertEquals(BigInteger.TEN, evaluator.parse("10"));
        assertEquals(BigInteger.valueOf(-10), evaluator.parse("-10"));
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE), evaluator.parse(Long.MAX_VALUE + ""));
        assertEquals(BigInteger.valueOf(Long.MIN_VALUE), evaluator.parse(Long.MIN_VALUE + ""));
    }

    @Test
    void getValue() {
        assertEquals(BigInteger.TEN, evaluator.getValue(10));
        assertEquals(BigInteger.valueOf(-5), evaluator.getValue(-5));
        assertEquals(BigInteger.valueOf(Long.MIN_VALUE), evaluator.getValue(Long.MIN_VALUE));
        assertEquals(BigInteger.valueOf(Long.MAX_VALUE), evaluator.getValue(Long.MAX_VALUE));
    }
}