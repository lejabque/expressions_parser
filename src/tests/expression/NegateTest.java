package expression;

import evaluator.DoubleEvaluator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NegateTest {
    final private GenericExpression<Double> expr = new Negate<>(new Variable<>("x"));

    @Test
    void toStringTest() {
        assertEquals("(-x)", expr.toString());
    }

    @Test
    void evaluateTest() {
        assertEquals(-22.0, expr.evaluate(22.0, 0.0, 0.0, new DoubleEvaluator()), 1e-9);
        assertEquals(-2.0, expr.evaluate(2.0, 1.0, 0.0, new DoubleEvaluator()), 1e-9);
        assertEquals(-2.0, expr.evaluate(2.0, 0.0, 1.0, new DoubleEvaluator()), 1e-9);
        assertEquals(0.0, expr.evaluate(0.0, 1.0, 2.0, new DoubleEvaluator()), 1e-9);
    }

    @Test
    void equalsTest() {
        assertEquals(expr, new Negate<>(new Variable<>("x")));
        assertNotEquals(new Negate<>(new Variable<>("y")), expr);
        assertNotEquals(new Negate<>(new Const<>("10")), expr);
        assertNotEquals(0, expr);
        assertNotEquals(expr, 0);
    }

    @Test
    void hashCodeTest() {
        assertEquals(expr.hashCode(), new Negate<>(new Variable<>("x")).hashCode());
        assertNotEquals(new Negate<>(new Variable<>("y")).hashCode(), expr.hashCode());
        assertNotEquals(new Negate<>(new Const<>("10")).hashCode(), expr.hashCode());
        assertNotEquals(0, expr.hashCode());
        assertNotEquals(expr.hashCode(), 0);
    }
}