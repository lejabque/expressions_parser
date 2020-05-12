package expression;

import evaluator.DoubleEvaluator;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class VariableTest {
    final private GenericExpression<Double> varX = new Variable<>("x");
    final private GenericExpression<Double> varY = new Variable<>("y");
    final private GenericExpression<Double> varZ = new Variable<>("z");

    @Test
    void toStringTest() {
        assertEquals("x", varX.toString());
        assertEquals("y", varY.toString());
        assertEquals("z", varZ.toString());
    }

    @Test
    void evaluateTest() {
        assertEquals(1.0, varX.evaluate(1.0, 2.0, 3.0, new DoubleEvaluator()), 1e-9);
        assertEquals(2.0, varY.evaluate(1.0, 2.0, 3.0, new DoubleEvaluator()), 1e-9);
        assertEquals(3.0, varZ.evaluate(1.0, 2.0, 3.0, new DoubleEvaluator()), 1e-9);
    }

    @Test
    void equalsTest() {
        assertEquals(new Variable<>("x"), varX);
        assertEquals(new Variable<>("y"), varY);
        assertEquals(new Variable<>("z"), varZ);
        assertNotEquals("x", varX);
        assertNotEquals(varX, "x");
        assertNotEquals(new Const<>("15"), varX);
        assertNotEquals(new Variable<>("x"), varY);
    }

    @Test
    void hashCodeTest() {
        assertEquals(new Variable<>("x").hashCode(), varX.hashCode());
        assertEquals(new Variable<>("y").hashCode(), varY.hashCode());
        assertEquals(new Variable<>("z").hashCode(), varZ.hashCode());
        assertNotEquals(varX.hashCode(), varY.hashCode());
        assertNotEquals(new Const<>("15").hashCode(), varX.hashCode());
        assertNotEquals(new Variable<>("x").hashCode(), varY.hashCode());
    }

}