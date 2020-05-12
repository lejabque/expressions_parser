package evaluator;

import exceptions.OverflowException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CheckedIntegerEvaluatorTest {
    private final Evaluator<Integer> evaluator = new CheckedIntegerEvaluator();
    private final BaseIntegerEvaluatorTest baseTester = new BaseIntegerEvaluatorTest(evaluator);


    void overflowExceptionTest(Executable operation) {
        assertThrows(OverflowException.class, operation);
    }

    @Test
    void add() {
        baseTester.add();
        overflowExceptionTest(() -> evaluator.add(Integer.MAX_VALUE, 1));
        overflowExceptionTest(() -> evaluator.add(Integer.MIN_VALUE, Integer.MIN_VALUE));
        overflowExceptionTest(() -> evaluator.add(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test
    void subtract() {
        baseTester.subtract();
        overflowExceptionTest(() -> evaluator.subtract(Integer.MAX_VALUE, -1));
        overflowExceptionTest(() -> evaluator.subtract(-10, Integer.MAX_VALUE));
        overflowExceptionTest(() -> evaluator.subtract(-2, Integer.MAX_VALUE));
        overflowExceptionTest(() -> evaluator.subtract(0, Integer.MIN_VALUE));
        overflowExceptionTest(() -> evaluator.subtract(10, Integer.MIN_VALUE));
        overflowExceptionTest(() -> evaluator.subtract(Integer.MIN_VALUE, 10));
        overflowExceptionTest(() -> evaluator.subtract(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    @Test
    void multiply() {
        baseTester.multiply();
        overflowExceptionTest(() -> evaluator.multiply(Integer.MIN_VALUE, -1));
        overflowExceptionTest(() -> evaluator.multiply(Integer.MIN_VALUE, 2));
    }

    @Test
    void divide() {
        baseTester.divide();
        overflowExceptionTest(() -> evaluator.divide(Integer.MIN_VALUE, -1));
    }

    @Test
    void divisionByZero() {
        baseTester.divisionByZero();
    }

    @Test
    void negate() {
        baseTester.negate();
        overflowExceptionTest(() -> evaluator.negate(Integer.MIN_VALUE));
    }

    @Test
    void countWithOverflow() {
        baseTester.count();
    }

    @Test
    void max() {
        baseTester.max();
    }

    @Test
    void min() {
        baseTester.min();
    }

    @Test
    void parse() {
        baseTester.parse();
    }

    @Test
    void getValue() {
        baseTester.getValue();
    }
}