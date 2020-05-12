package expression;

import evaluator.DoubleEvaluator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MultiplyTest {
    final private GenericExpression<Double> expr = new Multiply<>(new Const<>("10"), new Variable<>("x"));

    @Test
    void toStringTest() {
        assertEquals("(10 * x)", expr.toString());
    }

    @Test
    void evaluateTest() {
        assertEquals(20.0, expr.evaluate(2.0, 0.0, 0.0, new DoubleEvaluator()), 1e-9);
        assertEquals(20.0, expr.evaluate(2.0, 1.0, 0.0, new DoubleEvaluator()), 1e-9);
        assertEquals(20.0, expr.evaluate(2.0, 0.0, 1.0, new DoubleEvaluator()), 1e-9);
        assertEquals(0.0, expr.evaluate(0.0, 1.0, 2.0, new DoubleEvaluator()), 1e-9);
    }

    @Test
    void equalsTest() {
        assertEquals(expr, new Multiply<>(new Const<>("10"), new Variable<>("x")));
        assertNotEquals(new Multiply<>(new Const<>("12"), new Variable<>("x")), expr);
        assertNotEquals(new Multiply<>(new Const<>("10"), new Variable<>("y")), expr);
        assertNotEquals(0, expr);
        assertNotEquals(expr, 0);
    }

    @Test
    void hashCodeTest() {
        assertEquals(expr.hashCode(), new Multiply<>(new Const<>("10"), new Variable<>("x")).hashCode());
        assertNotEquals(new Multiply<>(new Const<>("12"), new Variable<>("x")).hashCode(), expr.hashCode());
        assertNotEquals(new Multiply<>(new Const<>("10"), new Variable<>("y")).hashCode(), expr.hashCode());
        assertNotEquals(0, expr.hashCode());
        assertNotEquals(expr.hashCode(), 0);
    }
}