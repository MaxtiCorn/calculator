package core.business;


import core.domain.Token;
import lombok.Setter;

import java.util.List;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;

import static core.domain.TokenType.NUMBER;

public class Calculator {
    @Setter
    private List<Token> input;

    public Double calculate() {
        Stack<Double> temp = new Stack<>();
        for (Token token : input) {
            if (token.getType().equals(NUMBER)) {
                temp.push((Double) token.getContent());
            } else {
                DoubleBinaryOperator operator = (DoubleBinaryOperator) token.getContent();
                Double b = temp.pop();
                Double a = temp.pop();
                temp.push(operator.applyAsDouble(a, b));
            }
        }
        return temp.peek();
    }

    public Calculator() {
    }
}
