package core.utils.math.operators;


import java.util.function.DoubleBinaryOperator;

import static core.utils.math.operators.Operators.*;

public class OperatorFactory {
    public static DoubleBinaryOperator from(String string) {
        switch (string) {
            case "+": {
                return add();
            }
            case "−": {
                return deduct();
            }
            case "×": {
                return multiply();
            }
            case "÷": {
                return divide();
            }
        }
        return null;
    }
}
