import evaluator.Evaluator;
import evaluator.IntegerEvaluator;
import exceptions.ParsingException;
import org.junit.jupiter.api.Test;
import parser.ExpressionParser;

import static org.junit.jupiter.api.Assertions.*;

class EvaluateExpressionsTest {
    private final ExpressionParser<Integer> parser = new ExpressionParser<>();
    private final Evaluator<Integer> evaluator = new IntegerEvaluator();
    private final Integer xValue = 1;
    private final Integer yValue = 2;
    private final Integer zValue = 3;

    void parseAndCalculateCorrectExpression(String s, Integer result) {
        try {
            assertEquals(result, parser.parse(s).evaluate(xValue, yValue, zValue, evaluator));
        } catch (ParsingException e) {
            fail(e);
        }
    }

    @Test
    void parseConst() {
        parseAndCalculateCorrectExpression("10", 10);
        parseAndCalculateCorrectExpression("-10", -10);
    }

    @Test
    void parseVariable() {
        parseAndCalculateCorrectExpression("x", 1);
        parseAndCalculateCorrectExpression("y", 2);
        parseAndCalculateCorrectExpression("z", 3);
    }

    @Test
    void parseOperations() {
        parseAndCalculateCorrectExpression("x+2", 3);
        parseAndCalculateCorrectExpression("2-y", 0);
        parseAndCalculateCorrectExpression("  3*  z  ", 9);
        parseAndCalculateCorrectExpression("x/  -  2", 0);
        parseAndCalculateCorrectExpression("count 5", 2);
        parseAndCalculateCorrectExpression("2 min 3", 2);
        parseAndCalculateCorrectExpression("3 max 5", 5);
    }


    @Test
    void parseComplicatedExpression() {
        parseAndCalculateCorrectExpression("x*y+(z-1   )/10", 2);
        parseAndCalculateCorrectExpression("x--y--z", 6);
    }
}