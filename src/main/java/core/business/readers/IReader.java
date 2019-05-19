package core.business.readers;

import core.domain.Token;

import java.util.Optional;

/**
 * Интерфейс для поиска токена в строке
 */
public interface IReader {
    Optional<Token> findToken(String string);
}
