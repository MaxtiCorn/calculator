package core.business.readers;

import core.domain.Token;
import core.domain.TokenType;
import core.utils.math.operators.OperatorFactory;

import java.util.function.DoubleBinaryOperator;

public class OperatorReader extends Reader<DoubleBinaryOperator> {
    public OperatorReader() {
        super("^[+−÷×]");
    }

    @Override
    protected Token<DoubleBinaryOperator> from(String string) {
        return new Token<>(
                OperatorFactory.from(string),
                string.length(),
                TokenType.OPERATOR
        );
    }
}
