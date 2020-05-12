package parser;

import exceptions.InvalidVariableException;
import exceptions.MissingClosingParenthesis;
import exceptions.MissingOpeningParenthesis;
import exceptions.ParsingException;
import expression.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {
    private final ExpressionParser<Integer> parser = new ExpressionParser<>();

    void parseCorrectExpression(String s, GenericExpression<Integer> result) {
        try {
            assertEquals(result, parser.parse(s));
        } catch (ParsingException e) {
            fail(e);
        }
    }

    @Test
    void parseConst() {
        parseCorrectExpression("10", new Const<>("10"));
        parseCorrectExpression("-10", new Const<>("-10"));
    }

    @Test
    void parseVariable() {
        parseCorrectExpression("x", new Variable<>("x"));
        parseCorrectExpression("y", new Variable<>("y"));
        parseCorrectExpression("z", new Variable<>("z"));
    }

    @Test
    void parseOperations() {
        parseCorrectExpression("x+2", new Add<>(new Variable<>("x"), new Const<>("2")));
        parseCorrectExpression("2-y", new Subtract<>(new Const<>("2"), new Variable<>("y")));
        parseCorrectExpression("  3*  z  ", new Multiply<>(new Const<>("3"), new Variable<>("z")));
        parseCorrectExpression("x/  -  2", new Divide<>(new Variable<>("x"), new Negate<>(new Const<>("2"))));
        parseCorrectExpression("count 5", new Count<>(new Const<>("5")));
        parseCorrectExpression("2 min 3", new Min<>(new Const<>("2"), new Const<>("3")));
        parseCorrectExpression("3 max 5", new Max<>(new Const<>("3"), new Const<>("5")));
    }


    @Test
    void parseComplicatedExpression() {
        parseCorrectExpression("x*y+(z-1   )/10",
                new Add<>(
                        new Multiply<>(new Variable<>("x"), new Variable<>("y")),
                        new Divide<>(
                                new Subtract<>(new Variable<>("z"), new Const<>("1")),
                                new Const<>("10"))
                )
        );
        parseCorrectExpression("x--y--z",
                new Subtract<>(
                        new Subtract<>(new Variable<>("x"), new Negate<>(new Variable<>("y"))),
                        new Negate<>(new Variable<>("z")))
        );
    }


    void exceptionTest(String s, Class<? extends ParsingException> expectedException) {
        assertThrows(expectedException, () -> parser.parse(s));
    }

    @Test
    void missingParenthsis() {
        exceptionTest("x * y)", MissingOpeningParenthesis.class);
        exceptionTest("(x * y", MissingClosingParenthesis.class);
        exceptionTest("((x + y) + 2", MissingClosingParenthesis.class);
        exceptionTest("(x + y) + 2)", MissingOpeningParenthesis.class);
    }

    @Test
    void invalidArgument() {
        exceptionTest("* y * z", InvalidVariableException.class);
        exceptionTest("x * y * ", InvalidVariableException.class);
        exceptionTest("x *  * z", InvalidVariableException.class);
        exceptionTest("1 + (* y * z) + 2", InvalidVariableException.class);
        exceptionTest("1 + (x *  / 9) + 3", InvalidVariableException.class);
        exceptionTest("1 + (x * y - ) + 3", InvalidVariableException.class);


        exceptionTest("/ * y * z", InvalidVariableException.class);
        exceptionTest("x * y * /", InvalidVariableException.class);
        exceptionTest("x * / * z", InvalidVariableException.class);

    }

    @Test
    void invalidExpression() {
        exceptionTest("10 20", ParsingException.class);
        exceptionTest("+", ParsingException.class);
        exceptionTest("-", ParsingException.class);
        exceptionTest("/", ParsingException.class);
        exceptionTest("(())", ParsingException.class);
    }
}