package evaluator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FloatEvaluatorTest {
    private final Evaluator<Float> evaluator = new FloatEvaluator();

    @Test
    void add() {
        assertEquals(3, evaluator.add((float) 1, (float) 2), 1e-9);
        assertEquals(3, evaluator.add((float) 2, (float) 1), 1e-9);
        assertEquals(-1, evaluator.add((float) 1, (float) -2), 1e-9);
        assertEquals(3, evaluator.add((float) 0, (float) 3), 1e-9);
        assertEquals(1,
                evaluator.add((float) 1 / (float) 3.0, (float) 2 / (float) 3.0), 1e-9);
        assertEquals((float) 41.0 / (float) 3,
                evaluator.add((float) 10.0, (float) 11.0 / (float) 3), 1e-9);

        assertEquals(Float.MAX_VALUE, evaluator.add(Float.MAX_VALUE, (float) 0), 1e-9);
        assertEquals(Float.MAX_VALUE - 1, evaluator.add(Float.MAX_VALUE, (float) -1), 1e-9);
        assertEquals(Float.POSITIVE_INFINITY, evaluator.add(Float.MAX_VALUE, Float.MAX_VALUE), 1e-9);
    }

    @Test
    void subtract() {
        assertEquals(-1, evaluator.subtract((float) 1, (float) 2), 1e-9);
        assertEquals(1, evaluator.subtract((float) 2, (float) 1), 1e-9);
        assertEquals(3, evaluator.subtract((float) 1, (float) -2), 1e-9);
        assertEquals(-3, evaluator.subtract((float) 0, (float) 3), 1e-9);
        assertEquals(3, evaluator.subtract((float) 3, (float) 0), 1e-9);
        assertEquals((float) 1 / (float) 3.0,
                evaluator.subtract((float) 2 / (float) 3.0, (float) 1 / (float) 3.0), 1e-9);

        assertEquals(Float.MAX_VALUE, evaluator.subtract(Float.MAX_VALUE, (float) 0), 1e-9);
        assertEquals(Float.MAX_VALUE - 1, evaluator.subtract(Float.MAX_VALUE, (float) 1), 1e-9);
        assertEquals(0, evaluator.subtract(Float.MAX_VALUE, Float.MAX_VALUE), 1e-9);
        assertEquals(0, evaluator.subtract(Float.MIN_VALUE, Float.MIN_VALUE), 1e-9);
    }

    @Test
    void multiply() {
        assertEquals(2, evaluator.multiply((float) 1, (float) 2), 1e-9);
        assertEquals(6, evaluator.multiply((float) 2, (float) 3), 1e-9);
        assertEquals(-2, evaluator.multiply((float) 1, (float) -2), 1e-9);
        assertEquals(2, evaluator.multiply((float) -1, (float) -2), 1e-9);
        assertEquals(0, evaluator.multiply((float) 0, (float) 3), 1e-9);
        assertEquals(0, evaluator.multiply((float) 3, (float) 0), 1e-9);
        assertEquals((float) 8.0385, evaluator.multiply((float) 3.45, (float) 2.33), 1e-9);

        assertEquals(0, evaluator.multiply(Float.MAX_VALUE, (float) 0), 1e-9);
        assertEquals(-Float.MAX_VALUE, evaluator.multiply(Float.MAX_VALUE, (float) -1), 1e-9);
        assertEquals(Float.MAX_VALUE, evaluator.multiply(Float.MAX_VALUE, (float) 1), 1e-9);
        assertEquals(-Float.MIN_VALUE, evaluator.multiply(Float.MIN_VALUE, (float) -1), 1e-9);
    }

    @Test
    void divide() {
        assertEquals(2, evaluator.divide((float) 2, (float) 1), 1e-9);
        assertEquals(2, evaluator.divide((float) 6, (float) 3), 1e-9);
        assertEquals((float) 2.0 / (float) 3, evaluator.divide((float) 2, (float) 3), 1e-9);
        assertEquals((float) -0.5, evaluator.divide((float) 1, (float) -2), 1e-9);
        assertEquals((float) -2, evaluator.divide((float) -4, (float) 2), 1e-9);
        assertEquals(0, evaluator.divide((float) 0, (float) 3), 1e-9);
        assertEquals((float) 10.0 / (float) 3, evaluator.divide((float) 10, (float) 3), 1e-9);

        assertEquals(0, evaluator.divide((float) 0, Float.MAX_VALUE), 1e-9);
        assertEquals(0, evaluator.divide((float) 0, Float.MIN_VALUE), 1e-9);
        assertEquals(1, evaluator.divide(Float.MIN_VALUE, Float.MIN_VALUE), 1e-9);
        assertEquals(1, evaluator.divide(Float.MAX_VALUE, Float.MAX_VALUE), 1e-9);
        assertEquals(Float.MAX_VALUE, evaluator.divide(Float.MAX_VALUE, (float) 1), 1e-9);
        assertEquals(-Float.MAX_VALUE, evaluator.divide(Float.MAX_VALUE, (float) -1), 1e-9);
        assertEquals(Float.MIN_VALUE, evaluator.divide(Float.MIN_VALUE, (float) -1), 1e-9);
    }

    @Test
    void divisionByZero() {
        assertEquals(Float.POSITIVE_INFINITY, evaluator.divide((float) 10, (float) 0));
        assertEquals(Float.NEGATIVE_INFINITY, evaluator.divide((float) -10, (float) 0));
        assertEquals(Float.NaN, evaluator.divide((float) 0, (float) 0));
        assertEquals(Float.POSITIVE_INFINITY, evaluator.divide(Float.MAX_VALUE, (float) 0));
        assertEquals(Float.POSITIVE_INFINITY, evaluator.divide(Float.MIN_VALUE, (float) 0));
        assertEquals(Float.NEGATIVE_INFINITY, evaluator.divide(-Float.MAX_VALUE, (float) 0));
    }

    @Test
    void negate() {
        assertEquals(-2, evaluator.negate((float) 2), 1e-9);
        assertEquals(3, evaluator.negate((float) -3), 1e-9);
        assertEquals(0, evaluator.negate((float) 0), 1e-9);
        assertEquals(-Float.MAX_VALUE, evaluator.negate(Float.MAX_VALUE), 1e-9);
        assertEquals(Float.MIN_VALUE, evaluator.negate(Float.MIN_VALUE), 1e-9);
    }

    @Test
    void count() {
        assertEquals(3, evaluator.count((float) 5), 1e-9);
        assertEquals(0, evaluator.count((float) 0), 1e-9);
        assertEquals(30, evaluator.count(Float.MAX_VALUE), 1e-9);
        assertEquals(1, evaluator.count(Float.MIN_VALUE), 1e-9);
    }

    @Test
    void max() {
        assertEquals(2, evaluator.max((float) 2, (float) 1), 1e-9);
        assertEquals(6, evaluator.max((float) 6, (float) 3), 1e-9);
        assertEquals(3, evaluator.max((float) 2, (float) 3), 1e-9);
        assertEquals(1, evaluator.max((float) 1, (float) -2), 1e-9);
        assertEquals(2, evaluator.max((float) -4, (float) 2), 1e-9);
        assertEquals(3, evaluator.max((float) 0, (float) 3), 1e-9);

        assertEquals(Float.MAX_VALUE, evaluator.max(Float.MAX_VALUE, (float) 0), 1e-9);
        assertEquals(Float.MAX_VALUE, evaluator.max(Float.MAX_VALUE, Float.MIN_VALUE), 1e-9);
        assertEquals(-10, evaluator.max(-Float.MAX_VALUE, (float) -10), 1e-9);
        assertEquals(10, evaluator.max(-Float.MAX_VALUE, (float) 10), 1e-9);
    }

    @Test
    void min() {
        assertEquals(1, evaluator.min((float) 2, (float) 1), 1e-9);
        assertEquals(3, evaluator.min((float) 6, (float) 3), 1e-9);
        assertEquals(2, evaluator.min((float) 2, (float) 3), 1e-9);
        assertEquals(-2, evaluator.min((float) 1, (float) -2), 1e-9);
        assertEquals(-4, evaluator.min((float) -4, (float) 2), 1e-9);
        assertEquals(0, evaluator.min((float) 0, (float) 3), 1e-9);

        assertEquals(0, evaluator.min(Float.MAX_VALUE, (float) 0), 1e-9);
        assertEquals(-Float.MAX_VALUE, evaluator.min(-Float.MAX_VALUE, (float) 0), 1e-9);
        assertEquals(-Float.MAX_VALUE, evaluator.min(Float.MAX_VALUE, -Float.MAX_VALUE), 1e-9);
        assertEquals(-Float.MAX_VALUE, evaluator.min(-Float.MAX_VALUE, (float) -10), 1e-9);
        assertEquals(-Float.MAX_VALUE, evaluator.min(-Float.MAX_VALUE, (float) 10), 1e-9);
    }

    @Test
    void parse() {
        assertEquals(10, evaluator.parse("10"), 1e-9);
        assertEquals(-10, evaluator.parse("-10"), 1e-9);
        assertEquals(Float.MAX_VALUE, evaluator.parse(Float.MAX_VALUE + ""), 1e-9);
        assertEquals(Float.MIN_VALUE, evaluator.parse(Float.MIN_VALUE + ""), 1e-9);
    }

    @Test
    void getValue() {
        assertEquals(10, evaluator.getValue(10), 1e-9);
        assertEquals(-5, evaluator.getValue(-5), 1e-9);
        assertEquals(Float.MIN_VALUE, evaluator.getValue(Float.MIN_VALUE), 1e-9);
        assertEquals(Float.MAX_VALUE, evaluator.getValue(Float.MAX_VALUE), 1e-9);
    }
}