package expression;

import evaluator.DoubleEvaluator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MinTest {
    final private GenericExpression<Double> expr = new Min<>(new Const<>("10"), new Variable<>("x"));

    @Test
    void toStringTest() {
        assertEquals("(10 min x)", expr.toString());
    }

    @Test
    void evaluateTest() {
        assertEquals(10.0, expr.evaluate(22.0, 0.0, 0.0, new DoubleEvaluator()), 1e-9);
        assertEquals(2.0, expr.evaluate(2.0, 1.0, 0.0, new DoubleEvaluator()), 1e-9);
        assertEquals(2.0, expr.evaluate(2.0, 0.0, 1.0, new DoubleEvaluator()), 1e-9);
        assertEquals(0.0, expr.evaluate(0.0, 1.0, 2.0, new DoubleEvaluator()), 1e-9);
    }

    @Test
    void equalsTest() {
        assertEquals(expr, new Min<>(new Const<>("10"), new Variable<>("x")));
        assertNotEquals(new Min<>(new Const<>("12"), new Variable<>("x")), expr);
        assertNotEquals(new Min<>(new Const<>("10"), new Variable<>("y")), expr);
        assertNotEquals(0, expr);
        assertNotEquals(expr, 0);
    }

    @Test
    void hashCodeTest() {
        assertEquals(expr.hashCode(), new Min<>(new Const<>("10"), new Variable<>("x")).hashCode());
        assertNotEquals(new Min<>(new Const<>("12"), new Variable<>("x")).hashCode(), expr.hashCode());
        assertNotEquals(new Min<>(new Const<>("10"), new Variable<>("y")).hashCode(), expr.hashCode());
        assertNotEquals(0, expr.hashCode());
        assertNotEquals(expr.hashCode(), 0);
    }
}