package evaluator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoubleEvaluatorTest {
    private final Evaluator<Double> evaluator = new DoubleEvaluator();

    @Test
    void add() {
        assertEquals(3, evaluator.add((double) 1, (double) 2), 1e-9);
        assertEquals(3, evaluator.add((double) 2, (double) 1), 1e-9);
        assertEquals(-1, evaluator.add((double) 1, (double) -2), 1e-9);
        assertEquals(3, evaluator.add((double) 0, (double) 3), 1e-9);
        assertEquals(1, evaluator.add(1 / 3.0, 2 / 3.0), 1e-9);
        assertEquals(41.0 / 3, evaluator.add(10.0, 11.0 / 3), 1e-9);

        assertEquals(Double.MAX_VALUE, evaluator.add(Double.MAX_VALUE, (double) 0), 1e-9);
        assertEquals(Double.MAX_VALUE - 1, evaluator.add(Double.MAX_VALUE, (double) -1), 1e-9);
        assertEquals(Double.POSITIVE_INFINITY, evaluator.add(Double.MAX_VALUE, Double.MAX_VALUE), 1e-9);
    }

    @Test
    void subtract() {
        assertEquals(-1, evaluator.subtract((double) 1, (double) 2), 1e-9);
        assertEquals(1, evaluator.subtract((double) 2, (double) 1), 1e-9);
        assertEquals(3, evaluator.subtract((double) 1, (double) -2), 1e-9);
        assertEquals(-3, evaluator.subtract((double) 0, (double) 3), 1e-9);
        assertEquals(3, evaluator.subtract((double) 3, (double) 0), 1e-9);
        assertEquals(1 / 3.0, evaluator.subtract(2 / 3.0, 1 / 3.0), 1e-9);

        assertEquals(Double.MAX_VALUE, evaluator.subtract(Double.MAX_VALUE, (double) 0), 1e-9);
        assertEquals(Double.MAX_VALUE - 1, evaluator.subtract(Double.MAX_VALUE, (double) 1), 1e-9);
        assertEquals(0, evaluator.subtract(Double.MAX_VALUE, Double.MAX_VALUE), 1e-9);
        assertEquals(0, evaluator.subtract(Double.MIN_VALUE, Double.MIN_VALUE), 1e-9);
    }

    @Test
    void multiply() {
        assertEquals(2, evaluator.multiply((double) 1, (double) 2), 1e-9);
        assertEquals(6, evaluator.multiply((double) 2, (double) 3), 1e-9);
        assertEquals(-2, evaluator.multiply((double) 1, (double) -2), 1e-9);
        assertEquals(2, evaluator.multiply((double) -1, (double) -2), 1e-9);
        assertEquals(0, evaluator.multiply((double) 0, (double) 3), 1e-9);
        assertEquals(0, evaluator.multiply((double) 3, (double) 0), 1e-9);
        assertEquals(8.0385, evaluator.multiply(3.45, 2.33), 1e-9);

        assertEquals(0, evaluator.multiply(Double.MAX_VALUE, (double) 0), 1e-9);
        assertEquals(-Double.MAX_VALUE, evaluator.multiply(Double.MAX_VALUE, (double) -1), 1e-9);
        assertEquals(Double.MAX_VALUE, evaluator.multiply(Double.MAX_VALUE, (double) 1), 1e-9);
        assertEquals(-Double.MIN_VALUE, evaluator.multiply(Double.MIN_VALUE, (double) -1), 1e-9);
    }

    @Test
    void divide() {
        assertEquals(2, evaluator.divide((double) 2, (double) 1), 1e-9);
        assertEquals(2, evaluator.divide((double) 6, (double) 3), 1e-9);
        assertEquals(2.0 / 3, evaluator.divide((double) 2, (double) 3), 1e-9);
        assertEquals(-0.5, evaluator.divide((double) 1, (double) -2), 1e-9);
        assertEquals(-2, evaluator.divide((double) -4, (double) 2), 1e-9);
        assertEquals(0, evaluator.divide((double) 0, (double) 3), 1e-9);
        assertEquals(10.0 / 3, evaluator.divide((double) 10, (double) 3), 1e-9);

        assertEquals(0, evaluator.divide((double) 0, Double.MAX_VALUE), 1e-9);
        assertEquals(0, evaluator.divide((double) 0, Double.MIN_VALUE), 1e-9);
        assertEquals(1, evaluator.divide(Double.MIN_VALUE, Double.MIN_VALUE), 1e-9);
        assertEquals(1, evaluator.divide(Double.MAX_VALUE, Double.MAX_VALUE), 1e-9);
        assertEquals(Double.MAX_VALUE, evaluator.divide(Double.MAX_VALUE, (double) 1), 1e-9);
        assertEquals(-Double.MAX_VALUE, evaluator.divide(Double.MAX_VALUE, (double) -1), 1e-9);
        assertEquals(Double.MIN_VALUE, evaluator.divide(Double.MIN_VALUE, (double) -1), 1e-9);
    }

    @Test
    void divisionByZero() {
        assertEquals(Double.POSITIVE_INFINITY, evaluator.divide((double) 10, (double) 0));
        assertEquals(Double.NEGATIVE_INFINITY, evaluator.divide((double) -10, (double) 0));
        assertEquals(Double.NaN, evaluator.divide((double) 0, (double) 0));
        assertEquals(Double.POSITIVE_INFINITY, evaluator.divide(Double.MAX_VALUE, (double) 0));
        assertEquals(Double.POSITIVE_INFINITY, evaluator.divide(Double.MIN_VALUE, (double) 0));
        assertEquals(Double.NEGATIVE_INFINITY, evaluator.divide(-Double.MAX_VALUE, (double) 0));
    }

    @Test
    void negate() {
        assertEquals(-2, evaluator.negate((double) 2), 1e-9);
        assertEquals(3, evaluator.negate((double) -3), 1e-9);
        assertEquals(0, evaluator.negate((double) 0), 1e-9);
        assertEquals(-Double.MAX_VALUE, evaluator.negate(Double.MAX_VALUE), 1e-9);
        assertEquals(Double.MIN_VALUE, evaluator.negate(Double.MIN_VALUE), 1e-9);
    }

    @Test
    void count() {
        assertEquals(3, evaluator.count((double) 5), 1e-9);
        assertEquals(0, evaluator.count((double) 0), 1e-9);
        assertEquals(62, evaluator.count(Double.MAX_VALUE), 1e-9);
        assertEquals(1, evaluator.count(Double.MIN_VALUE), 1e-9);
    }

    @Test
    void max() {
        assertEquals(2, evaluator.max((double) 2, (double) 1), 1e-9);
        assertEquals(6, evaluator.max((double) 6, (double) 3), 1e-9);
        assertEquals(3, evaluator.max((double) 2, (double) 3), 1e-9);
        assertEquals(1, evaluator.max((double) 1, (double) -2), 1e-9);
        assertEquals(2, evaluator.max((double) -4, (double) 2), 1e-9);
        assertEquals(3, evaluator.max((double) 0, (double) 3), 1e-9);

        assertEquals(Double.MAX_VALUE, evaluator.max(Double.MAX_VALUE, (double) 0), 1e-9);
        assertEquals(Double.MAX_VALUE, evaluator.max(Double.MAX_VALUE, Double.MIN_VALUE), 1e-9);
        assertEquals(-10, evaluator.max(-Double.MAX_VALUE, (double) -10), 1e-9);
        assertEquals(10, evaluator.max(-Double.MAX_VALUE, (double) 10), 1e-9);
    }

    @Test
    void min() {
        assertEquals(1, evaluator.min((double) 2, (double) 1), 1e-9);
        assertEquals(3, evaluator.min((double) 6, (double) 3), 1e-9);
        assertEquals(2, evaluator.min((double) 2, (double) 3), 1e-9);
        assertEquals(-2, evaluator.min((double) 1, (double) -2), 1e-9);
        assertEquals(-4, evaluator.min((double) -4, (double) 2), 1e-9);
        assertEquals(0, evaluator.min((double) 0, (double) 3), 1e-9);

        assertEquals(0, evaluator.min(Double.MAX_VALUE, (double) 0), 1e-9);
        assertEquals(-Double.MAX_VALUE, evaluator.min(-Double.MAX_VALUE, (double) 0), 1e-9);
        assertEquals(-Double.MAX_VALUE, evaluator.min(Double.MAX_VALUE, -Double.MAX_VALUE), 1e-9);
        assertEquals(-Double.MAX_VALUE, evaluator.min(-Double.MAX_VALUE, (double) -10), 1e-9);
        assertEquals(-Double.MAX_VALUE, evaluator.min(-Double.MAX_VALUE, (double) 10), 1e-9);
    }

    @Test
    void parse() {
        assertEquals(10, evaluator.parse("10"), 1e-9);
        assertEquals(-10, evaluator.parse("-10"), 1e-9);
        assertEquals(Double.MAX_VALUE, evaluator.parse(Double.MAX_VALUE + ""), 1e-9);
        assertEquals(Double.MIN_VALUE, evaluator.parse(Double.MIN_VALUE + ""), 1e-9);
    }

    @Test
    void getValue() {
        assertEquals(10, evaluator.getValue(10), 1e-9);
        assertEquals(-5, evaluator.getValue(-5), 1e-9);
        assertEquals(Double.MIN_VALUE, evaluator.getValue(Double.MIN_VALUE), 1e-9);
        assertEquals(Double.MAX_VALUE, evaluator.getValue(Double.MAX_VALUE), 1e-9);
    }
}