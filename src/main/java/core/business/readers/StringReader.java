package core.business.readers;

import core.domain.Token;
import core.domain.TokenType;

public class StringReader extends Reader<String> {
    public StringReader(String string) {
        super("^" + string);
    }

    @Override
    protected Token<String> from(String string) {
        return new Token<>(
                string,
                string.length(),
                TokenType.STRING);
    }
}
