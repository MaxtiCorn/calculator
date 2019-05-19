package core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Token<T> {
    private final T content;
    private final int length;
    private final TokenType type;
}
