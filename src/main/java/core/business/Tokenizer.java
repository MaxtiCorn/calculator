package core.business;

import core.business.readers.IReader;
import core.domain.Token;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Класс для разбиения строки на токены
 */
public class Tokenizer {
    @Setter
    private List<IReader> readers;

    private List<Token> tokenize(final List<Token> collector, final String string) throws Exception {
        Token bestToken = readers.stream()
                .map(reader -> reader.findToken(string)) // Каждым ридером пытаюсь прочесть токен
                .filter(Optional::isPresent) // Фильтрую только найденные
                .map(Optional::get) // Распаковываю в токены
                .max(Comparator.comparingInt(Token::getLength)) // Нахожу длиннейший
                .orElseThrow(() -> new Exception("Не удалось разбить строку на токены"));
        collector.add(bestToken);
        final String nextString = string.substring(bestToken.getLength());
        if (!nextString.equals("")) {
            return tokenize(collector, nextString);
        }
        return collector;
    }

    public List<Token> tokenize(final String string) throws Exception {
        return tokenize(new ArrayList<>(), string);
    }
}
