package expression;

import evaluator.IntegerEvaluator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CountTest {
    final private GenericExpression<Integer> expr = new Count<>(new Variable<>("x"));

    @Test
    void toStringTest() {
        assertEquals("(count x)", expr.toString());
    }

    @Test
    void evaluateTest() {
        assertEquals(3, expr.evaluate(22, 0, 0, new IntegerEvaluator()));
        assertEquals(1, expr.evaluate(2, 1, 0, new IntegerEvaluator()));
        assertEquals(1, expr.evaluate(2, 0, 1, new IntegerEvaluator()));
        assertEquals(0, expr.evaluate(0, 1, 2, new IntegerEvaluator()));
    }

    @Test
    void equalsTest() {
        assertEquals(expr, new Count<>(new Variable<>("x")));
        assertNotEquals(new Count<>(new Variable<>("y")), expr);
        assertNotEquals(new Count<>(new Const<>("10")), expr);
        assertNotEquals(0, expr);
        assertNotEquals(expr, 0);
    }

    @Test
    void hashCodeTest() {
        assertEquals(expr.hashCode(), new Count<>(new Variable<>("x")).hashCode());
        assertNotEquals(new Count<>(new Variable<>("y")).hashCode(), expr.hashCode());
        assertNotEquals(new Count<>(new Const<>("10")).hashCode(), expr.hashCode());
        assertNotEquals(0, expr.hashCode());
        assertNotEquals(expr.hashCode(), 0);
    }
}