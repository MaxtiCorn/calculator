package core.utils.math.operators;

import java.util.function.DoubleBinaryOperator;

public class Operators {
    private static DoubleBinaryOperator add;
    private static DoubleBinaryOperator deduct;
    private static DoubleBinaryOperator multiply;
    private static DoubleBinaryOperator divide;

    public static DoubleBinaryOperator add() {
        if (add == null) {
            add = Double::sum;
        }
        return add;
    }

    public static DoubleBinaryOperator deduct() {
        if (deduct == null) {
            deduct = (arg1, arg2) -> arg1 - arg2;
        }
        return deduct;
    }

    public static DoubleBinaryOperator multiply() {
        if (multiply == null) {
            multiply = (arg1, arg2) -> arg1 * arg2;
        }
        return multiply;
    }

    public static DoubleBinaryOperator divide() {
        if (divide == null) {
            divide = (arg1, arg2) -> arg1 / arg2;
        }
        return divide;
    }
}
