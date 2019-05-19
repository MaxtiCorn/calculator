import core.business.Calculator;
import core.business.Tokenizer;
import core.business.readers.DoubleReader;
import core.business.readers.OperatorReader;
import core.business.readers.StringReader;
import core.domain.Token;
import core.domain.TokenType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static core.domain.TokenType.STRING;
import static core.utils.math.operators.Operators.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Tests {
    private void assertToken(Token token,
                             Object expectedContent, TokenType expectedType, int expectedLength) {
        assertEquals(expectedContent, token.getContent());
        assertEquals(expectedType, token.getType());
        assertEquals(expectedLength, token.getLength());
    }

    @Test
    public void testDoubleReader() {
        DoubleReader doubleReader = new DoubleReader();
        String test1 = "1.5 ab";
        Optional<Token> optionalToken = doubleReader.findToken(test1);
        assertTrue(optionalToken.isPresent());
        assertToken(optionalToken.get(), 1.5, TokenType.NUMBER, 3);

        String test2 = "1 ab";
        Optional<Token> optionalToken2 = doubleReader.findToken(test2);
        assertTrue(optionalToken2.isPresent());
        assertToken(optionalToken2.get(), 1.0, TokenType.NUMBER, 1);
    }

    @Test
    public void testOperatorReader() {
        OperatorReader operatorReader = new OperatorReader();
        String test1 = "+ ab";
        Optional<Token> optionalToken = operatorReader.findToken(test1);
        assertTrue(optionalToken.isPresent());
        assertToken(optionalToken.get(), add(), TokenType.OPERATOR, 1);

        String test2 = "− ab";
        Optional<Token> optionalToken2 = operatorReader.findToken(test2);
        assertTrue(optionalToken2.isPresent());
        assertToken(optionalToken2.get(), deduct(), TokenType.OPERATOR, 1);

        String test3 = "× ab";
        Optional<Token> optionalToken3 = operatorReader.findToken(test3);
        assertTrue(optionalToken3.isPresent());
        assertToken(optionalToken3.get(), multiply(), TokenType.OPERATOR, 1);

        String test4 = "÷ ab";
        Optional<Token> optionalToken4 = operatorReader.findToken(test4);
        assertTrue(optionalToken4.isPresent());
        assertToken(optionalToken4.get(), divide(), TokenType.OPERATOR, 1);
    }

    @Test
    public void testStringReader() {
        StringReader stringReader = new StringReader("abc");
        String test1 = "abcd+ ";
        Optional<Token> optionalToken = stringReader.findToken(test1);
        assertTrue(optionalToken.isPresent());
        assertToken(optionalToken.get(), "abc", TokenType.STRING, 3);
    }

    @Test
    public void testTokenizer() {
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.setReaders(new ArrayList<>());
        try {
            tokenizer.tokenize("15 7");
        } catch (Exception e) {
            assertEquals("Не удалось разбить строку на токены", e.getMessage());
        }
        tokenizer.setReaders(Arrays.asList(
                new DoubleReader(),
                new OperatorReader(),
                new StringReader(" "),
                new StringReader("abc")
        ));
        try {
            List<Token> tokens = tokenizer.tokenize("15 7");
            List<Token> expectedTokens = Arrays.asList(
                    new Token<>(15.0, 2, TokenType.NUMBER),
                    new Token<>(" ", 1, TokenType.STRING),
                    new Token<>(7.0, 1, TokenType.NUMBER)
            );
            for (int i = 0; i < tokens.size(); ++i) {
                Token expectedToken = expectedTokens.get(i);
                assertToken(tokens.get(i),
                        expectedToken.getContent(), expectedToken.getType(), expectedToken.getLength());
            }
        } catch (Exception ignored) {
        }
    }

    private void testCalculator(List<Token> tokens, Double expectedResult) {
        Calculator calculator = new Calculator();
        calculator.setInput(tokens);
        assertEquals(expectedResult, calculator.calculate());
    }

    @Test
    public void testCalculator() {
        Tokenizer tokenizer = new Tokenizer();
        tokenizer.setReaders(Arrays.asList(
                new DoubleReader(),
                new OperatorReader(),
                new StringReader(" "),
                new StringReader("abc")
        ));
        try {
            List<Token> tokens = tokenizer.tokenize("15 7 1 1 + − ÷ 3 × 2 1 1 + + − ")
                    .stream()
                    .filter(token -> !token.getType().equals(STRING))
                    .collect(Collectors.toList());
            testCalculator(tokens, 5.0);
        } catch (Exception ignored) {
        }
    }
}
