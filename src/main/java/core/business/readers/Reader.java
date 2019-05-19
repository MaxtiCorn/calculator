package core.business.readers;

import core.domain.Token;
import lombok.Getter;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Reader<T> implements IReader {
    private final String regexPattern;

    protected abstract Token<T> from(String string);

    @Override
    public Optional<Token> findToken(String string) {
        Matcher matcher = Pattern.compile(regexPattern).matcher(string);
        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String substring = string.substring(start, end);
            return Optional.of(from(substring));
        }
        return Optional.empty();
    }

    public Reader(String regexPattern) {
        this.regexPattern = regexPattern;
    }
}
