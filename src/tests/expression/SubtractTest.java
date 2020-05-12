package expression;

import evaluator.DoubleEvaluator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SubtractTest {
    final private GenericExpression<Double> expr = new Subtract<>(new Const<>("10"), new Variable<>("x"));

    @Test
    void toStringTest() {
        assertEquals("(10 - x)", expr.toString());
    }

    @Test
    void evaluateTest() {
        assertEquals(8.0, expr.evaluate(2.0, 0.0, 0.0, new DoubleEvaluator()), 1e-9);
        assertEquals(8.0, expr.evaluate(2.0, 1.0, 0.0, new DoubleEvaluator()), 1e-9);
        assertEquals(8.0, expr.evaluate(2.0, 0.0, 1.0, new DoubleEvaluator()), 1e-9);
        assertEquals(10.0, expr.evaluate(0.0, 1.0, 2.0, new DoubleEvaluator()), 1e-9);
    }

    @Test
    void equalsTest() {
        assertEquals(expr, new Subtract<>(new Const<>("10"), new Variable<>("x")));
        assertNotEquals(new Subtract<>(new Const<>("12"), new Variable<>("x")), expr);
        assertNotEquals(new Subtract<>(new Const<>("10"), new Variable<>("y")), expr);
        assertNotEquals(0, expr);
        assertNotEquals(expr, 0);
    }

    @Test
    void hashCodeTest() {
        assertEquals(expr.hashCode(), new Subtract<>(new Const<>("10"), new Variable<>("x")).hashCode());
        assertNotEquals(new Subtract<>(new Const<>("12"), new Variable<>("x")).hashCode(), expr.hashCode());
        assertNotEquals(new Subtract<>(new Const<>("10"), new Variable<>("y")).hashCode(), expr.hashCode());
        assertNotEquals(0, expr.hashCode());
        assertNotEquals(expr.hashCode(), 0);
    }
}