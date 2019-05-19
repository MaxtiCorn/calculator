package core.business.readers;

import core.domain.Token;
import core.domain.TokenType;

public class DoubleReader extends Reader<Double> {
    public DoubleReader() {
        super("^\\d+\\.*\\d*");
    }

    @Override
    protected Token<Double> from(String string) {
        return new Token<>(
                Double.valueOf(string),
                string.length(),
                TokenType.NUMBER);
    }
}
