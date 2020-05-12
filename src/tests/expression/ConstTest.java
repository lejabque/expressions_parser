package expression;

import evaluator.DoubleEvaluator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ConstTest {
    final private GenericExpression<Double> expr = new Const<>("10");

    @Test
    void toStringTest() {
        assertEquals("10", expr.toString());
    }

    @Test
    void evaluateTest() {
        assertEquals(10.0, expr.evaluate(2.0, 0.0, 0.0, new DoubleEvaluator()), 1e-9);
        assertEquals(10.0, expr.evaluate(2.0, 1.0, 0.0, new DoubleEvaluator()), 1e-9);
        assertEquals(10.0, expr.evaluate(2.0, 0.0, 1.0, new DoubleEvaluator()), 1e-9);
        assertEquals(10.0, expr.evaluate(0.0, 1.0, 2.0, new DoubleEvaluator()), 1e-9);
    }

    @Test
    void equalsTest() {
        assertEquals(new Const<>("10"), expr);
        assertNotEquals(10, expr);
        assertNotEquals(expr, 10);
        assertNotEquals(new Const<>("15"), expr);
        assertNotEquals(new Variable<>("x"), expr);
    }

    @Test
    void hashCodeTest() {
        assertEquals(new Const<>("10").hashCode(), expr.hashCode());
        assertNotEquals(10, expr.hashCode());
        assertNotEquals(expr.hashCode(), 10);
        assertNotEquals(new Const<>("15").hashCode(), expr.hashCode());
        assertNotEquals(new Variable<>("x").hashCode(), expr.hashCode());
    }
}